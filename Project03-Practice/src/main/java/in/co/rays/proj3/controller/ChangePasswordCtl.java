package in.co.rays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.UserModelInt;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;
/**
 * Change Password functionality controller.Performs operation 
 * for change password.
 * 
 * @author Nidhi
 * @version 1.0
 *
 */
@WebServlet(name="ChangePasswordCtl",urlPatterns= {"/ctl/ChangePasswordCtl"})
public class ChangePasswordCtl extends BaseCtl{

	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";
	
	@Override
		protected boolean validate(HttpServletRequest request) {
			boolean pass=true;
			
			if(DataValidator.isNull(request.getParameter("newPassword"))) {
				request.setAttribute("newPassword", PropertyReader.getValue("error.require","Password"));
				pass=false;
			}else if(!DataValidator.isPasswordLength(request.getParameter("newPassword"))) {
				request.setAttribute("newPassword", "Password Length should be between 8 to 10 characters");
				pass=false;
			}else if(!DataValidator.isPassword(request.getParameter("newPassword"))) {
				request.setAttribute("newPassword", "Invalid Password");
				pass=false;
			}	
			
			if(DataValidator.isNull(request.getParameter("confirmPassword"))) {
				request.setAttribute("confirmPassword", PropertyReader.getValue("error.require","Password"));
				pass=false;
			}else if(!DataValidator.isPasswordLength(request.getParameter("confirmPassword"))) {
				request.setAttribute("confirmPassword", "Password Length should be between 8 to 10 characters");
				pass=false;
			}else if(!DataValidator.isPassword(request.getParameter("confirmPassword"))) {
				request.setAttribute("confirmPassword", "Invalid Password");
				pass=false;
			}	

			if (!request.getParameter("newPassword").equals(request.getParameter("confirmPassword"))
					&& !"".equals(request.getParameter("confirmPassword"))) {
				request.setAttribute("newConfirmPassword", "New and confirm passwords not matched");
				pass = false;
			}

			
			return pass;
		
		}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession session=request.getSession();
			UserDTO dto=(UserDTO)session.getAttribute("user");
			ServletUtility.setDto(dto, request);
			ServletUtility.forward(getView(), request, response);
		
		}
	
	@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		
		HttpSession session=request.getSession();
		String op=DataUtility.getString(request.getParameter("operation"));
		UserModelInt model=ModelFactory.getInstance().getUserModel();
		UserDTO dto=(UserDTO)session.getAttribute("user");
		
		String oldPassword=request.getParameter("oldPassword");
		String newPassword=request.getParameter("newPassword");
		
		long id=dto.getId();
		
		if(OP_SAVE.equalsIgnoreCase(op)) {
			try {
				boolean flag=model.changePassword(id, newPassword, oldPassword);
				if(flag==true) {
					model.findByLogin(dto.getLogin());
					ServletUtility.setSuccessMessage("Password has been changed successfully", request);
				}
			} catch (RecordNotFoundException e) {
				ServletUtility.handleException(e, request, response, "Password", dto);
			}catch(ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}else if(OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MY_PROFILE_CTL, request, response);
			return;	
			}
		ServletUtility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);
		}
	
	@Override
	protected String getView() {
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}
