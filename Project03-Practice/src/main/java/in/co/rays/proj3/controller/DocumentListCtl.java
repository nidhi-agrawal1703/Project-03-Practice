package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.DocumentDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.model.DocumentModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;

@WebServlet(name="DocumentListCtl",urlPatterns= {"/ctl/DocumentListCtl"})
public class DocumentListCtl extends BaseCtl {
	
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		
		DocumentDTO dto=new DocumentDTO();
		
		dto.setDocumentName(request.getParameter("documentName"));
		dto.setDocumentType(request.getParameter("documentType"));
		
		return dto;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		
		int pageNo=1;
		int pageSize=DataUtility.getInt(PropertyReader.getValue("page.size"));
		
		DocumentDTO dto=(DocumentDTO)populateDTO(request);
		DocumentModelInt model=ModelFactory.getInstance().getDocumentModel();
		
		try {
			List<DocumentDTO> list=model.search(dto,pageNo,pageSize);
			List<DocumentDTO> next=model.search(dto,pageNo+1,pageSize);
			int nextSize=next.size();
			
			if(list==null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No Record Found", request);
				
			}
			
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setDto(dto, request);
			request.setAttribute("nextListSize",
			        (next != null) ? next.size() : 0);
			
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		
		List list=null;
		List next=null;
		
		int pageNo=DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize=DataUtility.getInt(request.getParameter("pageSize"));
		
		pageNo=(pageNo==0)?1:pageNo;
		pageSize=(pageSize==0)?DataUtility.getInt(PropertyReader.getValue("page.size")):pageSize;
		
		DocumentDTO dto=(DocumentDTO)populateDTO(request);
		DocumentModelInt model=ModelFactory.getInstance().getDocumentModel();
		
		String op=DataUtility.getString(request.getParameter("operation"));
		String[] ids=request.getParameterValues("ids");
		
		try {
			if(OP_SEARCH.equalsIgnoreCase(op)||"Next".equalsIgnoreCase(op)||"Previous".equalsIgnoreCase(op)) {
				if(OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo=1;
				}else if("Next".equalsIgnoreCase(op)) {
					pageNo++;
				}else if("Previous".equalsIgnoreCase(op)) {
					pageNo--;
				}
				
			}else if(OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.DOCUMENT_CTL, request, response);
				return;
				
			}else if(OP_DELETE.equalsIgnoreCase(op)) {
				pageNo=1;
				if(ids!=null && ids.length>0) {
					DocumentDTO deletedto=new DocumentDTO();
					for(String id:ids) {
						deletedto.setId(DataUtility.getLong(id));
						model.delete(deletedto);
						ServletUtility.setSuccessMessage("Data is deleted successfully", request);
					}
				}else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
				
			}else if(OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.DOCUMENT_LIST_CTL, request, response);
				return;
				
			}else if(OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.DOCUMENT_LIST_CTL, request, response);
				return;
				
			}
			
			list=model.search(dto,pageNo,pageSize);
			System.out.println("Document Id in Document List Ctl"+dto.getId());
			next=model.search(dto,pageNo+1,pageSize);
			
			if(list==null || list.isEmpty()) {
				ServletUtility.setSuccessMessage("No Record Found", request);
			}
			System.out.println("List in Document list ctl: "+list);
			ServletUtility.setList(list, request);
			
			ServletUtility.setPageNo(pageNo,request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setDto(dto, request);
			request.setAttribute("nextListSize",
			        (next != null) ? next.size() : 0);
			ServletUtility.forward(getView(), request, response);
			
		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}

	}
	
	@Override
	protected String getView() {
		return ORSView.DOCUMENT_LIST_VIEW;
	}

}
