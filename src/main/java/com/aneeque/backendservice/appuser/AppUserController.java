package com.aneeque.backendservice.appuser;

import com.aneeque.backendservice.response.ApiResponse;
import com.aneeque.backendservice.role.PrivilegeListRequest;
import com.aneeque.backendservice.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Okala III
 */

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/user")

public class AppUserController {

    public static final String REGISTER_USER = "auth/register";
    public static final String LOGIN_USER = "auth/login";

    public static final String ENABLE_USER_ACCOUNT = "{userId}/enable-account";
    public static final String DISABLE_USER_ACCOUNT = "{userId}/disable-account";
    public static final String LOCK_USER_ACCOUNT = "{userId}/lock-account";
    public static final String UNLOCK_USER_ACCOUNT = "{userId}/unlock-account";

    public static final String GET_ALL_USERS = "get-all";
    public static final String GET_ALL_USERS_BY_ROLE = "get-all/role/{roleId}";
    public static final String DELETE_USER = "{userId}/delete";
    public static final String ASSIGN_PRIVILEGE_TO_USER = "{userId}/assign-privilege";
    public static final String GET_ALL_PRIVILEGES_ASSIGNED_TO_USER = "{userId}/get-all/assigned-privileges";
    public static final String ACTIVATE_ACCOUNT = "auth/{token}/activate-account";


    @Autowired
    private AppUserService appUserService;

    @Autowired
    private TokenService tokenService;


    @PostMapping(path = REGISTER_USER)
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        AuthenticationResponse response = appUserService.signUp(registrationRequest);
        return ResponseEntity.ok(new ApiResponse( "user registration successful", response));
    }

    @PutMapping(path = ACTIVATE_ACCOUNT)
    public ResponseEntity<ApiResponse> activateAccount(@PathVariable String token) {
        return ResponseEntity.ok(new ApiResponse(tokenService.confirmToken(token)));
    }


    @PostMapping(path = LOGIN_USER)
    public ResponseEntity<ApiResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse response = appUserService.loginUser(loginRequest);
        return ResponseEntity.ok(new ApiResponse( "login successful", response));

    }


    @PutMapping(path = ENABLE_USER_ACCOUNT)
    public ResponseEntity<ApiResponse> enableUserAccount(@PathVariable Long userId) {
        String response = appUserService.enableAppUser(userId);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, response));

    }

    @PutMapping(path = DISABLE_USER_ACCOUNT)
    public ResponseEntity<ApiResponse> disableUserAccount(@PathVariable Long userId) {
        String response = appUserService.disableAppUser(userId);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, response));

    }


    @PutMapping(path = UNLOCK_USER_ACCOUNT)
    public ResponseEntity<ApiResponse> unlockUserAccount(@PathVariable Long userId) {
        String response = appUserService.unLockAppUser(userId);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, response));

    }

    @PutMapping(path = LOCK_USER_ACCOUNT)
    public ResponseEntity<ApiResponse> lockUserAccount(@PathVariable Long userId) {
        String response = appUserService.lockAppUser(userId);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, response));

    }

    @GetMapping(path = GET_ALL_USERS)
    public ResponseEntity<?> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(new ApiResponse(appUserService.getAllUsers(page, size)));
    }

    @GetMapping(path = GET_ALL_USERS_BY_ROLE)
    public ResponseEntity<?> getAllUsersByRole(@PathVariable Long roleId, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(new ApiResponse(appUserService.getAllUsersByRole(roleId, page, size)));
    }

    @DeleteMapping(path = DELETE_USER)
    public ResponseEntity<?> deleteUserAccount(@PathVariable Long roleId, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(new ApiResponse(appUserService.deleteUser(roleId)));
    }

    @PostMapping(path = ASSIGN_PRIVILEGE_TO_USER)
    public ResponseEntity<?> assignPrivilegesToUser(@PathVariable Long userId, @RequestBody PrivilegeListRequest request) {
        return ResponseEntity.ok(new ApiResponse(appUserService.assignPrivilegesToUser(userId, request.getPrivileges())));
    }


    @GetMapping(path = GET_ALL_PRIVILEGES_ASSIGNED_TO_USER)
    public ResponseEntity<?> getPrivilegesAssignedToUser(@PathVariable Long userId) {
        return ResponseEntity.ok(new ApiResponse(appUserService.getPrivilegesAssignedToUser(userId)));
    }







}
