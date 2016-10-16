package com.umair.todolist.servlet;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.umair.todolist.dblayer.Task;
import com.umair.todolist.dblayer.TodoListDbUtils;
@WebServlet("/DeleteTaskPath")
public class DeleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int 	LId			= Integer.parseInt(request.getParameter("LId"));
		boolean	deleted		= false;
		Task	task		= new Task();
		String	message		= "<div style='background-color:#FFBABA;border:1px solid #D8000C;padding:7pt;width:400pt;margin-left: -30pt'>"
								+ "<b>Error! Faild to perform action</b>"
							+ "</div>";
		HttpSession session = request.getSession(true);
		task.setLId(LId);
		try {
			deleted = TodoListDbUtils.deleteTask(task);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!deleted)	
			session.setAttribute("message",message);
		response.sendRedirect("index.jsp");
	}

}
