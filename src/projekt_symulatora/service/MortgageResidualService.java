package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.MortgageResidual;
import projekt_symulatora.model.Rate;
import projekt_symulatora.model.RateAmounts;

public interface MortgageResidualService {
    MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData);
    MortgageResidual calculate(RateAmounts rateAmounts, Rate previousRate);
}
