package com.kobobook.www.admin;

import com.kobobook.www.admin.domain.Member;
import com.kobobook.www.admin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Date;

@SpringBootApplication
public class AdminApplication implements ApplicationRunner {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:aws.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
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
