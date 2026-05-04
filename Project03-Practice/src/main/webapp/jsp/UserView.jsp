<%@page import="in.co.rays.proj3.controller.ORSView"%>
<%@page import="in.co.rays.proj3.controller.UserCtl"%>
<%@page import="in.co.rays.proj3.dto.UserDTO"%>
<%@page import="in.co.rays.proj3.dto.RoleDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj3.util.HTMLUtility"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>

<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>Add User</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
<style>
	.bgColor {
            background-color: white;
            padding-top: 120px;
            /* Because header is fixed */
            padding-bottom: 80px;
            /* Because footer is fixed */
        }

        .input-group-addon {
            box-shadow: 9px 8px 7px #001a33;
        }
	h1,h2,h3{
		color:#004e92;
	}

</style>

</head>
<body class="bgColor">
<!-- Header -->
	<%@ include file="Header.jsp" %>
	
<!-- Bean Tag -->
	<jsp:useBean id="dto" class="in.co.rays.proj3.dto.UserDTO" scope="request"></jsp:useBean>
	
	
<!-- Main Content -->
	<div class="container pt-4">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="card input-group-addon">
					<div class="card-body">
						<%
							long id = DataUtility.getLong(request.getParameter("id"));
						
							if(dto.getId()!=null && dto.getId()>0){						
						%>				
						<h3 class="text-center text-primary pb-3">Update User</h3>
						<%}else { %>
						<h3 class="text-center text-primary pb-3">Add User</h3>
						<%} %>
						
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
						
						
						<!-- Form Start -->
						<form action="<%=ORSView.USER_CTL %>" method="post">
						
						<!-- Dropdown Data -->
						
						<%
							List<RoleDTO> roleList=(List<RoleDTO>)request.getAttribute("roleList");
						%>
						
						<!-- User First Name-->
 							<label><b>User First Name</b><span style="color:red">*</span></label>
 							<div class="input-group mb-3">
 								<div class="input-class-prepend">
 									<span class="input-group-text" id="basic-addon1"><i class="fa fa-clock" style="font-size: 25px;"></i></span>
 									
 								</div>
 								
 								<input type="text" class="form-control" name="firstName" placeholder="User First Name" 
 								value="<%=DataUtility.getStringData(dto.getFirstName())%>">
 								</div>
 								<font color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font><br>
						
						<!-- User Last Name-->
 							<label><b>User Last Name</b><span style="color:red">*</span></label>
 							<div class="input-group mb-3">
 								<div class="input-class-prepend">
 									<span class="input-group-text" id="basic-addon1"><i class="fa fa-clock" style="font-size: 25px;"></i></span>
 									
 								</div>
 								
 								<input type="text" class="form-control" name="lastName" placeholder="User Last Name" 
 								value="<%=DataUtility.getStringData(dto.getLastName())%>">
 								</div>
 								<font color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font><br>
 								
							<!--User Login -->
 							<label><b>User Login</b><span style="color:red">*</span></label>
 							<div class="input-group mb-3">
 								<div class="input-class-prepend">
 									<span class="input-group-text" id="basic-addon1"><i class="fa fa-list" style="font-size: 25px;"></i></span>
 									
 								</div>
 								<input type="text" class="form-control" name="login" placeholder="User Login"
 								value="<%=DataUtility.getStringData(dto.getLogin())%>">
 								</div>
 								<font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font><br> 
 							
							
						<!--Password-->
 							<label><b>Password</b><span style="color:red">*</span></label>
 							<div class="input-group mb-3">
 								<div class="input-class-prepend">
 									<span class="input-group-text" id="basic-addon1"><i class="fa fa-clock" style="font-size: 25px;"></i></span>
 									
 								</div>
 								
 								<input type="text" class="form-control" name="password" placeholder="Password" 
 								value="<%=DataUtility.getStringData(dto.getPassword())%>">
 								</div>
 								<font color="red"><%=ServletUtility.getErrorMessage("password", request)%></font><br>
						
						<!-- Confirm Password-->
 							<label><b>Confirm Password</b><span style="color:red">*</span></label>
 							<div class="input-group mb-3">
 								<div class="input-class-prepend">
 									<span class="input-group-text" id="basic-addon1"><i class="fa fa-clock" style="font-size: 25px;"></i></span>
 									
 								</div>
 								
 								<input type="text" class="form-control" name="confirmPassword" placeholder="Confirm Password" 
 								value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>">
 								</div>
 								<font color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font><br>
						
						
							<!--User Gender -->
 							<label><b>User Gender</b><span style="color:red">*</span></label>
 							<div class="input-group mb-3">
 								<div class="input-class-prepend">
 									<span class="input-group-text" id="basic-addon1"><i class="fa fa-list" style="font-size: 25px;"></i></span>
 									
 								</div>
 								<%
 								HashMap<String,String> map=new HashMap<String,String>();
 								map.put("Male","Male");
 								map.put("Female","Female");
 								%>
 								<%=HTMLUtility.getList("gender", dto.getGender(), map) %>
 								</div>
 								<font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font><br> 
						
						<!-- User DOB-->
 							<label><b>Date of Birth</b><span style="color: red">*</span></label>
							<div class="input-group mb-3">
								<div class="input-class-prepend">
									<span class="input-group-text" id="basic-addon1"><i
										class="fas fa-calendar-alt" style="font-size: 25px;"></i></span>

								</div>
								<input type="text" readonly="readonly" id="udate" name="dob"
									placeholder="Select Date of Birth"
									value="<%=DataUtility.getDateString(dto.getDob())%>">
									</div>
								<font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font><br>
						
						<!-- User Mobile Number -->
 							<label><b>User Mobile Number</b><span style="color:red">*</span></label>
 							<div class="input-group mb-3">
 								<div class="input-class-prepend">
 									<span class="input-group-text" id="basic-addon1"><i class="fa fa-user-alt" style="font-size: 25px;"></i></span>
 									
 								</div>
 								<input type="text" class="form-control" name="mobileNo"
 									 placeholder="User Mobile Number" value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
 								</div>
 								<font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font><br>	 
 							 							
 							<!--User Role -->
 							<label><b>User Role</b><span style="color:red">*</span></label>
 							<div class="input-group mb-3">
 								<div class="input-class-prepend">
 									<span class="input-group-text" id="basic-addon1"><i class="fa fa-list" style="font-size: 25px;"></i></span>
 									
 								</div>
 								<%=HTMLUtility.getList("roleId", String.valueOf(dto.getRoleId()),roleList) %>
 								</div>
 								<font color="red"><%=ServletUtility.getErrorMessage("roleId", request)%></font><br>
																			
								
 							
							<input type="hidden" name="id" value="<%=dto.getId()%>">
            				<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            				<input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            				<input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
           					 <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">					
						
						<!-- Buttons -->
						<div class="text-center">
							<input type="submit" class="btn btn-success" name="operation" value="<%=UserCtl.OP_SAVE%>" >						
						
						<%
                        if (dto.getId() !=null) {
                    %>
                                                
                                
                                <input type="submit" class="btn btn-secondary" name="operation"value="<%=UserCtl.OP_CANCEL%>"><br>  								                          
                            </div> 							
						
                    <%
                        } else {
                    %>
                                                
                                
                                <input type="submit" class="btn btn-secondary" name="operation"value="<%=UserCtl.OP_RESET%>"><br>  								                          
                            </div> 							
						
                    <%
                        }
                    %>						
						</form>							
					</div>
				</div>
				
			</div>
			<div class="col-md-3"></div>	
		</div>
	
	</div>
	
	
	<!-- Footer -->
	<%@ include file="Footer.jsp"%>
</body>
</html>