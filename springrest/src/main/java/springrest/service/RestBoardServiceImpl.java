package springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springrest.dao.RestBoardDao;
import springrest.domain.RestBoard;

@Service
public class RestBoardServiceImpl implements RestBoardService {
	
	@Autowired
	private RestBoardDao restBoardDao; 

	@Override
	public List<RestBoard> listRestBoard() throws Exception {
		return restBoardDao.listRestBoard();
	}

	@Override
	public RestBoard getRestBoard(int rbid) throws Exception {
		return restBoardDao.getRestBoard(rbid);
	}

	@Override
	public int insertRestBoard(RestBoard restBoard) throws Exception {
		return restBoardDao.insertRestBoard(restBoard);
	}

	@Override
	public int updateRestBoard(RestBoard restBoard) throws Exception {
		return restBoardDao.updateRestBoard(restBoard);
	}

	@Override
	public int deleteRestBoard(int rbid) throws Exception {
		return restBoardDao.deleteRestBoard(rbid);
	}

}
