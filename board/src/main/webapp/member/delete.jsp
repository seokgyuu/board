<%@page import="member.memberdao.MemberDAO"%>
<%@page import="member.memberdto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	MemberDTO dto = (MemberDTO)session.getAttribute("login");
	String password = request.getParameter("password");
	
	String msg = "";
	String url = "";
	
	if(!dto.getPassword().equals(password)){
		msg = "비밀번호가 틀렸습니다.!!이전페이지로....";
		url = "/MyHome/member/deleteForm.jsp";
	}else{
		MemberDAO dao = MemberDAO.getInstance();
		boolean check = dao.deleteMember(dto.getNo());
		if(check){
			session.invalidate();
			msg = "탈퇴성공!!!인덱스페이지로...";
			url = "/MyHome/";
		}else{
			msg = "탈퇴실패!!잠시후에 다시 시도하십시오..이전페이지로....";
			url = "/MyHome/member/deleteForm.jsp";
		}
	}
	
	request.setAttribute("msg", msg);
	request.setAttribute("url", url);
	
	pageContext.forward("/member/result.jsp");	
%>












