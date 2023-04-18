package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.action.DeleteAction;
import member.action.JoinAction;
import member.action.UpdateAction;
import util.Action;
import util.ActionForward;

@WebServlet("*.me")
public class Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Action action = null;
		ActionForward actionForward = null;
		
		String requestURL = request.getRequestURL().toString();
		
		int s = requestURL.lastIndexOf("/") + 1;
		int e = requestURL.lastIndexOf(".me");
		
		String path = requestURL.substring(s,e);
		
		switch(path) {
		case "joinForm":
			actionForward = new ActionForward("/MyHome/member/joinForm.jsp", true);
			break;
		case "join":
			action = new JoinAction();
			actionForward = new ActionForward("/member/result.jsp", false);
			break;
		case "myPage":
			actionForward = new ActionForward("/MyHome/member/myPage.jsp", true);
			break;
		case "updateForm":
			actionForward = new ActionForward("/MyHome/member/updateForm.jsp", true);
			break;
		case "deleteForm":
			actionForward = new ActionForward("/MyHome/member/deleteForm.jsp", true);
			break;
		case "delete":
			action = new DeleteAction();
			actionForward = new ActionForward("/member/result.jsp", false);
			break;
		case "update":
			action = new UpdateAction();
			actionForward = new ActionForward("/member/result.jsp", false);
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
