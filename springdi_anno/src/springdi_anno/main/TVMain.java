package springdi_anno.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springdi_anno.domain.TV;

public class TVMain {
	
	//bean을 생성/관리하는 빈 컨테이너
	private ApplicationContext context; 
	
	public TVMain() {
		context = new GenericXmlApplicationContext("/springdi_anno/conf/applicationContext.xml");
	}
	
	public static void main(String[] args) {
		
		TVMain tvmain = new TVMain();
		
		TV tv = (TV)tvmain.context.getBean("tv");
		System.out.println(tv);
		
		tv.getSpeaker().printName();
	}//main
	
}//class
	