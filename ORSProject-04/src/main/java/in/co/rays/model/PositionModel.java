package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import in.co.rays.bean.PositionBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

public class PositionModel {

	public long nextPk() throws Exception {

		long pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(identifier) from st_position");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getLong(1);

		}

		return pk + 1;

	}

	public void add(PositionBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_position values(?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getDesignation());
			pstmt.setDate(3, new Date(bean.getOpeningDate().getTime()));
			pstmt.setString(4, bean.getRequiredExperince());
			pstmt.setString(5, bean.getCondition());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data added successfully  " + i);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				throw new ApplicationException("Exception : Exception in rollback " + e1);
			}
			throw new ApplicationException("Exception : Exception in Add " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(PositionBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_position set designation = ?, openingDate = ?, requiredExperince = ?, conditionn = ?  where identifier = ?");

			
			
			pstmt.setString(1, bean.getDesignation());
			pstmt.setDate(2, new Date(bean.getOpeningDate().getTime()));
			pstmt.setString(3, bean.getRequiredExperince());
			pstmt.setString(4, bean.getCondition());
			pstmt.setLong(5, bean.getIdentifier());

			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println("data updated successfully => " + i);

		} catch (Exception e) {

			try {

				conn.rollback();

			} catch (Exception e1) {

				throw new ApplicationException("Exception : Exception in rollback " + e1.getMessage());

			}

			throw new ApplicationException("Exception : Exception in update " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(long id) throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_position where identifier = ?");
			pstmt.setLong(1, id);
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data Deleted Successfully => " + i);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				throw new ApplicationException("Exception: Exception in rollback " + e1);
			}
			throw new ApplicationException("Exception: Exception in delete " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public PositionBean finedByPk(long id) {

		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_position where identifier = ?");
			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

		} catch (Exception e) {
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return null;
	}

	public List list() {
		return search(null, 0, 0);

	}

	public List list(PositionBean bean, int pageNo, int pageSize) {
		return search(null, 0, 0);

	}

	public List search(PositionBean bean, int pageNo, int pageSize) {
		return null;

	}

	public static void main(String[] args) throws Exception {

		// testadd();
		testUpdate();
		// testDelete();

	}

	private static void testDelete() throws ApplicationException {

		PositionModel model = new PositionModel();

		model.delete(0);

	}

	private static void testUpdate() throws Exception {

		PositionBean bean = new PositionBean();
		PositionModel model = new PositionModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setDesignation("demo0");
		bean.setOpeningDate(sdf.parse("2025-04-02"));
		bean.setRequiredExperince("0yr");
		bean.setCondition("demo0");
		bean.setIdentifier(1);

		model.update(bean);

	}

	private static void testadd() throws Exception {

		PositionBean bean = new PositionBean();
		PositionModel model = new PositionModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setDesignation("demo3");
		bean.setOpeningDate(sdf.parse("2025-03-17"));
		bean.setRequiredExperince("2 yr");
		bean.setCondition("demo2");

		model.add(bean);

	}

}
