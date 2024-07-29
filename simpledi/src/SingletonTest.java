//Singleton pattern(싱글턴 패턴)
//객체를 하나만 생성할 필요가 있을 때 사용
//1. private static 멤버 변수 생성
//2. private 생성자
//3. public으로 만들어진 객체를 반환하는 public 메소드 생성

public class SingletonTest {
	private static SingletonTest obj = new SingletonTest();
	
	private SingletonTest() {
	}
	
	public static SingletonTest getInstance() {
			return obj;
	}
	
	
}//class
