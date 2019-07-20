package com.kobobook.www.admin.service;

import com.kobobook.www.admin.domain.Member;
import com.kobobook.www.admin.domain.Role;
import com.kobobook.www.admin.exception.AlreadyExistingMemberException;
import com.kobobook.www.admin.repository.MemberRepository;
import com.kobobook.www.admin.util.HashUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;

    /*
    * OAuth 회원가입 및 로그인
    * */
    @Transactional
    public Member save(Member member) {
        member.setRole(Role.ROLE_USER);
        member.setRegDate(new Date());
        Member loginMember = memberRepository.findByOauthId(member.getOauthId());
        if(loginMember == null) {
            return memberRepository.save(member);
        }
        System.out.println("loginMember2 : " + loginMember);
        return loginMember;
    }

    /*
    * 로그인 성공 후 회원이름 반환
    * */
    @Transactional
    public String findUserName(int id) {
        return memberRepository.findUserNameById(id).getUsername();
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
            member.setRole(Role.ROLE_USER);
            member.setRegDate(new Date());
            member.setPassword(HashUtil.sha256(member.getPassword()));
            memberRepository.save(member);
        }
    }

    @Transactional
    public Member selectMember(String userEmail) {
        return memberRepository.findByUserEmail(userEmail);
    }
}
