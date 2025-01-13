package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.ClientBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.util.JDBCDataSource;

public class ClientModel {

	public long nextPk() throws DatabaseException {

		long pk = 0;

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_client");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				pk = rs.getLong(1);

				System.out.println("max  id => " + pk);

			}

		} catch (Exception e) {

			throw new DatabaseException("Exception in nextPk " + e);

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;
	}

	public void add(ClientBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_client values(?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getFullName());
			pstmt.setDate(3, new Date(bean.getAppointmentDate().getTime()));
			pstmt.setString(4, bean.getPhone());
			pstmt.setString(5, bean.getIllness());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data added successfully => " + i);
		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {
				throw new ApplicationException("Exception in add/rollback " + e1);
			}
			throw new ApplicationException("Exception in add " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void update(ClientBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_client set fullName = ?, appointmentDate = ?, phone = ?, illness = ? where id = ?");

			pstmt.setString(1, bean.getFullName());
			pstmt.setDate(2, new Date(bean.getAppointmentDate().getTime()));
			pstmt.setString(3, bean.getPhone());
			pstmt.setString(4, bean.getIllness());
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data updated successfully => " + i);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				throw new ApplicationException("Exception in update/rollback " + e1);
			}
			throw new ApplicationException("Exception in update " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(long id) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_client where id = ?");

			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data deleted successfully => " + i);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				throw new ApplicationException("Exception in delete/rollback " + e1);
			}
			throw new ApplicationException("Exception in delete " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public ClientBean finedByPk(long pk) throws ApplicationException {

		ClientBean bean = null;

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_client where id = ?");

			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new ClientBean();

				bean.setId(rs.getLong(1));
				bean.setFullName(rs.getString(2));
				bean.setAppointmentDate(rs.getDate(3));
				bean.setPhone(rs.getString(4));
				bean.setIllness(rs.getString(5));

			}
		} catch (Exception e) {
			throw new ApplicationException("Exception in finedByPk " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	public List list() throws Exception {
		return search(null, 0, 0);
	}

	public List search(ClientBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;

		List list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_client where 1 = 1 ");

		if (bean != null) {
			if (bean.getId() > 0) {

				sql.append(" and id = " + bean.getId());

			}
			if (bean.getFullName() != null && bean.getFullName().length() > 0) {

				sql.append(" and fullName is like '" + bean.getFullName() + "%'");

			}
			if (bean.getAppointmentDate() != null && bean.getAppointmentDate().getTime() > 0) {

				Date d = new Date(bean.getAppointmentDate().getTime());

				sql.append(" and appointmentDate is like '" + d + "%'");

			}
			if (bean.getPhone() != null && bean.getPhone().length() > 0) {

				sql.append(" and phone is like '" + bean.getPhone() + "%'");

			}
			if (bean.getIllness() != null && bean.getIllness().length() > 0) {

				sql.append(" and illness is like '" + bean.getIllness() + "%'");

			}

		}
		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" limit " + pageNo + "," + pageSize);

		}

		System.out.println("Sql ===>>> " + sql.toString());

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new ClientBean();

				bean.setId(rs.getLong(1));
				bean.setFullName(rs.getString(2));
				bean.setAppointmentDate(rs.getDate(3));
				bean.setPhone(rs.getString(4));
				bean.setIllness(rs.getString(5));

				list.add(bean);

			}
		} catch (Exception e) {
			throw new ApplicationException("Exception in search " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}

}
