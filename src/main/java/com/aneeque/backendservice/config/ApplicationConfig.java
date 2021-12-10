package com.aneeque.backendservice.config;


import com.aneeque.backendservice.appuser.AppUserRole;
import com.aneeque.backendservice.appuser.AppUserService;
import com.aneeque.backendservice.privilege.Privilege;
import com.aneeque.backendservice.privilege.PrivilegeRepository;
import com.aneeque.backendservice.privilege.PrivilegeService;
import com.aneeque.backendservice.privilege.UserPrivilege;
import com.aneeque.backendservice.role.Role;
import com.aneeque.backendservice.role.RoleRepository;
import com.aneeque.backendservice.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        for (Role r : roleList) {
            System.out.println(r.getName());
        }
        if (roleList.size() != 0) databaseAlreadyConfigured = true;

        if (!databaseAlreadyConfigured) {


            //create new role records
            log.info("database not configured");
            log.info("setting up database");
            List<Role> roles = new ArrayList<>();
            for (AppUserRole role : AppUserRole.values()) {
                roles.add(new Role(role.name()));
            }
            roleService.getRoleRepository().saveAll(roles);

            List<Privilege> privileges = new ArrayList<>();
            for (UserPrivilege privilege : UserPrivilege.values()) {
                privileges.add(new Privilege(privilege.name(), "Description", "USER_MGNT_MODULE"));
            }
            privilegeService.getPrivilegeRepository().saveAll(privileges);

        } else
            log.info("database is configured");


    }

    public List<Role> getRoles() {
        return roleService.findAllRoles();

    }


}
