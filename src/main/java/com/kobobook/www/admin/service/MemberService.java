package com.kobobook.www.admin.service;

import com.kobobook.www.admin.domain.Member;
import com.kobobook.www.admin.domain.Role;
import com.kobobook.www.admin.dto.MemberDTO;
import com.kobobook.www.admin.exception.AlreadyExistingMemberException;
import com.kobobook.www.admin.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;

    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;

    /*
    * OAuth 회원가입 및 로그인
    * */
    @Transactional
    public Member save(Member member) {
        member.setRole(Role.ROLE_USER);
        member.setRegDate(LocalDateTime.now());
        Member loginMember = memberRepository.findByOauthId(member.getOauthId());
        if(loginMember == null) {
            return memberRepository.save(member);
        }
        System.out.println("loginMember2 : " + loginMember);
        return loginMember;
    }

    /*
    * 회원가입
    * */
    @Transactional
    public void create(Member member) throws AlreadyExistingMemberException {
        Member chkMember = memberRepository.findByUserEmail(member.getUserEmail());
        if(chkMember != null) {
            throw new AlreadyExistingMemberException("dup id " + member.getUserEmail());
        } else {
            member.setRole(Role.ROLE_ADMIN);
            member.setRegDate(LocalDateTime.now());
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            memberRepository.save(member);
        }
    }

    @Transactional
    public Member selectMember(String userEmail) {
        return memberRepository.findByUserEmail(userEmail);
    }

    /*
     * 회원정보
     * */
    @Transactional
    public MemberDTO readMember(Integer memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        return modelMapper.map(member, MemberDTO.class);
    }
}
