package com.aneeque.backendservice.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    private String address;

    private String city;

    private String country;

    private String dateOfBirth;

    private String gender;

    private String jobDescription;

    private String jobTitle;

    private String maritalStatus;

    private String title;

    private String postCode;

    @OneToOne
    private Role role;


    @ManyToMany(fetch = FetchType.LAZY)
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

    public Set<Privilege> getAllUserPrivileges(){
        Set<Privilege> allPrivileges = new HashSet<>();
        allPrivileges.addAll(getRole().getPrivileges());
        allPrivileges.addAll(getUserAssignedPrivileges());
        return allPrivileges;

    }


}
