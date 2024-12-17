package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.model.CourseModel;

public class TestCourseModel {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testdelete();
		// testFinedByPk();
		// testFinedByName();
		testSearch();

	}

	private static void testSearch() throws Exception {

		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		int pageNo = 1;
		int pageSize = 12;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// bean.setName("java");
		// bean.setDuration("1 year");
		// bean.setDescription("Core JAVA, Advance JAVA, Corporate JAVA");
		// bean.setCreatedBy("admin");
		// bean.setModifiedBy("admin");
		// bean.setCreatedDatetime(new Timestamp(sdf.parse(" 2024-12-13 01:04:22").getTime()));
		// bean.setModifiedDatetime(new Timestamp(sdf.parse("2024-12-13 01:04:22").getTime()));
		
		List list = new ArrayList();

		list = model.search(bean, pageNo, pageSize);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (CourseBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

	private static void testFinedByName() throws Exception {

		CourseBean bean = new CourseBean();

		CourseModel model = new CourseModel();

		String name = "java";

		bean = model.finedByName(name);

		if(bean != null) {
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDuration());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
		}else {
			
			System.out.println("user not found");
			
		}

	}

	private static void testFinedByPk() throws Exception {

		CourseBean bean = new CourseBean();

		CourseModel model = new CourseModel();

		long id = 2;

		bean = model.finedByPk(id);

		if(bean != null) {
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDuration());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
		}else {
			
			System.out.println("user not found");
			
		}

	}

	private static void testdelete() throws Exception {

		long id = 2;

		CourseModel model = new CourseModel();

		model.delete(id);

	}

	private static void testUpdate() throws Exception {

		CourseBean bean = new CourseBean();

		CourseModel model = new CourseModel();

		bean.setId(2);
		bean.setName("python");
		bean.setDuration("1 year");
		bean.setDescription("Core Python, Advance Python, Corporate Python");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		CourseBean bean = new CourseBean();

		CourseModel model = new CourseModel();

		bean.setName("java");
		bean.setDuration("1 year");
		bean.setDescription("Core JAVA, Advance JAVA, Corporate JAVA");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
