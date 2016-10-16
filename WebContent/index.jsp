<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
import = "com.umair.todolist.dblayer.Task"
import = "com.umair.todolist.dblayer.TodoListDbUtils"
import = "java.util.List"
import = "java.sql.SQLException"
%>
<%
String alert = "";
if (session.getAttribute("message") != null){
	alert = (String)session.getAttribute("message");
	session.removeAttribute("message");
	 }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="http://dinbror.dk/bpopup/assets/jquery.bpopup-0.9.4.min.js"></script>
		<script src="http://dinbror.dk/bpopup/assets/jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="javascript/helper.js"></script>
		<title>To Do List</title>
	</head>
	<body>
		<center>
			<dir id='alert'><%=alert %></dir>
			<div class="form">
				<div class="form-heading" >To Do List</div>
				<div id='list' >
					<table>
						<col width="1300">
						<col width="100">
						<tr>
							<td><input type="text" id='task' class="input-field" name="field1" placeholder='My New Task'/></td>
							<td><input type="submit" class='submit' value="Add"  id="addTask"  onClick='addTask();'/></td>
						</tr>
						<%
						String 	description 		= "";
						int 	id 					= 0;
						boolean completionStatus 	= false;
						String 	checkedStatus       = "";
						String  taskClass			= "";
						List<Task> result 			= null;
						try{
							result  = TodoListDbUtils.viewTask();
						}
						catch(SQLException e){
						}
						for (Task task : result){
							description 		= task.getDescription();
							id 					= task.getLId();
							completionStatus 	= task.isCompletionStatus();
							if(completionStatus){
								checkedStatus 	= "Checked";
								taskClass 		= "checked";	
							}else{
								checkedStatus 	= "";
								taskClass 		= "";
							}
						%>
						<tr>
							<td><span id='task<%=id%>' class='<%=taskClass%>'><%=description %></span></td>
							<td><input type="button" value="X" class='del'  onClick='toDelete("<%=id %>");' />|<input id='check<%=id%>' type="checkbox" onClick='taskChecked("<%=id %>");' value="x" <%=checkedStatus %> /></form></td>
						</tr>	
						<%
						}
						%>
						<tr>
							<td colspan="2"><u><center><input type="button" class='del' value="Clear All"   onClick='clearAll();'/></center></u></td>
						</tr>
					</table>
				</div>
			</div>
		</center>
	</body>
	<div id="deleteSingle" class='a' style="display:none; background:#F6F7F8; float:left;padding:6pt;">
	<div class='pop_header'></div>
	   	<form method='post' action='DeleteTaskPath'>
	   		<table>
	   		<tr>
	   			<td>This removes the item from you task list</td>
	   			<input type='text' id='deleteTask' hidden value='<%=id %>' name='LId'>
	   		</tr>
	   		<tr>
	   			<td colspan='4'><br><center><input type='submit' class='submit' value='Remove It!'></center></td>
	   		</tr>
	   		</table>
	   </form>
	 </div>
	 <div id="deleteAll" class='a' style="display:none; background:#F6F7F8; float:left;padding:6pt;">
	<div class='pop_header'></div>
	   	<form method='post' action='DeleteAllPath'>
	   		<table>
	   		<tr>
	   			<td>This removes all item from you task list</td>
	   		</tr>
	   		<tr>
	   			<td colspan='4'><br><center><input type='submit' class='submit' value='Clear All!'></center></td>
	   		</tr>
	   		</table>
	   </form>
	 </div>
</html>