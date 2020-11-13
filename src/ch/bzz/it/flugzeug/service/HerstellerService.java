package ch.bzz.it.flugzeug.service;

import ch.bzz.it.flugzeug.data.DataHandler;

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
@Path("hersteller")
public class HerstellerService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHersteller(@CookieParam("userRole")String userRole) {
        Map<String, Hersteller> herstellerMap = null;
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            herstellerMap = DataHandler.getHerstellerMap();
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
                .entity(herstellerMap)
                .cookie(cookie)
                .build();
        return response;
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readHersteller(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("herstellerUUID") String herstellerUUID,
            @CookieParam("userRole") String userRole){
        Hersteller hersteller = null;
        int httpStatus;

        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            hersteller = DataHandler.getHerstellerMap().get(herstellerUUID);

            if (hersteller != null) {
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
                .entity(hersteller)
                .cookie(cookie)
                .build();
        return response;
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createHersteller(
            @Valid @BeanParam Hersteller hersteller,
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @CookieParam("userRole") String userRole


    ) {
        int httpStatus;

        if (userRole != null && userRole.equals("admin")) {

            httpStatus = 200;

            hersteller.setHerstellerUUID(UUID.randomUUID().toString());

            Map<String, Hersteller> herstellerMap = DataHandler.getHerstellerMap();

            herstellerMap.put(hersteller.getHerstellerUUID(), hersteller);
            DataHandler.writeHersteller(herstellerMap);

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
    public Response deleteHersteller(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("herstellerUUID") String herstellerUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;

        if (userRole != null && userRole.equals("admin")) {

            Map<String, Hersteller> herstellerMap = DataHandler.getHerstellerMap();
            Hersteller hersteller = herstellerMap.get(herstellerUUID);
            if (hersteller != null) {
                httpStatus = 200;
                herstellerMap.remove(herstellerUUID);
                DataHandler.writeHersteller(herstellerMap);
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
    public Response updateHersteller(
            @Valid @BeanParam Hersteller hersteller,
            @CookieParam("userRole") String userRole

    ) {
        int httpStatus;
        if (userRole != null && userRole.equals("admin")) {

            Map<String, Hersteller> herstellerMap = DataHandler.getHerstellerMap();
            if (herstellerMap.containsKey(hersteller.getHerstellerUUID())) {
                herstellerMap.put(hersteller.getHerstellerUUID(), hersteller);
                DataHandler.writeHersteller(herstellerMap);
                httpStatus = 200;
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
