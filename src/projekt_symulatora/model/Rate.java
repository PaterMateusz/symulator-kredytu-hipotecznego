package projekt_symulatora.model;

import java.math.BigDecimal;

public class Rate {
    private TimePoint timePoint;
    private final BigDecimal rateNumber;
    private final RateAmounts rateAmounts;
    private final MortgageResidual mortgageResidual;
    private final MortgageReference mortgageReference;

    public Rate(
            TimePoint timePoint,
            BigDecimal rateNumber,
            RateAmounts rateAmounts,
            MortgageResidual mortgageResidual,
            MortgageReference mortgageReference
    )
    {
        this.timePoint = timePoint;
        this.rateNumber = rateNumber;
        this.rateAmounts = rateAmounts;
        this.mortgageResidual = mortgageResidual;
        this.mortgageReference = mortgageReference;
    }

    public TimePoint getTimePoint() {
        return timePoint;
    }

    public BigDecimal getRateNumber() {
        return rateNumber;
    }

    public RateAmounts getRateAmounts() {
        return rateAmounts;
    }

    public MortgageResidual getMortgageResidual() {
        return mortgageResidual;
    }

    public MortgageReference getMortgageReference() {
        return mortgageReference;
    }
}
