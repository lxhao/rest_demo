package com.me.test.server.controller;

import com.me.test.server.bean.UserEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public interface UserService {

    @GET
    @Path("/query/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    UserEntity queryByUserId(@PathParam("username") String username);

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateUser(UserEntity user);

    @POST
    @Path("/add/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    Response addUser(UserEntity user);

    @DELETE
    @Path("/del/")
    @Consumes(MediaType.TEXT_PLAIN)
    Response delUser(@FormParam("id") Long id);

}