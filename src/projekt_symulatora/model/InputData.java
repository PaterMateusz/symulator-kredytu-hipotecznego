package projekt_symulatora.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class InputData {

    private static final BigDecimal PERCENT = BigDecimal.valueOf(100);

    private LocalDate paymentStartDate = LocalDate.of(2022, 4, 15);
    private BigDecimal wiborPercent = new BigDecimal(1.5);
    private BigDecimal bankMarginPercent = BigDecimal.valueOf(1.95);

    private BigDecimal amout = new BigDecimal(300000);
    private BigDecimal monthsDuration = new BigDecimal(360);
    private RateType rateType = RateType.DECREASING;

    private Map<Integer, BigDecimal> overpaymentSchema = Map.of(
            5, BigDecimal.valueOf(50000),
            7, BigDecimal.valueOf(50000),
            10, BigDecimal.valueOf(50000)
    );

    private String overpaymentReduceWay = Overpayment.REDUCE_PERIOD;
    private BigDecimal overpaymentProvision = BigDecimal.valueOf(3);
    private BigDecimal overpaymentMonths = BigDecimal.valueOf(36);

    public InputData withOverpaymentSchema(Map<Integer, BigDecimal> overpaymentSchema){
        this.overpaymentSchema =overpaymentSchema;
        return this;
    }

    public InputData withOverpaymentReduceWay(String overpaymentReduceWay){
        this.overpaymentReduceWay = overpaymentReduceWay;
        return this;
    };
    public InputData withOverpaymentProvision(BigDecimal overpaymentProvision){
        this.overpaymentProvision = overpaymentProvision;
        return this;
    };
    public InputData withOverpaymentMonths(BigDecimal overpaymentMonths){
        this.overpaymentMonths = overpaymentMonths;
        return this;
    };

    public InputData withPaymentStartDate(LocalDate paymentStartDate){
        this.paymentStartDate=paymentStartDate;
        return this;
    };
    public InputData withWibor(BigDecimal wiborPercent){
        this.wiborPercent=wiborPercent;
        return this;
    };
    public InputData withBankMargin(BigDecimal bankMarginPercent){
        this.bankMarginPercent=bankMarginPercent;
        return this;
    };
    public InputData withAmout(BigDecimal amout){
        this.amout=amout;
        return this;
    };
    public InputData withMonthsDuration(BigDecimal monthsDuration){
        this.monthsDuration=monthsDuration;
        return this;
    };
    public InputData withRateTyp(RateType rateType){
        this.rateType=rateType;
        return this;
    };


    public LocalDate getPaymentStartDate() {
        return paymentStartDate;
    }

    public BigDecimal getAmout() {
        return amout;
    }

    public BigDecimal getMonthsDuration() {
        return monthsDuration;
    }

    public RateType getRateType() {
        return rateType;
    }

    public BigDecimal getInterestPercent(){
        return bankMarginPercent.add(wiborPercent).divide(PERCENT, 4, RoundingMode.HALF_UP);
    };

    public BigDecimal getInterestDisplay(){
        return bankMarginPercent.add(wiborPercent).setScale(2, RoundingMode.HALF_UP);
    };

    public String getOverpaymentReduceWay() {
        return overpaymentReduceWay;
    }

    public Map<Integer, BigDecimal> getOverpaymentSchema() {
        return overpaymentSchema;
    }

    public BigDecimal getOverpaymentProvisionPercent() {
        return overpaymentProvision.divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getOverpaymentMonths() {
        return overpaymentMonths;
    }
}
