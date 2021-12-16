package com.aneeque.backendservice.appuser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.aneeque.backendservice.dto.request.RegistrationRequest;
import com.aneeque.backendservice.dto.request.ResetPasswordRequest;
import com.aneeque.backendservice.service.AppUserService;
import org.junit.jupiter.api.Test;

class AppUserServiceTest {
    @Test
    void testSignUpShouldFailForEmptyLastname() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("");
        registrationRequest.setPassword("password");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("password");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("john.doe@aneeque.com");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
        String expectedMessage = "last name can not be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testSignUpShouldFailForEmptyLastName() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("");
        registrationRequest.setPassword("password");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("password");
        registrationRequest.setFirstName("john");
        registrationRequest.setEmailAddress("john.doe@aneeque.com");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
        String expectedMessage = "last name can not be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testSignUpShouldFailForEmptyEmailAddress() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("doe");
        registrationRequest.setPassword("password");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("password");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
        String expectedMessage = "email address can not be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    void testSignUpShouldFailForPasswordAndConfirmPasswordMismatch() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("Password");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("password");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("john.doe@aneeque.com");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
        String expectedMessage = "passwords mismatch";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testSignUpShouldFailForEmptyMobileNumber() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("Password");
        registrationRequest.setMobileNumber("");
        registrationRequest.setConfirmPassword("password");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("john.doe@aneeque.com");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
        String expectedMessage = "mobile number can not be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testSignUpShouldFailForEmptyConfirmPassword() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("Password");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("john.doe@aneeque.com");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
        String expectedMessage = "confirm password field cannot be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));    }

    @Test
    void testSignUpShouldFailForEmptyPassword() {
        AppUserService appUserService = new AppUserService();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("");
        registrationRequest.setMobileNumber("42");
        registrationRequest.setConfirmPassword("");
        registrationRequest.setFirstName("John");
        registrationRequest.setEmailAddress("john.doe@aneeque.com");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUserService.signUp(registrationRequest));
        String expectedMessage = "password field cannot be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));    }

    @Test
    void testResetUserPwdShouldFailForEmptyEmailAddress() {
        AppUserService appUserService = new AppUserService();
        ResetPasswordRequest resetPasswordRequest = mock(ResetPasswordRequest.class);
        when(resetPasswordRequest.getEmail()).thenReturn("");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUserService.resetUserPwd(resetPasswordRequest));
        String expectedMessage = "email address can not be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(resetPasswordRequest).getEmail();
    }

    @Test
    void testEnableAppUserShouldFailForEmptyEmailAddress() {
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).enableAppUserById(0L));
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).enableAppUserByEmail(""));
        String expectedMessage = "email address can not be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDisableAppUserShouldFailForEmptyEmailAddress() {
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).disableAppUserById(0L));
        assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).disableAppUserById(0L));
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).disableAppUserById(""));
        String expectedMessage = "email address can not be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUnLockAppUserShouldFailForInvalidUserId() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).unLockAppUserById(0L));
        String expectedMessage = "invalid user id";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testLockAppUserShouldFailForInvalidUserId() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).lockAppUserById(0L));
        String expectedMessage = "invalid user id";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDeleteUserShouldFailForInvalidUserId() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).deleteUserById(0L));
        String expectedMessage = "invalid user id";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindUserByEmailShouldFailForEmptyEmail() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).findUserByEmail(""));
        String expectedMessage = "email address can not be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindUserByIdShouldFailForInvalidUserId() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).findUserById(0L));
        String expectedMessage = "invalid user id";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testAssignPrivilegesToUserShouldFailForInvalidUserId() {
        AppUserService appUserService = new AppUserService();
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> appUserService.assignPrivilegesToUser(0L, new ArrayList<Long>()));
        String expectedMessage = "invalid user id";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetPrivilegesAssignedToUserShouldFailForInvalidUserId() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> (new AppUserService()).getPrivilegesAssignedToUser(0L));
        String expectedMessage = "invalid user id";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


    }
}

