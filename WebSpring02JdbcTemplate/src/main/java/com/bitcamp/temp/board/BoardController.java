package com.bitcamp.temp.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BoardController {
	@RequestMapping("/boardList")
	public ModelAndView boardList() {
		//레코드 선택
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list= dao.selectAll();
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/boardList");
		mav.addObject("list", list);
		return mav;
	}
	//글쓰기이동
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value="/boardWriteOk", method=RequestMethod.POST)
	public ModelAndView boardWriteOk(BoardVO vo, HttpServletRequest request) {
		//아이디
		HttpSession session = request.getSession();
		vo.setUserid((String)session.getAttribute("logId"));
		//ip
		vo.setIp(request.getRemoteAddr());
		BoardDAO dao = new BoardDAO();
		int result = dao.insertBoard(vo);
		ModelAndView mav = new ModelAndView();
		if(result>0) {
			//레코드가 추가
			mav.setViewName("redirect:boardList");
		}else {
			mav.addObject("msg", "글등록이 실패하였습니다.");
			mav.setViewName("board/resultPage");
		}
		return mav;
	}
	@RequestMapping("/boardView")
	public ModelAndView boardView(@RequestParam("no") int no) { //가져올 데이터 이름 /데이터 타입/ 가져온데이터를 담을 변수
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.selectBoard(no);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", vo);
		mav.setViewName("board/boardView");
		
		return mav;
	}
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(@RequestParam("no") int no) {
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.selectEditBoard(no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("board/boardEdit");
		return mav;
	}
	@RequestMapping(value="/boardEditOk", method=RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo, HttpSession session) {
		vo.setUserid((String)session.getAttribute("logId"));
		BoardDAO dao = new BoardDAO();
		int result = dao.updateBoard(vo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", vo.getNo());
		mav.addObject("result", result);
		
		mav.setViewName("board/editResult");
		return mav;
	}
	@RequestMapping("/boardDelete")
	public ModelAndView boardDelete(@RequestParam("no") int no, HttpSession session) {
		BoardDAO dao = new BoardDAO();
		int cnt = dao.boardDelete(no, (String)session.getAttribute("logId"));
		ModelAndView mav = new ModelAndView();
		if(cnt>0) {
			mav.setViewName("redirect:boardList");
		}else {
			mav.addObject("no", no);
			mav.setViewName("redirect:boardView");
		}
		
		return mav;
	}
}
