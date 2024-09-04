package springmvc5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import springmvc5.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	public static List<Person> personList = new ArrayList<Person>();
	
	static {
		personList.add(new Person(1, "홍길동", 20));
		personList.add(new Person(2, "강감찬", 30));
		personList.add(new Person(3, "이순신", 40));
	}

	@Override
	public List<Person> getPersonList() {
		return personList;
	}

	@Override
	public Person getPerson(int pid) {
		return personList.get(pid-1);
	}

	@Override
	public List<Person> insertPerson(Person person) {
		personList.add(person);
		return personList;
	}

	@Override
	public List<Person> updatePerson(Person person) {
		for(Person each:personList) {
			if(each.getPid()==person.getPid()) {
				personList.remove(each);
				personList.add(person);
			}
		}
		return personList;
	}

	@Override
	public List<Person> deletePerson(int pid) {
		for (Person each:personList) {
			if(each.getPid()==pid) {
				List<Person>removePersonList = new ArrayList<Person>();
				removePersonList.add(each);
				personList.removeAll(removePersonList);
			}
		}
		return personList;
	}

}//class
