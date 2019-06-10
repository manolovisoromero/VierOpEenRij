package REST;


import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginEndpoint {

    private final Gson gson = new Gson();
    RESTlogic restlogic = RESTlogic.getInstance();

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(RESTMsg restMsg){
        restlogic.handleMsg(restMsg);
        return Response.status(200).entity(restlogic.getResponse()).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(RESTMsg restMsg){
        return Response.status(200).entity("To be made").build();
    }


}
