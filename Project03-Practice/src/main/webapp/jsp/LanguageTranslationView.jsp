<%@page import="in.co.rays.proj3.controller.ORSView"%>
<%@page import="in.co.rays.proj3.controller.LanguageTranslationCtl"%>
<%@page import="in.co.rays.proj3.dto.LanguageTranslationDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj3.util.HTMLUtility"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>

<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>Add LanguageTranslation</title>
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
	<jsp:useBean id="dto" class="in.co.rays.proj3.dto.LanguageTranslationDTO" scope="request"></jsp:useBean>
	
	
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
						<h3 class="text-center text-primary pb-3">Update LanguageTranslation</h3>
						<%}else { %>
						<h3 class="text-center text-primary pb-3">Add LanguageTranslation</h3>
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
						<form action="<%=ORSView.LANGUAGETRANSLATION_CTL %>" method="post">
						
						
						
						<!-- Source Language-->
 							<label><b>Source Language</b><span style="color:red">*</span></label>
 							<div class="input-group mb-3">
 								<div class="input-class-prepend">
 									<span class="input-group-text" id="basic-addon1"><i class="fa fa-clock" style="font-size: 25px;"></i></span>
 									
 								</div>
 								
 								<input type="text" class="form-control" name="sourceLanguage" placeholder="Source Language" 
 								value="<%=DataUtility.getStringData(dto.getSourceLanguage())%>">
 								</div>
 								<font color="red"><%=ServletUtility.getErrorMessage("sourceLanguage", request)%></font><br>
 								
 												
						<!-- Target Language-->
 							<label><b>Target Language</b><span style="color:red">*</span></label>
 							<div class="input-group mb-3">
 								<div class="input-class-prepend">
 									<span class="input-group-text" id="basic-addon1"><i class="fa fa-clock" style="font-size: 25px;"></i></span>
 									
 								</div>
 								
 								<input type="text" class="form-control" name="targetLanguage" placeholder="Target Language" 
 								value="<%=DataUtility.getStringData(dto.getTargetLanguage())%>">
 								</div>
 								<font color="red"><%=ServletUtility.getErrorMessage("targetLanguage", request)%></font><br>
 								
							<!--Input Text -->
 							<label><b>Input Text</b><span style="color:red">*</span></label>
 							<div class="input-group mb-3">
 								<div class="input-class-prepend">
 									<span class="input-group-text" id="basic-addon1"><i class="fa fa-list" style="font-size: 25px;"></i></span>
 									
 								</div>
 								<input type="text" class="form-control" name="inputText" placeholder="Input Text"
 								value="<%=DataUtility.getStringData(dto.getInputText())%>">
 								</div>
 								<font color="red"><%=ServletUtility.getErrorMessage("inputText", request)%></font><br> 
 												
							<!--Translated Text -->
 							<label><b>Translated Text</b><span style="color:red">*</span></label>
 							<div class="input-group mb-3">
 								<div class="input-class-prepend">
 									<span class="input-group-text" id="basic-addon1"><i class="fa fa-list" style="font-size: 25px;"></i></span>
 									
 								</div>
 								<input type="text" class="form-control" name="translatedText" placeholder="Translated Text"
 								value="<%=DataUtility.getStringData(dto.getTranslatedText())%>">
 								</div>
 								<font color="red"><%=ServletUtility.getErrorMessage("translatedText", request)%></font><br> 
								
 							
							<input type="hidden" name="id" value="<%=dto.getId()%>">
            				<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            				<input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            				<input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
           					 <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">					
						
						<!-- Buttons -->
						<div class="text-center">
							<input type="submit" class="btn btn-success" name="operation" value="<%=LanguageTranslationCtl.OP_SAVE%>" >						
						
						<%
                        if (dto.getId() !=null) {
                    %>
                                                
                                
                                <input type="submit" class="btn btn-secondary" name="operation"value="<%=LanguageTranslationCtl.OP_CANCEL%>"><br>  								                          
                            </div> 							
						
                    <%
                        } else {
                    %>
                                                
                                
                                <input type="submit" class="btn btn-secondary" name="operation"value="<%=LanguageTranslationCtl.OP_RESET%>"><br>  								                          
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