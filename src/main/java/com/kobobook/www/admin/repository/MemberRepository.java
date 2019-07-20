package com.kobobook.www.admin.repository;

import com.kobobook.www.admin.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findUserNameById(int id);

    Member findByOauthId(String oauthId);

    Member findByUserEmail(String userEmail);
}
