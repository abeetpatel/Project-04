<%@page import="in.co.rays.bean.StaffMemberBean"%>
<%@page import="in.co.rays.ctl.StaffMemberListCtl"%>
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
		<form action="<%=ORSView.STAFFMEMBER_LIST_CTL%>" method="post">

			<jsp:useBean id="bean" class="in.co.rays.bean.StaffMemberBean"
				scope="request"></jsp:useBean>

			<div align="center">
				<h1>
					<font color="navy">Staff Member List</font>
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

				List staffMemberList = (List) request.getAttribute("staffMemberList");

				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>

					<td align="center"><label><b>Full Name :</b></label> <%=HTMLUtility.getList("identifier", DataUtility.getStringData(bean.getIdentifier()),
						staffMemberList)%>
						<input type="submit" name="operation"
						value="<%=StaffMemberListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=StaffMemberListCtl.OP_RESET%>"></td>
				</tr>
			</table>

			<br>

			<table border="1%" style="width: 100%">
				<tr style="background-color: lavender; color: black;">
					<th><input type="checkbox" id="selectall"></th>
					<th>S.No.</th>
					<th>Full Name</th>
					<th>Joining Date</th>
					<th>Division</th>
					<th>Previous Employer</th>
					<th>Edit</th>

				</tr>

				<%
					while (it.hasNext()) {
							bean = (StaffMemberBean) it.next();
				%>

				<tr align="center">
					<td><input type="checkbox" class="case" name="ids"
						value="<%=bean.getIdentifier()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getFullName()%></td>
					<td><%=bean.getJoiningDate()%></td>
					<td><%=bean.getDivision()%></td>
					<td><%=bean.getPreviousEmployer()%></td>
					<td><a
						href="<%=ORSView.STAFFMEMBER_CTL%>?id=<%=bean.getIdentifier()%>">Edit</a></td>

				</tr>

				<%
					}
				%>

			</table>

			<br>

			<table style="width: 100%">
				<tr>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=StaffMemberListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=StaffMemberListCtl.OP_NEW%>"></td>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=StaffMemberListCtl.OP_DELETE%>"></td>
					<td style="text-align: right;"><input type="submit"
						name="operation" value="<%=StaffMemberListCtl.OP_NEXT%>"
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
						value="<%=StaffMemberListCtl.OP_BACK%>"></td>
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