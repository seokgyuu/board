package board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.boarddao.BoardDAO;
import board.boarddto.BoardDTO;
import member.memberdto.MemberDTO;
import util.Action;

public class WriteAction implements Action{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		MemberDTO member = (MemberDTO)request.getSession().getAttribute("login");
		
		//MultipartRequest ��ü�� �̿��Ͽ� ���� ���ε�
		
		String realPath = request.getServletContext().getRealPath("/storage");
		
		//MultipartRequest ��ü ����
		MultipartRequest mr = new MultipartRequest(request, realPath, 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		
		//1. request ��ü
		//2. ������ ������ ���� ��� - �������
		//3. �ִ� ���� ũ�� - byte������ �ۼ�...
		//4. ���ڵ� Ÿ�� - UTF-8
		//5. ���� ����� ���� ���� Ŭ���� - ���û���.
		
//		String id = member.getId();
//		String name = member.getName();
//		String title = request.getParameter("title");
//		String content = request.getParameter("content").replaceAll("\r\n", "<br>");
	
		
		String id = member.getId();
		String name = member.getName();
		String title = mr.getParameter("title");
		String content = mr.getParameter("content").replaceAll("\r\n", "<br>");
		
		String filename = mr.getFilesystemName("filename");
		
		//mr.getParameter("filename") - null
		System.out.println(mr.getParameter("filename"));
		//mr.getOriginalFileName("filename") - ���� ���ε�� ���ϸ�
		System.out.println(mr.getOriginalFileName("filename"));
		//mr.getFilesystemName("filename") - ���� ����� ���ϸ�
		System.out.println(mr.getFilesystemName("filename"));
		
		BoardDTO dto = new BoardDTO(id, name, title, content, filename);
		
		
		
		boolean check = BoardDAO.getInstance().write(dto);
		
		
		
		request.setAttribute("check", check);
		request.setAttribute("status", "write");
		request.setAttribute("url", "/MyHome/board/list.brd");
		
	}

}
