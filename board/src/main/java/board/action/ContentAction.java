package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.boarddao.BoardDAO;
import board.boarddto.BoardDTO;
import util.Action;

public class ContentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String mode = request.getParameter("mode");
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		
		if(mode == null) {
			BoardDAO.getInstance().readCount(seq);
		}
		
		BoardDTO dto = BoardDAO.getInstance().getContent(seq);
		
		if(mode != null && mode.equals("update")) {
			String content = dto.getContent();
			dto.setContent(content.replaceAll("<br>", "\r\n"));
		}
		
		request.setAttribute("dto", dto);

	}

}
