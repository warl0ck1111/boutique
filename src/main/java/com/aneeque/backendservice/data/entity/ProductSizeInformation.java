package com.aneeque.backendservice.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Okala Bashir .O.
 */

@Entity
@Data
@NoArgsConstructor
public class ProductSizeInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String name;
    private String unit;

    @Column(name = "from_x")
    private Double fromX;
    @Column(name = "to_y")
    private Double toY;
    private Long productId;




}
