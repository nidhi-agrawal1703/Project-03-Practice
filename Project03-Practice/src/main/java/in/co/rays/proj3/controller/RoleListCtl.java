package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.RoleModelInt;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;

/**
 * Role List functionality controller.
 * Performs operation for list,search operations of role
 * 
 * @author Nidhi
 * @version 1.0
 */
@WebServlet(name="RoleListCtl",urlPatterns= {"/ctl/RoleListCtl"})
public class RoleListCtl extends BaseCtl{
	
		
	@Override
		protected BaseDTO populateDTO(HttpServletRequest request) {
			
			RoleDTO dto=new RoleDTO();
			
			//dto.setId(DataUtility.getLong(request.getParameter("roleId")));
			dto.setName(request.getParameter("name"));
			
			return dto;
		
		}
	
	@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
			
			int pageNo=1;
			int pageSize=DataUtility.getInt(PropertyReader.getValue("page.size"));
			
			RoleDTO dto=(RoleDTO)populateDTO(request);
			RoleModelInt model=ModelFactory.getInstance().getRoleModel();
			
			try {
				List<RoleDTO> list=model.search(dto,pageNo,pageSize);
				List<RoleDTO> next=model.search(dto,pageNo+1,pageSize);
				int nextSize=next.size();
				
				if(list==null || list.isEmpty()) {
					System.out.println("List is empty in doGet of roleListCtl"+list);
					ServletUtility.setErrorMessage("Nidhi", request);
					
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
			//System.out.println("In post of RoleList ctl");
			List list=null;
			List next=null;
			
			int pageNo=DataUtility.getInt(request.getParameter("pageNo"));
			int pageSize=DataUtility.getInt(request.getParameter("pageSize"));
			
			pageNo=(pageNo==0)?1:pageNo;
			pageSize=(pageSize==0)?DataUtility.getInt(PropertyReader.getValue("page.size")):pageSize;
			
			RoleModelInt model=ModelFactory.getInstance().getRoleModel();
			RoleDTO dto=(RoleDTO)populateDTO(request);
			//System.out.println("Role Id in dopost of rolelistctl "+ dto.getId());
			
			
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
					ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
					return;
					
				}else if(OP_DELETE.equalsIgnoreCase(op)) {
					pageNo=1;
					if(ids!=null && ids.length>0) {
						RoleDTO deletedto=new RoleDTO();
						for(String id:ids) {
							deletedto.setId(DataUtility.getLong(id));
							model.delete(deletedto);
							ServletUtility.setSuccessMessage("Data is deleted successfully", request);
						}
					}else {
						ServletUtility.setErrorMessage("Select at least one record", request);
					}
					
				}else if(OP_RESET.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
					return;
					
				}else if(OP_BACK.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
					return;
					
				}
				
				list=model.search(dto,pageNo,pageSize);
				System.out.println("Role Id in Role List Ctl"+dto.getId());
				next=model.search(dto,pageNo+1,pageSize);
				
				if(list==null || list.isEmpty()) {
					ServletUtility.setSuccessMessage("No Record Found", request);
				}
				System.out.println("List in Role list ctl: "+list);
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
		return ORSView.ROLE_LIST_VIEW;
	}

}
