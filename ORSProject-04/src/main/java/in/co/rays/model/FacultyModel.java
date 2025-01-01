
package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.bean.FacultyBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

public class FacultyModel {

	public long nextPk() throws DatabaseException {

		long pk = 0;

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_faculty");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				pk = rs.getLong(1);

				System.out.println("max id => " + pk);

			}

		} catch (Exception e) {

			throw new DatabaseException("Exception : Exception in nextPk" + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return pk + 1;

	}

	public void add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		CollegeModel clgmodel = new CollegeModel();
		CollegeBean clgbean = clgmodel.finedByPk(bean.getCollegeId());
		String collegeName = clgbean.getName();

		CourseModel cmodel = new CourseModel();
		CourseBean cbean = cmodel.finedByPk(bean.getCourseId());
		String courseName = cbean.getName();

		SubjectModel smodel = new SubjectModel();
		SubjectBean sbean = smodel.finedByPk(bean.getSubjectId());
		String subjectName = sbean.getName();

		FacultyBean existBean = finedByEmail(bean.getEmail());
		if (existBean != null) {
			throw new DuplicateRecordException("Email Already Exist");
		}

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_faculty values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setDate(4, new Date(bean.getDob().getTime()));
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setLong(8, bean.getCollegeId());
			pstmt.setString(9, collegeName);
			pstmt.setLong(10, bean.getCourseId());
			pstmt.setString(11, courseName);
			pstmt.setLong(12, bean.getSubjectId());
			pstmt.setString(13, subjectName);
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, bean.getCreatedDatetime());
			pstmt.setTimestamp(17, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data added successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {

				throw new ApplicationException("Exception : Exception in rollback" + e1.getMessage());

			}

			throw new ApplicationException("Exception : Exception in add" + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		CollegeModel clgmodel = new CollegeModel();
		CollegeBean clgbean = clgmodel.finedByPk(bean.getCollegeId());
		String collegeName = clgbean.getName();

		CourseModel cmodel = new CourseModel();
		CourseBean cbean = cmodel.finedByPk(bean.getCourseId());
		String courseName = cbean.getName();

		SubjectModel smodel = new SubjectModel();
		SubjectBean sbean = smodel.finedByPk(bean.getSubjectId());
		String subjectName = sbean.getName();

		FacultyBean existBean = finedByEmail(bean.getEmail());
		if (existBean != null) {
			throw new DuplicateRecordException("Email Already Exist");
		}

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_faculty set first_name = ?, last_name = ?, dob = ?, gender = ?, mobile_no = ?, email = ?, college_id = ?, college_name = ?, course_id = ?, course_name = ?, subject_id = ?, subject_name = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setDate(3, new Date(bean.getDob().getTime()));
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setString(6, bean.getEmail());
			pstmt.setLong(7, bean.getCollegeId());
			pstmt.setString(8, collegeName);
			pstmt.setLong(9, bean.getCourseId());
			pstmt.setString(10, courseName);
			pstmt.setLong(11, bean.getSubjectId());
			pstmt.setString(12, subjectName);
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());
			pstmt.setLong(17, bean.getId());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data updated successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {

				throw new ApplicationException("Exception : Exception in rollback" + e1.getMessage());

			}

			throw new ApplicationException("Exception : Exception in update" + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public void delete(long id) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_faculty where id = ?");

			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data deleted successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {

				throw new ApplicationException("Exception : Exception in rollback" + e1.getMessage());

			}

			throw new ApplicationException("Exception : Exception in delete" + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public FacultyBean finedByPk(long id) throws ApplicationException {

		Connection conn = null;

		FacultyBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty where id =?");

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new FacultyBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCourseId(rs.getLong(10));
				bean.setCourseName(rs.getString(11));
				bean.setSubjectId(rs.getLong(12));
				bean.setSubjectName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));

			}

		} catch (Exception e) {

			throw new ApplicationException("Exception : Exception in finedByPk" + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return bean;

	}

	public FacultyBean finedByEmail(String email) throws ApplicationException {

		Connection conn = null;

		FacultyBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty where email =?");

			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new FacultyBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCourseId(rs.getLong(10));
				bean.setCourseName(rs.getString(11));
				bean.setSubjectId(rs.getLong(12));
				bean.setSubjectName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));

			}

		} catch (Exception e) {

			throw new ApplicationException("Exception : Exception in finedByPk" + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return bean;

	}

	public List list() throws Exception {
		return search(null, 0, 0);
	}

	public List search(FacultyBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_faculty where 1 = 1");

		if (bean != null) {

			if (bean.getId() > 0) {

				sql.append(" and id = " + bean.getId());
			}

			if (bean.getCollegeId() > 0) {

				sql.append(" and college_id = " + bean.getCollegeId());
			}

			if (bean.getCourseId() > 0) {

				sql.append(" and course_id = " + bean.getCourseId());
			}

			if (bean.getSubjectId() > 0) {

				sql.append(" and subject_id = " + bean.getSubjectId());
			}

			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {

				sql.append(" and first_name like '" + bean.getFirstName() + "%'");

			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {

				sql.append(" and last_name like '" + bean.getLastName() + "%'");

			}
			if (bean.getDob() != null && bean.getDob().getTime() > 0) {

				Date d = new Date(bean.getDob().getTime());

				sql.append(" and dob like '" + d + "%'");

			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {

				sql.append(" and gender like '" + bean.getGender() + "%'");

			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {

				sql.append(" and mobile_no like '" + bean.getMobileNo() + "%'");

			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {

				sql.append(" and email like '" + bean.getEmail() + "%'");

			}
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

		Connection conn = null;

		List list = new ArrayList();

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new FacultyBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCourseId(rs.getLong(10));
				bean.setCourseName(rs.getString(11));
				bean.setSubjectId(rs.getLong(12));
				bean.setSubjectName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
				list.add(bean);

			}

		} catch (Exception e) {

			throw new ApplicationException("Exception : Exception in search" + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return list;

	}

}
