package com.umair.todolist.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.umair.todolist.dblayer.Task;
import com.umair.todolist.dblayer.TodoListDbUtils;
@WebServlet("/TaskCompletedPath")
public class TaskCompleted extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean 	completionStatus	= Boolean.parseBoolean(request.getParameter("status"));
		int 		LId					= Integer.parseInt(request.getParameter("LId"));
		Task		task				= new Task();
		boolean 	statusUpdated		= false;
		task.setCompletionStatus(completionStatus);
		task.setLId(LId);
		try {
			statusUpdated = TodoListDbUtils.taskStatus(task);
		} catch (Exception e) {
			
		}
		
		if(statusUpdated)
			response.getWriter().write(Boolean.toString(statusUpdated));
			
	}

}
