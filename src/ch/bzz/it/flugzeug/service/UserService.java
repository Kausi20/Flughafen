package ch.bzz.it.flugzeug.service;

import ch.bzz.it.flugzeug.data.DataHandler;
import ch.bzz.it.flugzeug.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * short description
 * <p>
 * Flughafen
 *
 * @author Kaushican Uthayakumaran
 * @version 1.0
 * @since 23.04.20
 */
@Path("user")
public class UserService {

    /**
     * login a user with username/password
     *
     * @param username the username
     * @param password the password
     * @return Response-object with the userrole
     */
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        int httpStatus;

        User user = DataHandler.readUser(username, password);
        if (user.getUserRole().equals("guest")) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }

        NewCookie cookie = new NewCookie(
                "userRole",
                user.getUserRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );
        Response response = Response
                .status(httpStatus)
                .cookie(cookie)
                .entity("")
                .build();
        return response;
    }

    /**
     * logout current user
     *
     * @return Response object with guest-Cookie
     */
    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout() {
        NewCookie cookie = new NewCookie(
                "userRole",
                "guest",
                "/",
                "",
                "Login-Cookie",
                1,
                false
        );
        Response response = Response
                .status(200)
                .cookie(cookie)
                .entity("")
                .build();
        return response;
    }
}
