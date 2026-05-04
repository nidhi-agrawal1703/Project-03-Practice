package in.co.rays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.ContactDTO;
import in.co.rays.proj3.dto.DocumentDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.ContactModelInt;
import in.co.rays.proj3.model.DocumentModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;

@WebServlet(name="DocumentCtl",urlPatterns= {"/ctl/DocumentCtl"})
public class DocumentCtl extends BaseCtl {
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		
		boolean pass=true;
		
		if(DataValidator.isNull(request.getParameter("documentName"))) {
			request.setAttribute("documentName", PropertyReader.getValue("error.require","Document Name"));
			pass=false;
		}else if(!DataValidator.isName(request.getParameter("documentName"))) {
			request.setAttribute("documentName", "Invalid Name");
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("documentType"))) {
			request.setAttribute("documentType", PropertyReader.getValue("error.require","Document Type"));
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("filePath"))) {
			request.setAttribute("filePath", PropertyReader.getValue("error.require","File Path"));
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("uploadDate"))) {
			request.setAttribute("uploadDate", PropertyReader.getValue("error.require","Upload Date"));
			pass=false;
		}
		return pass;
	}
	
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		
		DocumentDTO dto=new DocumentDTO();
		
		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setDocumentName(DataUtility.getString(request.getParameter("documentName")));
		dto.setDocumentType(DataUtility.getString(request.getParameter("documentType")));
		dto.setUploadDate(DataUtility.getDate(request.getParameter("uploadDate")));
		dto.setFilePath(DataUtility.getString(request.getParameter("filePath")));
		
		populateBean(dto, request);
		
		return dto;
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		
		long id=DataUtility.getLong(request.getParameter("id"));
		
		DocumentModelInt model=ModelFactory.getInstance().getDocumentModel();
		if(id>0) {
			try {
				DocumentDTO dto=model.findByPK(id);
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
		DocumentModelInt model=ModelFactory.getInstance().getDocumentModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equalsIgnoreCase(op)) {
			DocumentDTO dto=(DocumentDTO)populateDTO(request);
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
				ServletUtility.handleException(e, request, response, "Document", dto);
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
		return ORSView.DOCUMENT_VIEW;
	}

}
