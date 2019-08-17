package hibernate.lesson4.controller;

import hibernate.lesson4.exceptions.AuthorizedException;
import hibernate.lesson4.model.User;

public class SessionAuthorization {

    private static User authorized;

    public static User getAuthorized() {
        return authorized;
    }

    public static void setAuthorized(User authorized) {
        SessionAuthorization.authorized = authorized;
    }

    public static void validate() throws Exception {
        if (SessionAuthorization.getAuthorized() == null)
            throw new AuthorizedException("user was not authorized ... ");
    }
}
