package in.co.rays.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import in.co.rays.bean.StockPurchaseBean;
import in.co.rays.model.StockPurchaseModel;

public class TestStockPurchase {

	public static void main(String[] args) throws Exception {

		testAdd();

	}

	private static void testAdd() throws Exception {

		StockPurchaseBean bean = new StockPurchaseBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StockPurchaseModel model = new StockPurchaseModel();

		bean.setQuantity(10);
		bean.setPurchasePrice(500);
		bean.setPurchaseDate(sdf.parse("2024-01-02"));
		bean.setOrderType("stock");

		model.add(bean);

	}

}
