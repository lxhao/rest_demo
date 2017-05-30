package com.me.test.server.controller;

import com.me.test.server.bean.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customer")
public interface CustomerService {
    //查找客户信息
    @GET
    @Path("/query/{id}")
    // json格式回复
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    Customer GetCustomerName(@PathParam("id") String id);

    //更新客户信息
    @PUT
    @Path("/update")
    @Consumes(MediaType.TEXT_PLAIN)
    Response updateCustomer(@DefaultValue("123") @QueryParam("id") Long id, @QueryParam("name") String name);

    @POST
    @Path("/add/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Response addCustomer(Customer customer);

    @DELETE
    @Path("/del/")
    @Consumes(MediaType.TEXT_PLAIN)
    Response delCustomer(@FormParam("id") Long id);

    @GET
    @Path("/test/")
    @Produces(MediaType.TEXT_PLAIN)
    String test();
}