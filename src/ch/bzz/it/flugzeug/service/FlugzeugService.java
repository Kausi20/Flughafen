package ch.bzz.it.flugzeug.service;

import ch.bzz.it.flugzeug.data.DataHandler;
import ch.bzz.it.flugzeug.model.Flugzeug;
import ch.bzz.it.flugzeug.model.Hersteller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *
 * @author Kaushican Uthayakumaran
 */
@Path("flugzeug")
public class FlugzeugService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listFlugzeuge(@CookieParam("userRole")String userRole) {
        Map<String, Flugzeug> flugzeugMap = null;
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            flugzeugMap = DataHandler.getFlugzeugMap();
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );
        Response response = Response
                .status(httpStatus)
                .entity(flugzeugMap)
                .cookie(cookie)
                .build();
        return response;
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readFlugzeug(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("flugzeugUUID") String flugzeugUUID,
            @CookieParam("userRole") String userRole){
        Flugzeug flugzeug = null;
        int httpStatus;

        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            flugzeug = DataHandler.getFlugzeugMap().get(flugzeugUUID);

            if (flugzeug != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        }

        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        Response response = Response
                .status(httpStatus)
                .entity(flugzeug)
                .cookie(cookie)
                .build();
        return response;
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createFlugzeuge(
            @Valid @BeanParam Flugzeug flugzeug,
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("herstellerUUID") String herstellerUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        if (userRole != null && userRole.equals("admin")) {

            httpStatus = 200;

            flugzeug.setFlugzeugUUID(UUID.randomUUID().toString());
            if (DataHandler.getHerstellerMap().containsKey(herstellerUUID)) {
                Hersteller hersteller = DataHandler.getHerstellerMap().get(herstellerUUID);
                flugzeug.setHersteller(hersteller);

                Map<String, Flugzeug> flugzeugMap = DataHandler.getFlugzeugMap();

                flugzeugMap.put(flugzeug.getFlugzeugUUID(), flugzeug);
                DataHandler.writeFlugzeuge(flugzeugMap);
            } else {
                httpStatus = 400;
            }

        } else {
            httpStatus = 403;
        }

        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        Response response = Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)

    public Response deleteFlugzeuge(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("flugzeugUUID") String flugzeugUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;

        if (userRole != null && userRole.equals("admin")) {

            Map<String, Flugzeug> flugzeugMap = DataHandler.getFlugzeugMap();
            Flugzeug flugzeug = flugzeugMap.get(flugzeugUUID);
            if (flugzeug != null) {
                httpStatus = 200;
                flugzeugMap.remove(flugzeugUUID);
                DataHandler.writeFlugzeuge(flugzeugMap);
            } else {
                httpStatus = 404;
            }
        } else {
            httpStatus = 403;
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        Response response = Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return response;


    }


    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)

    public Response updateFlugzeug(
            @Valid @BeanParam Flugzeug flugzeug,
            @FormParam("herstellerUUID") String herstellerUUID,
            @CookieParam("userRole") String userRole


    ) {
        int httpStatus;
        if (userRole != null && userRole.equals("admin")) {

            Map<String, Flugzeug> flugzeugMap = DataHandler.getFlugzeugMap();
            if (flugzeugMap.containsKey(flugzeug.getFlugzeugUUID())) {
                if (DataHandler.getHerstellerMap().containsKey(herstellerUUID)) {
                    Hersteller hersteller = DataHandler.getHerstellerMap().get(herstellerUUID);

                    flugzeug.setHersteller(hersteller);
                    flugzeugMap.put(flugzeug.getFlugzeugUUID(), flugzeug);
                    DataHandler.writeFlugzeuge(flugzeugMap);
                    httpStatus = 200;
                } else {
                    httpStatus = 400;
                }
            } else {
                httpStatus = 404;
            }
        } else {
            httpStatus = 403;
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );



        Response response = Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

}
