package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.ClientBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.ClientModel;
import in.co.rays.model.RoleModel;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/ClientCtl", urlPatterns = { "/ctl/ClientCtl" })
public class ClientCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean isValid = true;

		// validation for fullName
		if (DataValidator.isNull(request.getParameter("fullName"))) {
			request.setAttribute("fullName", PropertyReader.getValue("error.require", "Full Name"));
			isValid = false;
		} else if (!DataValidator.isName(request.getParameter("fullName"))) {
			request.setAttribute("fullName", "Invalid Full Name");
			isValid = false;
		}

		// validation for appointmentDate
		if (DataValidator.isNull(request.getParameter("appointmentDate"))) {
			request.setAttribute("appointmentDate", PropertyReader.getValue("error.require", "Appointment Date"));
			isValid = false;
		}

		// validation for phone
		if (DataValidator.isNull(request.getParameter("phone"))) {
			request.setAttribute("phone", PropertyReader.getValue("error.require", "Phone"));
			isValid = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("phone"))) {
			request.setAttribute("phone", "Mobile No must have 10 digits");
			isValid = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("phone"))) {
			request.setAttribute("phone", "Invalid Mobile No");
			isValid = false;
		}

		// validation for illness
		if (DataValidator.isNull(request.getParameter("illness"))) {
			request.setAttribute("illness", PropertyReader.getValue("error.require", "Illness"));
			isValid = false;
		}

		return isValid;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		ClientBean bean = new ClientBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFullName(DataUtility.getString(request.getParameter("fullName")));
		bean.setAppointmentDate(DataUtility.getDate(request.getParameter("appointmentDate")));
		bean.setPhone(DataUtility.getString(request.getParameter("phone")));
		bean.setIllness(DataUtility.getString(request.getParameter("illness")));

		populateDTO(bean, request);

		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			ClientModel model = new ClientModel();

			try {
				ClientBean bean = model.finedByPk(id);
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

		ClientModel model = new ClientModel();

		ClientBean bean = (ClientBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {

			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("User Added Successfully", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CLIENT_CTL, request, response);

			return;

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			bean = (ClientBean) populateBean(request);
			System.out.println("");
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
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CLIENT_LIST_CTL, request, response);

			return;
		}

	}

	@Override
	protected String getView() {
		return ORSView.CLIENT_VIEW;
	}

}
