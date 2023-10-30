package com.coupond.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.coupond.errorDecoder.CustomErrorDecoder;

@FeignClient(url="http://localhost:5050/sendMail", name="mail-service", configuration = CustomErrorDecoder.class)
public interface EmailClient {

	@GetMapping("/{to}/{subject}/{body}")
	public String sendMail(@PathVariable String to,@PathVariable String subject,@PathVariable String body);	
}
