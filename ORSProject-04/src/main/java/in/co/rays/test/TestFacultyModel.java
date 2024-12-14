package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.bean.FacultyBean;
import in.co.rays.model.FacultyModel;

public class TestFacultyModel {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testFinedByPk();
		testSearch();

	}

	private static void testSearch() throws Exception {

		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// bean.setFirstName("ruhi");
		// bean.setLastName("patel");
		// bean.setDob(sdf.parse("2000-01-01"));
		// bean.setGender("female");
		// bean.setMobileNo("9148684684");
		// bean.setEmail("ruhi@gmail.com");
		// bean.setCreatedBy("admin");
		// bean.setModifiedBy("admin");
		// bean.setCreatedDatetime(new Timestamp(sdf.parse("2024-12-15
		// 01:05:59").getTime()));
		bean.setModifiedDatetime(new Timestamp(sdf.parse("2024-12-15 01:10:22").getTime()));

		int pageNo = 1;
		int pageSize = 5;
		List list = new ArrayList();
		list = model.search(bean, pageNo, pageSize);
		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (FacultyBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

	private static void testFinedByPk() throws Exception {

		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		long id = 2;
		bean = model.finedByPk(id);

		System.out.println(bean.getId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getDob());
		System.out.println(bean.getGender());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getEmail());
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getCourseId());
		System.out.println(bean.getCourseName());
		System.out.println(bean.getSubjectId());
		System.out.println(bean.getSubjectName());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	private static void testDelete() throws Exception {

		long id = 3;

		FacultyModel model = new FacultyModel();

		model.delete(id);

	}

	private static void testUpdate() throws Exception {

		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(2);
		bean.setFirstName("ruhi");
		bean.setLastName("sachdeva");
		bean.setDob(sdf.parse("2000-01-01"));
		bean.setGender("female");
		bean.setMobileNo("9141542684");
		bean.setEmail("ruhi@gmail.com");
		bean.setCollegeId(2);
		bean.setCourseId(2);
		bean.setSubjectId(2);
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("aman");
		bean.setLastName("patel");
		bean.setDob(sdf.parse("2001-01-01"));
		bean.setGender("male");
		bean.setMobileNo("9148684684");
		bean.setEmail("aman@gmail.com");
		bean.setCollegeId(1);
		bean.setCourseId(1);
		bean.setSubjectId(1);
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
