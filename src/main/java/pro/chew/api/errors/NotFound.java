package pro.chew.api.errors;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}