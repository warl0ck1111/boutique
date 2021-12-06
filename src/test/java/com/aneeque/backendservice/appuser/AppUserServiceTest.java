package com.aneeque.backendservice.appuser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AppUserServiceTest {
    @Test
    void testSignUp() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("");
        registrationRequest.setPassword("password");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("password");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("42 Main St");
        assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
    }

    @Test
    void testSignUp2() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("");
        registrationRequest.setPassword("password");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("password");
        registrationRequest.setFirstName("");
        registrationRequest.setEmailAddress("42 Main St");
        assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
    }

    @Test
    void testSignUp3() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("");
        registrationRequest.setPassword("password");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("password");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("");
        assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
    }

    @Test
    void testSignUp4() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("Password");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("password");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("42 Main St");
        assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
    }

    @Test
    void testSignUp5() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("Password");
        registrationRequest.setMobileNumber("");
        registrationRequest.setConfirmPassword("password");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("42 Main St");
        assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
    }

    @Test
    void testSignUp6() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("Password");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("42 Main St");
        assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
    }

    @Test
    void testSignUp7() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("42 Main St");
        assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
    }

    @Test
    void testResetUserPwd() {
        AppUserService appUserService = new AppUserService();
        ResetPasswordRequest resetPasswordRequest = mock(ResetPasswordRequest.class);
        when(resetPasswordRequest.getEmail()).thenReturn("");
        assertThrows(IllegalArgumentException.class, () -> appUserService.resetUserPwd(resetPasswordRequest));
        verify(resetPasswordRequest).getEmail();
    }

    @Test
    void testEnableAppUser() {
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).enableAppUser(0L));
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).enableAppUser(""));
    }

    @Test
    void testDisableAppUser() {
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).disableAppUser(0L));
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).disableAppUser(0L));
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).disableAppUser(""));
    }

    @Test
    void testUnLockAppUser() {
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).unLockAppUser(0L));
    }

    @Test
    void testLockAppUser() {
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).lockAppUser(0L));
    }

    @Test
    void testDeleteUser() {
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).deleteUser(0L));
    }

    @Test
    void testFindUserByEmail() {
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).findUserByEmail(""));
    }

    @Test
    void testFindUserById() {
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).findUserById(0L));
    }

    @Test
    void testAssignPrivilegesToUser() {
        AppUserService appUserService = new AppUserService();
        assertThrows(IllegalArgumentException.class,
                () -> appUserService.assignPrivilegesToUser(0L, new ArrayList<Long>()));
    }

    @Test
    void testGetPrivilegesAssignedToUser() {
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).getPrivilegesAssignedToUser(0L));
    }
}

