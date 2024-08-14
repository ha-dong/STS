package springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import oracle.jdbc.proxy.annotation.Post;
import springrest.domain.RestBoard;
import springrest.service.RestBoardService;

@RestController
public class RestBoardController {

	@Autowired
	private RestBoardService restBoardService;
	
	//GET /board 요청하면 RestBoard들을 담고 있는 List와 전송 헤더 데이터를 JSON을 변환해서 리턴
	//ResponseEntity: 전송되는 데이터(payload) + 전송 해더 데이터
	@GetMapping(value="/board")
	public ResponseEntity<List<RestBoard>> listRestBoard() throws Exception{
		List<RestBoard> restBoardList = restBoardService.listRestBoard();
		return ResponseEntity.ok(restBoardList);// statusCode 200(정상 응답)
	}
	
	// GET/board/1요청하면 RestBoard와 전송 헤더 데이터를 JSON으로 변환해서 리턴
	// 요청URL에 {}안에 변수명, @PathVariable과 매핑
	@GetMapping(value="/board/{rdid}")
	public ResponseEntity<RestBoard>getRestBoard(@PathVariable int rbid)throws Exception{
		RestBoard restBoard = restBoardService.getRestBoard(rbid);
		return ResponseEntity.ok(restBoard);
	}
	
	//POST /board 요청하면
	//producer-consumer패턴
	//:데이터를 생산하는 것(producer)과 데이터를 소비하는 것(consumer)를 분리하는 패턴
	// produces: 요청 데이터의 타입을 정의
	// consumes: 응답 데이터의 타입을 정의
	// @RequestBody: 요청바디에 있는 json객체를 RestBoard객체에 매핑
	@PostMapping(value="/board/", produces = MediaType.APPLICATION_JSON_VALUE)
		public int insertRestBoard(@RequestBody RestBoard restBoard)throws Exception{
		return restBoardService.insertRestBoard(restBoard);
	}
	
	@PutMapping(value="/board/", produces = MediaType.APPLICATION_JSON_VALUE)
		public int updateRestBoard(@RequestBody RestBoard restBoard)throws Exception{
		return restBoardService.updateRestBoard(restBoard);
	}
	
	@DeleteMapping(value="/board/{rdid}")
	public ResponseEntity<RestBoard>deleteRestBoard(@PathVariable int rbid)throws Exception{
		return restBoardService.deleteRestBoard(rbid);
	}
}//class