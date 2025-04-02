<%@page import="in.co.rays.ctl.FacultyCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
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

	<form action="<%=ORSView.FACULTY_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.FacultyBean"
			scope="request" />
		<%
			List collegeList = (List) request.getAttribute("collegeList");
			List courseList = (List) request.getAttribute("courseList");
			List subjectList = (List) request.getAttribute("subjectList");
		%>

		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> Faculty
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
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<!-- First Name -->
				<tr>
					<th align="left">First Name <span style="color: red">*</span></th>
					<td><input type="text" name="firstName"
						placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>

				<!-- Last Name -->
				<tr>
					<th align="left">Last Name <span style="color: red">*</span></th>
					<td><input type="text" name="lastName"
						placeholder="Enter Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>

				<!-- Date of Birth -->
				<tr>
					<th align="left">DOB<span style="color: red">*</span></th>
					<td><input style="width: 97%" type="date" name="dob"
						placeholder="Select Date of Birth" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font></td>

				</tr>

				<!-- Gender -->
				<tr>
					<th align="left">Gender <span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> map = new HashMap<>();
							map.put("male", "Male");
							map.put("female", "Female");
						%> <%=HTMLUtility.getList("gender", bean.getGender(), map)%>
					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font></td>
				</tr>

				<!-- Mobile No -->
				<tr>
					<th align="left">Mobile No <span style="color: red">*</span></th>
					<td><input type="tel" name="mobileNo"
						placeholder="Enter Mobile No."
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
				</tr>

				<!-- Email -->
				<tr>
					<th align="left">Email <span style="color: red">*</span></th>
					<td><input type="text" name="email"
						placeholder="Enter Email Id"
						value="<%=DataUtility.getStringData(bean.getEmail())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("email", request)%></font></td>
				</tr>

				<!-- College -->

				<tr>
					<th align="left">College <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("collegeId", DataUtility.getStringData(bean.getCollegeId()), collegeList)%></td>

					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("collegeId", request)%></font></td>
				</tr>

				<!-- Course -->

				<tr>
					<th align="left">Course <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("courseId", DataUtility.getStringData(bean.getCourseId()), courseList)%></td>

					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("courseId", request)%></font></td>
				</tr>

				<!-- Subject -->

				<tr>
					<th align="left">Subject <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("subjectId", DataUtility.getStringData(bean.getSubjectId()), subjectList)%></td>

					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("subjectId", request)%></font></td>
				</tr>


				<!-- Submit Buttons -->
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=FacultyCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=FacultyCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=FacultyCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=FacultyCtl.OP_RESET%>">
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