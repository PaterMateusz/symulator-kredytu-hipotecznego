package projekt_symulatora.service;

public class MortgageException extends RuntimeException{
    public MortgageException() {
        super("Case not handled");
    }
}
