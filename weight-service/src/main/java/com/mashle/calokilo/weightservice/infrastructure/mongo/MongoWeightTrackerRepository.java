package com.mashle.calokilo.weightservice.infrastructure.mongo;

import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.ports.WeightTrackerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class MongoWeightTrackerRepository implements WeightTrackerRepository {

    private final SpringDataMongoWeightTrackerRepository weightTrackerRepository;

    @Override
    public WeightTracker save(WeightTracker weightTracker) {
        WeightTrackerEntity saved = weightTrackerRepository.save(new WeightTrackerEntity(weightTracker));

        return saved.toWeightTracker();
    }

    @Override
    public Optional<WeightTracker> getById(Long userId) {
        return weightTrackerRepository.findById(userId).map(WeightTrackerEntity::toWeightTracker);
    }
}
