package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.model.CollegeModel;

public class TestCollegeModel {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFinedByName();
		testSearch();

	}

	private static void testSearch() throws Exception {

		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		int pageNo = 1;
		int pageSize = 5;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// bean.setName("BGIEM");
		// bean.setAddress("JBP,MP");
		// bean.setState("MP");
		// bean.setCity("JBP");
		// bean.setPhoneNo("6284546548");
		// bean.setCreatedBy("admin");
		// bean.setModifiedBy("admin");
		// bean.setCreatedDatetime(new Timestamp(sdf.parse("2024-12-12
		// 20:40:02").getTime()));
		bean.setModifiedDatetime(new Timestamp(sdf.parse("2024-12-12 20:40:02").getTime()));

		List list = new ArrayList();

		list = model.search(bean, pageNo, pageSize);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (CollegeBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

	private static void testFinedByName() throws Exception {

		CollegeBean bean = new CollegeBean();

		CollegeModel model = new CollegeModel();

		String name = "BGIEM";

		bean = model.finedByName(name);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		} else {

			System.out.println("user not found..");

		}

	}

	private static void testFindByPk() throws Exception {

		CollegeBean bean = new CollegeBean();

		CollegeModel model = new CollegeModel();

		long id = 1;

		bean = model.finedByPk(id);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		} else {

			System.out.println("user not found..");

		}

	}

	private static void testDelete() throws Exception {

		long id = 3;

		CollegeModel model = new CollegeModel();

		model.delete(id);

	}

	private static void testUpdate() throws Exception {

		CollegeBean bean = new CollegeBean();

		CollegeModel model = new CollegeModel();

		bean.setName("SRC");
		bean.setAddress("JBP,MP");
		bean.setState("MP");
		bean.setCity("JBP");
		bean.setPhoneNo("5154458544");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(2);

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		CollegeBean bean = new CollegeBean();

		CollegeModel model = new CollegeModel();

		bean.setName("BGIEM");
		bean.setAddress("JBP,M.P.");
		bean.setState("M.P.");
		bean.setCity("JBP");
		bean.setPhoneNo("6284546548");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
