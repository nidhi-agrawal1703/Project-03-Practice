package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.AllowDTO;
import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.AllowModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.RoleModelInt;
import in.co.rays.proj3.model.UserModelInt;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;

@WebServlet(name="UserCtl",urlPatterns={"/ctl/UserCtl"})
public class UserCtl extends BaseCtl {
	
	@Override
	protected void preload(HttpServletRequest request) {
		
		RoleModelInt roleModel=ModelFactory.getInstance().getRoleModel();
		//RoleModel roleModel=new RoleModel();
		try {
			List<RoleDTO> roleList=roleModel.list();
			if(roleList==null) {
				roleList=new ArrayList<>();
			}
			request.setAttribute("roleList", roleList);
		} catch (Exception e) {
			e.printStackTrace();
			//throw new DatabaseException("Unable to load roles", e); 
		}
	}
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass=true;
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "Invalid First Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "Invalid Last Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}

		
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date of Birth"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "password"));
			pass = false;
		} else if (!DataValidator.isPasswordLength(request.getParameter("password"))) {
			request.setAttribute("password", "Password should be 8 to 12 characters");
			pass = false;
		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", "Password Must contain uppercase, lowercase, digit & special character");
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "confirmPassword"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("roleId"))) {
			request.setAttribute("roleId", PropertyReader.getValue("error.require", "Role"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "MobileNo"));
			pass = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Mobile No must have 10 digits");
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Invalid Mobile No");
			pass = false;
		}
		if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			
			request.setAttribute("confirmPassword","Confirm  Password  should  be matched.");
			pass = false;
		}
		return pass;
	}
	
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		
		UserDTO dto=new UserDTO();
		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		dto.setLastName(DataUtility.getString(request.getParameter("lastName")));
		dto.setLogin(DataUtility.getString(request.getParameter("login")));
		dto.setPassword(DataUtility.getString(request.getParameter("password")));
		dto.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		dto.setGender(DataUtility.getString(request.getParameter("gender")));
		dto.setDob(DataUtility.getDate(request.getParameter("dob")));
		dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		dto.setRoleId(DataUtility.getLong(request.getParameter("roleId")));

		populateBean(dto, request);

		return dto;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		long id = DataUtility.getLong(request.getParameter("id"));

		//UserModel model = new UserModel();
		
		UserModelInt model=ModelFactory.getInstance().getUserModel();

		if (id > 0) {
			try {
				UserDTO dto=model.findByPK(id);
				ServletUtility.setDto(dto, request);
				//ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		
		String op=DataUtility.getString(request.getParameter("operation"));
		UserModelInt model=ModelFactory.getInstance().getUserModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equalsIgnoreCase(op)) {
			UserDTO dto=(UserDTO)populateDTO(request);
			try {
				if(id>0) {
					model.update(dto);
					ServletUtility.setSuccessMessage("Data is successfully updated", request);
				}else {
					model.add(dto);
					ServletUtility.setSuccessMessage("Data is successfully added",request);
				}
				ServletUtility.setDto(dto, request);
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response, "User", dto);
			}catch(ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}else if((OP_RESET.equalsIgnoreCase(op))) {
			ServletUtility.forward(getView(), request, response);
			return;
		}
		
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected String getView() {
		return ORSView.USER_VIEW;
	}

}
