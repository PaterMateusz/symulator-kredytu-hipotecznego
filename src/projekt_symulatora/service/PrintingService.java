package projekt_symulatora.service;

import projekt_symulatora.model.InputData;
import projekt_symulatora.model.Rate;
import projekt_symulatora.model.Summary;

import java.util.List;

public interface PrintingService {

    String INTEREST_SUM = "SUMA ODSETEK: ";
    String OVERPAYMENT_PROVISION = "PROWIZJA ZA NADPŁATY: ";
    String LOSTS_SUM = "CAŁKOWITA SUMA STRAT: ";
    String OVERPAYMENT_REDUCE_RATE = "NADPŁATA, ZMNIEJSZENIE RATY";
    String OVERPAYMENT_REDUCE_PERIOD = "NADPŁATA, SKROCENIE OKRESU";
    String OVERPAYMENT_FREQUENCY = "SCHEMAT DOKONYWANIA NADPŁATY: ";

    String RATE_NUMBER = "NR: ";
    String YEAR = "ROK: ";
    String MONTH = "MIESIĄC: ";
    String DATE = "DATA: ";
    String MONTHS = " MIESIĘCY, ";
    String RATE = "RATA:";
    String INTEREST = "ODSETKI: ";
    String CAPITAL = "KAPITAŁ: ";
    String OVERPAYMENT = "NADPŁATA: ";
    String LEFT_AMOUNT = "POZOSTAŁA KWOTA: ";
    String LEFT_MONTHS = "POZOSTAŁO MIESIĘCY: ";
    String MORTGAGE_AMOUNT = "KWOTA KREDYTU: ";
    String MORTGAGE_PERIOD = "OKRES KREDYTOWANIA: ";

    String CURRENCY = " ZŁ ";
    String NEW_LINE = "\n";
    String PERCENT = "% ";

    void printInputData(final InputData inputData);

    void printRates(List<Rate> rates);

    void printSummary(Summary summary);

}

