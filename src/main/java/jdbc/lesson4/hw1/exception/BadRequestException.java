package jdbc.lesson4.hw1.exception;

public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }
}
