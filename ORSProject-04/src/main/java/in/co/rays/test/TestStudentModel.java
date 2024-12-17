package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.bean.StudentBean;
import in.co.rays.model.StudentModel;

public class TestStudentModel {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testFinedByPk();
		testSearch();

	}

	private static void testSearch() throws Exception {

		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List list = new ArrayList();
		int pageNo = 1;
		int pageSize = 12;

		// bean.setFirstName("ajay");
		// bean.setLastName("thakur");
		// bean.setDob(sdf.parse("2001-08-02"));
		// bean.setGender("male");
		// bean.setMobileNo("9125454588");
		// bean.setEmail("ajay@gmail.com");
		// bean.setCreatedBy("admin");
		// bean.setModifiedBy("admin");
		// bean.setCreatedDatetime(new Timestamp(simple.parse("2024-12-13
		// 18:51:28").getTime()));
		// bean.setModifiedDatetime(new Timestamp(simple.parse("2024-12-13
		// 18:55:26").getTime()));

		list = model.search(bean, pageNo, pageSize);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (StudentBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

	private static void testFinedByPk() throws Exception {

		StudentBean bean = new StudentBean();

		StudentModel model = new StudentModel();

		long id = 1;

		bean = model.finedByPk(id);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getGender());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		} else {

			System.out.println("user not found");

		}

	}

	private static void testDelete() throws Exception {

		long id = 3;

		StudentModel model = new StudentModel();

		model.delete(id);

	}

	private static void testUpdate() throws Exception {

		StudentBean bean = new StudentBean();

		StudentModel model = new StudentModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(2);
		bean.setFirstName("ajay");
		bean.setLastName("sahu");
		bean.setDob(sdf.parse("2001-08-02"));
		bean.setGender("male");
		bean.setMobileNo("9125454548");
		bean.setEmail("ajay@gmail.com");
		bean.setCollegeId(2);
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		StudentBean bean = new StudentBean();

		StudentModel model = new StudentModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(1);
		bean.setFirstName("mohit");
		bean.setLastName("thakur");
		bean.setDob(sdf.parse("2001-01-01"));
		bean.setGender("male");
		bean.setMobileNo("9125454588");
		bean.setEmail("mohit@gmail.com");
		bean.setCollegeId(1);
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
