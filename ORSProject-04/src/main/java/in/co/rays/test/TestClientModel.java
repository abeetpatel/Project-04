package in.co.rays.test;

import in.co.rays.bean.ClientBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.ClientModel;

public class TestClientModel {

	public static void main(String[] args) throws ApplicationException {

		testFinedByPk();

	}

	private static void testFinedByPk() throws ApplicationException {

		ClientBean bean = new ClientBean();

		ClientModel model = new ClientModel();

		bean = model.finedByPk(1);

		System.out.println(bean.getId());
		System.out.println(bean.getFullName());
		System.out.println(bean.getPhone());
		System.out.println(bean.getAppointmentDate());
		System.out.println(bean.getIllness());

	}

}
