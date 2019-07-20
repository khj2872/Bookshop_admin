package com.kobobook.www.admin.dto;

import com.kobobook.www.admin.domain.Address;
import com.kobobook.www.admin.domain.Role;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Integer id;

    private String userEmail;

    private String username;

    private String phone;

    private Role role;

    private Address address;

    private Date regDate;

    private long point;

}
