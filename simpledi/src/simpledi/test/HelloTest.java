package simpledi.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import simpledi.bean.Hello;
import simpledi.service.Printer;

public class HelloTest {
	public static void main(String[] args) {
		//Maven�� ���̺귯�� �����ϴ� ����
		
		//BeanFanctory�� ��� ���� ApplicationContext�� ����
		//conf/beans.xml(�� ���� ����)�� �о ApplicationContext�� ����
		ApplicationContext context =
				new GenericXmlApplicationContext("conf/beans.xml");
		
		Hello hello=(Hello)context.getBean("hello");//hello��� id�� ���� ���� ã�� hello ��ü�� ����
		System.out.println(hello);
		
		Hello hello2=(Hello)context.getBean("hello");
		//�� ��ü�� ���� ��ü, Ư���� ������ ���� ������ �������� ��ü��
		//��Ŭ��(Ŭ�������� ��ü�� �ϳ��� �����ϴ� ����)���� ����
		//�� ������ scope="prototype"���� �ϸ� ��ü �����ø��� �ٸ� ��ü�� ������ ��
		System.out.println(hello==hello2);
		
		//stringPrinter��� id�� ���� ���� ã�Ƽ� StringPrinter ��ü�� ����
		Printer stringPrinter=(Printer)context.getBean("stringPrinter");
		stringPrinter.print("stringPrinter");
		System.out.println(stringPrinter.toString());
		
		//consolePrinter��� id�� ���� ���� ã�Ƽ� consolePrinter ��ü�� ����
		Printer consolePrinter=(Printer)context.getBean("consolePrinter");
		consolePrinter.print("consolePrinter");
		
		
	}//main
}//class