package easydbtester.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Session Bean implementation class GeneralDao
 */
public class GeneralDao {

	/**
	 * Default constructor.
	 */
	public GeneralDao() {
		// TODO Auto-generated constructor stub
	}

	public long select(Connection conn, String sql, long userId) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		long cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				cnt++;
			}
		} finally {
			rs.close();
			ps.close();
		}
		return cnt;
	}
	
	public long select(Connection conn, String sql) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				cnt++;
			}
		} finally {
			ps.close();
			rs.close();
		}
		return cnt;
	}
	
	public int update(Connection conn, String sql, long userId) throws SQLException {
		PreparedStatement ps = null;
		int ret = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, userId);
			ret = ps.executeUpdate();
		} finally {
			ps.close();
		}
		return ret;
	}
	
	public int update(Connection conn, String sql) throws SQLException {
		PreparedStatement ps = null;
		int ret = 0;
		try {
			ps = conn.prepareStatement(sql);
			ret = ps.executeUpdate();
		} finally {
			ps.close();
		}
		return ret;
	}
	

}
