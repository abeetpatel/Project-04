package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.FollowUpBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.util.JDBCDataSource;

public class FollowUpModel {

	public long nextPk() throws DatabaseException {

		long pk = 0;

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_follow_up");

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

	public void add(FollowUpBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_follow_up values(?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getPatient());
			pstmt.setString(3, bean.getDoctor());
			pstmt.setDate(4, new java.sql.Date(bean.getVisitDate().getTime()));
			pstmt.setInt(5, bean.getFees());

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

	public void update(FollowUpBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_follow_up set patient = ?, doctor = ?, visitDate = ?, fees = ? where id = ?");

			pstmt.setString(1, bean.getPatient());
			pstmt.setString(2, bean.getDoctor());
			pstmt.setDate(3, new java.sql.Date(bean.getVisitDate().getTime()));
			pstmt.setInt(4, bean.getFees());
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

			PreparedStatement pstmt = conn.prepareStatement("delete from st_follow_up where id = ?");

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

	public FollowUpBean finedByPk(long id) throws ApplicationException {

		FollowUpBean bean = null;

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_follow_up where id = ?");

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new FollowUpBean();

				bean.setId(rs.getLong(1));
				bean.setPatient(rs.getString(2));
				bean.setDoctor(rs.getString(3));
				bean.setVisitDate(new java.sql.Date(rs.getDate(4).getTime()));
				bean.setFees(rs.getInt(5));

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

	public List search(FollowUpBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_follow_up where 1 = 1");

		if (bean != null) {

			if (bean.getId() > 0) {

				sql.append(" and id = " + bean.getId());

			}

			if (bean.getPatient() != null && bean.getPatient().length() > 0) {

				sql.append(" and patient like '" + bean.getPatient() + "%'");

			}
			if (bean.getDoctor() != null && bean.getDoctor().length() > 0) {

				sql.append(" and doctor like '" + bean.getDoctor() + "%'");

			}
			if (bean.getVisitDate() != null && bean.getVisitDate().getTime() > 0) {

				Date d = new Date(bean.getVisitDate().getTime());

				sql.append(" and visitDate like '" + d + "%'");

			}
			if (bean.getFees() > 0) {

				sql.append(" and fees like '" + bean.getFees() + "%'");

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

				bean = new FollowUpBean();

				bean.setId(rs.getLong(1));
				bean.setPatient(rs.getString(2));
				bean.setDoctor(rs.getString(3));
				bean.setVisitDate(new java.sql.Date(rs.getDate(4).getTime()));
				bean.setFees(rs.getInt(5));
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
