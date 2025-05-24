package tech.buildrun.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tech.buildrun.service.UserService;
import tech.entity.UserEntity;

import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Transactional
    public Response createUser(UserEntity userEntity) {
        return Response.ok(userService.createUser(userEntity)).build();
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var users = userService.findAll(page, pageSize);
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")  // ✅ Correto
    public Response getById(@PathParam("id") UUID userId) {
        return Response.ok(userService.findById(userId)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")  // ✅ Correto
    public Response updateUser(@PathParam("id") UUID userId, UserEntity userEntity) {
        return Response.ok(userService.updateUser(userId,userEntity)).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteById(@PathParam("id") UUID userId) {
        String result = userService.deleteById(userId);
        return Response.ok(result).build();
    }

}
