package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import in.co.rays.bean.UserBean;
import in.co.rays.util.JDBCDataSource;

public class UserModel {

	public long nextPk() throws Exception {

		long pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_user");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getLong(1);

		}

		JDBCDataSource.closeConnection(conn);

		return pk + 1;

	}

	public void add(UserBean bean) throws Exception {

		UserBean existbean = finedByLogin(bean.getLogin());

		if (existbean != null) {

			throw new Exception("User Already Exists..");

		}

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_user values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

		pstmt.setLong(1, nextPk());
		pstmt.setString(2, bean.getFirstName());
		pstmt.setString(3, bean.getLastName());
		pstmt.setString(4, bean.getLogin());
		pstmt.setString(5, bean.getPassword());
		pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(7, bean.getMobileNo());
		pstmt.setLong(8, bean.getRoleId());
		pstmt.setString(9, bean.getGender());
		pstmt.setString(10, bean.getCreatedBy());
		pstmt.setString(11, bean.getModifiedBy());
		pstmt.setTimestamp(12, bean.getCreatedDatetime());
		pstmt.setTimestamp(13, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data added successfully => " + i); 

	}

	public void update(UserBean bean) throws Exception {

		UserBean existbean = finedByLogin(bean.getLogin());

		if (existbean != null && bean.getId() != existbean.getId()) {

			throw new Exception("User Already Exists..");

		}

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_user set first_name = ?, last_name = ?, login = ?, password = ?, dob = ?, mobile_no =?, role_id = ?, gender = ?, created_by = ?,modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setString(1, bean.getFirstName());
		pstmt.setString(2, bean.getLastName());
		pstmt.setString(3, bean.getLogin());
		pstmt.setString(4, bean.getPassword());
		pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(6, bean.getMobileNo());
		pstmt.setLong(7, bean.getRoleId());
		pstmt.setString(8, bean.getGender());
		pstmt.setString(9, bean.getCreatedBy());
		pstmt.setString(10, bean.getModifiedBy());
		pstmt.setTimestamp(11, bean.getCreatedDatetime());
		pstmt.setTimestamp(12, bean.getModifiedDatetime());
		pstmt.setLong(13, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data updated successfully => " + i);

	}

	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id = ?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data deleted successfully => " + i);

	}

	public UserBean finedByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where id = ?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {

			bean = new UserBean();

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(new java.sql.Date(rs.getDate(6).getTime()));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));

		}

		JDBCDataSource.closeConnection(conn);

		return bean;

	}

	public UserBean finedByLogin(String login) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login = ?");

		pstmt.setString(1, login);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {

			bean = new UserBean();

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(new java.sql.Date(rs.getDate(6).getTime()));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));

		}

		JDBCDataSource.closeConnection(conn);

		return bean;

	}

	public List search(UserBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		StringBuffer sql = new StringBuffer("select * from st_user where 1 = 1");

		if (bean != null) {

			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {

				sql.append(" and first_name like '" + bean.getFirstName() + "%'");

			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {

				sql.append(" and last_name like '" + bean.getLastName() + "%'");

			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {

				sql.append(" and login like '" + bean.getLogin() + "%'");

			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {

				sql.append(" and password like '" + bean.getPassword() + "%'");

			}
			if (bean.getDob() != null && bean.getDob().getTime() > 0) {

				Date d = new Date(bean.getDob().getTime());

				sql.append(" and dob like '" + d + "%'");

			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {

				sql.append(" and mobile_no like '" + bean.getMobileNo() + "%'");

			}
			if (bean.getRoleId() > 0) {

				sql.append(" and role_id like '" + bean.getRoleId() + "%'");

			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {

				sql.append(" and gender like '" + bean.getGender() + "%'");

			}
			if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {

				sql.append(" and created_by like '" + bean.getCreatedBy() + "%'");

			}
			if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {

				sql.append(" and modified_by like '" + bean.getModifiedBy() + "%'");

			}
			if (bean.getCreatedDatetime() != null && bean.getCreatedDatetime().getTime() > 0) {

				String[] arr = bean.getCreatedDatetime().toString().split("\\.");

				System.out.println(arr[0]);

				sql.append(" and created_datetime like '" + arr[0] + "%'");

			}
			if (bean.getModifiedDatetime() != null && bean.getModifiedDatetime().getTime() > 0) {

				String[] arr = bean.getModifiedDatetime().toString().split("\\.");

				System.out.println(arr[0]);

				sql.append(" and modified_datetime like '" + arr[0] + "%'");

			}

		}
		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" limit " + pageNo + "," + pageSize);

		}

		System.out.println("sql => " + sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {

			bean = new UserBean();

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(new java.sql.Date(rs.getDate(6).getTime()));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));
			list.add(bean);

		}

		JDBCDataSource.closeConnection(conn);

		return list;

	}

}