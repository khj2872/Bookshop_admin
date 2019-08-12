package com.kobobook.www.admin;

import com.kobobook.www.admin.domain.Member;
import com.kobobook.www.admin.domain.Role;
import com.kobobook.www.admin.repository.MemberRepository;
import com.kobobook.www.admin.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class AdminApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired

    @Test
    public void contextLoads() {
        //given
        memberRepository.save(Member.builder()
                .username("user1")
                .password("test")
                .userEmail("testEmail")
                .role(Role.ROLE_USER)
                .build());
        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Member member = memberList.get(0);
        assertThat(member.getUsername()).isEqualTo("user1");
    }

}
