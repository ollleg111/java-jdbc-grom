package hibernate.lesson4.controller;

import hibernate.lesson4.exceptions.AuthorizedException;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

public class SessionAuthorization {

    private static User authorized;

    public static User getAuthorized() {
        return authorized;
    }

    public static void setAuthorized(User authorized) {
        SessionAuthorization.authorized = authorized;
    }

    public static void validateUser() throws Exception {
        if (SessionAuthorization.getAuthorized() == null)
            throw new AuthorizedException("an user was not authorized ... ");
    }

    public static boolean isAdmin() {
        return (SessionAuthorization.getAuthorized() != null &&
                SessionAuthorization.getAuthorized().getUserType() == UserType.ADMIN.toString());
    }
}
