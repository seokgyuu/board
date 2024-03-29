package login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import login.action.CheckAction;
import login.action.FormAction;
import login.action.LogoutAction;
import util.Action;
import util.ActionForward;


@WebServlet("*.do") // /MyHome/ ......  .do
public class Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Action action = null;
		ActionForward actionForward = null;
		
		String requestURL = request.getRequestURL().toString();
		
		//System.out.println(requestURL);
		
		int start = requestURL.lastIndexOf("/");
		int end = requestURL.lastIndexOf(".do");
		
		String path = requestURL.substring(start + 1, end);
		
		//System.out.println(path);
		
		RequestDispatcher rd = null;
		
		switch(path) {
		case "login":
			action = new FormAction();
			actionForward = new ActionForward("/login/form.jsp", false);
//			rd = request.getRequestDispatcher("/login/form.jsp");
//			rd.forward(request, response);
			break;
		case "check":
			action = new CheckAction();
			actionForward = new ActionForward("/login/result.jsp", false);
//			rd = request.getRequestDispatcher("/login/result.jsp");
//			rd.forward(request, response);
			break;
		case "logout":
			action = new LogoutAction();
			actionForward = new ActionForward("/MyHome/login/logout.jsp", true);
			//response.sendRedirect("/MyHome/login/logout.jsp");
			break;
		}
		
		if(action != null) {
			action.execute(request, response);
		}
		
		if(actionForward.isRedirect()) {
			response.sendRedirect(actionForward.getNextPath());
		}else {
			request.getRequestDispatcher(actionForward.getNextPath()).forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
}














