package in.co.rays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.AllowDTO;
import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.ContactDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.AllowModelInt;
import in.co.rays.proj3.model.ContactModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;

@WebServlet(name="AllowCtl",urlPatterns= {"/ctl/AllowCtl"})
public class AllowCtl extends BaseCtl {
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		
		boolean pass=true;
		
		if(DataValidator.isNull(request.getParameter("allowCode"))) {
			request.setAttribute("allowCode", PropertyReader.getValue("error.require","Allow Code"));
			pass=false;
		}

		if(DataValidator.isNull(request.getParameter("userName"))) {
			request.setAttribute("userName", PropertyReader.getValue("error.require","UserName"));
			pass=false;
		}else if(!DataValidator.isName(request.getParameter("userName"))) {
			request.setAttribute("userName", "Invalid Username");
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("source"))) {
			request.setAttribute("source", PropertyReader.getValue("error.require","Source"));
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require","Status"));
			pass=false;
		}
		
		return pass;
	}
	
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		
		AllowDTO dto=new AllowDTO();
		
		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setAllowCode(DataUtility.getString(request.getParameter("allowCode")));
		dto.setUserName(DataUtility.getString(request.getParameter("userName")));
		dto.setSource(DataUtility.getString(request.getParameter("source")));
		dto.setStatus(DataUtility.getString(request.getParameter("status")));
		
		populateBean(dto, request);
		
		return dto;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		long id=DataUtility.getLong(request.getParameter("id"));
		
		AllowModelInt model=ModelFactory.getInstance().getAllowModel();
		
		if(id>0) {
			try {
				AllowDTO dto=model.findByPK(id);
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
		AllowModelInt model=ModelFactory.getInstance().getAllowModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equalsIgnoreCase(op)) {
			AllowDTO dto=(AllowDTO)populateDTO(request);
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
				ServletUtility.handleException(e, request, response, "Allow", dto);
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
		return ORSView.ALLOW_VIEW;
	}

}
