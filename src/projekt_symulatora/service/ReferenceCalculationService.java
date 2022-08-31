package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.MortgageReference;
import projekt_symulatora.model.Rate;
import projekt_symulatora.model.RateAmounts;

public interface ReferenceCalculationService {
    MortgageReference calculate(InputData inputData);

    MortgageReference calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate);
}
