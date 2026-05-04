<!DOCTYPE html>
<%@page import="in.co.rays.proj3.dto.UserDTO"%>
<%@page import="in.co.rays.proj3.controller.LoginCtl"%>
<%@page import="in.co.rays.proj3.dto.RoleDTO"%>
<%@page import="in.co.rays.proj3.controller.ORSView"%>
<html>
<head>
  <link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
  
  <!-- Bootstrap CSS -->
 	
	
	<!-- Bootstrap Libraries -->
<!-- 	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"> -->

    <!-- jQuery (must be first) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- jQuery UI -->
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	    
    <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>	
<!--   <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script> 
  <style>
  	
    .footer {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        color: white;
        text-align: center;
        background-image: linear-gradient(to bottom right, grey, black);
    }
    .e {
        background: linear-gradient(135deg, #000428, #004e92);
    }
  </style>
</head>
<body>
<!-- SCRIPTS: jQuery full -> Popper -> Bootstrap -> then your custom scripts -->
  
 <!-- Move your custom JS below the libraries -->
  	
  	<script type="text/javascript" src="/Project-03/js/datepicker.js"></script>
    <script type="text/javascript" src="/Project-03/js/checkbox.js"></script>
 
 <%
UserDTO user = (UserDTO) session.getAttribute("user");

boolean userLoggedIn = user != null;

String welcomeMsg = "Hi, ";

if (userLoggedIn) {
	String role = (String) session.getAttribute("role");
	welcomeMsg += user.getFirstName() + " (" + role + ")";//welcomeMsg =welcomeMsg+ user.getFirstName() + " (" + role + ")";
} else {
	welcomeMsg += "Guest";
}
%>
<%
if(userLoggedIn){
%>
<div>
    <nav class="navbar navbar-expand-lg fixed-top e">
      <a class="navbar-brand" href="#">
        <img src="https://www.raystec.com/assets/img/rays/customLogoOuterglow.png" width="190" height="50">
      </a>
      
      <!-- Navbar collapse button -->
		<button class="navbar-toggler" data-toggle="collapse" data-target="#navbarNav">
        <i class="fa fa-bars" style="color:#fff; font-size:28px;"></i>
      </button>
      
      <div class="collapse navbar-collapse" id="navbarNav">
      
      <ul class="navbar-nav ml-auto">

        <%
        if (user.getRoleId() == RoleDTO.COLLEGE || user.getRoleId() == RoleDTO.FACULTY
			|| user.getRoleId() == RoleDTO.ADMIN){
        %>
        
        <%} %>  

        <%
        if (user.getRoleId() == RoleDTO.COLLEGE || user.getRoleId() == RoleDTO.ADMIN){
        %>
        <!-- COURSE -->
        

        <!-- STUDENT -->
        

        <!-- FACULTY -->
      
        
        <!-- SUBJECT -->
        
        
        <%} %>
        <%
 	   if (user.getRoleId() == RoleDTO.ADMIN) {
       %>
       
        <!-- STUDENT-->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" style="color:white;">Student</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=ORSView.STUDENT_CTL%>"><i class="fa fa-user-circle"></i> Add Student</a>
            <a class="dropdown-item" href="<%=ORSView.STUDENT_LIST_CTL%>"><i class="fa fa-user-friends"></i>Student List</a>
          </div>
        </li>
       
       <!-- COLLEGE-->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" style="color:white;">College</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=ORSView.COLLEGE_CTL%>"><i class="fa fa-user-circle"></i> Add College</a>
            <a class="dropdown-item" href="<%=ORSView.COLLEGE_LIST_CTL%>"><i class="fa fa-user-friends"></i>College List</a>
          </div>
        </li>
       
        <!-- DOCUMENT-->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" style="color:white;">Document</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=ORSView.DOCUMENT_CTL%>"><i class="fa fa-user-circle"></i> Add Document</a>
            <a class="dropdown-item" href="<%=ORSView.DOCUMENT_LIST_CTL%>"><i class="fa fa-user-friends"></i>Document List</a>
          </div>
        </li>
        
        <!-- ALLOW-->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" style="color:white;">Allow</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=ORSView.ALLOW_CTL%>"><i class="fa fa-user-circle"></i> Add Allow</a>
            <a class="dropdown-item" href="<%=ORSView.ALLOW_LIST_CTL%>"><i class="fa fa-user-friends"></i>Allow List</a>
          </div>
        </li>
        
       <!-- SETTINGS -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" style="color:white;">Settings</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=ORSView.SETTINGS_CTL%>"><i class="fa fa-user-circle"></i> Add Settings</a>
            <a class="dropdown-item" href="<%=ORSView.SETTINGS_LIST_CTL%>"><i class="fa fa-user-friends"></i>Settings List</a>
          </div>
        </li>
        
       <!-- USAGE -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" style="color:white;">Usage</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=ORSView.USAGE_CTL%>"><i class="fa fa-user-circle"></i> Add Usage</a>
            <a class="dropdown-item" href="<%=ORSView.USAGE_LIST_CTL%>"><i class="fa fa-user-friends"></i>Usage List</a>
          </div>
        </li>
       
       <!-- CONTACT -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" style="color:white;">Contact</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=ORSView.CONTACT_CTL%>"><i class="fa fa-user-circle"></i> Add Contact</a>
            <a class="dropdown-item" href="<%=ORSView.CONTACT_LIST_CTL%>"><i class="fa fa-user-friends"></i> Contact List</a>
          </div>
        </li>
        
        <!-- USER -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" style="color:white;">User</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=ORSView.USER_CTL%>"><i class="fa fa-user-circle"></i> Add User</a>
            <a class="dropdown-item" href="<%=ORSView.USER_LIST_CTL%>"><i class="fa fa-user-friends"></i> User List</a>
          </div>
        </li>
        
       <!-- ROLE -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button"  data-toggle="dropdown" style="color:white;">Role</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=ORSView.ROLE_CTL%>"><i class="fa fa-user-tie"></i> Add Role</a>
            <a class="dropdown-item" href="<%=ORSView.ROLE_LIST_CTL%>"><i class="fa fa-users"></i> Role List</a>
          </div>
        </li>
       
       <%} %>
       
        
		
        
        <!-- PROFILE / LOGOUT -->
        <li class="nav-item dropdown" style="padding-right: 40px;">
          <a class="nav-link dropdown-toggle" data-toggle="dropdown" style="color:white;">
            <%=welcomeMsg %>
          </a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=ORSView.MY_PROFILE_CTL%>"><i class="fa fa-user"></i> My Profile</a>
            <a class="dropdown-item" href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class="fa fa-key"></i>Change Password</a>
            <a class="dropdown-item" href="/Project-03/doc/index.html"><i class="fa-solid fa-file"></i>Javadoc</a>
            <a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOGOUT%>"><i class="fa fa-sign-out-alt"></i> Logout</a>
          </div>
        </li>
        
      </ul>
      </div><!-- Button collapse div -->
    </nav>
  </div>
	
<% }else{%>
<div>
    <nav class="navbar navbar-expand-lg fixed-top e">
      <a class="navbar-brand" href="#">
        <img src="https://www.raystec.com/assets/img/rays/customLogoOuterglow.png" width="190" height="50">
      </a>
        <ul class="navbar-nav ml-auto">

          <li class="nav-item dropdown" style="padding-right: 40px;">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown" style="color:white;">
              <%=welcomeMsg %>
            </a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>"><i class="fa fa-sign-in-alt"></i> Login</a>
              <a class="dropdown-item" href="<%=ORSView.USER_REGISTRATION_CTL%>"><i class="fa fa-registered"></i> User Registration</a>
            </div>
          </li>
        </ul>
    </nav>
  </div>	
<% }%>

<script>
$('.dropdown-toggle').dropdown();
</script>

</body>
</html>