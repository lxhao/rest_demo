package com.me.test.server.controller.impl;

import com.me.test.server.bean.Customer;
import com.me.test.server.controller.CustomerService;
import io.swagger.annotations.Api;

import javax.ws.rs.core.Response;

@Api("/customer")
public class CustomerServiceImpl implements CustomerService {
    //查找客户信息
    public Customer GetCustomerName(String id) {
        return new Customer().setId(100).setName("xrl");
    }

    //更新客户信息
    public Response updateCustomer(Long id, String name) {
        System.out.println("----invoking updateCustomer, Customer name is: " + name);
        Response r;
        //if modify OK!
        if (id == 123) {
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }
        return r;
    }

    public Response addCustomer(Customer customer) {
        System.out.println("----invoking updateCustomer, Customer name is: " + customer.getName());
        Response r;
        r = Response.ok().build();
        return r;
    }

    public Response delCustomer(Long id) {
        Response r;
        if (id > 100) {
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }
        return r;
    }

    public String test() {
        return "hello, test success";
    }
}