package com.kobobook.www.admin.service;

import com.kobobook.www.admin.domain.Member;
import com.kobobook.www.admin.domain.Role;
import com.kobobook.www.admin.exception.IdPasswordNotMatchingException;
import com.kobobook.www.admin.exception.UnAuthException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private MemberService memberService;

    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userEmail = authentication.getName();
        String password = authentication.getCredentials().toString();

        Member member = memberService.selectMember(userEmail);

        if (member == null || !passwordEncoder.matches(password, member.getPassword())) {
            throw new IdPasswordNotMatchingException("아이디와 비밀번호가 일치하지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        // 로그인한 계정에게 권한 부여
        if (member.isAdmin()) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString()));
        } else {
            throw new UnAuthException("관리자 계정만 로그인 할 수 있습니다.");
        }

        // 로그인 성공시 로그인 사용자 정보 반환
        return new UsernamePasswordAuthenticationToken(member, password, grantedAuthorityList);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
