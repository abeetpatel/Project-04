<%@page import="in.co.rays.bean.FacultyBean"%>
<%@page import="in.co.rays.ctl.FacultyListCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
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
		<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">

			<jsp:useBean id="bean" class="in.co.rays.bean.FacultyBean"
				scope="request"></jsp:useBean>

			<div align="center">
				<h1>
					<font color="navy">Faculty List</font>
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
				List collegeList = (List) request.getAttribute("collegeList");
				List courseList = (List) request.getAttribute("courseList");
				List subjectList = (List) request.getAttribute("subjectList");
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>First Name :</b></label> <input
						type="text" name="firstName"
						value="<%=ServletUtility.getParameter("firstName", request)%>">
						<label><b>Last Name :</b></label> <input type="text"
						name="lastName"
						value="<%=ServletUtility.getParameter("lastName", request)%>">
						<label><b>Email :</b></label> <input type="text" name="email"
						value="<%=ServletUtility.getParameter("email", request)%>">
						<label><b>Mobile No. :</b></label> <input type="text"
						name="mobileNo"
						value="<%=ServletUtility.getParameter("mobileNo", request)%>">
						<label><b>Gender :</b></label> <%
 	HashMap<String, String> map = new HashMap<>();
 		map.put("male", "Male");
 		map.put("female", "Female");
 %> <%=HTMLUtility.getList("gender", bean.getGender(), map)%> <label><b>College
								:</b></label> <%=HTMLUtility.getList("collegeId", DataUtility.getStringData(bean.getCollegeId()), collegeList)%><label><b>Course
								:</b></label> <%=HTMLUtility.getList("courseId", DataUtility.getStringData(bean.getCourseId()), courseList)%><label><b>Subject
								:</b></label> <%=HTMLUtility.getList("subjectId", DataUtility.getStringData(bean.getSubjectId()), subjectList)%><input
						type="submit" name="operation"
						value="<%=FacultyListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=FacultyListCtl.OP_RESET%>"></td>
				</tr>
			</table>

			<br>

			<table border="1%" style="width: 100%">
				<tr style="background-color: lavender; color: black;">
					<th><input type="checkbox" id="selectall"></th>
					<th>S.No.</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>DOB</th>
					<th>Gender</th>
					<th>Mobile No.</th>
					<th>email</th>
					<th>College Name</th>
					<th>Course Name</th>
					<th>Subject Name</th>
					<th>Edit</th>

				</tr>
				<%
					while (it.hasNext()) {
							bean = (FacultyBean) it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" class="case" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getDob()%></td>
					<td><%=bean.getGender()%></td>
					<td><%=bean.getMobileNo()%></td>
					<td><%=bean.getEmail()%></td>
					<td><%=bean.getCollegeName()%></td>
					<td><%=bean.getCourseName()%></td>
					<td><%=bean.getSubjectName()%></td>
					<td><a href="<%=ORSView.USER_CTL%>?id=<%=bean.getId()%>">edit</a></td>

				</tr>
				<%
					}
				%>
			</table>

			<br>

			<table style="width: 100%">
				<tr>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_NEW%>"></td>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_DELETE%>"></td>
					<td style="text-align: right;"><input type="submit"
						name="operation" value="<%=FacultyListCtl.OP_NEXT%>"
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
						value="<%=FacultyListCtl.OP_BACK%>"></td>
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