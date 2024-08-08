package springrestnew.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springrestnew.domain.RestBoard;

@Repository
public class RestBoardDaoImpl implements RestBoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<RestBoard> listRestBoard() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestBoard getRestBoard(int rbid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertRestBoard(RestBoard restBoard) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRestBoard(RestBoard restBoard) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRestBoard(int rbid) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
