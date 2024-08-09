package springrest.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springrest.domain.RestBoard;

@Repository
public class RestBoardDaoImpl implements RestBoardDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<RestBoard> listRestBoard() throws Exception {
		return sqlSession.selectList("RestBoard.listRestBoard");
	}

	@Override
	public RestBoard getRestBoard(int rbid) throws Exception {
		return sqlSession.selectOne("RestBoard.getRestBoard", rbid);
	}

	@Override
	public int insertRestBoard(RestBoard restBoard) throws Exception {
		return sqlSession.insert("RestBoard.insertRestBoard", restBoard);
	}

	@Override
	public int updateRestBoard(RestBoard restBoard) throws Exception {
		return sqlSession.update("RestBoard.updateRestBoard", restBoard);
	}

	@Override
	public int deleteRestBoard(int rbid) throws Exception {
		return sqlSession.delete("RestBoard.getRestBoard", rbid);
	}

}
