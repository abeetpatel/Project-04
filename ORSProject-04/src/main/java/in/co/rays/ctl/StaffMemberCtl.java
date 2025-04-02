package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.StaffMemberBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.StaffMemberModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/StaffMemberCtl", urlPatterns = { "/ctl/StaffMemberCtl" })
public class StaffMemberCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {

		StaffMemberModel model = new StaffMemberModel();

		try {
			List staffMemberList = model.list();

			request.setAttribute("staffMemberList", staffMemberList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

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

		// validation for joiningDate
		if (DataValidator.isNull(request.getParameter("joiningDate"))) {
			request.setAttribute("joiningDate", PropertyReader.getValue("error.require", "Joining Date"));
			isValid = false;
		}if (!DataValidator.isDate(request.getParameter("joiningDate"))) {
			request.setAttribute("joiningDate", PropertyReader.getValue("error.date", "Joining Date"));
			isValid = false;
		}

		// validation for division
		if (DataValidator.isNull(request.getParameter("division"))) {
			request.setAttribute("division", PropertyReader.getValue("error.require", "Division"));
			isValid = false;
		}
		
		// validation for previousEmployer
		if (DataValidator.isNull(request.getParameter("previousEmployer"))) {
			request.setAttribute("previousEmployer", PropertyReader.getValue("error.require", "Previous Employer"));
			isValid = false;
		}

		return isValid;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		StaffMemberBean bean = new StaffMemberBean();

		bean.setIdentifier(DataUtility.getLong(request.getParameter("identifier")));
		bean.setFullName(DataUtility.getString(request.getParameter("fullName")));
		bean.setJoiningDate(DataUtility.getDate(request.getParameter("joiningDate")));
		bean.setDivision(DataUtility.getString(request.getParameter("division")));
		bean.setPreviousEmployer(DataUtility.getString(request.getParameter("previousEmployer")));

		populateDTO(bean, request);

		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("identifier"));

		if (id > 0) {

			StaffMemberModel model = new StaffMemberModel();

			try {
				StaffMemberBean bean = model.finedByPk(id);
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
		Long id = DataUtility.getLong(request.getParameter("identifier"));

		StaffMemberModel model = new StaffMemberModel();

		StaffMemberBean bean = (StaffMemberBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {

			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("User Added Successfully", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.STAFFMEMBER_CTL, request, response);

			return;

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			bean = (StaffMemberBean) populateBean(request);
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

			ServletUtility.redirect(ORSView.STAFFMEMBER_LIST_CTL, request, response);
			return;

		}
	}

	@Override
	protected String getView() {
		return ORSView.STAFFMEMBER_VIEW;
	}

}
