package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.TimePoint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class TimePointServiceImplementation implements TimePointService {

    private static final BigDecimal YEAR = BigDecimal.valueOf(12);

    @Override
    public TimePoint calculate(BigDecimal rateNumber, InputData inputData) {

        LocalDate date = calculateLocalDate(inputData, rateNumber);
        BigDecimal year = calculateYear(rateNumber);
        BigDecimal month = calculateMonth(rateNumber);

        return new TimePoint(date, year, month);
    }

    private LocalDate calculateLocalDate(InputData inputData, final BigDecimal rateNumber){
        return inputData.getPaymentStartDate()
                .plus(rateNumber.subtract(BigDecimal.ONE).intValue(), ChronoUnit.MONTHS);
    }

    private BigDecimal calculateYear(final BigDecimal rateNumber){
        return rateNumber.divide(YEAR, RoundingMode.UP).max(BigDecimal.ONE);
    }

    private BigDecimal calculateMonth(final BigDecimal rateNumber){
        return BigDecimal.ZERO.equals(rateNumber.remainder(YEAR)) ? YEAR : rateNumber.remainder(YEAR);
    }


}
