package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.javadoc.ThrowsTag;

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

	public PositionBean finedByPk(long id) throws ApplicationException{

		PositionBean bean = new PositionBean();
		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_position where identifier = ?");
			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean.setIdentifier(rs.getLong(1));
				bean.setDesignation(rs.getString(2));
				bean.setOpeningDate(rs.getDate(3));
				bean.setRequiredExperince(rs.getString(4));
				bean.setCondition(rs.getString(5));

			}

		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in finedByPk " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List list() throws ApplicationException {
		return search(null, 0, 0);

	}

	public List list(PositionBean bean, int pageNo, int pageSize) throws ApplicationException {
		return search(null, 0, 0);

	}

	public List search(PositionBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;

		List list = new ArrayList();

		try {

			conn = JDBCDataSource.getConnection();

			StringBuffer sql = new StringBuffer("select * from st_position where 1 = 1 ");

			if (bean != null) {

				if (bean.getDesignation() != null && bean.getDesignation().length() > 0) {

					sql.append("and designation like '" + bean.getDesignation() + "%'");

					// sql.append("and designation = " + bean.getDesignation());

				}

			}
			if (pageNo > 0) {

				pageNo = (pageNo - 1) * pageSize;

			}

			System.out.println("sql ====>>> " + sql.toString());

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new PositionBean();

				bean.setIdentifier(rs.getLong(1));
				bean.setDesignation(rs.getString(2));
				bean.setOpeningDate(rs.getDate(3));
				bean.setRequiredExperince(rs.getString(4));
				bean.setCondition(rs.getString(5));

				list.add(bean);

			}

		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception search method => " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testFinedByPk();
		testSearch();

	}

	private static void testSearch() throws ApplicationException {

		int pageNo = 1;

		int pageSize = 10;

		PositionModel model = new PositionModel();

		PositionBean bean = new PositionBean();

		List list = model.search(bean, pageNo, pageSize);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (PositionBean) it.next();

			System.out.println("designation => " + bean.getDesignation());
			System.out.println("openingDate => " + bean.getOpeningDate());
			System.out.println("requiredExperince => " + bean.getRequiredExperince());
			System.out.println("condition => " + bean.getCondition());

		}

	}

	private static void testFinedByPk() throws ApplicationException {

		long id = 1;

		PositionModel model = new PositionModel();

		PositionBean bean = model.finedByPk(id);

		System.out.println("designation => " + bean.getDesignation());
		System.out.println("openingDate => " + bean.getOpeningDate());
		System.out.println("requiredExperince => " + bean.getRequiredExperince());
		System.out.println("condition => " + bean.getCondition());

	}

	private static void testDelete() throws ApplicationException {

		PositionModel model = new PositionModel();

		model.delete(3);

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

	private static void testAdd() throws Exception {

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
