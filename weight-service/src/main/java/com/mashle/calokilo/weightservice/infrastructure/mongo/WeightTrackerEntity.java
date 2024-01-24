package com.mashle.calokilo.weightservice.infrastructure.mongo;

import com.mashle.calokilo.weightservice.domain.WeightEntry;
import com.mashle.calokilo.weightservice.domain.WeightTracker;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@Document("weight_tracker")
public class WeightTrackerEntity {

    @Id
    private Long userId;

    private double initialWeight;
    private double targetWeight;
    private List<WeightEntry> weightHistory;


    public WeightTrackerEntity(WeightTracker weightTracker) {
        this.userId = weightTracker.userId();
        this.initialWeight = weightTracker.initialWeight();
        this.targetWeight = weightTracker.targetWeight();
        this.weightHistory = weightTracker.weightHistory();
    }


    public WeightTracker toWeightTracker() {
        return WeightTracker.builder()
                .userId(this.userId)
                .initialWeight(this.initialWeight)
                .targetWeight(this.targetWeight)
                .weightHistory(this.weightHistory)
                .build();
    }
}
