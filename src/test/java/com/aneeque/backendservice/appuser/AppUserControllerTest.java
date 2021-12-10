package com.aneeque.backendservice.appuser;

import com.aneeque.backendservice.role.PrivilegeListRequest;
import com.aneeque.backendservice.token.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AppUserController.class})
@ExtendWith(SpringExtension.class)
class AppUserControllerTest {
    @Autowired
    private AppUserController appUserController;

    @MockBean
    private AppUserService appUserService;

    @MockBean
    private TokenService tokenService;

    @Test
    void testActivateAccount() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/auth/{token}/activate-account",
                "ABC123");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testAssignPrivilegesToUser() throws Exception {
        PrivilegeListRequest privilegeListRequest = new PrivilegeListRequest();
        privilegeListRequest.setPrivileges(new ArrayList<Long>());
        String content = (new ObjectMapper()).writeValueAsString(privilegeListRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{userId}/assign-privilege", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testDeleteUserAccount() throws Exception {
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/{userId}/delete", 123L);
        MockHttpServletRequestBuilder paramResult = deleteResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testDisableUserAccount() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/{userId}/disable-account", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testEnableUserAccount() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/{userId}/enable-account", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetAll() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/get-all");
        MockHttpServletRequestBuilder paramResult = getResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetAllUsersByRole() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/get-all/role/{roleId}", 123L);
        MockHttpServletRequestBuilder paramResult = getResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetPrivilegesAssignedToUser() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{userId}/get-all/assigned-privileges",
                123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testLockUserAccount() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/{userId}/lock-account", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testLoginUser() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("password");
        loginRequest.setEmailAddress("john.doe@aneeque.com");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRegisterUser() throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("password");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("password");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("john.doe@aneeque.com");
        String content = (new ObjectMapper()).writeValueAsString(registrationRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRegisterUser2() throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("password");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("password");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("john.doe@aneeque.com");
        String content = (new ObjectMapper()).writeValueAsString(registrationRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testUnlockUserAccount() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/{userId}/unlock-account", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

