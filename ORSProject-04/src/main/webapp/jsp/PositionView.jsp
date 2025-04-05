<%@page import="in.co.rays.ctl.PositionCtl"%>
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

	<form action="<%=ORSView.POSITION_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.PositionBean"
			scope="request" />
		<%
			List positionList = (List) request.getAttribute("positionList");
		%>

		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> Position
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
			<input type="hidden" name="identifier"
				value="<%=bean.getIdentifier()%>"> <input type="hidden"
				name="createdBy" value="<%=bean.getCreatedBy()%>"> <input
				type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
			<input type="hidden" name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<!-- Designation -->
				<tr>
					<th align="left">Designation <span style="color: red">*</span></th>
					<td><input type="text" name="designation"
						placeholder="Enter Designation"
						value="<%=DataUtility.getStringData(bean.getDesignation())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("designation", request)%></font></td>
				</tr>

				<!-- Opening Date -->
				<tr>
					<th align="left">Opening Date <span style="color: red">*</span></th>
					<td><input style="width: 97%" type="text" id="udate"
						name="openingDate" placeholder="Select Opening Date" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("openingDate", request)%></font></td>

				</tr>

				<!-- Required Experince -->
				<tr>
					<th align="left">Required Experince <span style="color: red">*</span></th>
					<td><input type="text" name="requiredExperince"
						placeholder="Enter Required Experince"
						value="<%=DataUtility.getStringData(bean.getRequiredExperince())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("requiredExperince", request)%></font></td>
				</tr>

				<!-- Condition -->
				<tr>
					<th align="left">Condition <span style="color: red">*</span></th>
					<td><input type="text" name="condition"
						placeholder="Enter Condition"
						value="<%=DataUtility.getStringData(bean.getCondition())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("condition", request)%></font></td>
				</tr>

				<!-- Submit Buttons -->
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getIdentifier() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=PositionCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=PositionCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=PositionCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=PositionCtl.OP_RESET%>">
						<%
							}
						%>
				</tr>
			</table>
		</div>
	</form>

	<%@include file="Footer.jsp"%>
</body>
</html>