package tn.iit.exception;

import java.io.Serial;

public class CompteNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CompteNotFoundException(String msg) {
        super(msg);
    }

}
