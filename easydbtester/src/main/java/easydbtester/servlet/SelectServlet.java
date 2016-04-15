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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import easydbtester.dao.GeneralDao;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
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
		out.println("sql: " + sql);
		
		String displayerrorStr = request.getParameter("displayerror");
		boolean displayerror = false;
		if (displayerrorStr != null && !displayerrorStr.equals("")) 
			displayerror = Boolean.parseBoolean(displayerrorStr);
		
		int loop = 1;
		String loopStr = request.getParameter("loop");
		if (loopStr != null) {
			loop = Integer.parseInt(loopStr);
			out.println("loop: " + loopStr);
		}
		
		out.println("session id is valid? " + request.isRequestedSessionIdValid());
		HttpSession session = request.getSession(true);
		if (session.isNew()) {
			out.println("session is new");
		} else {
			out.println("session id: " + session.getId());
		}
		
		GeneralDao dao = new GeneralDao();
		InitialContext ctx;
		Connection conn = null;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(datasource);
			conn = ds.getConnection();
			
			for (int i = 0; i < loop; i++) {
				long count = dao.select(conn, sql);
				out.println("success. record count: " + count);
			}

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
