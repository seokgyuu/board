package login.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.memberdao.MemberDAO;
import member.memberdto.MemberDTO;
import util.Action;

public class CheckAction implements Action {

	public void execute(HttpServletRequest request,HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		MemberDTO dto = new MemberDTO();
		
		dto.setId(id);
		dto.setPassword(password);
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dto = dao.checkLogin(dto);
		
		boolean check = false;
		String msg = null;
		
		if(dto != null){
			check = true;
			msg = dto.getName() + "���� �α��� �ϼ̽��ϴ�.";
			
			HttpSession session = request.getSession();
			//�α��� ó��
			session.setAttribute("login", dto);
			
			//��Ű���� ���� ����...
			
			//���̵� ����ϱ� üũ ����
			
			String ckid = request.getParameter("ckid");
			
			Cookie ck = null;
			
			//��Ű���� �о����...
			Cookie[] cks = request.getCookies();
			
			//���� ���� �˻�
			if(cks != null){
				for(Cookie c : cks){
					if(c.getName().equals("ckid")){
						ck = c;
						break;
					}
				}
			}
			
			if(ckid != null){
				if(ck== null){
					//��Ű��ü ����
					ck = new Cookie("ckid",dto.getId());
					
					//��ȿ�ð� ����
					ck.setMaxAge(60*60*24);
					
					//Ŭ�󸮾�Ʈ���� ��Ű���� ����..
					response.addCookie(ck);
				}else{
					if(!ck.getValue().equals(id)){
						ck.setValue(id);
						response.addCookie(ck);
					}
				}
			}else{
				if(ck != null){
					if(ck.getValue().equals(id)){
						//��Ű���� ����
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
				}
			}
			
		}else{
			msg = "���̵� Ȥ�� ��й�ȣ�� �߸��Ǿ����ϴ�.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("check", check);
		
	}
}
