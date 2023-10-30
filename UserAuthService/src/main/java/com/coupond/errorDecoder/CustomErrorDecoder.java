package com.coupond.errorDecoder;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;

import com.coupond.exception.ResourceAlreadyExistsException;
import com.coupond.exception.ResourceNotFoundException;
import com.coupond.exception.ValidationFailedException;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
	
	@Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            String responseBody = getResponseBody(response);
            return new ResourceNotFoundException(responseBody);
        } else if(response.status() == 406) {
        	String responseBody = getResponseBody(response);
            return new ResourceAlreadyExistsException(responseBody);
        } else if(response.status() == 400) {
        	String responseBody = parseResponseBody(response);
        	return new ValidationFailedException(responseBody);
        }
        // Handle other errors or return default decoder
        return FeignException.errorStatus(methodKey, response);
    }

    private String parseResponseBody(Response response) {
    	try {
    		String res = "";
            if (response.body() != null) {
                try (java.io.Reader reader = response.body().asReader()) {
                    // Parse the JSON response and extract the "message" field
                    com.fasterxml.jackson.databind.JsonNode root = new com.fasterxml.jackson.databind.ObjectMapper().readTree(reader);
                    if (root.has("message")) {
                        res = res + root.get("message").asText();
                        if(root.has("details")) {
                        	res = res + "! - " + root.get("details").asText();
                        }
                        return res;
                    }   
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Resource not found"; // Default message if the response body is empty
    }
	
	private String getResponseBody(Response response) {
        try {
            if (response.body() != null) {
                try (java.io.Reader reader = response.body().asReader()) {
                    // Parse the JSON response and extract the "message" field
                    com.fasterxml.jackson.databind.JsonNode root = new com.fasterxml.jackson.databind.ObjectMapper().readTree(reader);
                    if (root.has("message")) {
                        return root.get("message").asText();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Something went wrong with the Resource"; // Default message if the response body is empty or doesn't contain the "message" field
    }
}
