package in.co.rays.test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.bean.RoleBean;
import in.co.rays.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		testDelete();

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
