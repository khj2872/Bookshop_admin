package com.kobobook.www.admin;

import com.kobobook.www.admin.domain.Member;
import com.kobobook.www.admin.exception.AlreadyExistingMemberException;
import com.kobobook.www.admin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class AdminApplication implements ApplicationRunner{

    private static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "file:///E:/workspace/IDEA_workspace/config/kobobook-admin/real-application.yml,"
            + "/app/config/kobobook-admin/real-application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

    @Autowired
    MemberService memberService;

    @Override
    public void run(ApplicationArguments args) {
        Member member = Member.builder()
                .userEmail("test")
                .password("test")
                .username("관리자")
                .build();
        member.setRegDate(LocalDateTime.now());
        try {
            memberService.create(member);
        } catch (AlreadyExistingMemberException ame) {
            System.out.println("중복 id");
        }

    }
}
