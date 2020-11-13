package ch.bzz.it.flugzeug.service;

import ch.bzz.it.flugzeug.data.DataHandler;
import ch.bzz.it.flugzeug.model.Flugzeug;
import ch.bzz.it.flugzeug.model.Passagier;

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
@Path("passagier")
public class PassagierService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPassagiere(@CookieParam("userRole") String userRole) {
        Map<String, Passagier> passagierMap = null;
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            passagierMap = DataHandler.getPassagierMap();
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
                .entity(passagierMap)
                .cookie(cookie)
                .build();
        return response;
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPassagier(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("passagierUUID") String passagierUUID,
            @CookieParam("userRole") String userRole){
        Passagier passagier = null;
        int httpStatus;

        if(userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            passagier = DataHandler.getPassagierMap().get(passagierUUID);

            if (passagier != null) {
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
                .entity(passagier)
                .build();
        return response;
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)

    public Response createPassagiere(
            @Valid @BeanParam Passagier passagier,
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("flugzeugUUID") String flugzeugUUID,
            @CookieParam("userRole") String userRole


    ) {

        int httpStatus;
        if (userRole != null && userRole.equals("admin")) {

            httpStatus = 200;

            passagier.setPassagierUUID(UUID.randomUUID().toString());
            if (DataHandler.getFlugzeugMap().containsKey(flugzeugUUID)) {
                Flugzeug flugzeug = DataHandler.getFlugzeugMap().get(flugzeugUUID);
                passagier.setFlugzeug(flugzeug);

                Map<String, Passagier> passagierMap = DataHandler.getPassagierMap();

                passagierMap.put(passagier.getPassagierUUID(), passagier);
                DataHandler.writePassagiere(passagierMap);
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

    public Response deletePassagiere(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String passagierUUID,
             @CookieParam("userRole") String userRole
    ) {
        int httpStatus;

        if (userRole != null && userRole.equals("admin")) {

            Map<String, Passagier> passagierMap = DataHandler.getPassagierMap();
            Passagier passagier = passagierMap.get(passagierUUID);
            if (passagier != null) {
                httpStatus = 200;
                passagierMap.remove(passagierUUID);
                DataHandler.writePassagiere(passagierMap);
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

    public Response updatePassagier(
            @Valid @BeanParam Passagier passagier,
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("flugzeugUUID") String flugzeugUUID,
            @CookieParam("userRole") String userRole


    ) {
        int httpStatus;

        if (userRole != null && userRole.equals("admin")) {

            Map<String, Passagier> passagierMap = DataHandler.getPassagierMap();
            if (passagierMap.containsKey(passagier.getPassagierUUID())) {
                if (DataHandler.getFlugzeugMap().containsKey(flugzeugUUID)) {
                    Flugzeug flugzeug = DataHandler.getFlugzeugMap().get(flugzeugUUID);

                    passagier.setFlugzeug(flugzeug);
                    passagierMap.put(passagier.getPassagierUUID(), passagier);
                    DataHandler.writePassagiere(passagierMap);
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
