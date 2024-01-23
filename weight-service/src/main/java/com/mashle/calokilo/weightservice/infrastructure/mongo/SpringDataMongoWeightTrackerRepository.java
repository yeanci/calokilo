package com.mashle.calokilo.weightservice.infrastructure.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoWeightTrackerRepository extends MongoRepository<WeightTrackerEntity, Long> {
}
