package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.util.JDBCDataSource;

public class MarksheetModel {

	public long nextPk() throws Exception {

		long pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_marksheet");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getLong(1);

			System.out.println("max id => " + pk);

		}

		JDBCDataSource.closeConnection(conn);

		return pk + 1;

	}

	public void add(MarksheetBean bean) throws Exception {

		StudentModel model = new StudentModel();
		StudentBean stbean = model.finedByPk(bean.getStudentId());
		String name = (stbean.getFirstName() + " " + stbean.getLastName());

		MarksheetBean existsbean = finedByRollNo(bean.getRollNo());
		if (existsbean != null) {
			throw new Exception("Marksheet already exists");
		}

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_marksheet values(?,?,?,?,?,?,?,?,?,?,?)");

		pstmt.setLong(1, nextPk());
		pstmt.setString(2, bean.getRollNo());
		pstmt.setLong(3, bean.getStudentId());
		pstmt.setString(4, name);
		pstmt.setInt(5, bean.getPhysics());
		pstmt.setInt(6, bean.getChemistry());
		pstmt.setInt(7, bean.getMaths());
		pstmt.setString(8, bean.getCreatedBy());
		pstmt.setString(9, bean.getModifiedBy());
		pstmt.setTimestamp(10, bean.getCreatedDatetime());
		pstmt.setTimestamp(11, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data added successfully => " + i);

	}

	public void update(MarksheetBean bean) throws Exception {

		StudentModel model = new StudentModel();
		StudentBean stbean = model.finedByPk(bean.getStudentId());
		String name = (stbean.getFirstName() + " " + stbean.getLastName());

		MarksheetBean existsbean = finedByRollNo(bean.getRollNo());
		if (existsbean != null && existsbean.getId() != bean.getId()) {
			throw new Exception("Marksheet already exists");
		}

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_marksheet set roll_no = ?, student_id = ?, name = ?, physics = ?, chemistry = ?, maths = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ? ");

		pstmt.setString(1, bean.getRollNo());
		pstmt.setLong(2, bean.getStudentId());
		pstmt.setString(3, name);
		pstmt.setInt(4, bean.getPhysics());
		pstmt.setInt(5, bean.getChemistry());
		pstmt.setInt(6, bean.getMaths());
		pstmt.setString(7, bean.getCreatedBy());
		pstmt.setString(8, bean.getModifiedBy());
		pstmt.setTimestamp(9, bean.getCreatedDatetime());
		pstmt.setTimestamp(10, bean.getModifiedDatetime());
		pstmt.setLong(11, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data updated successfully => " + i);

	}

	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_marksheet where id = ?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data deleted successfully => " + i);

	}

	public MarksheetBean finedByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_marksheet where id = ?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		MarksheetBean bean = null;

		while (rs.next()) {

			bean = new MarksheetBean();

			bean.setId(rs.getLong(1));
			bean.setRollNo(rs.getString(2));
			bean.setStudentId(rs.getLong(3));
			bean.setName(rs.getString(4));
			bean.setPhysics(rs.getInt(5));
			bean.setChemistry(rs.getInt(6));
			bean.setMaths(rs.getInt(7));
			bean.setCreatedBy(rs.getString(8));
			bean.setModifiedBy(rs.getString(9));
			bean.setCreatedDatetime(rs.getTimestamp(10));
			bean.setModifiedDatetime(rs.getTimestamp(11));

		}

		JDBCDataSource.closeConnection(conn);

		return bean;

	}

	public MarksheetBean finedByRollNo(String rollNo) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_marksheet where roll_no = ?");

		pstmt.setString(1, rollNo);

		ResultSet rs = pstmt.executeQuery();

		MarksheetBean bean = null;

		while (rs.next()) {

			bean = new MarksheetBean();

			bean.setId(rs.getLong(1));
			bean.setRollNo(rs.getString(2));
			bean.setStudentId(rs.getLong(3));
			bean.setName(rs.getString(4));
			bean.setPhysics(rs.getInt(5));
			bean.setChemistry(rs.getInt(6));
			bean.setMaths(rs.getInt(7));
			bean.setCreatedBy(rs.getString(8));
			bean.setModifiedBy(rs.getString(9));
			bean.setCreatedDatetime(rs.getTimestamp(10));
			bean.setModifiedDatetime(rs.getTimestamp(11));

		}

		JDBCDataSource.closeConnection(conn);

		return bean;

	}

	public List search(MarksheetBean bean, int pageNo, int pageSize) throws Exception {

//		StudentModel model = new StudentModel();
//		StudentBean stbean = model.finedByPk(bean.getStudentId());
//		String name = (stbean.getFirstName() + " " + stbean.getLastName());
//		
//		System.out.println(name);

		Connection conn = JDBCDataSource.getConnection();

		StringBuffer sql = new StringBuffer("select * from st_marksheet where 1 = 1");

		if (bean != null) {

			if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {

				sql.append(" and roll_no like '" + bean.getRollNo() + "%'");

			}
//			if (name != null && name.length() > 0) {
//
//				sql.append(" and name like '" + name + "%'");
//
//			}
			if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {

				sql.append(" and created_by like '" + bean.getCreatedBy() + "%'");

			}
			if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {

				sql.append(" and modified_by like '" + bean.getModifiedBy() + "%'");

			}
			if (bean.getCreatedDatetime() != null && bean.getCreatedDatetime().getTime() > 0) {

				Timestamp ts = new Timestamp(bean.getCreatedDatetime().getTime());

				String[] arr = ts.toString().split("\\.");

				System.out.println("date => " + arr[0]);

				sql.append(" and created_datetime like '" + arr[0] + "%'");

			}
			if (bean.getModifiedDatetime() != null && bean.getModifiedDatetime().getTime() > 0) {

				Timestamp ts = new Timestamp(bean.getModifiedDatetime().getTime());

				String[] arr = ts.toString().split("\\.");

				System.out.println("date => " + arr[0]);

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

			bean = new MarksheetBean();

			bean.setId(rs.getLong(1));
			bean.setRollNo(rs.getString(2));
			bean.setStudentId(rs.getLong(3));
			bean.setName(rs.getString(4));
			bean.setPhysics(rs.getInt(5));
			bean.setChemistry(rs.getInt(6));
			bean.setMaths(rs.getInt(7));
			bean.setCreatedBy(rs.getString(8));
			bean.setModifiedBy(rs.getString(9));
			bean.setCreatedDatetime(rs.getTimestamp(10));
			bean.setModifiedDatetime(rs.getTimestamp(11));
			list.add(bean);

		}

		JDBCDataSource.closeConnection(conn);

		return list;

	}

}