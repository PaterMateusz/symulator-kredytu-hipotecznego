package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.Overpayment;
import projekt_symulatora.model.Rate;
import projekt_symulatora.model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConstantAmountsCalculationServiceImplementation implements ConstantAmountsCalculationService{

    private static final BigDecimal YEAR = BigDecimal.valueOf(12);

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal q = calculateQ(interestPercent);

        BigDecimal residualAmount = inputData.getAmout();
        BigDecimal referenceAmount = inputData.getAmout();
        BigDecimal referenceDuration = inputData.getMonthsDuration();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal rateAmount = calculateRateAmount(q, referenceAmount, referenceDuration, interestAmount, residualAmount);
        BigDecimal capitalAmount = calculateCapitalAmount(rateAmount, interestAmount, residualAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);

    }
    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal q = calculateQ(interestPercent);

        BigDecimal residualAmount = previousRate.getMortgageResidual().getAmount();
        BigDecimal referenceAmount = previousRate.getMortgageReference().getReferenceAmount();
        BigDecimal referenceDuration = previousRate.getMortgageReference().getReferenceDuration();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal rateAmount = calculateRateAmount(q, referenceAmount, referenceDuration, interestAmount, residualAmount);
        BigDecimal capitalAmount = calculateCapitalAmount(rateAmount, interestAmount, residualAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
    }

    private BigDecimal calculateQ(BigDecimal interestPercent) {
        return interestPercent.divide(YEAR, 10, RoundingMode.HALF_UP).add(BigDecimal.ONE);
    }

    private BigDecimal calculateRateAmount(
            BigDecimal q,
            BigDecimal amout,
            BigDecimal monthsDuration,
            BigDecimal interestAmount,
            BigDecimal residualAmount
    ) {
        BigDecimal rateAmount = amout
                .multiply(q.pow(monthsDuration.intValue()))
                .multiply(q.subtract(BigDecimal.ONE))
                .divide(q.pow(monthsDuration.intValue()).subtract(BigDecimal.ONE), 10, RoundingMode.HALF_UP);
        return compareWithResidual(rateAmount, interestAmount, residualAmount);
    }

    private BigDecimal compareWithResidual(BigDecimal rateAmount, BigDecimal interestAmount, BigDecimal residualAmount) {
        if (rateAmount.subtract(interestAmount).compareTo(residualAmount) >= 0) {
            return residualAmount.add(interestAmount);
        }
        return rateAmount;
    }

    private BigDecimal calculateCapitalAmount(
            BigDecimal rateAmount,
            BigDecimal interestAmount,
            BigDecimal residualAmount)
    {
        BigDecimal capitalAmount = rateAmount.subtract(interestAmount);

        if (capitalAmount.compareTo(residualAmount) >= 0){
            return residualAmount;
        }

        return capitalAmount;
    }

    private BigDecimal calculateInterestAmount(BigDecimal residualAmout, BigDecimal interestPercent) {
        return residualAmout.multiply(interestPercent).divide(YEAR, 10, RoundingMode.HALF_UP);
    }

}
