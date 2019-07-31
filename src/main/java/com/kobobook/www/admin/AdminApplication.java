package com.kobobook.www.admin;

import com.kobobook.www.admin.domain.Member;
import com.kobobook.www.admin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class AdminApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @Autowired
    MemberService memberService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member member = new Member();
        member.setUsername("관리자");
        member.setRegDate(new Date());
        member.setUserEmail("test");
        member.setPassword("test");

        memberService.create(member);
    }
}
