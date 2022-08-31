package projekt_symulatora;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.Overpayment;
import projekt_symulatora.model.RateType;
import projekt_symulatora.service.*;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        InputData inputData = new InputData()
                .withAmout(new BigDecimal(255000))
                .withMonthsDuration(BigDecimal.valueOf(300))
                .withOverpaymentReduceWay(Overpayment.REDUCE_PERIOD)
                .withRateTyp(RateType.DECREASING);


        PrintingService printingService = new PrintingServiceImplementation();
        RateCalculationService rateCalculationService = new RateCalculationServiceImplementation(
                new TimePointServiceImplementation(),
                new RateAmountsServiceImplementation(new ConstantAmountsCalculationServiceImplementation(),
                        new DecreasingAmountsCalculationServiceImplementation()),
                new OverpaymentCalculationServiceImplementation(),
                new MortgageResidualServiceImplementation(),
                new ReferenceCalculationServiceImplementation()
        );

        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                printingService,
                rateCalculationService,
                SummaryServiceFactory.create()
        );

        mortgageCalculationService.calculate(inputData);

    }
}
