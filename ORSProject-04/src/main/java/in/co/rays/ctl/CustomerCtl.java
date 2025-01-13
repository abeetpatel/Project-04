package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CustomerBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CustomerModel;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/CustomerCtl", urlPatterns = { "/ctl/CustomerCtl" })
public class CustomerCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {

		CustomerModel model = new CustomerModel();

		try {
			List customerList = model.list();

			request.setAttribute("customerList", customerList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean isValid = true;

		// validation for clientName
		if (DataValidator.isNull(request.getParameter("clientName"))) {
			request.setAttribute("clientName", PropertyReader.getValue("error.require", "Client Name"));
			isValid = false;
		} else if (!DataValidator.isName(request.getParameter("clientName"))) {
			request.setAttribute("clientName", "Invalid Client Name");
			isValid = false;
		}

		// validation for location
		if (DataValidator.isNull(request.getParameter("location"))) {
			request.setAttribute("location", PropertyReader.getValue("error.require", "Location"));
			isValid = false;
		}

		// validation for contactNumber
		if (DataValidator.isNull(request.getParameter("contactNumber"))) {
			request.setAttribute("contactNumber", PropertyReader.getValue("error.require", "Contact Number"));
			isValid = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("contactNumber"))) {
			request.setAttribute("contactNumber", "Contact No must have 10 digits");
			isValid = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("contactNumber"))) {
			request.setAttribute("contactNumber", "Invalid Contact No");
			isValid = false;
		}

		// validation for importance
		if (DataValidator.isNull(request.getParameter("importance"))) {
			request.setAttribute("importance", PropertyReader.getValue("error.require", "Importance"));
			isValid = false;
		}

		return isValid;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CustomerBean bean = new CustomerBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setClientName(DataUtility.getString(request.getParameter("clientName")));
		bean.setLocation(DataUtility.getString(request.getParameter("location")));
		bean.setContactNumber(DataUtility.getString(request.getParameter("contactNumber")));
		bean.setImportance(DataUtility.getString(request.getParameter("importance")));

		populateDTO(bean, request);

		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			CustomerModel model = new CustomerModel();

			try {
				CustomerBean bean = model.finedByPk(id);
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

		CustomerModel model = new CustomerModel();

		CustomerBean bean = (CustomerBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {

			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("User Added Successfully", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CUSTOMER_CTL, request, response);

			return;

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			bean = (CustomerBean) populateBean(request);
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

			ServletUtility.redirect(ORSView.CUSTOMER_LIST_CTL, request, response);
			return;

		}
	}

	@Override
	protected String getView() {
		return ORSView.CUSTOMER_VIEW;
	}

}
