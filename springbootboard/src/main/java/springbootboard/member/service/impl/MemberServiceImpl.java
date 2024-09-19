package springbootboard.member.service.impl;

import java.lang.reflect.Member;
import java.util.Optional;

import org.hibernate.metamodel.internal.MemberResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootboard.member.repositpry.MemberRepository;
import springbootboard.member.service.Memberservice;

@Service
public class MemberServiceImpl implements Memberservice{
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public Member getMember(Member member) {
		//optional은 null방지용 클래스
		Optional<Member> findMember=
				memberRepo.findById(member.getId());
		if(findMember.isPresent()) {
			return findMember.get();
		}else {
			return null;
		}
	}  
	
	
}//class
