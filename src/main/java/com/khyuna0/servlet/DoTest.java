package com.khyuna0.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class DoTest
 */
@WebServlet("*.do")
public class DoTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoTest() {
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
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI(); // 클라이언트가 요청한 URI 저장
		// uri -> http://localhost:8888/jsp_webServlet_20250807/login.do
		// 위 uri를 ~~/login.do로 바꾸기
		
		String con = request.getContextPath();
		// con - login.jsp 를 제외한 http://localhost:8888/jsp_webServlet_20250807/ 부분 저장
		String command = uri.substring(con.length());
		// command -> login.do
		
		String viewPage = "";
		if (command.equals("/login.do")) {
			viewPage = "login.jsp";
			
			
		} else if (command.equals("/loginOk.do")) {
			String mid = request.getParameter("mid");
			String mpw = request.getParameter("mpw");
			//System.out.println("login.jsp에서 넘겨받은 아이디 값 : " + mid);
			//System.out.println("login.jsp에서 넘겨받은 비밀번호 값 : " + mpw);
			
			if(mid.equals("tiger") && mpw.equals("12345")) { // 참이면 로그인 성공 처리
				// 세션에 아이디 값 저장 -> 로그인 성공
				HttpSession session = request.getSession(); // 세션 생성하기
				session.setAttribute("sid", mid); // 세션에 아이디 값 올리기
				//response.sendRedirect("welcome.jsp"); // 로그인 성공 페이지로 이동
			} else { //로그인 실패
				//response.sendRedirect("loginFail.jsp"); // 로그인 실패 페이지로 이동
				//request.setAttribute("failId", mid); // 로그인 실패한 아이디
				request.setAttribute("errorMsg", "아이디 또는 비밀번호가 잘못되었습니다. 다시 확인하세요.");
				
			}
			viewPage = "loginOk.jsp";
		} else if (command.equals("/welcome.do")) {
			viewPage = "welcome.jsp";
			
		}
		//request.getRequestDispatcher("login.jsp").forward(request, response);
		
		// viewPage 값으로 request 객체를 포함해서 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
