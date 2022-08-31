package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.Rate;

import java.util.List;

public interface RateCalculationService {
    List<Rate> rateCalculate(InputData inputData);
}
