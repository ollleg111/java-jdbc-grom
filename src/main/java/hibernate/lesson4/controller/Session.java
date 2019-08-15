package hibernate.lesson4.controller;

import hibernate.lesson4.model.User;

public class Session {

    private static User authorized;

    public static User getAuthorized() {
        return authorized;
    }

    public static void setAuthorized(User authorized) {
        Session.authorized = authorized;
    }
}
