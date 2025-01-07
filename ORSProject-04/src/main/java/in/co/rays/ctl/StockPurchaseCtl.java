package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.StockPurchaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.StockPurchaseModel;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/StockPurchaseCtl", urlPatterns = { "/ctl/StockPurchaseCtl" })
public class StockPurchaseCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean isValid = true;

		// validation for quantity
		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "quantity"));
			isValid = false;
		}

		// validation for purchasePrice
		if (DataValidator.isNull(request.getParameter("purchasePrice"))) {
			request.setAttribute("purchasePrice", PropertyReader.getValue("error.require", "purchasePrice"));
			isValid = false;
		}

		// validation for purchaseDate
		if (DataValidator.isNull(request.getParameter("purchaseDate"))) {
			request.setAttribute("purchaseDate", PropertyReader.getValue("error.require", "purchaseDate"));
			isValid = false;
		} else if (!DataValidator.isDate(request.getParameter("purchaseDate"))) {
			request.setAttribute("purchaseDate", PropertyReader.getValue("error.date", "purchaseDate"));
			isValid = false;
		}

		// validation for orderType
		if (DataValidator.isNull(request.getParameter("orderType"))) {
			request.setAttribute("orderType", PropertyReader.getValue("error.require", "orderType"));
			isValid = false;
		}

		return isValid;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		StockPurchaseBean bean = new StockPurchaseBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setQuantity(DataUtility.getInt(request.getParameter("quantity")));
		bean.setPurchasePrice(DataUtility.getLong(request.getParameter("purchasePrice")));
		bean.setPurchaseDate(DataUtility.getDate(request.getParameter("purchaseDate")));
		bean.setOrderType(DataUtility.getStringData(request.getParameter("orderType")));

		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			StockPurchaseModel model = new StockPurchaseModel();

			try {
				StockPurchaseBean bean = model.finedByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		StockPurchaseModel model = new StockPurchaseModel();

		StockPurchaseBean bean = (StockPurchaseBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {

			try {
				model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully added", request);
				ServletUtility.forward(getView(), request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.STOCKPURCHASE_CTL, request, response);

			return;

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			bean = (StockPurchaseBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully updated", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
				return;
			} 
		}

		else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.STOCKPURCHASE_LIST_CTL, request, response);

		}

	}

	@Override
	protected String getView() {
		return ORSView.STOCKPURCHASE_VIEW;
	}

}
