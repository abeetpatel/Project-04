package in.co.rays.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.StaffMemberBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.model.StaffMemberModel;

public class TestStaffMemberModel {

	public static void main(String[] args) throws DatabaseException, ApplicationException, ParseException {

		// testNextPk();
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFinedByPk();
		testSearch();

	}

	private static void testSearch() throws ApplicationException {

		StaffMemberBean bean = new StaffMemberBean();
		StaffMemberModel model = new StaffMemberModel();
		int pageNo = 1;
		int pageSize = 5;

		List list = new ArrayList();
 
		bean.setIdentifier((long) 5);

		list = model.search(bean, pageNo, pageSize);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (StaffMemberBean) it.next();

			System.out.print(bean.getIdentifier());
			System.out.print("\n" + bean.getFullName());
			System.out.print("\n" + bean.getJoiningDate());
			System.out.print("\n" + bean.getDivision());
			System.out.println("\n" + bean.getPreviousEmployer());

		}

	}

	private static void testFinedByPk() throws ApplicationException {

		long pk = 5;

		StaffMemberBean bean = new StaffMemberBean();
		StaffMemberModel model = new StaffMemberModel();

		bean = model.finedByPk(pk);

		System.out.println(bean.getIdentifier());
		System.out.println(bean.getFullName());
		System.out.println(bean.getJoiningDate());
		System.out.println(bean.getDivision());
		System.out.println(bean.getPreviousEmployer());

	}

	private static void testDelete() throws ApplicationException {

		long id = 1;

		StaffMemberModel model = new StaffMemberModel();

		model.delete(id);

	}

	private static void testUpdate() throws ParseException, ApplicationException {

		StaffMemberBean bean = new StaffMemberBean();

		StaffMemberModel model = new StaffMemberModel();

		SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");

		bean.setIdentifier((long) 1);
		bean.setFullName("Mohit Prajapat");
		bean.setJoiningDate(smp.parse("2025-01-02"));
		bean.setDivision("2nd");
		bean.setPreviousEmployer("Goutam Prajapat");

		model.update(bean);

	}

	private static void testAdd() throws ApplicationException, ParseException {

		StaffMemberBean bean = new StaffMemberBean();

		StaffMemberModel model = new StaffMemberModel();

		SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFullName("Mohit Prajapat");
		bean.setJoiningDate(smp.parse("2025-01-02"));
		bean.setDivision("2nd");
		bean.setPreviousEmployer("Goutam Prajapat");

		model.add(bean);

	}

	private static void testNextPk() throws DatabaseException {

		StaffMemberModel model = new StaffMemberModel();

		long pk = model.nextPk();

		System.out.println(pk);

	}

}
