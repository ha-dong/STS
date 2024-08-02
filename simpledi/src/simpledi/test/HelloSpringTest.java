package simpledi.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class HelloSpringTest {
		@RunWith(SpringJUnit4ClassRunner.class)
		@ContextConfiguration(locations = "classpath:conf/bean.mxl")
		public class HelloSpringTest{
			
			@Autowired
			private ApplicationContext context;
			
			@Before
			public void beforeTest() {
				System.out.println("test ���� ����");
			}
			
			@Test
			public void beanTest() {
				System.out.println("test ����");
				assertNull(new Object());
				assertFalse(false);
			}
			
			@After
			public void afterTest() {
				System.out.println("test ���Ŀ� ����!");
			}
		}
}//class