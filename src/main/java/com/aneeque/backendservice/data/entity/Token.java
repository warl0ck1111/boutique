package com.aneeque.backendservice.data.entity;

import com.aneeque.backendservice.enums.TokenType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Okala III
 */

@Data
@NoArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private String emailAddress;

    @Enumerated(value = EnumType.STRING)
    private TokenType tokenType;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime confirmedAt;


    public Token(String token, TokenType tokenType, String emailAddress, LocalDateTime createdAt, LocalDateTime expiredAt) {
        this.token = token;
        this.tokenType = tokenType;
        this.createdAt = createdAt;
        this.emailAddress = emailAddress;
        this.expiredAt = expiredAt;

    }
}
