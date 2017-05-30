package com.me.test.server;

import com.me.test.server.bean.Customer;
import com.me.test.server.bean.UserEntity;
import com.me.test.server.bean.UserSexEnum;
import com.me.test.server.dao.UserMapper;
import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleRestApplication.class)
@WebIntegrationTest
public class RestAppTest {
    public interface TestService {

        @RequestLine("GET /customer/query/{id}")
        @Headers({"Content-Type: application/json", "Accept: application/json"})
        Customer queryCustomer(@Param(value = "id") int id);

        @RequestLine("GET /user/query/{username}")
        UserEntity queryUser(@Param(value = "username") String name);

        @RequestLine("PUT /user/update/")
        void updateUser(UserEntity user);

        @RequestLine("DELETE /user/del/")
        void delUser(@Param(value = "id") long id);

        @RequestLine("POST /user/add/")
        void addUser(UserEntity user);
    }

    String baseURL = "http://127.0.0.1:8080/services";

    public void testqueryUser() throws Exception {
        TestService service = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(TestService.class, baseURL);
        service.addUser(new UserEntity("xrl", "123455", UserSexEnum.MAN));
        UserEntity user = service.queryUser("xrl");
        assertEquals("xrl", user.getUsername());
        service.delUser(1);
        user.setPassword("aaa");
        service.updateUser(user);
    }

    @Test
    public void test() throws Exception {
        TestService service = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(TestService.class, baseURL);
        UserEntity user = service.queryUser("xrl");
        assertEquals("xrl", user.getUsername());
    }


    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
        userMapper.deleteAll();
        userMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
        userMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
        userMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));

        assertEquals(3, userMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<UserEntity> users = userMapper.getAll();
        System.out.println(users.toString());
    }
}