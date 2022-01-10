package com.aneeque.backendservice.config;


import com.aneeque.backendservice.dto.request.RegistrationRequest;
import com.aneeque.backendservice.enums.AppUserRole;
import com.aneeque.backendservice.service.AppUserService;
import com.aneeque.backendservice.data.entity.Privilege;
import com.aneeque.backendservice.service.PrivilegeService;
import com.aneeque.backendservice.enums.UserPrivilege;
import com.aneeque.backendservice.data.entity.Role;
import com.aneeque.backendservice.data.repository.RoleRepository;
import com.aneeque.backendservice.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@Component
public class ApplicationConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeService privilegeService;

    private boolean databaseAlreadyConfigured = false;


    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("onApplicationEvent");
        List<Role> roleList = getRoles();

        if (roleList.size() >4)
            databaseAlreadyConfigured = true;

        if (!databaseAlreadyConfigured) {


            //create new role records
            log.info("database not configured");
            log.info("setting up roles and privileges in the database");
            privilegeService.getPrivilegeRepository().deleteAll();
            roleRepository.deleteAll();
            List<Role> roles = new ArrayList<>();
            for (AppUserRole role : AppUserRole.values()) {
                Role roleInDb = roleService.findRoleByName(role.name());
                if(Objects.isNull(roleInDb))
                    roles.add(new Role(role.name()));
            }
            if(roles.size() > 0)
                roleService.getRoleRepository().saveAll(roles);

            List<Privilege> privileges = new ArrayList<>();
            for (UserPrivilege privilege : UserPrivilege.values()) {
                if(privilegeService.getPrivilegeRepository().findByName(privilege.toString().toUpperCase().trim()).isEmpty())
                    privileges.add(new Privilege(privilege.toString().toUpperCase().trim(), privilege.description, privilege.module));
            }
            if(privileges.size() > 0) {
                privilegeService.getPrivilegeRepository().saveAll(privileges);
            }
            if(Objects.isNull(appUserService.findUserByEmail("superadmin@aneeque.com")))
                appUserService.signUp(new RegistrationRequest("Super", "Admin", "superadmin@aneeque.com", "07068693731","password", "password", 1L));
            if(Objects.isNull(appUserService.findUserByEmail("aneequeadmin@aneeque.com")))
                appUserService.signUp(new RegistrationRequest("Aneeque", "Admin", "aneequeadmin@aneeque.com", "07068693731","password", "password", 2L));

        } else
            log.info("database is configured");


    }

    public List<Role> getRoles() {
        return roleService.findAllRoles();
    }


}
