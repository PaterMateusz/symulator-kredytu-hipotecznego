package projekt_symulatora.service;

import projekt_symulatora.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class RateCalculationServiceImplementation implements RateCalculationService {

    private final TimePointService timePointService;
    private final RateAmountsService rateAmountsService;
    private final OverpaymentCalculationService overpaymentCalculationService;
    private final MortgageResidualService mortgageResidualService;
    private final ReferenceCalculationService referenceCalculationService;

    public RateCalculationServiceImplementation(TimePointService timePointService,
                                                RateAmountsService rateAmountsService,
                                                OverpaymentCalculationService overpaymentCalculationService,
                                                MortgageResidualService mortgageResidual,
                                                ReferenceCalculationService referenceCalculationService
                                                ) {
        this.timePointService = timePointService;
        this.rateAmountsService = rateAmountsService;
        this.overpaymentCalculationService = overpaymentCalculationService;
        this.mortgageResidualService = mortgageResidual;
        this.referenceCalculationService = referenceCalculationService;
    }

    @Override
    public List<Rate> rateCalculate(InputData inputData) {
        List<Rate> rates = new LinkedList<>();
        BigDecimal rateNumber = BigDecimal.ONE;
        Rate firstRate = calculateRate(rateNumber, inputData);
        rates.add(firstRate);
        Rate previousRate = firstRate;

        for (
                BigDecimal index = rateNumber.add(BigDecimal.ONE);
                index.compareTo(inputData.getMonthsDuration()) <= 0;
                index = index.add(BigDecimal.ONE)
        ){
          Rate nextRate = calculateRate(index, inputData, previousRate);
          rates.add(nextRate);
          previousRate = nextRate;

          if (BigDecimal.ZERO.equals(nextRate.getMortgageResidual().getAmount().setScale(0, RoundingMode.HALF_UP))){
              break;
          }

        }

        return rates;

    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData)
    {
        TimePoint timePoint = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = rateAmountsService.calculate(inputData, overpayment);
        MortgageResidual mortgageResidual = mortgageResidualService.calculate(rateAmounts, inputData);
        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData);

        return new Rate(timePoint, rateNumber, rateAmounts, mortgageResidual, mortgageReference);
    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData, Rate previousRate)
    {
        TimePoint timePoint = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = rateAmountsService.calculate(inputData, overpayment, previousRate);
        MortgageResidual mortgageResidual = mortgageResidualService.calculate(rateAmounts, previousRate);
        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData, rateAmounts, previousRate);

        return new Rate(timePoint, rateNumber, rateAmounts, mortgageResidual, mortgageReference);
    }
}
