package springrestnew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springrestnew.dao.RestBoardDao;
import springrestnew.domain.RestBoard;


@Service
public class RestBoardServiceImpl implements RestBoardService {

   @Autowired
   private RestBoardDao restBoradDao;
   
   @Override
   public List<RestBoard> listRestBoard() throws Exception {
      return restBoradDao.listRestBoard();
   }

   @Override
   public RestBoard getRestBoard(int rbid) throws Exception {
      return restBoradDao.getRestBoard(rbid);
   }

   @Override
   public int insertRestBoard(RestBoard restBoard) throws Exception {
      return restBoradDao.insertRestBoard(restBoard);
   }

   @Override
   public int updateRestBoard(RestBoard restBoard) throws Exception {
      return restBoradDao.updateRestBoard(restBoard);
   }

   @Override
   public int deleteRestBoard(int rbid) throws Exception {
      return restBoradDao.deleteRestBoard(rbid);
   }

}

