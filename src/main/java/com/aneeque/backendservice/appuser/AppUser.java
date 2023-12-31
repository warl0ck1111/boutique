package com.aneeque.backendservice.appuser;

import com.aneeque.backendservice.privilege.Privilege;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.aneeque.backendservice.role.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author B.O Okala III
 */


@Slf4j
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(name="user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @JsonProperty("userId")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String emailAddress;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String mobileNumber;

    @JsonIgnore
    private int failedLoginAttempt; //(MAX = 10, )

    @OneToOne
    private Role role;

    @OneToMany
    private Collection<Privilege> userAssignedPrivileges = new ArrayList<>();

    private Boolean locked = false;

    private Boolean enabled = false;

    private Boolean isDeleted = false;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime lastLogin = LocalDateTime.now();

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime modifiedAt;

    @JsonIgnore
    @Override
    public Collection<Privilege> getAuthorities() {
        List<Privilege> privileges = new ArrayList<>();
        privileges.addAll(role.getPrivileges());
        privileges.addAll(userAssignedPrivileges);
        return privileges;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public List<String> getAllUserPrivileges(){
        Collection<Privilege> privilegeCollection = new ArrayList<>();
        privilegeCollection.addAll(getRole().getPrivileges());
        privilegeCollection.addAll(getUserAssignedPrivileges());

        List<String> allPrivileges = new ArrayList<>();
        privilegeCollection.forEach(privilege -> {
            allPrivileges.add(privilege.getName());
        });
        return allPrivileges;

    }


}
