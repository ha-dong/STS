package springbootboard.member.repositpry;

import java.lang.reflect.Member;

import org.springframework.data.repository.CrudRepository;

//insert, update, delete를 이것으로 다 해결한 것
public interface MemberRepository extends CrudRepository<Member, String>{
	
	
	
}
