package com.umair.todolist.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.umair.todolist.dblayer.Task;
import com.umair.todolist.dblayer.TodoListDbUtils;
@WebServlet(description = "this servlet will add new task in database", urlPatterns = { "/AddTaskPath" })
public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String 		description			= request.getParameter("description");
		String 		htmlReturn			= "";
		int 		taskAdded 			= 0;
		List<Task> 	result 				= null;
		int 		id 					= 0;
		boolean 	completionStatus 	= false;
		Task		task				= new Task();
		String 		checkedStatus       = "";
		String  	taskClass			= "";
		task.setDescription(description);
		try {
			taskAdded = TodoListDbUtils.addTask(task);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(taskAdded > 0){
			htmlReturn = "<table>"
							+ "<col width='1300'>"
							+ "<col width='100'>"
							+ "<tr>"
								+ "<td><input type='text' id='task' class='input-field' name='field1' placeholder='My New Task' /></td>"
								+ "<td><input type='submit' value='Add' class='submit' id='addTask'  onClick='addTask();' /></td>"
							+ "</tr>";
							try{
								result  = TodoListDbUtils.viewTask();
							}
							catch(SQLException e){
							}
							for (Task taskResult : result){
								description 		= taskResult.getDescription();
								id 					= taskResult.getLId();
								completionStatus 	= taskResult.isCompletionStatus();
								if(completionStatus){
									checkedStatus 	= "Checked";
									taskClass 		= "checked";	
								}else{
									checkedStatus 	= "";
									taskClass 		= "";
								}
				htmlReturn += "<tr>"
								+ "<td><span id='task"+id+"' class='"+taskClass+"'>"+description+"</span></td>"
								+ "<td><input type='button' value='X' class='del'  onClick='toDelete("+id+");' />|<input id='check"+id+"' type='checkbox' onClick='taskChecked("+id+");' value='x' "+checkedStatus+" /></form></td>"
							+ "</tr>";
							}
				htmlReturn += "<tr>"
								+ "<td colspan='2'><u><center><input type='button' class='del' value='Clear All'   onClick='clearAll();'/></center></u></td>"
							+ "</tr>"
					+ "</table>";
		}
		response.getWriter().write(htmlReturn);
	}
}
