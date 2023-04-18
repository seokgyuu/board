<%@page import="member.memberdao.MemberDAO"%>
<%@page import="member.memberdto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	MemberDTO login = (MemberDTO)session.getAttribute("login");
	//dto.setEmail(request.getParameter("email"));
	//dto.setTel1(request.getParameter("tel1"));
	//dto.setTel2(request.getParameter("tel2"));
	//dto.setTel3(request.getParameter("tel3"));
	
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
		//session.setAttribute("login", login);
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
	
	pageContext.forward("/member/result.jsp");
	
%>

















