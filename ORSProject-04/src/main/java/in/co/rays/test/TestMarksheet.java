package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.model.MarksheetModel;

public class TestMarksheet {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testFinedByPk();
		// testFinedByRollNo();
		testSearch();

	}

	private static void testSearch() throws Exception {

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int pageNo = 1;
		int pageSize = 12;

		// bean.setRollNo("A105");
		// bean.setCreatedBy("admin");
     	// bean.setModifiedBy("admin");
		// bean.setCreatedDatetime(new Timestamp(sdf.parse("2024-12-14 02:27:05").getTime()));
		bean.setModifiedDatetime(new Timestamp(sdf.parse("2024-12-14 02:32:03").getTime()));

		List list = new ArrayList();
		list = model.search(bean, pageNo, pageSize);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (MarksheetBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getRollNo());
			System.out.print("\t" + bean.getStudentId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getPhysics());
			System.out.print("\t" + bean.getChemistry());
			System.out.print("\t" + bean.getMaths());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

	private static void testFinedByRollNo() throws Exception {

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();

		bean = model.finedByRollNo("A105");

		if(bean != null) {
		System.out.println(bean.getId());
		System.out.println(bean.getRollNo());
		System.out.println(bean.getStudentId());
		System.out.println(bean.getName());
		System.out.println(bean.getPhysics());
		System.out.println(bean.getChemistry());
		System.out.println(bean.getMaths());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
		}else {
			
			System.out.println("user not found");
			
		}

	}

	private static void testFinedByPk() throws Exception {

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();

		bean = model.finedByPk(2);

		if(bean != null) {
		System.out.println(bean.getId());
		System.out.println(bean.getRollNo());
		System.out.println(bean.getStudentId());
		System.out.println(bean.getName());
		System.out.println(bean.getPhysics());
		System.out.println(bean.getChemistry());
		System.out.println(bean.getMaths());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
		}else {
			
			System.out.println("user not found");
			
		}

	}

	private static void testDelete() throws Exception {

		long id = 3;

		MarksheetModel model = new MarksheetModel();

		model.delete(id);

	}

	private static void testUpdate() throws Exception {

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		bean.setId(2);
		bean.setRollNo("A104");
		bean.setStudentId(2);
		bean.setPhysics(58);
		bean.setChemistry(40);
		bean.setMaths(75);
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		bean.setRollNo("A106");
		bean.setStudentId(1);
		bean.setPhysics(54);
		bean.setChemistry(60);
		bean.setMaths(45);
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
