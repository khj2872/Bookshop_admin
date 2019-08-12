package com.kobobook.www.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Builder
    public Member(String userEmail, String username, String password, Role role) {
        this.userEmail = userEmail;
        this.username = username;
        this.password = password;
        this.role = role;
    }

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
