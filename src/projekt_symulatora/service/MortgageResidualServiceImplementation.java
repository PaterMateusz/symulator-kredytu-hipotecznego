package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.MortgageResidual;
import projekt_symulatora.model.Rate;
import projekt_symulatora.model.RateAmounts;

import java.math.BigDecimal;

public class MortgageResidualServiceImplementation implements MortgageResidualService {
    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData)
    {
        BigDecimal residualAmount = calculateResidualAmount(rateAmounts, inputData.getAmout());
        BigDecimal residualDuration = inputData.getMonthsDuration().subtract(BigDecimal.ONE);
        return new MortgageResidual(residualAmount, residualDuration);
    }

    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, Rate previousRate)
    {
        MortgageResidual residual = previousRate.getMortgageResidual();
        BigDecimal previousDuration = residual.getDuration();

        BigDecimal residualAmount = calculateResidualAmount(rateAmounts, residual.getAmount());
        BigDecimal residualDuration = previousDuration.subtract(BigDecimal.ONE);
        return new MortgageResidual(residualAmount, residualDuration);
    }

    private BigDecimal calculateResidualAmount(RateAmounts rateAmounts, BigDecimal amount) {
        return amount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }


}
