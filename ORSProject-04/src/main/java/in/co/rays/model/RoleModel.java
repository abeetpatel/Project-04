package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.util.JDBCDataSource;

public class RoleModel {

	public long nextPk() throws Exception {

		long pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_role");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);

		}

		return pk + 1;

	}

	public void add(RoleBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_role values(?,?,?,?,?,?,?)");

		pstmt.setLong(1, nextPk());
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getDescription());
		pstmt.setString(4, bean.getCreatedBy());
		pstmt.setString(5, bean.getModifiedBy());
		pstmt.setTimestamp(6, bean.getCreatedDatetime());
		pstmt.setTimestamp(7, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data added successfully => " + i);

	}

	public void update(RoleBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

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

		JDBCDataSource.closeConnection(conn);

		System.out.println("data updated successfully => " + i);

	}

	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_role where id = ?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data deleted successfully => " + i);

	}

	public RoleBean finedById(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_role where id = ?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		RoleBean bean = null;

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

		return bean;

	}

	public List search(RoleBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

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
				
				Date d = new Date(bean.getCreatedDatetime().getTime());

				sql.append(" and created_datetime like '" + d + "%'");

			}
			if(bean.getModifiedDatetime() != null && bean.getModifiedDatetime().getTime() > 0) {
				
				Date d = new Date(bean.getModifiedDatetime().getTime());
				
				sql.append(" and modified_by like '" + d + "%'");
				
			}

		}

		System.out.println("sql  =>  " + sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDatetime(rs.getTimestamp(6));
			bean.setModifiedDatetime(rs.getTimestamp(7));
			list.add(bean);

		}

		return list;

	}

}
