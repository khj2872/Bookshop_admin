package com.kobobook.www.admin;

import com.kobobook.www.admin.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApplicationTests {

//    @Autowired
//    private MemberRepository memberRepository;

    @Test
    public void contextLoads() {
//        //given
//        memberRepository.save(Member.builder()
//                .username("user1")
//                .password("test")
//                .userEmail("testEmail")
//                .role(Role.ROLE_USER)
//                .build());
//        //when
//        List<Member> memberList = memberRepository.findAll();
//
//        //then
//        Member member = memberList.get(0);
//        assertThat(member.getUsername()).isEqualTo("user1");
    }

}
