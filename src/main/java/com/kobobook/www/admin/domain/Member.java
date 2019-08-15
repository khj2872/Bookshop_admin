package com.kobobook.www.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Integer id;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String username;

    private String password;

    private String oauthId;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String phone;

    @Embedded
    private Address address;

    private LocalDateTime regDate;

    private long point;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public boolean matchPassword(String password) {
        return this.getPassword().equals(password);
    }

    public boolean isAdmin() {
        return this.role.equals(Role.ROLE_ADMIN);
    }
}
