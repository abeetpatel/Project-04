package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.FollowUpBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.FollowUpModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/FollowUpCtl", urlPatterns = { "/ctl/FollowUpCtl" })
public class FollowUpCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean isValid = true;

		// validation for patient
		if (DataValidator.isNull(request.getParameter("patient"))) {
			request.setAttribute("patient", PropertyReader.getValue("error.require", "patient"));
			isValid = false;
		} else if (!DataValidator.isName(request.getParameter("patient"))) {
			request.setAttribute("patient", "Invalid patient Name");
			isValid = false;
		}

		// validation for doctor
		if (DataValidator.isNull(request.getParameter("doctor"))) {
			request.setAttribute("doctor", PropertyReader.getValue("error.require", "doctor"));
			isValid = false;
		} else if (!DataValidator.isName(request.getParameter("patient"))) {
			request.setAttribute("doctor", "Invalid doctor Name");
			isValid = false;
		}

		// validation for visitDate
		if (DataValidator.isNull(request.getParameter("visitDate"))) {
			request.setAttribute("visitDate", PropertyReader.getValue("error.require", "Visit Date"));
			isValid = false;
		} else if (!DataValidator.isDate(request.getParameter("visitDate"))) {
			request.setAttribute("visitDate", PropertyReader.getValue("error.date", "Visit Date"));
			isValid = false;
		}

		// validation for fees
		if (DataValidator.isNull(request.getParameter("fees"))) {
			request.setAttribute("fees", PropertyReader.getValue("error.require", "fees"));
			isValid = false;
		}

		return isValid;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		FollowUpBean bean = new FollowUpBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setPatient(DataUtility.getString(request.getParameter("patient")));
		bean.setDoctor(DataUtility.getString(request.getParameter("doctor")));
		bean.setVisitDate(DataUtility.getDate(request.getParameter("visitDate")));
		bean.setFees(DataUtility.getInt(request.getParameter("fees")));
		populateDTO(bean, request);

		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			FollowUpModel model = new FollowUpModel();

			try {
				FollowUpBean bean = model.finedByPk(id);
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

		FollowUpModel model = new FollowUpModel();

		FollowUpBean bean = (FollowUpBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {

			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("Follow Up Data Added Successfully", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			bean = (FollowUpBean) populateBean(request);
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
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FOLLOWUP_CTL, request, response);

			return;

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FOLLOWUP_LIST_CTL, request, response);

			return;

		}

	}

	@Override
	protected String getView() {
		return ORSView.FOLLOWUP_VIEW;
	}

}
