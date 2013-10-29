package org.alterq.repo;

import java.util.List;

import org.alterq.domain.Member;

public interface MemberDao
{
    public Member findById(String id);

    public Member findByEmail(String email);

    public List<Member> findAllOrderedByName();

    public void register(Member member);
}
