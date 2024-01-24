package com.mashle.calokilo.weightservice.application.controllers;

import com.mashle.calokilo.weightservice.application.requests.WeightEntryRequest;
import com.mashle.calokilo.weightservice.application.resources.WeightEntryResource;
import com.mashle.calokilo.weightservice.application.resources.WeightTrackerResource;
import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.ports.AddOrUpdateWeightEntryPort;
import com.mashle.calokilo.weightservice.domain.ports.GetWeightTrackerPort;
import com.mashle.calokilo.weightservice.domain.shared.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weight")
public class WeightTrackerController {

    private final GetWeightTrackerPort getWeightTrackerPort;
    private final AddOrUpdateWeightEntryPort addOrUpdateWeightEntryPort;

    public WeightTrackerController(GetWeightTrackerPort getWeightTrackerPort, AddOrUpdateWeightEntryPort addOrUpdateWeightEntryPort) {
        this.getWeightTrackerPort = getWeightTrackerPort;
        this.addOrUpdateWeightEntryPort = addOrUpdateWeightEntryPort;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<WeightTrackerResource> getWeightTracker(@PathVariable Long userId) throws NotFoundException {
        WeightTracker weightTracker = getWeightTrackerPort.getWeightTracker(userId);

        return ResponseEntity.status(HttpStatus.OK).body(new WeightTrackerResource(weightTracker));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<WeightEntryResource> addOrUpdateWeightEntry(@PathVariable Long userId,
                                                                      @RequestBody WeightEntryRequest weightEntryRequest) throws NotFoundException {
        WeightEntryResource saved = new WeightEntryResource(
                addOrUpdateWeightEntryPort.addOrUpdateWeightEntry(userId, weightEntryRequest.toWeightEntry())
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
