package az.restopia.security.service;

public interface UserService {
    /**
     * Retrieves the current user's email.
     *
     * @return the email of the current user
     */
    String getCurrentUserEmail();

    /**
     * Retrieves the current user's ID.
     *
     * @return the ID of the current user
     */
    Long getCurrentUserId();

    /**
     * Retrieves the current user's role.
     *
     * @return the role of the current user
     */
    String getCurrentUserRole();
}
