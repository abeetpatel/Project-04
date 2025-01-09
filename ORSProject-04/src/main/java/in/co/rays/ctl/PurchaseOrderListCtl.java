package in.co.rays.ctl;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "/PurchaseOrderListCtl", urlPatterns = { "/ctl/PurchaseOrderListCtl" })
public class PurchaseOrderListCtl extends BaseCtl {

	@Override
	protected String getView() {
		return ORSView.PURCHASEORDER_LIST_VIEW;
	}

}
