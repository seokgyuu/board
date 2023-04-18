package login.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Action;

public class FormAction implements Action {

	public void execute(HttpServletRequest request,HttpServletResponse response) {
		
		//��Ű���� �о�ͼ� ���̵� �ѱ��...
		String id = request.getParameter("id");

		boolean check = false;
		
		if(id == null){
			
			Cookie[] cks = request.getCookies();
			
			if(cks != null){
				for(Cookie ck : cks){
					if(ck.getName().equals("ckid")){
						id = ck.getValue();
						check = true;
						break;
					}
					
				}
			}

		}
		request.setAttribute("check", check);
		request.setAttribute("id", id);
		
	}
	
}
