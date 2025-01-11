package in.co.rays.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import in.co.rays.bean.PurchaseOrderBean;
import in.co.rays.model.PurchaseOrderModel;

public class TestPurchaseOrderModel {

	public static void main(String[] args) throws Exception {

		testAdd();

	}

	private static void testAdd() throws Exception {

		PurchaseOrderBean bean = new PurchaseOrderBean();

		PurchaseOrderModel model = new PurchaseOrderModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setTotalQuantity(5);
		bean.setProduct("demo");
		bean.setOrderDate(sdf.parse("2025-01-10"));
		bean.setTotalCost(1000);

		model.add(bean);

	}
}
