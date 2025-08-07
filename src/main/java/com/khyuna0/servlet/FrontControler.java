package com.khyuna0.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.khyuna0.DTO.BoardDto;
import com.khyuna0.DTO.MemberDto;

/**
 * Servlet implementation class FrontControler
 */
@WebServlet("*.do")
public class FrontControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	private void doProcess (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// request 객체 안에는 mid, mpw 값
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();  // 사용자가 요청한 URI
		// URL과의 차이 -> URL은 스프링버퍼 객체로 받아야됨
		//System.out.println(uri); // /jsp_webServlet_20250807/loginOk.do
		String con = request.getContextPath(); // 컨텍스트 패스 가져오기 // /jsp_webServlet_20250807/
		
		String  command = uri.substring(con.length()); // uri 에서 /loginOk.do 문자열만 가져옴
		System.out.println("클라이언트 요청 : " + command); 
		String viewPage = ""; // 실제 클라이언트에게 전송할  jsp 파일이 저장될 변수
		HttpSession session = null; // 세션 전역변수로 선언하기
		
		if (command.equals("/loginOk.do")) {
			String mid = request.getParameter("mid");
			String mpw = request.getParameter("mpw");
			
			// 로그인 성공
			if(mid.equals("tiger") && mpw.equals("1234")) { 
				session = request.getSession();
				session.setAttribute("sid", mid ); //세샨에 id 값 올리기 ->로그인 상태에서 변경
				//response.sendRedirect()
				request.setAttribute("mid", mid);
				viewPage = "welcome.jsp";
			// 로그인 실패	
			} else { 
				System.out.println("로그인 실패");
				// errorMsg에 값 전달하기
				request.setAttribute("errorMsg", "아이디 또는 비밀번호가 잘못되었습니다.");
				viewPage = "login.jsp";
			} 
		} else if (command.equals("/login.do")) {
			viewPage = "login.jsp";
		} else if (command.equals("/welcome.do")) {
			session = request.getSession();
			request.setAttribute("mid", (String)session.getAttribute("sid"));
			viewPage = "welcome.jsp";
		} else if (command.equals("/logout.do")) {
			session = request.getSession();
			session.invalidate();
			request.setAttribute("errorMsg", "로그인하시려면 아이디와 비밀번호를 다시 입력해주세요.");
			viewPage = "login.jsp";
		} else if (command.equals("/freeboard.do")) { // 게시판 보여달라는 요청
			// 더미데이터 게시판
			List<BoardDto> boardList = new ArrayList<BoardDto>();
			
			boardList.add(new BoardDto("안녕하세요, 오늘은 목요일 입니다.","홍길동","2025-08-07"));
			boardList.add(new BoardDto("안녕하세요, 오늘은 8월 7일 입니다.","김유신","2025-08-07"));
			boardList.add(new BoardDto("안녕하세요, 지금은 3시 30분 입니다.","이순신","2025-08-07"));
			boardList.add(new BoardDto("안녕하세요, 지금은 8월입니다.","강감찬","2025-08-07"));
			boardList.add(new BoardDto("안녕하세요, 오늘 날씨는 맑음입니다.","홍길동","2025-08-07"));
			
			request.setAttribute("boardList", boardList); // 리퀘스트 객체에 게시판 글 목록 싣기
			
			viewPage = "boardList.jsp";
		} else if (command.equals("/memberList.do")) { //회원 목록 조회 요청 
			List<MemberDto> memberList = new ArrayList<MemberDto>();
			
			memberList.add(new MemberDto("tiger","홍길동",17,"2025-03-01"));
			memberList.add(new MemberDto("lion","김유신",19,"2025-04-09"));
			memberList.add(new MemberDto("blackcat","강감찬",20,"2025-04-11"));
			memberList.add(new MemberDto("whitedog","이순신",27,"2025-05-05"));
			memberList.add(new MemberDto("redtiger","이몽룡",31,"2025-06-20"));
			
			request.setAttribute("memberList", memberList);
			viewPage = "memberList.jsp";
		}
		
		
		//response.sendRedirect(viewPage);
		// viewPage에 저장된 jsp 페이지로 이동시킬 때 request 객체를 포함해서 이동시키는 방법
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
}
