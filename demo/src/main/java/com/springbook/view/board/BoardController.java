package com.springbook.view.board;

import java.util.HashMap;
import java.util.Map;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("board") // null 방지 
public class BoardController {
    // 검색 조건 목록 설정
    @ModelAttribute("conditionMap")
    public Map<String, String> searchConditionMap() {
        Map<String, String> conditionMap = new HashMap<String, String>();
        conditionMap.put("제목", "TITLE");
        conditionMap.put("내용", "CONTENT");
        return conditionMap;
    }


    // 글 등록
    @RequestMapping(value="/insertBoard.do")
    public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
        boardDAO.insertBoard(vo);
        return "redirect:getBoardList.do";

    }

    // 글 수정
    @RequestMapping("/updateBoard.do")
    public String updateBoard(@ModelAttribute("board") BoardVO vo, BoardDAO boardDAO) {
        System.out.println("작성자 이름 : " + vo.getWriter());
        boardDAO.updateBoard(vo);
        return "getBoardList.do";
    }

    // 글 삭제
    @RequestMapping("/deleteBoard.do")
    public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
        boardDAO.deleteBoard(vo);
        return "getBoardList.do";
    }

    // 글 상세 조회
    @RequestMapping("/getBoard.do")
    public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
        model.addAttribute("board", boardDAO.getBoard(vo)); // Model 정보 저장
        return "getBoard.jsp"; // View 이름 리턴
    }

    // 글 목록 검색
    @RequestMapping("/getBoardList.do")
    public String getBoardList(//@RequestParam(value="searchCondition", defaultValue="TITLE", required = false) String condition,
                               // @RequestParam(value="searchKeyword", defaultValue = "", required = false)String keyword, 
                               BoardVO vo, BoardDAO boardDAO, Model model) {
                                // @ReqeustParam을 사용하기 싫다면 Getter/Setter 메소드를 생성
        // System.out.println("검색 조건 : " + condition);
        // System.out.println("검색 단어 : " + keyword);
        model.addAttribute("boardList", boardDAO.getBoardList(vo)); // Model 정보 저장
        return "getBoardList.jsp"; // View 이름 리턴
    }
    
}
