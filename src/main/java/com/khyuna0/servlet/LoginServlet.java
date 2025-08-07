package com.khyuna0.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginOk")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("로그인 호출");
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		//System.out.println("login.jsp에서 넘겨받은 아이디 값 : " + mid);
		//System.out.println("login.jsp에서 넘겨받은 비밀번호 값 : " + mpw);
		
		if(mid.equals("tiger") && mpw.equals("12345")) { // 참이면 로그인 성공 처리
			// 세션에 아이디 값 저장 -> 로그인 성공
			HttpSession session = request.getSession(); // 세션 생성하기
			session.setAttribute("sid", mid); // 세션에 아이디 값 올리기
			response.sendRedirect("welcome.jsp"); // 로그인 성공 페이지로 이동
		} else { //로그인 실패
			//response.sendRedirect("loginFail.jsp"); // 로그인 실패 페이지로 이동
			//request.setAttribute("failId", mid); // 로그인 실패한 아이디
			request.setAttribute("errorMsg", "아이디 또는 비밀번호가 잘못되었습니다. 다시 확인하세요.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
