package login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Action;

public class LogoutAction implements Action {

	public void execute(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		session.invalidate();
	}
}
