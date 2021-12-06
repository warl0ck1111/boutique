package com.aneeque.backendservice.role;

import com.aneeque.backendservice.privilege.Privilege;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author B.O Okala III
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @SequenceGenerator(name = "app_user_role_sequence", sequenceName = "app_user_role_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_role_sequence")
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    private String entity = "ANEEQUE";

    @JsonIgnore
    @Basic(optional = true)
    @Column(name = "IS_DELETED")
    private Boolean isDeleted = Boolean.FALSE;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Privilege> privileges = new ArrayList<>();

    @JsonIgnore
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime modifiedAt;


    public static Role valueOf(String role) {
        return new Role(role);
    }


    public Role(String name) {
        this.name = name;
    }

    public Role(String name, Collection<Privilege> privileges) {
        this.name = name;
        this.privileges = privileges;
    }


    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }


}
