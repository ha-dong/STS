package springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import springrest.domain.RestBoard;
import springrest.service.RestBoardService;

@RestController
public class RestBoardController {

	@Autowired
	private RestBoardService restBoardService; 
	
	@PostMapping(value="/restBoard")
	public int insertRestBoard(RestBoard restBoard) throws Exception {
		return restBoardService.insertRestBoard(restBoard);
	}
	
}














