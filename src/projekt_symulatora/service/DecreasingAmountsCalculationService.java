package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.Overpayment;
import projekt_symulatora.model.Rate;
import projekt_symulatora.model.RateAmounts;

public interface DecreasingAmountsCalculationService {
    RateAmounts calculate(InputData inputData, Overpayment overpayment);

    RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate);
}
