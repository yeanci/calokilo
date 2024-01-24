package com.mashle.calokilo.weightservice.domain.ports;

import com.mashle.calokilo.weightservice.domain.WeightEntry;
import com.mashle.calokilo.weightservice.domain.shared.NotFoundException;

public interface AddOrUpdateWeightEntryPort {

    WeightEntry addOrUpdateWeightEntry(Long userId, WeightEntry weightEntry) throws NotFoundException;
}
