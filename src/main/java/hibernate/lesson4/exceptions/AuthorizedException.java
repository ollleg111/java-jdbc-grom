package hibernate.lesson4.exceptions;

public class AuthorizedException extends Exception {

    public AuthorizedException(String message) {
        super(message);
    }
}
