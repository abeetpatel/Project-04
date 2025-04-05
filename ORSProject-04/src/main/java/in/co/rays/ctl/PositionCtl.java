package in.co.rays.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.PositionBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.PositionModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/PositionCtl", urlPatterns = { "/ctl/PositionCtl" })
public class PositionCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean isValid = true;

		// validation for designation
		if (DataValidator.isNull(request.getParameter("designation"))) {
			request.setAttribute("designation", PropertyReader.getValue("error.require", "Designation"));
			isValid = false;
		}

		// validation for openingDate
		if (DataValidator.isNull(request.getParameter("openingDate"))) {
			request.setAttribute("openingDate", PropertyReader.getValue("error.require", "Opening Date"));
			isValid = false;
		} else if (!DataValidator.isDate(request.getParameter("openingDate"))) {
			request.setAttribute("openingDate", PropertyReader.getValue("error.date", "Opening Date"));
			isValid = false;
		}

		// validation for requiredExperince
		if (DataValidator.isNull(request.getParameter("requiredExperince"))) {
			request.setAttribute("requiredExperince", PropertyReader.getValue("error.require", "Required Experince"));
			isValid = false;
		}

		// validation for condition
		if (DataValidator.isNull(request.getParameter("condition"))) {
			request.setAttribute("condition", PropertyReader.getValue("error.require", "Condition"));
			isValid = false;
		}

		return isValid;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		PositionBean bean = new PositionBean();

		bean.setIdentifier(DataUtility.getLong(request.getParameter("identifier")));
		bean.setDesignation(DataUtility.getString(request.getParameter("designation")));
		bean.setOpeningDate(DataUtility.getDate(request.getParameter("openingDate")));
		bean.setRequiredExperince(DataUtility.getString(request.getParameter("requiredExperince")));
		bean.setCondition(DataUtility.getString(request.getParameter("condition")));

		populateDTO(bean, request);

		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		Long identifier = DataUtility.getLong(request.getParameter("identifier"));

		if (identifier > 0) {

			PositionModel model = new PositionModel();

			try {
				PositionBean bean = model.finedByPk(identifier);
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
		Long identifier = DataUtility.getLong(request.getParameter("identifier"));

		PositionModel model = new PositionModel();

		PositionBean bean = (PositionBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {

			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("Position Added Successfully", request);
				ServletUtility.forward(getView(), request, response);

			} catch (ApplicationException e) {
				e.printStackTrace();
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.POSITION_CTL, request, response);

			return;

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			bean = (PositionBean) populateBean(request);
			try {
				if (identifier > 0) {
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

			ServletUtility.redirect(ORSView.POSITION_LIST_CTL, request, response);
			return;

		}

	}

	@Override
	protected String getView() {
		return ORSView.POSITION_VIEW;
	}

}
