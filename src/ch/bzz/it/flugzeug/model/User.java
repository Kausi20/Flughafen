package ch.bzz.it.flugzeug.model;

/**
 * short description
 * <p>
 * Flughafen
 *
 * @author Kaushican Uthayakumaran
 * @version 1.0
 * @since 22.04.20
 */
public class User {
    private String username;
    private String userRole;

    /**
     * default constructor
     */
    public User() {
        setUserRole("guest");
    }

    /**
     * Gets the username
     *
     * @return value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     *
     * @param username the value to set
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the userRole
     *
     * @return value of userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Sets the userRole
     *
     * @param userRole the value to set
     */

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
