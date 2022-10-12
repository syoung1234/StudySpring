package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class GetBoardController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("글 상세 조회 처리");

        // 1. 검색할 게시글 번호 추출(검색 기능은 나중에 구현)
        String seq = request.getParameter("seq");

        // 2. DB 연동 처리
        BoardVO vo = new BoardVO();
        vo.setSeq(Integer.parseInt(seq));

        BoardDAO boardDAO = new BoardDAO();
        BoardVO board = boardDAO.getBoard(vo);

        // 3. 응답 화면 구성
        ModelAndView mav = new ModelAndView();
        mav.addObject("board", board); // Model 정보 저장
        mav.setViewName("getBoard"); // View 정보 저장
        return mav;
    }
    
}
