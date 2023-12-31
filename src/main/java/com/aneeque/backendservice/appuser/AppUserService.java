package com.aneeque.backendservice.appuser;


import com.aneeque.backendservice.exception.ApiRequestException;
import com.aneeque.backendservice.security.jwt.JwtUtil;
import com.aneeque.backendservice.privilege.Privilege;
import com.aneeque.backendservice.privilege.PrivilegeService;
import com.aneeque.backendservice.role.Role;
import com.aneeque.backendservice.role.RoleService;
import com.aneeque.backendservice.token.Token;
import com.aneeque.backendservice.token.TokenService;
import com.aneeque.backendservice.token.TokenType;
import com.aneeque.backendservice.util.validator.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static com.aneeque.backendservice.util.Util.*;

/**
 * @author B.O Okala III
 */
@Slf4j
@Service
public class AppUserService implements UserDetailsService {
    @Value(value = "${user-mgnt.account.activation-email.template}")
    private String TOKEN_MSG_TEMPLATE;

    @Value(value = "${user-mgnt.account.activation-token.lifetime.minutes}")
    private int TOKEN_LIFETIME_IN_MINUTES;

    private static final String USER_NOT_FOUND_MSG = "no user with email: %s found";


    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailValidator emailValidator;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return appUserRepository.findByEmailAddress(email).orElseThrow(() -> {
            log.error(String.format(USER_NOT_FOUND_MSG, email));
            return new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        });
    }


    public AuthenticationResponse loginUser(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailAddress(), request.getPassword()));

        } catch (BadCredentialsException ex) {
            log.error("invalid username or password");
            throw new ApiRequestException("invalid username or password", HttpStatus.UNAUTHORIZED);
        } catch (DisabledException ex) {
            log.error("user account is disabled");
            throw new ApiRequestException("user is disabled", HttpStatus.UNAUTHORIZED);
        } catch (LockedException ex) {
            log.error("user account is locked");
            throw new ApiRequestException("user account is locked", HttpStatus.LOCKED);
        }

        final UserDetails userDetails = loadUserByUsername(request.getEmailAddress());
        AppUser appUser = findUserByEmail(request.getEmailAddress());
        LocalDateTime lastLogin = appUser.getLastLogin();
        appUser.setLastLogin(LocalDateTime.now());
        appUserRepository.save(appUser);

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt, appUser.getRole().getName(), appUser.getAllUserPrivileges(),
                appUser.getId().toString(), appUser.getFirstName(), appUser.getLastName(), lastLogin);

    }


    public AuthenticationResponse signUp(RegistrationRequest request) {

        if (!hasValue(request.getEmailAddress())) throw new IllegalArgumentException("email address can not be empty");

        if (!hasValue(request.getFirstName())) throw new IllegalArgumentException("first name can not be empty");

        if (!hasValue(request.getLastName())) throw new IllegalArgumentException("last name can not be empty");

        if (!hasValue(request.getMobileNumber())) throw new IllegalArgumentException("mobile number can not be empty");

        if (!hasValue(request.getPassword())) throw new IllegalArgumentException("password field cannot be empty");

        if (!hasValue(request.getConfirmPassword()))
            throw new IllegalArgumentException("confirm password field cannot be empty");

        if (!request.getPassword().equals(request.getConfirmPassword()))
            throw new IllegalArgumentException("passwords mismatch");

        boolean userExists = appUserRepository.existsByEmailAddress(request.getEmailAddress());
        boolean userPhoneNoExists = appUserRepository.existsByMobileNumber(request.getMobileNumber());

        if (userExists) throw new ApiRequestException("Email already taken", HttpStatus.BAD_REQUEST);

        if (userPhoneNoExists) throw new ApiRequestException("mobile number already taken", HttpStatus.BAD_REQUEST);


        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(request, appUser);
        return signUp(appUser);
    }

    @Transactional
    public AuthenticationResponse signUp(AppUser appUser) {

//        Optional<Token> token = tokenService.getTokenRepository().findByEmailAddressAndTokenType(appUser.getEmailAddress(), TokenType.NEW_ACCOUNT);
//        boolean userRequestedToken = token.isPresent();


        String encodedPassword = bCryptPasswordEncoder.encode((appUser.getPassword()));
        appUser.setPassword(encodedPassword);
        appUser.setRole(roleService.findRoleByName(AppUserRole.REGULAR_USER.name()));

        appUser.setLastLogin(LocalDateTime.now());

        log.debug("user to be saved details \n" + appUser);
        appUser.setEnabled(true);
        AppUser newUser = appUserRepository.save(appUser);

//        sendActivateAccountEmail(newUser.getEmailAddress());

        final String jwt = jwtTokenUtil.generateToken(appUser);
        return new AuthenticationResponse(jwt, newUser.getRole().getName(), newUser.getAllUserPrivileges(),
                appUser.getId().toString(), newUser.getFirstName(), newUser.getLastName(), newUser.getLastLogin());
    }


