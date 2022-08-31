package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.Overpayment;

import java.math.BigDecimal;

public interface OverpaymentCalculationService {

    Overpayment calculate(BigDecimal rateNumber, InputData inputData);
}
