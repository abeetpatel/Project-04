package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.SubjectBean;
import in.co.rays.model.SubjectModel;

public class TestSubjectModel {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testFinedByPk();
		// testFinedByName();
		testSearch();

	}

	private static void testSearch() throws Exception {

		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int pageNo = 1;
		int pageSize = 5;
		List list = new ArrayList();

		// bean.setName("collection");
		// bean.setDescription("collection");
		// bean.setCreatedBy("admin");
		// bean.setModifiedBy("admin");
		// bean.setCreatedDatetime(new Timestamp(sdf.parse("2024-12-14
		// 14:58:47").getTime()));
		bean.setModifiedDatetime(new Timestamp(sdf.parse("2024-12-14 15:00:48").getTime()));

		list = model.search(bean, pageNo, pageSize);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (SubjectBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

	private static void testFinedByName() throws Exception {

		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();
		String name = "collection";

		bean = model.finedByName(name);

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getCourseId());
		System.out.println(bean.getCourseName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	private static void testFinedByPk() throws Exception {

		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();
		long id = 1;

		bean = model.finedByPk(id);

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getCourseId());
		System.out.println(bean.getCourseName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	private static void testDelete() throws Exception {

		long id = 3;

		SubjectModel model = new SubjectModel();

		model.delete(id);

	}

	private static void testUpdate() throws Exception {

		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();

		bean.setId(2);
		bean.setName("OOP");
		bean.setCourseId(1);
		bean.setDescription("OOP");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();

		bean.setName("collection");
		bean.setCourseId(1);
		bean.setDescription("collection");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