//    @Transactional
//    public String forgotPassword(String email) {
//        AppUser appUser = appUserRepository.findByEmailAddress(email).orElseThrow(() -> new ApiRequestException("email does not exist", HttpStatus.NOT_FOUND));
//
//        String phoneNo = appUser.getUserPhoneNo();
//        String token = tokenService.generateOTP(6);
//
//        Optional<Token> token1 = tokenService.getTokenRepository().findByEmailAndTokenType(email, TokenType.FORGOT_PASSWORD);
//        if (token1.isPresent()) {
//            token1.get().setToken(token);
//            token1.get().setCreatedAt(LocalDateTime.now());
//            token1.get().setExpiredAt(LocalDateTime.now().plusMinutes(TOKEN_LIFETIME_IN_MINUTES));
//            tokenService.getTokenRepository().save(token1.get());
//            smsService.send(phoneNo, String.format("your token is: %s", token));
//
//            log.info(String.format("your token is: %s", token));
//            return String.format("OTP sent to %s successfully", hideChar(phoneNo));
//        }
//        Token confirmationOTP = new Token(token, TokenType.FORGOT_PASSWORD, email, appUser.getUserPhoneNo(), LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(TOKEN_LIFETIME_IN_MINUTES));
//
//        tokenService.saveToken(confirmationOTP);
//        smsService.send(phoneNo, String.format("your token is: %s", token));
//        log.info(String.format("your token is: %s", token));
//        return String.format("OTP sent to %s successfully", phoneNo);
//
//    }


    @Transactional
    public void resetUserPwd(ResetPasswordRequest request) {
        AppUser appUser = findUserByEmail(request.getEmail());
        String encodedPassword = bCryptPasswordEncoder.encode((request.getPassword()));
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

    }

    @Transactional
    public String enableAppUser(Long userId) {
        AppUser appUser = findUserById(userId);
        appUser.setEnabled(true);
        appUserRepository.save(appUser);
        return String.format("user account enabled successfully");

    }

    @Transactional
    public String enableAppUser(String email) {
        AppUser appUser = findUserByEmail(email);
        appUser.setEnabled(true);
        appUserRepository.save(appUser);
        return String.format("user account enabled successfully");

    }

    @Transactional
    public String disableAppUser(Long userId) {
        AppUser appUser = findUserById(userId);
        appUser.setEnabled(false);
        appUserRepository.save(appUser);
        return String.format("user account disabled successfully");
    }

    @Transactional
    public String disableAppUser(String email) {
        AppUser appUser = findUserByEmail(email);
        appUser.setEnabled(false);
        appUserRepository.save(appUser);
        return String.format("user account disabled successfully");
    }

    @Transactional
    public String unLockAppUser(Long userId) {
        AppUser appUser = findUserById(userId);
        appUser.setLocked(false);
        appUserRepository.save(appUser);
        return String.format("user account unlocked successfully");

    }

    @Transactional
    public String lockAppUser(Long userId) {
        AppUser appUser = findUserById(userId);
        appUser.setLocked(true);
        appUserRepository.save(appUser);
        return String.format("user account locked successfully");


    }

    public Page<AppUser> getAllUsers(int page, int size) {
        return appUserRepository.findAll(PageRequest.of(page, size));
    }

    public Page<AppUser> getAllUsersByRole(long roleId, int page, int size) {
        Role role = roleService.findRoleById(roleId);
        return appUserRepository.findAllByRole(role, PageRequest.of(page, size));
    }

    @Transactional
    public String deleteUser(Long userId) {
        AppUser appUser = findUserById(userId);
        appUser.setEnabled(false);
        appUser.setLocked(true);
        appUser.setIsDeleted(true);
        //should I unAssign roles and privileges too??
        appUserRepository.save(appUser);
        return "user deleted successfully";
    }

    AppUser findUserByEmail(String email) {
        if (!hasValue(email)) {
            throw new IllegalArgumentException("email address can not be empty");
        }
        return appUserRepository.findByEmailAddress(email).orElseThrow(() -> {
            return new ApiRequestException("email not found", HttpStatus.NOT_FOUND);
        });
    }

    AppUser findUserById(Long userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("invalid user id");
        }
        return appUserRepository.findById(userId).orElseThrow(() -> {
            return new ApiRequestException("user not found", HttpStatus.NOT_FOUND);
        });
    }

    @Transactional
    public AppUserDto assignPrivilegesToUser(Long userId, List<Long> privilegeIds) {
        AppUser appUser = findUserById(userId);
        List<Privilege> privileges = privilegeService.getPrivilegeRepository().findAllById(privilegeIds);
        appUser.setUserAssignedPrivileges(privileges);
        appUserRepository.save(appUser);

        AppUserDto appUserDto = new AppUserDto();
        BeanUtils.copyProperties(appUser, appUserDto);
        return appUserDto;
    }

    public Collection<Privilege> getPrivilegesAssignedToUser(Long userId) {
        AppUser appUser = findUserById(userId);
        return appUser.getUserAssignedPrivileges();
    }


    public String sendActivateAccountEmail(String email) {

        String otp = tokenService.generateToken(6);
        String time = LocalDateTime.now().toLocalTime().plusMinutes(TOKEN_LIFETIME_IN_MINUTES).toString().split("\\.")[0];
        Optional<Token> token = tokenService.getTokenRepository().findByEmailAddressAndTokenType(email, TokenType.NEW_ACCOUNT);


        if (token.isPresent()) {
            token.get().setToken(otp);
            token.get().setCreatedAt(LocalDateTime.now());
            token.get().setExpiredAt(LocalDateTime.now().plusMinutes(TOKEN_LIFETIME_IN_MINUTES));

            tokenService.getTokenRepository().save(token.get());

            //TODO: send email with genereated token (otp)

            log.info("sending mail (mock)...");
            String message = String.format(TOKEN_MSG_TEMPLATE, otp, time + getTimeOfDay(time));

            log.info(String.format(message));
            return String.format("OTP sent to %s successfully", hideChar(email));
        }

        String message = String.format(TOKEN_MSG_TEMPLATE, otp, time + getTimeOfDay(time));
        log.info(message);

        Token newToken = new Token(otp, TokenType.NEW_ACCOUNT, email, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(TOKEN_LIFETIME_IN_MINUTES));

        tokenService.getTokenRepository().save(newToken);

        log.debug(message);
        return String.format("mail sent to %s successfully", hideEmailChar(email));

    }

    private String getTimeOfDay(String time) {
        return Integer.parseInt(time.split(":")[0]) > 12 ? " PM" : " AM";
    }

}
