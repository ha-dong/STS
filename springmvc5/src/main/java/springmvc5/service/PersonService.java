package springmvc5.service;

import java.util.List;

import springmvc5.domain.Person;

public interface PersonService {
	
	public abstract List<Person> getPersonList();
	
	public abstract Person getPerson(int pid);
	
	public List<Person> insertPerson(Person person);
	public List<Person> updatePerson(Person person);
	public List<Person> deletePerson(int pid);
	
}
