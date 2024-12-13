package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.bean.UserBean;
import in.co.rays.model.UserModel;

public class TestUserModel {

	public static void main(String[] args) throws Exception {

		 testAdd();
		// testUpdate();
		// testDelete();
		// testFinedByPk();
		// testFinedByLogin();
		// testSearch();

	}

	private static void testSearch() throws Exception {

		UserBean bean = new UserBean();

		UserModel model = new UserModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = s.parse("2024-12-11 23:53:49");
		Timestamp ts = new Timestamp(d.getTime());
		System.out.println(ts);

		int pageNo = 1;
		int pageSize = 5;

		// bean.setFirstName("abeet");
		// bean.setLastName("patel");
		// bean.setLogin("demo@gmail.com");
		// bean.setPassword("demo123");
		// bean.setDob(sdf.parse("2000-01-01"));
		// bean.setMobileNo("6515485248");
		// bean.setRoleId(12);
		// bean.setGender("male");
		// bean.setCreatedBy("admin");
		// bean.setModifiedBy("admin");
		// bean.setCreatedDatetime(ts);
		bean.setModifiedDatetime(ts);

		List list = model.search(bean, pageNo, pageSize);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (UserBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		}

	}

	private static void testFinedByLogin() throws Exception {

		String login = "demo1@gmail.com";

		UserModel model = new UserModel();

		UserBean bean = new UserBean();

		bean = model.finedByLogin(login);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		} else {

			System.out.println("user not found..");

		}

	}

	private static void testFinedByPk() throws Exception {

		long id = 1;

		UserModel model = new UserModel();

		UserBean bean = new UserBean();

		bean = model.finedByPk(id);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		} else {

			System.out.println("user not found..");

		}

	}

	private static void testDelete() throws Exception {

		int id = 2;

		UserModel model = new UserModel();

		model.delete(id);

	}

	private static void testUpdate() throws Exception {

		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("demo");
		bean.setLastName("demo");
		bean.setLogin("demo1@gmail.com");
		bean.setPassword("demo123");
		bean.setDob(sdf.parse("2001-01-01"));
		bean.setMobileNo("6515485248");
		bean.setRoleId(2);
		bean.setGender("female");
		bean.setCreatedBy("admin1");
		bean.setModifiedBy("admin1");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("ram");
		bean.setLastName("patel");
		bean.setLogin("demo4@gmail.com");
		bean.setPassword("demo123");
		bean.setDob(sdf.parse("2000-01-01"));
		bean.setMobileNo("6515485248");
		bean.setRoleId(12);
		bean.setGender("male");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
