package com.aneeque.backendservice.category.department.attribute;

import com.aneeque.backendservice.category.department.Department;
import com.aneeque.backendservice.category.property.Property;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author Okala III
 */

@Entity
@NoArgsConstructor
public class Attribute {
    @Id
    @SequenceGenerator(name="attribute_sequence", sequenceName = "attribute_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attribute_sequence")
    @JsonProperty("attributeId")
    private Long id;

    private String name;


    @JsonBackReference
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ATTRIBUTE_PROPERTIES",
            joinColumns = @JoinColumn(name = "attribute_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "property_id", referencedColumnName = "id")
    )
    private Collection<Property> properties;


    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime modifiedAt;

    public Attribute(String name) {
        this.name = name;
    }
}
