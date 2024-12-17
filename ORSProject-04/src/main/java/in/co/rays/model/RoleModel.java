package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import in.co.rays.bean.RoleBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

public class RoleModel {

	public long nextPk() throws DatabaseException {

		long pk = 0;

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_role");

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

	public void add(RoleBean bean) throws DuplicateRecordException, ApplicationException {

		Connection conn = null;
		long pk = 0;

		RoleBean existBean = finedByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("role name already exist");
		}

		try {

			pk = nextPk();

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_role values(?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data added successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {

				throw new ApplicationException("Exception : Exception in rollback " + e1.getMessage());

			}

			throw new ApplicationException("Exception : Exception in add" + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public void update(RoleBean bean) throws DuplicateRecordException, ApplicationException {

		RoleBean existBean = finedByName(bean.getName());

		if (existBean != null && bean.getId() != existBean.getId()) {
			throw new DuplicateRecordException("role name already exist");
		}

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_role set name = ?, description = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ? ");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreatedDatetime());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7, bean.getId());

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

			PreparedStatement pstmt = conn.prepareStatement("delete from st_role where id = ?");

			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();

			System.out.println("data deleted successfully => " + i);

		} catch (Exception e) {

			throw new ApplicationException("Exception : Exception in delete " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public RoleBean finedByPk(long id) throws ApplicationException {

		RoleBean bean = null;

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_role where id = ?");

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new RoleBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));

			}

		} catch (Exception e) {

			throw new ApplicationException("Exception : Exception in FinedByPk " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return bean;

	}

	public RoleBean finedByName(String name) throws ApplicationException {

		Connection conn = null;

		RoleBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_role where name = ?");

			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new RoleBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));

			}

		} catch (Exception e) {

			throw new ApplicationException("Exception : Exception in FinedByName" + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return bean;

	}

	public List search(RoleBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_role where 1 = 1");

		if (bean != null) {

			if (bean.getName() != null && bean.getName().length() > 0) {

				sql.append(" and name like '" + bean.getName() + "%'");

			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {

				sql.append(" and description like '" + bean.getDescription() + "%'");

			}
			if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {

				sql.append(" and created_by like '" + bean.getCreatedBy() + "%'");

			}
			if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {

				sql.append(" and modified_by like '" + bean.getModifiedBy() + "%'");

			}
			if (bean.getCreatedDatetime() != null && bean.getCreatedDatetime().getTime() > 0) {

				String[] arr = bean.getCreatedDatetime().toString().split("\\.");

				sql.append(" and created_datetime like '" + arr[0] + "%'");
			}
			if (bean.getModifiedDatetime() != null && bean.getModifiedDatetime().getTime() > 0) {

				String[] arr = bean.getModifiedDatetime().toString().split("\\.");

				sql.append(" and modified_datetime like '" + arr[0] + "%'");

			}

		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" limit " + pageNo + "," + pageSize);

		}

		System.out.println("sql  =>  " + sql.toString());

		Connection conn = null;

		List list = new ArrayList();

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new RoleBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
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
