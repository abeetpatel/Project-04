package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import in.co.rays.bean.StaffMemberBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.util.JDBCDataSource;

public class StaffMemberModel {

	public long nextPk() throws DatabaseException {

		long pk = 0;

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(identifier) from st_staffmember");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				pk = rs.getLong(1);

				System.out.println("max id => " + pk);

			}

		} catch (Exception e) {

			throw new DatabaseException("Exception in nextPk => " + e);
		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return pk + 1;

	}

	public void add(StaffMemberBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_staffmember values(?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getFullName());
			pstmt.setDate(3, new Date(bean.getJoiningDate().getTime()));
			pstmt.setString(4, bean.getDivision());
			pstmt.setString(5, bean.getPreviousEmployer());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data added successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {

				throw new ApplicationException("Exception in add/rollback => " + e1);

			}

			throw new ApplicationException("Exception in add => " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public void update(StaffMemberBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_staffmember set fullName = ?, joiningDate = ?, division = ?, previousEmployer = ? where identifier = ?");

			pstmt.setString(1, bean.getFullName());
			pstmt.setDate(2, new Date(bean.getJoiningDate().getTime()));
			pstmt.setString(3, bean.getDivision());
			pstmt.setString(4, bean.getPreviousEmployer());
			pstmt.setLong(5, bean.getIdentifier());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data updated successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {

				throw new ApplicationException("Exception in update/rollback => " + e1);

			}
			throw new ApplicationException("Exception in update => " + e);
		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public void delete(long id) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_staffmember where identifier = ?");

			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data deleted successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {
				throw new ApplicationException("Exception in delete/rollback => " + e1);
			}

			throw new ApplicationException("Exception in delete => " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public StaffMemberBean finedByPk(long pk) throws ApplicationException {

		StaffMemberBean bean = null;

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_staffmember where identifier = ?");

			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new StaffMemberBean();

				bean.setIdentifier(rs.getLong(1));
				bean.setFullName(rs.getString(2));
				bean.setJoiningDate(rs.getDate(3));
				bean.setDivision(rs.getString(4));
				bean.setPreviousEmployer(rs.getString(5));

			}
		} catch (Exception e) {
			throw new ApplicationException("Exception in finedByPk => " + e);
		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return bean;

	}

	public List list() throws Exception {
		return search(null, 0, 0);

	}

	public List search(StaffMemberBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_staffmember where 1 = 1");

		if (bean != null) {

			if (bean.getIdentifier() > 0) {

				sql.append(" and identifier = " + bean.getIdentifier());

			}

			if (bean.getFullName() != null && bean.getFullName().length() > 0) {

				sql.append(" and fullName is like '" + bean.getFullName() + "%'");

			}

		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" limit " + pageNo + "," + pageSize);

		}

		System.out.println("Sql ===> " + sql.toString());

		Connection conn = null;

		List list = new ArrayList();

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new StaffMemberBean();

				bean.setIdentifier(rs.getLong(1));
				bean.setFullName(rs.getString(2));
				bean.setJoiningDate(rs.getDate(3));
				bean.setDivision(rs.getString(4));
				bean.setPreviousEmployer(rs.getString(5));

				list.add(bean);

			}

		} catch (Exception e) {
			throw new ApplicationException("Exception in search => " + e);
		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return list;

	}

}
