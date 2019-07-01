package com.twitter.clone.twitterclone;

import com.google.gson.Gson;
import com.twitter.clone.twitterclone.controller.UserController;
import com.twitter.clone.twitterclone.payload.LoginRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthenticationTest {


    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    public void testLoginSuccess() throws Exception {

        String url = "/api/users/login";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("teten@mail.com");
        loginRequest.setPassword("123456");

        Gson gson = new Gson();

        String content  = gson.toJson(loginRequest);

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
