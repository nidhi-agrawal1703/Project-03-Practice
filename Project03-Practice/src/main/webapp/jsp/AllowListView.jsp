<%@page import="in.co.rays.proj3.controller.ORSView"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.proj3.util.HTMLUtility"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.controller.AllowListCtl"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@page import="in.co.rays.proj3.dto.AllowDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>Allow List</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
 <style>
        body {
            
            padding-top: 90px;
        }

        .search-row {
            width: 90%;
            /* SEARCH WIDTH BADHA DIYA */
            margin: auto;
            margin-bottom: 20px;
        }
    </style>


</head>
<body>
<!-- Header File -->
<%@include file="Header.jsp"%>

<!-- JSP UseBean Tag -->
<jsp:useBean id="dto" class="in.co.rays.proj3.dto.AllowDTO" ></jsp:useBean>

			
<!-- Main Content -->

<div align="center" class="container-fluid">
<h1 class="text-dark font-weight-bold pb-3"><u>Allow List</u></h1>

						<!-- Static Success Message -->
 						<%if(ServletUtility.getSuccessMessage(request).length()>0){ %>
 						<div class="alert alert-success alert-dismissable">
 						<button type="button" class="close" data-dismiss="alert">&times;</button>
                            <h4><%=ServletUtility.getSuccessMessage(request)%></h4>
 						</div>
 						<%} %>
 						
 						
 						<!-- Static Error Message -->
 						<%if(ServletUtility.getErrorMessage(request).length()>0) {%>
 						<div class="alert alert-danger alert-dismissable">
 						<button type="button" class="close" data-dismiss="alert">&times;</button>
                            <h4><%=ServletUtility.getErrorMessage(request)%></h4>
 						</div>
 						<% }%>
 <!-- Form -->						
	<form action="<%=ORSView.ALLOW_LIST_CTL%>" method="post"> 
	
	<%
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = ((pageNo - 1) * pageSize) + 1;
	int nextPageSize = (request.getAttribute("nextListSize") != null)
		    ? DataUtility.getInt(request.getAttribute("nextListSize").toString())
		    : 0;
	
	
	List<AllowDTO> list = (List<AllowDTO>) ServletUtility.getList(request);
	
	Iterator<AllowDTO> it = list.iterator();

                if (list.size() != 0) {
            %>
	
	
	
	<!-- Hidden Fields -->
<input type="hidden" name="pageNo" value="<%=pageNo%>"> 
<input type="hidden" name="pageSize" value="<%=pageSize%>">
							
<!-- Search Form -->
<div class="row search-row">
	<div class="col-md-3">
	</div>
	<div class="col-md-3">
		<input type="text" class="form-control" name="allowCode" placeholder="Allow Code" value="<%=ServletUtility.getParameter("allowCode", request)%>">
	</div>
	<div class="col-md-3">
		<input type="text" class="form-control" name="userName" placeholder="UserName" value="<%=ServletUtility.getParameter("userName", request)%>">
	</div>
	<div class="col-md-3">
		<input type="submit" class="btn btn-primary btn-md" name="operation" value="<%=AllowListCtl.OP_SEARCH%>">
        <input type="submit" class="btn btn-secondary btn-md" name="operation" value="<%=AllowListCtl.OP_RESET%>">
	</div>
</div>

<!-- Table -->
<div>
	<table class="table table-bordered table-light table-hover">
		<thead>
                    <tr align="center" style="background:#8C8C8C;">
                    <th width="5%"><input type="checkbox" id="selectall" /></th>
                    <th align="center">S.NO</th>
							<th>Allow Code</th>
                       		 <th>UserName</th>
                       		 <th>Source</th>
                       		 <th>Status</th>
                       		 <th>Edit</th>

                     </tr>
                </thead>
	<%
                    while (it.hasNext()) {
                    	dto=(AllowDTO)it.next();
                %>
	
		<tbody align="center">
                    <tr>
                        <td style="text-align: center;">
                        <input type="checkbox" class="case" name="ids" value="<%=dto.getId()%>">
                    </td>
                    <td style="text-align: center;"><%=index++%></td>
                    <td align="center"><%=dto.getAllowCode()%></td>
							<td align="center"><%=dto.getUserName()%></td>
							<td align="center"><%=dto.getSource()%></td>
							<td align="center"><%=dto.getStatus()%></td>
							<td align="center"><a href="AllowCtl?id=<%=dto.getId()%>">Edit</a></td>
                    </tr>

                    
                </tbody>
			
	<%
                    }
                %>
	</table>

</div>

<!-- Search Button -->
<div class="row mt-3" style="width: 95%; margin:auto;">
            <div class="col-md-3">
                <input type="submit" name="operation" class="btn btn-primary btn-md" value="<%=AllowListCtl.OP_PREVIOUS%>" <%=pageNo > 1 ? "" : "disabled"%>>
            </div>

            <div class="col-md-3">
                <input type="submit" name="operation" class="btn btn-primary btn-md" value="<%=AllowListCtl.OP_NEW%>">
            </div>

            <div class="col-md-3">
                <input type="submit" name="operation" class="btn btn-primary btn-md" value="<%=AllowListCtl.OP_DELETE%>">
            </div>

            <div class="col-md-3 text-right">
                <input type="submit" name="operation" class="btn btn-primary btn-md" value="<%=AllowListCtl.OP_NEXT%>" <%=nextPageSize != 0 ? "" : "disabled"%>>
            </div>
        </div>

</div>
<!-- End of Main Content -->
	<%
                }
                if (list.size() == 0) {
            %>
            <table>
                <tr>
                    <td align="right">
                    	<input type="submit" name="operation" value="<%=AllowListCtl.OP_BACK%>">                       
                    </td>
                </tr>
            </table>
            <%
                }
            %>
	
	</form>
<!-- End of Form -->
	
<!-- Footer File -->
<%@include file="Footer.jsp"%>
</body>
</html>