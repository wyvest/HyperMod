package fun.iridescent.hypermod.exceptions;

public class OutOfDateException extends RuntimeException {
    public OutOfDateException(String msg) {
        super(msg);
    }
}