package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.Overpayment;
import projekt_symulatora.model.Rate;
import projekt_symulatora.model.RateAmounts;

public class RateAmountsServiceImplementation implements RateAmountsService {

    private  final ConstantAmountsCalculationService constantAmountsCalculationService;

    private final DecreasingAmountsCalculationService decreasingAmountsCalculationService;

    public RateAmountsServiceImplementation(
            ConstantAmountsCalculationService constantAmountsCalculationService,
            DecreasingAmountsCalculationService decreasingAmountsCalculationService) {
        this.constantAmountsCalculationService = constantAmountsCalculationService;
        this.decreasingAmountsCalculationService = decreasingAmountsCalculationService;
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        switch (inputData.getRateType()){
            case CONSTANT:
                return constantAmountsCalculationService.calculate(inputData, overpayment);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(inputData, overpayment);
            default:
                throw new RuntimeException("Case not handled");
        }

    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        switch (inputData.getRateType()){
            case CONSTANT:
                return constantAmountsCalculationService.calculate(inputData,overpayment, previousRate);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(inputData,overpayment, previousRate);
            default:
                throw new RuntimeException("Case not handled");
        }

    }









}
