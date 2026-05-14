package in.co.rays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.AccountDTO;
import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.NFTAssetDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.AccountModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.NFTAssetModelInt;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;

@WebServlet(name="NFTAssetCtl",urlPatterns= {"/ctl/NFTAssetCtl"})
public class NFTAssetCtl extends BaseCtl{
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		
		boolean pass=true;
		
		if(DataValidator.isNull(request.getParameter("nftCode"))) {
			request.setAttribute("nftCode", PropertyReader.getValue("error.require","NFT Code"));
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("assetName"))) {
			request.setAttribute("assetName", PropertyReader.getValue("error.require","NFT Asset Name"));
			pass=false;
		}else if(!DataValidator.isName(request.getParameter("assetName"))) {
			request.setAttribute("assetName", "Invalid Asset Name");
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("ownerName"))) {
			request.setAttribute("ownerName", PropertyReader.getValue("error.require","NFT Owner Name"));
			pass=false;
		}else if(!DataValidator.isName(request.getParameter("ownerName"))) {
			request.setAttribute("ownerName", "Invalid Owner Name");
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
		
		NFTAssetDTO dto=new NFTAssetDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setNftCode(DataUtility.getString(request.getParameter("nftCode")));
		dto.setAssetName(DataUtility.getString(request.getParameter("assetName")));
		dto.setOwnerName(DataUtility.getString(request.getParameter("ownerName")));
		dto.setStatus(DataUtility.getString(request.getParameter("status")));
		
		populateBean(dto, request);
		
		return dto;
	}
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		

		long id=DataUtility.getLong(request.getParameter("id"));
		
		NFTAssetModelInt model=ModelFactory.getInstance().getNFTAssetModel();
		
		if(id>0) {
			try {
				NFTAssetDTO dto=model.findByPK(id);
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
		NFTAssetModelInt model=ModelFactory.getInstance().getNFTAssetModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equalsIgnoreCase(op)) {
			NFTAssetDTO dto=(NFTAssetDTO)populateDTO(request);
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
				ServletUtility.handleException(e, request, response, "NFTAsset", dto);
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
		return ORSView.NFTASSET_VIEW;
	}

}
