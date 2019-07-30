package com.kobobook.www.admin;

import com.kobobook.www.admin.domain.Member;
import com.kobobook.www.admin.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApplicationTests {

    @Autowired
    private MemberService memberService;

    @Test
    public void contextLoads() {

    }

}
