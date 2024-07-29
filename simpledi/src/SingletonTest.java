//Singleton pattern(�̱��� ����)
//��ü�� �ϳ��� ������ �ʿ䰡 ���� �� ���
//1. private static ��� ���� ����
//2. private ������
//3. public���� ������� ��ü�� ��ȯ�ϴ� public �޼ҵ� ����

public class SingletonTest {
	private static SingletonTest obj = new SingletonTest();
	
	private SingletonTest() {
	}
	
	public static SingletonTest getInstance() {
			return obj;
	}
	
	
}//class
