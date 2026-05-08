package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.ReportDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.ReportModelInt;
import in.co.rays.proj3.model.UserModelInt;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;

@WebServlet(name="ReportListCtl",urlPatterns={"/ctl/ReportListCtl"})
public class ReportListCtl extends BaseCtl {
	
	
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		ReportDTO dto=new ReportDTO();
		
		dto.setReportName(DataUtility.getString(request.getParameter("reportName")));
		dto.setReportStatus(DataUtility.getString(request.getParameter("reportStatus")));
		

		return dto;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		ReportDTO dto = (ReportDTO) populateDTO(request);
		ReportModelInt model=ModelFactory.getInstance().getReportModel();
		
		try {
			List<UserDTO> list = model.search(dto, pageNo, pageSize);
			List<UserDTO> next = model.search(dto, pageNo + 1, pageSize);

			if (list == null) {
			    list = new ArrayList<UserDTO>();
			}

			if (list.isEmpty()) {
			    ServletUtility.setErrorMessage("No record found", request);
			}
			//System.out.println("List in doGet of ReportListCl: "+list);
			//if (list == null || list.isEmpty()) {
				//ServletUtility.setErrorMessage("No record found", request);
			//}
			ServletUtility.setList(list, request);
			ServletUtility.setDto(dto, request);
			//ServletUtility.setBean(bean, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			
			request.setAttribute("nextListSize",
			        (next != null) ? next.size() : 0);

			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		ReportDTO dto = (ReportDTO) populateDTO(request);
		ReportModelInt model=ModelFactory.getInstance().getReportModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		String[] ids = request.getParameterValues("ids");
		try {
			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if ("Next".equalsIgnoreCase(op)) {
					pageNo++;
				} else if ("Previous".equalsIgnoreCase(op)) {
					pageNo--;
				}
			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.REPORT_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					ReportDTO dtodelete=new ReportDTO();
					//UserBean deleteBean = new UserBean();
					for (String id : ids) {
						dtodelete.setId(DataUtility.getLong(id));
						model.delete(dtodelete);
						ServletUtility.setSuccessMessage("REport Deleted Successfully", request);
					}
				} else {

					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.REPORT_LIST_CTL, request, response);
				return;
			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.REPORT_LIST_CTL, request, response);
				return;
			}
			list = model.search(dto, pageNo, pageSize);
			next = model.search(dto, pageNo + 1, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setDto(dto, request);
			//ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());

			ServletUtility.forward(getView(), request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}

	@Override
	protected String getView() {
		return ORSView.REPORT_LIST_VIEW;
	}

}
