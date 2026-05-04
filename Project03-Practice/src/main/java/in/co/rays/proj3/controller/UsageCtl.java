package in.co.rays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.UsageDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.UsageModelInt;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;

@WebServlet(name="UsageCtl",urlPatterns= {"/ctl/UsageCtl"})
public class UsageCtl extends BaseCtl {
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		
		boolean pass=true;
		
		if(DataValidator.isNull(request.getParameter("usageCode"))) {
			request.setAttribute("usageCode", PropertyReader.getValue("error.require","Usage Code"));
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("userName"))) {
			request.setAttribute("userName", PropertyReader.getValue("error.require", "User Name"));
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("userType"))) {
			request.setAttribute("userType", PropertyReader.getValue("error.require", "User Type"));
			pass=false;
		}
		return pass;
	}
	
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		
		UsageDTO dto=new UsageDTO();
		
		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setUsageCode(DataUtility.getString(request.getParameter("usageCode")));
		dto.setUserName(DataUtility.getString(request.getParameter("userName")));
		dto.setUserType(DataUtility.getString(request.getParameter("userType")));
		
		populateBean(dto, request);
		
		return dto;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		long id=DataUtility.getLong(request.getParameter("id"));
		
		UsageModelInt model=ModelFactory.getInstance().getUsageModel();
		
		if(id>0) {
			try {
				UsageDTO dto=model.findByPK(id);
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
		//System.out.println("Usage added to database in Usagectl doPost");
		String op=DataUtility.getString(request.getParameter("operation"));
		UsageModelInt model=ModelFactory.getInstance().getUsageModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equalsIgnoreCase(op)) {
			UsageDTO dto=(UsageDTO)populateDTO(request);
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
				ServletUtility.handleException(e, request, response, "Usage", dto);
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
		return ORSView.UASGE_VIEW;
	}

}
