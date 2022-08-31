package projekt_symulatora.service;

import projekt_symulatora.model.Rate;

import java.math.BigDecimal;

public interface Function {

    BigDecimal calculate(Rate rate);

}
