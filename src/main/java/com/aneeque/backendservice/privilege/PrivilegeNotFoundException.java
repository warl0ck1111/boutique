package com.aneeque.backendservice.privilege;

/**
 * @author Okala III
 */
public class PrivilegeNotFoundException extends RuntimeException{
    /* Serial version */
    private static final long serialVersionUID = 5434016005679159613L;

    /**
     * Default constructor, no message put in exception.
     */
    public PrivilegeNotFoundException() {
        super();
    }

    /**
     * Constructor with given message put in exception.
     *
     * @param message the detail message.
     */
    public PrivilegeNotFoundException(String message) {
        super(message);
    }
}