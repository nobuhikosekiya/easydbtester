package easydbtester.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
	 public static void close(Connection conn, PreparedStatement ps, ResultSet rs)
	  {
	    if (rs != null) {
	      try
	      {
	        rs.close();
	      }
	      catch (SQLException e)
	      {
	        e.printStackTrace();
	      }
	    }
	    if (ps != null) {
	      try
	      {
	        ps.close();
	      }
	      catch (SQLException e)
	      {
	        e.printStackTrace();
	      }
	    }
	    if (conn != null) {
	      try
	      {
	        conn.close();
	      }
	      catch (SQLException e)
	      {
	        e.printStackTrace();
	      }
	    }
	  }
}
