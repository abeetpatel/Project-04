<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.ctl.StockPurchaseCtl"%>
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

	<form action="<%=ORSView.STOCKPURCHASE_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.StockPurchaseBean"
			scope="request" />

		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> Stock Purchase
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
				<!-- Quantity -->
				<tr>
					<th align="left">Quantity <span style="color: red">*</span></th>
					<td><input type="number" name="quantity"
						placeholder="Enter Quantity"
						value="<%=DataUtility.getStringData(bean.getQuantity()).equals("0") ? "" : bean.getQuantity()%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("quantity", request)%></font></td>
				</tr>

				<!-- Purchase Price -->
				<tr>
					<th align="left">Purchase Price <span style="color: red">*</span></th>
					<td><input type="number" name="purchasePrice"
						placeholder="Enter Purchase Price"
						value="<%=DataUtility.getStringData(bean.getPurchasePrice()).equals("0") ? "" : bean.getPurchasePrice()%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("purchasePrice", request)%></font></td>
				</tr>

				<!-- Purchase Date -->
				<tr>
					<th align="left">Purchase Date<span style="color: red">*</span></th>
					<td><input style="width: 97%" type="text" id="udate"
						name="purchaseDate" placeholder="Select Purchase Date" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("purchaseDate", request)%></font></td>

				</tr>

				<!-- Order Type -->
				<tr>
					<th align="left">Order Type <span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> map = new HashMap<>();
							map.put("market", "Market");
							map.put("limit", "Limit");
						%> <%=HTMLUtility.getList("orderType", bean.getOrderType(), map)%>
					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("orderType", request)%></font></td>
				</tr>

				<!-- Submit Buttons -->
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=StockPurchaseCtl.OP_UPDATE%>">
						<input type="submit" name="operation"
						value="<%=StockPurchaseCtl.OP_CANCEL%>"> <%
 	} else {
 %>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=StockPurchaseCtl.OP_SAVE%>"> <input
						type="submit" name="operation"
						value="<%=StockPurchaseCtl.OP_RESET%>"> <%
 	}
 %>
				</tr>
			</table>
		</div>
	</form>


	<%@include file="Footer.jsp"%></ body>
</html>