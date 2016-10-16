package com.umair.todolist.dblayer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.umair.todolist.dblayer.ConnetDb;
public class TodoListDbUtils{
	public static int addTask(Task task) throws SQLException{ 
		int 		result 		= 0; 
		Connection 	connection 	= ConnetDb.getConnection();
		if(connection == null){
			System.out.println("connection is null while preparing, class : com.umair.todolist.dblayer , Method : addTask");	
			return result = 0;
		}
		String query = "INSERT INTO list (task) values ('"+task.getDescription()+"')";
		try (Statement st = connection.createStatement();){
			if (st.executeUpdate(query) > 0){
				query = "SELECT LAST_INSERT_ID();";
				try (ResultSet rs = st.executeQuery(query);){
					while (rs.next())
					{
						result = rs.getInt("LAST_INSERT_ID()");
					}
				}catch(Exception e){
						System.out.println( e.getMessage()+" : method : view ad is not prepared in pakage : com.create_agent.umair.DbLayer");
						return result = 0;
				}
			}
		}catch (SQLException e){
			System.out.println(e.getMessage()+"  Method : addTask not prepared in pakage : com.umair.todolist.dblayer");
			return result = 0;
		}finally{
			if(connection != null)
				connection.close();
		}
		return result;
	}
	public static List<Task> viewTask() throws SQLException{
		List<Task> result 	= new ArrayList<Task>();
		Connection connection = ConnetDb.getConnection();
		if(connection == null){
			System.out.println("connection is null while preparing in pakage : com.umair.todolist.dblayer");
			return result = null;	
		}
		String query = "SELECT * FROM list";
		try (Statement st = connection.createStatement();ResultSet rs = st.executeQuery(query);){
			while (rs.next())
			{
				Task task = new Task();
					task.setLId(rs.getInt("LId"));
					task.setDescription(rs.getString("Task"));
					task.setCompletionStatus(rs.getBoolean("status"));
				result.add(task);
			}
		}catch(Exception e){
				System.out.println( e.getMessage()+" : method : view ad is not prepared in pakage : com.create_agent.umair.DbLayer");
				return result = null;
		}finally{
			if(connection != null)
				connection.close();
		}
		return result;
	}
	public static Boolean deleteTask(Task task) throws SQLException{ 
		Boolean 	result 		= false; 
		Connection 	connection 	= ConnetDb.getConnection();
		if(connection == null){
			System.out.println("connection is null while preparing, class : com.umair.todolist.dblayer , Method : DeleteTask");	
			return result = false;
		}
		String query = "DELETE FROM list WHERE LId='"+task.getLId()+"'";
		try (Statement st = connection.createStatement();){
			if (st.executeUpdate(query) > 0){
				result = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"  Method : DeleteTask not prepared in pakage :  com.manage_location.hashim.DbLayer");
			return result = false;
		}finally{
			if(connection != null)
				connection.close();
		}
		return result;
	}
	public static Boolean deleteAll() throws SQLException{ 
		Boolean 	result 		= true; 
		Connection 	connection 	= ConnetDb.getConnection();
		if(connection == null){
			System.out.println("connection is null while preparing, class : com.umair.todolist.dblayer , Method : deleteAll");	
			return result = false;
		}
		String query = "TRUNCATE TABLE list";
		try (Statement st = connection.createStatement();){
			if (st.executeUpdate(query) > 0){
				result = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"  Method : deleteAll not prepared in pakage :  com.manage_location.hashim.DbLayer");
			return result = false;
		}finally{
			if(connection != null)
				connection.close();
		}
		return result;
	}
	public static Boolean taskStatus(Task task) throws SQLException{ 
		Boolean 	result 		= false; 
		Connection 	connection 	= ConnetDb.getConnection();
		if(connection == null){
			System.out.println("connection is null while preparing, class : com.umair.todolist.dblayer , Method : taskStatus");	
			return result = false;
		}
		String query = "UPDATE list SET status = "+task.isCompletionStatus()+" WHERE LId ="+task.getLId()+" ";
		try (Statement st = connection.createStatement();){
			if (st.executeUpdate(query) > 0){
				result = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"  Method : taskStatus not prepared in pakage :  com.manage_location.hashim.DbLayer");
			return result = false;
		}finally{
			if(connection != null)
				connection.close();
		}
		return result;
	}
}
