package org.jboss.tools.example.springmvc.repo;

import java.util.List;

import org.jboss.tools.example.springmvc.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    public static final String COLLECTION_NAME = "member";

    public Member findById(Long id) {
	return null;
    }

    public Member findByEmail(String email) {
	return null;
    }

    public List<Member> findAllOrderedByName() {
	return null;
    }

    public void register(Member member) {
	mongoTemplate.insert(member, COLLECTION_NAME);
	return;
    }
}
