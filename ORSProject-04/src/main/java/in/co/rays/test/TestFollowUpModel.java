package in.co.rays.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import in.co.rays.bean.FollowUpBean;
import in.co.rays.model.FollowUpModel;

public class TestFollowUpModel {

	public static void main(String[] args) throws Exception {

		testAdd();

	}

	private static void testAdd() throws Exception {

		FollowUpBean bean = new FollowUpBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		FollowUpModel model = new FollowUpModel();

		bean.setPatient("demo");
		bean.setDoctor("dr. demo");
		bean.setVisitDate(sdf.parse("2025-01-07"));
		bean.setFees(1000);

		model.add(bean);

	}

}
