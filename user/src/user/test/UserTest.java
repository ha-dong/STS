package user.test;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserTest {
	
	private ApplicationContext context;
	
	@Before
	public void init() {
		context = new GenericXmlApplicationContext("conf/beans.xml");
	}
	
	@Test
	public void dataSourceTest() {
		
		DataSource ds = (DataSource)context.getBean("dataSource");
		
		try {
			Connection conn = ds.getConnection();
			System.out.println(conn);
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}//class