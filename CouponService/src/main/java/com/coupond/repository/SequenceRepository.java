package com.coupond.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.coupond.enitity.Sequence;

@Repository
public interface SequenceRepository extends MongoRepository<Sequence, String> {

}
