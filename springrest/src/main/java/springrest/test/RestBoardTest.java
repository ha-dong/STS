package springrest.test;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springrest.domain.RestBoard;
import springrest.service.RestBoardService;
import springrest.service.RestBoardServiceImpl;

public class RestBoardTest {
	
	private ApplicationContext context;
	
	private RestBoardService restBoardService;

	@Before
	public void init() {
		context = new GenericXmlApplicationContext("springrest/conf/beans.xml");
		restBoardService = (RestBoardServiceImpl)context.getBean("restBoardService");
	}
	
//	@Test
	public void insertRestBoard() throws Exception {
		RestBoard restBoard = new RestBoard(0, "작성자", "제목", "내용", null);
		int result = restBoardService.insertRestBoard(restBoard);
		assertEquals(result, 1);
	}
	
//	@Test
	public void selectRestBoard()throws Exception{
		List<RestBoard> restBoardList=restBoardService.listRestBoard();
		System.out.println(restBoardList);
	}
	
//	@Test
	public void getRestBoard()throws Exception{
		RestBoard restBoard = restBoardService.getRestBoard(3);
		System.out.println(restBoard);
	}
	
//	@Test
	public void updateRestBoard() throws Exception{
		RestBoard restBoard=new RestBoard(3, "작성자up", "제목up", "내용up", null);
		int result=restBoardService.updateRestBoard(restBoard);
		assertEquals(result, 1);
	}
	
//	@Test
	public void deleteRestBoard() throws Exception{
		int result=restBoardService.deleteRestBoard(5);
		assertEquals(result, 1);
	}

} // class
