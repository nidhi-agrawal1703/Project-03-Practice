package in.co.rays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.AllowDTO;
import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.LanguageTranslationDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.AllowModelInt;
import in.co.rays.proj3.model.LanguageTranslationModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;

@WebServlet(name="LanguageTranslationCtl",urlPatterns= {"/ctl/LanguageTranslationCtl"})
public class LanguageTranslationCtl extends BaseCtl{
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass=true;
		
		if(DataValidator.isNull(request.getParameter("sourceLanguage"))) {
			request.setAttribute("sourceLanguage", PropertyReader.getValue("error.require","Source Language"));
			pass=false;
		}else if(!DataValidator.isName(request.getParameter("sourceLanguage"))) {
			request.setAttribute("sourceLanguage", "Invalid Source Language");
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("targetLanguage"))) {
			request.setAttribute("targetLanguage", PropertyReader.getValue("error.require","Target Language"));
			pass=false;
		}else if(!DataValidator.isName(request.getParameter("targetLanguage"))) {
			request.setAttribute("targetLanguage", "Invalid Target Language");
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("inputText"))) {
			request.setAttribute("inputText", PropertyReader.getValue("error.require","Input Text"));
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("translatedText"))) {
			request.setAttribute("translatedText", PropertyReader.getValue("error.require","Translated Text"));
			pass=false;
		}
		
		return pass;
	}
	
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		
		LanguageTranslationDTO dto=new LanguageTranslationDTO();
		
		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setSourceLanguage(DataUtility.getString(request.getParameter("sourceLanguage")));
		dto.setTargetLanguage(DataUtility.getString(request.getParameter("targetLanguage")));
		dto.setInputText(DataUtility.getString(request.getParameter("inputText")));
		dto.setTranslatedText(DataUtility.getString(request.getParameter("translatedText")));
		
		populateBean(dto, request);
		
		
		return dto;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DatabaseException {
		
		long id=DataUtility.getLong(request.getParameter("id"));
		
		LanguageTranslationModelInt model=ModelFactory.getInstance().getLanguageTranslationModel();
		
		if(id>0) {
			try {
				LanguageTranslationDTO dto=model.findByPK(id);
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
		LanguageTranslationModelInt model=ModelFactory.getInstance().getLanguageTranslationModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equalsIgnoreCase(op)) {
			LanguageTranslationDTO dto=(LanguageTranslationDTO)populateDTO(request);
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
				ServletUtility.handleException(e, request, response, "LanguageTranslation", dto);
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
		return ORSView.LANGUAGETRANSLATION_VIEW;
	}

}
