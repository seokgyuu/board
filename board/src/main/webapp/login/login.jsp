<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//쿠키파일 읽어와서 아이디 넘기기...
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
	
	pageContext.forward("/login/form.jsp");
	
%>