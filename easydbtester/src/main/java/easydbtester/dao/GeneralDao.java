package easydbtester.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.example.querygeneral.util.TimeRecSingleton;
import com.example.utils.db.ConnectionUtil;

/**
 * Session Bean implementation class GeneralDao
 */
@Stateless
@LocalBean
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
			long start = System.currentTimeMillis();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				cnt++;
			}
			long end = System.currentTimeMillis();
			TimeRecSingleton.getInstance().addTime(end - start);
		} finally {
			ConnectionUtil.close(null, ps, rs);
		}
		return cnt;
	}
	
	public long select(Connection conn, String sql) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long cnt = 0;
		try {
			long start = System.currentTimeMillis();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				cnt++;
			}
			long end = System.currentTimeMillis();
			TimeRecSingleton.getInstance().addTime(end - start);
		} finally {
			ConnectionUtil.close(null, ps, rs);
		}
		return cnt;
	}
	
	public int update(Connection conn, String sql, long userId) throws SQLException {
		PreparedStatement ps = null;
		int ret = 0;
		try {
			long start = System.currentTimeMillis();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, userId);
			ret = ps.executeUpdate();
			long end = System.currentTimeMillis();
			TimeRecSingleton.getInstance().addTime(end - start);
		} finally {
			ConnectionUtil.close(null, ps, null);
		}
		return ret;
	}
	
	public int update(Connection conn, String sql) throws SQLException {
		PreparedStatement ps = null;
		int ret = 0;
		try {
			long start = System.currentTimeMillis();
			ps = conn.prepareStatement(sql);
			ret = ps.executeUpdate();
			long end = System.currentTimeMillis();
			TimeRecSingleton.getInstance().addTime(end - start);
		} finally {
			ConnectionUtil.close(null, ps, null);
		}
		return ret;
	}
	

}
