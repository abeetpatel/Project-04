package in.co.rays.test;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testFinedById();
		testSearch();

	}

	private static void testSearch() throws Exception {

		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// bean.setName("sales");
		// bean.setDescription("manager");
		// bean.setCreatedBy("admin@gmail.com");
		// bean.setModifiedBy("admin@gmail.com");
		// bean.setCreatedDatetime(sdf.parse("2024-12-11 15:40:58"));

		List list = model.search(bean);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (RoleBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

	private static void testFinedById() throws Exception {

		int id = 1;

		RoleBean bean = new RoleBean();

		RoleModel model = new RoleModel();

		bean = model.finedById(id);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		} else {

			System.out.println("user not found...");

		}

	}

	private static void testDelete() throws Exception {

		int id = 2;

		RoleModel model = new RoleModel();

		model.delete(id);

	}

	private static void testUpdate() throws Exception {

		RoleBean bean = new RoleBean();

		bean.setName("manager");
		bean.setDescription("manager");
		bean.setCreatedBy("admin2@gamil.com");
		bean.setModifiedBy("admin2@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(2);

		RoleModel model = new RoleModel();

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		RoleBean bean = new RoleBean();

		bean.setName("sales");
		bean.setDescription("sales");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		RoleModel model = new RoleModel();

		model.add(bean);

	}

}
