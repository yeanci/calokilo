package com.mashle.calokilo.weightservice.application.controllers;

import com.mashle.calokilo.weightservice.application.resources.WeightTrackerResource;
import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.ports.CreateWeightTrackerPort;
import com.mashle.calokilo.weightservice.domain.ports.GetWeightTrackerPort;
import com.mashle.calokilo.weightservice.domain.shared.UserNotFoundException;
import com.mashle.calokilo.weightservice.domain.shared.WeightTrackerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weight")
public class WeightTrackerController {

    private final GetWeightTrackerPort getWeightTrackerPort;

    public WeightTrackerController(GetWeightTrackerPort getWeightTrackerPort) {
        this.getWeightTrackerPort = getWeightTrackerPort;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<WeightTrackerResource> getWeightTracker(@PathVariable Long userId) throws Exception {
        WeightTracker weightTracker = getWeightTrackerPort.getWeightTracker(userId);

        return ResponseEntity.status(HttpStatus.OK).body(new WeightTrackerResource(weightTracker));
    }
}
