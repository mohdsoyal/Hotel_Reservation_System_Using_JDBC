package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectvity {
	
	  public static DataBaseConnectvity obj =  null  ;
		 
	 
	  private 	DataBaseConnectvity() {
		  
	  }
	  
	   public Connection getConnection() {
		   Connection con = null  ;
		   try {
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelManagementSystem", "root", "Soyal123456789") ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		    return con  ;
		    
	   }
	  
	  
	  
	  public static DataBaseConnectvity getInstance() {
		  
		  if(obj == null) {
			  
			  obj =   new DataBaseConnectvity() ;
			  
		  }
		   return obj  ;
		 }
	  
		
		
		

	}

