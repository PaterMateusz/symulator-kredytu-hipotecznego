package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.Overpayment;
import projekt_symulatora.model.Rate;
import projekt_symulatora.model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecreasingAmountsCalculationServiceImplementation implements DecreasingAmountsCalculationService{

    private static final BigDecimal YEAR = BigDecimal.valueOf(12);

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        BigDecimal interestPercent = inputData.getInterestPercent();

        BigDecimal residualAmount = inputData.getAmout();
        BigDecimal referenceAmount = inputData.getAmout();
        BigDecimal referenceDuration = inputData.getMonthsDuration();


        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateCapitalAmount(referenceAmount, referenceDuration, residualAmount);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);
        return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
    }
    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        BigDecimal interestPercent = inputData.getInterestPercent();

        BigDecimal residualAmount = previousRate.getMortgageResidual().getAmount();
        BigDecimal referenceAmount = previousRate.getMortgageReference().getReferenceAmount();
        BigDecimal referenceDuration = previousRate.getMortgageReference().getReferenceDuration();


        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateCapitalAmount(referenceAmount, referenceDuration, residualAmount);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);
        return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
    }

    private BigDecimal calculateInterestAmount(BigDecimal residualAmout, BigDecimal interestPercent) {
        return residualAmout.multiply(interestPercent).divide(YEAR, 10, RoundingMode.HALF_UP);
    }


    private BigDecimal calculateCapitalAmount(
            BigDecimal amount,
            BigDecimal monthsDuration,
            BigDecimal residualAmount)
    {
        BigDecimal capitalAmount = amount.divide(monthsDuration, 10, RoundingMode.HALF_UP);

        if (capitalAmount.compareTo(residualAmount) >= 0){
            return residualAmount;
        }

        return capitalAmount;
    }

}
