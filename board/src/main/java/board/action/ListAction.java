package board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.boarddao.BoardDAO;
import board.boarddto.BoardDTO;
import util.Action;

public class ListAction implements Action{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int start = 0;
		
		if(request.getParameter("start") != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}
			
		
		
		BoardDAO dao =  BoardDAO.getInstance();
		
		List<BoardDTO> list = dao.getList(start);
		int total = dao.getTotal();
		int nowPage = start / 5 + 1;
		int totalPage = total % 5 == 0 ? total / 5 : total / 5 + 1;
		
		request.setAttribute("start", start);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("total", total);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
				
	}

}
