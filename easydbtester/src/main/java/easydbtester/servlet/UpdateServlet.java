package easydbtester.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import easydbtester.dao.GeneralDao;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean useDefaultDatasource = false;
		
		PrintWriter out = response.getWriter();
		
		String useDefaultDatasourceString = request.getParameter("useDefaultDatasource");
		useDefaultDatasource = Boolean.parseBoolean(useDefaultDatasourceString);
		
		String datasource = null;
		if (useDefaultDatasource) {
			datasource = "java:comp/DefaultDataSource";
		} else {
			datasource = request.getParameter("datasource");
			if (datasource == null) {
				System.err.println("parameter[datasource] is not set");
				throw new ServletException("parameter[datasource] is not set");
				
			}
		}
		out.println("datasource: " + datasource);
		String sql = request.getParameter("sql");
		if (sql == null) {
			System.err.println("parameter[sql] is not set");
			throw new ServletException("parameter[sql] is not set");
		}
		String displayerrorStr = request.getParameter("displayerror");
		boolean displayerror = false;
		if (displayerrorStr != null && !displayerrorStr.equals("")) 
			displayerror = Boolean.parseBoolean(displayerrorStr);
		
		out.println("sql: " + sql);
		
		int loop = 1;
		String loopStr = request.getParameter("loop");
		if (loopStr != null) {
			loop = Integer.parseInt(loopStr);
			out.println("loop: " + loopStr);
		}
		
		GeneralDao dao = new GeneralDao();
		InitialContext ctx;
		Connection conn = null;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(datasource);
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			for (int i = 0; i < loop; i++) {
				int count = dao.update(conn, sql);
				out.println("update count: " + count);
			}
			conn.commit();
			
			out.println("success");
		} catch (NamingException e) {
			e.printStackTrace();
			
			if (displayerror) {
				out.println("failure");
				out.println(e.getMessage());
			} else {
				throw new ServletException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
			if (displayerror) {
				out.println("failure");
				out.println(e.getMessage());
			} else {
				throw new ServletException(e);
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

	}

}
