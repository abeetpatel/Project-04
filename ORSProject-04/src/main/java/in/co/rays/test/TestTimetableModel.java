package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.bean.TimetableBean;
import in.co.rays.model.TimetableModel;

public class TestTimetableModel {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testFinedByPk();
		 testSearch();

	}

	private static void testSearch() throws Exception {

		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int pageNo = 1;
		int pageSize = 12;

		// bean.setSemester("Semester 1");
		// bean.setDescription("End-term exam for Machine Learning");
		// bean.setExamDate(sdf.parse("2024-05-25"));
		// bean.setExamTime("10:00 AM");
		// bean.setCreatedBy("admin");
		// bean.setModifiedBy("admin");
		// bean.setCreatedDatetime(new Timestamp(simple.parse("2024-01-11 20:00:00 ").getTime()));
		// bean.setModifiedDatetime(new Timestamp(simple.parse("2024-01-08 17:00:00").getTime()));
		
		List list = new ArrayList();
		list = model.search(bean, pageNo, pageSize);
		Iterator it = list.iterator();
		while (it.hasNext()) {

			bean = (TimetableBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
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

		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		long id = 1;

		bean = model.finedByPk(id);
		
		if(bean != null) {

		System.out.println(bean.getId());
		System.out.println(bean.getSemester());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCourseId());
		System.out.println(bean.getCourseName());
		System.out.println(bean.getSubjectId());
		System.out.println(bean.getSubjectName());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
		
		}else {
			
			System.out.println("user not found");
			
		}

	}

	private static void testDelete() throws Exception {

		long id = 13;

		TimetableModel model = new TimetableModel();

		model.delete(id);

	}

	private static void testUpdate() throws Exception {

		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(13);
		bean.setSemester("2nd");
		bean.setDescription("2nd sem of 1st year");
		bean.setExamDate(sdf.parse("2024-11-01"));
		bean.setExamTime("10:30-01:30");
		bean.setCourseId(2);
		bean.setSubjectId(2);
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);
		;

	}

	private static void testAdd() throws Exception {

		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setSemester("1st");
		bean.setDescription("1st sem of 1st year");
		bean.setExamDate(sdf.parse("2024-05-01"));
		bean.setExamTime("12:00-03:00");
		bean.setCourseId(1);
		bean.setSubjectId(1);
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
