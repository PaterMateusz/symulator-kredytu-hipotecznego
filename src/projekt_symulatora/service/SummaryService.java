package projekt_symulatora.service;

import projekt_symulatora.model.Rate;
import projekt_symulatora.model.Summary;

import java.util.List;

public interface SummaryService {

    Summary calculate(List<Rate> rates);

}
