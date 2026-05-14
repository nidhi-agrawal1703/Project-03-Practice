package in.co.rays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.AccountDTO;
import in.co.rays.proj3.dto.AllowDTO;
import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.AccountModelInt;
import in.co.rays.proj3.model.AllowModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;

@WebServlet(name="AccountCtl",urlPatterns= {"/ctl/AccountCtl"})
public class AccountCtl extends BaseCtl{
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		
		boolean pass=true;
		
		if(DataValidator.isNull(request.getParameter("accNumber"))) {
			request.setAttribute("accNumber", PropertyReader.getValue("error.require","Account Number"));
			pass=false;
		}

		if(DataValidator.isNull(request.getParameter("holderName"))) {
			request.setAttribute("holderName", PropertyReader.getValue("error.require","Holder Name"));
			pass=false;
		}else if(!DataValidator.isName(request.getParameter("holderName"))) {
			request.setAttribute("holderName", "Invalid Holder Name");
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("accType"))) {
			request.setAttribute("accType", PropertyReader.getValue("error.require","Account Type"));
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.require","Email"));
			pass=false;
		}
			
		return pass;
	} 
	
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		
		AccountDTO dto=new AccountDTO();
		
		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setAccNumber(DataUtility.getString(request.getParameter("accNumber")));
		dto.setHolderName(DataUtility.getString(request.getParameter("holderName")));
		dto.setAccType(DataUtility.getString(request.getParameter("accType")));
		dto.setEmail(DataUtility.getString(request.getParameter("email")));
		
		populateBean(dto, request);
		return dto;
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		
		long id=DataUtility.getLong(request.getParameter("id"));
		
		AccountModelInt model=ModelFactory.getInstance().getAccountModel();
		
		if(id>0) {
			try {
				AccountDTO dto=model.findByPK(id);
				ServletUtility.setDto(dto, request);
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
		AccountModelInt model=ModelFactory.getInstance().getAccountModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equalsIgnoreCase(op)) {
			AccountDTO dto=(AccountDTO)populateDTO(request);
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
				ServletUtility.handleException(e, request, response, "Account", dto);
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
		return ORSView.ACCOUNT_VIEW;
	}

}
