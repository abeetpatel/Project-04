package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.PurchaseOrderBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.PurchaseOrderModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/PurchaseOrderCtl", urlPatterns = { "/ctl/PurchaseOrderCtl" })
public class PurchaseOrderCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean isValid = true;

		// validation for totalQuantity
		if (DataValidator.isNull(request.getParameter("totalQuantity"))) {
			request.setAttribute("totalQuantity", PropertyReader.getValue("error.require", "Total Quantity"));
			isValid = false;
		}

		// validation for product
		if (DataValidator.isNull(request.getParameter("product"))) {
			request.setAttribute("product", PropertyReader.getValue("error.require", "Product"));
			isValid = false;
		}

		// validation for orderDate
		if (DataValidator.isNull(request.getParameter("orderDate"))) {
			request.setAttribute("orderDate", PropertyReader.getValue("error.require", "Order Date"));
			isValid = false;
		}
		if (!DataValidator.isDate(request.getParameter("orderDate"))) {
			request.setAttribute("orderDate", PropertyReader.getValue("error.date", "Order Date"));
			isValid = false;
		}

		// validation for totalCost
		if (DataValidator.isNull(request.getParameter("totalCost"))) {
			request.setAttribute("totalCost", PropertyReader.getValue("error.require", "Total Cost"));
			isValid = false;
		}

		return isValid;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		PurchaseOrderBean bean = new PurchaseOrderBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setTotalQuantity(DataUtility.getInt(request.getParameter("totalQuantity")));
		bean.setProduct(DataUtility.getStringData(request.getParameter("product")));
		bean.setOrderDate(DataUtility.getDate(request.getParameter("orderDate")));
		bean.setTotalCost(DataUtility.getInt(request.getParameter("totalCost")));

		populateDTO(bean, request);

		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			PurchaseOrderModel model = new PurchaseOrderModel();

			try {
				PurchaseOrderBean bean = model.finedByPk(id);
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
		long id = DataUtility.getLong(request.getParameter("id"));

		PurchaseOrderModel model = new PurchaseOrderModel();

		PurchaseOrderBean bean = (PurchaseOrderBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {

			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("Data Added Successfully", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PURCHASEORDER_CTL, request, response);
			return;
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			bean = (PurchaseOrderBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data Updated Successfully", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PURCHASEORDER_LIST_CTL, request, response);
			return;
		}

	}

	@Override
	protected String getView() {
		return ORSView.PURCHASEORDER_VIEW;
	}

}
