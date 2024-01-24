package com.mashle.calokilo.weightservice.domain.ports;

import com.mashle.calokilo.weightservice.domain.WeightEntry;

public interface AddWeightEntryPort {

    WeightEntry addWeightEntry(Long userId, WeightEntry weightEntry);
}
