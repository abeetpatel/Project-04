<%@page import="in.co.rays.ctl.PurchaseOrderCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
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

	<form action="<%=ORSView.PURCHASEORDER_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.PurchaseOrderBean"
			scope="request" />

		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> Purchase Order
				</font>
			</h1>

			<!-- Success and Error Messages -->
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>

			<!-- Hidden Fields -->
			<input type="hidden" name="id" value="<%=bean.getId()%>">
			<table>
				<!-- Total Quantity -->
				<tr>
					<th align="left">Total Quantity <span style="color: red">*</span></th>
					<td><input type="number" name="totalQuantity"
						placeholder="Enter Total Quantity"
						value="<%=(DataUtility.getStringData(bean.getTotalQuantity()).equals("0") ? "" : bean.getTotalQuantity())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("totalQuantity", request)%></font></td>
				</tr>

				<!-- Product -->
				<tr>
					<th align="left">Last Name <span style="color: red">*</span></th>
					<td><input type="text" name="product"
						placeholder="Enter Product Name"
						value="<%=DataUtility.getStringData(bean.getProduct())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("product", request)%></font></td>
				</tr>

				<!-- Order Date -->
				<tr>
					<th align="left">Order Date <span style="color: red">*</span></th>
					<td><input style="width: 97%" type="date" name="orderDate"
						placeholder="Select Order Date" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("orderDate", request)%></font></td>

				</tr>

				<!-- Total Cost -->
				<tr>
					<th align="left">Total Cost <span style="color: red">*</span></th>
					<td><input type="number" name="totalCost"
						placeholder="Enter Total Cost"
						value="<%=(DataUtility.getStringData(bean.getTotalCost()).equals("0") ? "" : bean.getTotalCost())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("totalCost", request)%></font></td>
				</tr>

				<!-- Submit Buttons -->
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=PurchaseOrderCtl.OP_UPDATE%>">
						<input type="submit" name="operation"
						value="<%=PurchaseOrderCtl.OP_CANCEL%>"> <%
 	} else {
 %>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=PurchaseOrderCtl.OP_SAVE%>"> <input
						type="submit" name="operation"
						value="<%=PurchaseOrderCtl.OP_RESET%>"> <%
 	}
 %>
				</tr>
			</table>
		</div>
	</form>

	<%@include file="Footer.jsp"%>
</body>
</html>