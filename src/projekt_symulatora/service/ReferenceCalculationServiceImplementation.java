package projekt_symulatora.service;

import projekt_symulatora.model.*;

import java.math.BigDecimal;

public class ReferenceCalculationServiceImplementation implements  ReferenceCalculationService{

    @Override
    public MortgageReference calculate(InputData inputData) {
        if (BigDecimal.ZERO.equals(inputData.getAmout())){
            return new MortgageReference(BigDecimal.ZERO, BigDecimal.ZERO);
        }

        return new MortgageReference(inputData.getAmout(), inputData.getMonthsDuration());
    }

    @Override
    public MortgageReference calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate) {

        if (BigDecimal.ZERO.equals(previousRate.getMortgageResidual().getAmount())){
            return new MortgageReference(BigDecimal.ZERO, BigDecimal.ZERO);
        }

        switch (inputData.getOverpaymentReduceWay()){
            case Overpayment.REDUCE_PERIOD:
                return new MortgageReference(inputData.getAmout(), inputData.getMonthsDuration());
            case Overpayment.REDUCE_RATE:
                return reduceRateMortgageReference(rateAmounts, previousRate);
            default:
                throw new MortgageException();
        }

//        return new MortgageReference(inputData.getAmout(), inputData.getMonthsDuration());
    }

    private MortgageReference reduceRateMortgageReference(RateAmounts rateAmounts, Rate previousRate) {
        if (rateAmounts.getOverpayment().getAmount().compareTo(BigDecimal.ZERO) > 0) {

            BigDecimal referenceAmount = calculateResidualAmount(rateAmounts, previousRate.getMortgageResidual().getAmount());
            BigDecimal referenceDuration = previousRate.getMortgageResidual().getDuration().subtract(BigDecimal.ONE);
            return new MortgageReference(referenceAmount, referenceDuration);
        }
        return new MortgageReference(
                previousRate.getMortgageReference().getReferenceAmount(),
                previousRate.getMortgageReference().getReferenceDuration()
        );
    }

    private BigDecimal calculateResidualAmount(RateAmounts rateAmounts, BigDecimal amount) {
        return amount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
