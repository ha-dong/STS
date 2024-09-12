package springbootboard.member.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Member {
	
	@Id
	private String id;
	private String password;
	private String name;
	private String role;
	
	
}//class
