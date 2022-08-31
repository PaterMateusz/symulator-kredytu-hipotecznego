package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.TimePoint;

import java.math.BigDecimal;

public interface TimePointService {
    TimePoint calculate(BigDecimal rateNumber, InputData inputData);
}
