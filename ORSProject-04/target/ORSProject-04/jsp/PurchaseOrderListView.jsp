<%@page import="in.co.rays.bean.PurchaseOrderBean"%>
<%@page import="in.co.rays.ctl.PurchaseOrderListCtl"%>
<%@page import="in.co.rays.ctl.PurchaseOrderCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<div align="center">
		<form action="<%=ORSView.PURCHASEORDER_LIST_CTL%>" method="post">

			<jsp:useBean id="bean" class="in.co.rays.bean.PurchaseOrderBean"
				scope="request"></jsp:useBean>

			<div align="center">
				<h1>
					<font color="navy">Purchase Order List</font>
				</h1>
			</div>

			<div align="center" style="height: 15px; margin-bottom: 12px">
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
				<h3>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
			</div>

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List productList = (List) request.getAttribute("productList");

				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Product :</b></label><%=HTMLUtility.getList("id", DataUtility.getStringData(bean.getId()), productList)%>
						<input type="submit" name="operation"
						value="<%=PurchaseOrderListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=PurchaseOrderListCtl.OP_RESET%>"></td>
				</tr>
			</table>

			<br>

			<table border="1%" style="width: 100%">
				<tr style="background-color: lavender; color: black;">
					<th><input type="checkbox" id="selectall"></th>
					<th>S.No.</th>
					<th>Total Quantity</th>
					<th>Product</th>
					<th>Order Date</th>
					<th>Total Cost</th>
					<th>Edit</th>

				</tr>
				<%
					while (it.hasNext()) {
							bean = (PurchaseOrderBean) it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" class="case" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getTotalQuantity()%></td>
					<td><%=bean.getProduct()%></td>
					<td><%=bean.getOrderDate()%></td>
					<td><%=bean.getTotalCost()%></td>
					<td><a
						href="<%=ORSView.PURCHASEORDER_CTL%>?id=<%=bean.getId()%>">edit</a></td>

				</tr>
				<%
					}
				%>
			</table>

			<br>

			<table style="width: 100%">
				<tr>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=PurchaseOrderListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=PurchaseOrderListCtl.OP_NEW%>"></td>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=PurchaseOrderListCtl.OP_DELETE%>"></td>
					<td style="text-align: right;"><input type="submit"
						name="operation" value="<%=PurchaseOrderListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<br>
			<table>
				<tr>
					<td align="right"><input type="submit" name="operation"
						value="<%=PurchaseOrderListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
				}
			%>
		</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>