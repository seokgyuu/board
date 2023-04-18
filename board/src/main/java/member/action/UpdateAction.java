package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.memberdao.MemberDAO;
import member.memberdto.MemberDTO;
import util.Action;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		MemberDTO login = (MemberDTO)request.getSession().getAttribute("login");
		
		MemberDTO dto = new MemberDTO();
		dto.setNo(login.getNo());
		dto.setEmail(request.getParameter("email"));
		dto.setTel1(request.getParameter("tel1"));
		dto.setTel2(request.getParameter("tel2"));
		dto.setTel3(request.getParameter("tel3"));
		
		
		boolean check = MemberDAO.getInstance().updateMember(dto);
		
		
		String msg = null;
		String url = null;
		
		if(check){
			login.setEmail(request.getParameter("email"));
			login.setTel1(request.getParameter("tel1"));
			login.setTel2(request.getParameter("tel2"));
			login.setTel3(request.getParameter("tel3"));
			msg = "수정완료!!MyPage로...";
			url = "/MyHome/member/myPage.jsp";
		}else{
			msg = "수정실패!!이전페이지로...";
			url = "/MyHome/member/updateForm.jsp";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
	}
}
