package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.Rate;
import projekt_symulatora.model.Summary;

import java.util.List;

public class MortgageCalculationServiceImpl implements MortgageCalculationService {

    private final PrintingService printingService;

    private final RateCalculationService rateCalculationService;

    private final SummaryService summaryService;

    public MortgageCalculationServiceImpl(PrintingService printingService,
                                          RateCalculationService rateCalculationService,
                                          SummaryService summaryService)
    {
        this.printingService = printingService;
        this.rateCalculationService = rateCalculationService;
        this.summaryService = summaryService;
    }

    @Override
    public void calculate(InputData inputData) {

        printingService.printInputData(inputData);
        List<Rate> rates = rateCalculationService.rateCalculate(inputData);

        printingService.printRates(rates);

        Summary summary = summaryService.calculate(rates);
        printingService.printSummary(summary);



    }
}
