package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import in.co.rays.bean.CustomerBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

public class CustomerModel {

	public long nextPk() throws DatabaseException {

		long pk = 0;

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_customer");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				pk = rs.getLong(1);

			}

		} catch (Exception e) {

			throw new DatabaseException("Exception : Exception in nextPk " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return pk + 1;

	}

	public void add(CustomerBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_customer values(?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getClientName());
			pstmt.setString(3, bean.getLocation());
			pstmt.setString(4, bean.getContactNumber());
			pstmt.setString(5, bean.getImportance());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data added successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {

				throw new ApplicationException("Exception : Exception in rollback " + e1.getMessage());

			}

			throw new ApplicationException("Exception : Exception in add " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public void update(CustomerBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_customer set clientName = ?, location = ?, contactNumber = ?, importance = ? where id = ?");

			pstmt.setString(1, bean.getClientName());
			pstmt.setString(2, bean.getLocation());
			pstmt.setString(3, bean.getContactNumber());
			pstmt.setString(4, bean.getImportance());
			pstmt.setLong(5, bean.getId());

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

			PreparedStatement pstmt = conn.prepareStatement("delete from st_customer where id = ?");

			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data deleted successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {

				throw new ApplicationException("Exception : Exception in rollback " + e1.getMessage());

			}

			throw new ApplicationException("Exception : Exception in delete " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public CustomerBean finedByPk(long id) throws ApplicationException {

		CustomerBean bean = null;

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_customer where id = ?");

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new CustomerBean();

				bean.setId(rs.getLong(1));
				bean.setClientName(rs.getString(2));
				bean.setLocation(rs.getString(3));
				bean.setContactNumber(rs.getString(4));
				bean.setImportance(rs.getString(5));

			}

		} catch (Exception e) {

			throw new ApplicationException("Exception : Exception in finedByPk " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return bean;

	}

	public List list() throws Exception {
		return search(null, 0, 0);
	}

	public List search(CustomerBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_customer where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0) {

				sql.append(" and id = " + bean.getId());

			}

			if (bean.getClientName() != null && bean.getClientName().length() > 0) {

				sql.append(" and clientName like '" + bean.getClientName() + "%'");

			}
			if (bean.getLocation() != null && bean.getLocation().length() > 0) {

				sql.append(" and location like '" + bean.getLocation() + "%'");

			}
			if (bean.getContactNumber() != null && bean.getContactNumber().length() > 0) {

				sql.append(" and contactNumber like '" + bean.getContactNumber() + "%'");

			}
			if (bean.getImportance() != null && bean.getImportance().length() > 0) {

				sql.append(" and importance like '" + bean.getImportance() + "%'");

			}

		}
		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" limit " + pageNo + "," + pageSize);

		}

		System.out.println("sql => " + sql.toString());

		Connection conn = null;

		List list = new ArrayList();

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new CustomerBean();

				bean.setId(rs.getLong(1));
				bean.setClientName(rs.getString(2));
				bean.setLocation(rs.getString(3));
				bean.setContactNumber(rs.getString(4));
				bean.setImportance(rs.getString(5));
				list.add(bean);

			}

		} catch (Exception e) {

			throw new ApplicationException("Exception : Exception in Search " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return list;

	}

}
