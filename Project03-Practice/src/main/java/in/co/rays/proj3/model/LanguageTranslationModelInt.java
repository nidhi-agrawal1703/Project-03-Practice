package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.LanguageTranslationDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface LanguageTranslationModelInt {
	
	public Long add(LanguageTranslationDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void delete(LanguageTranslationDTO dto)throws ApplicationException,DatabaseException;
	public Long save(LanguageTranslationDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void update(LanguageTranslationDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public List list()throws ApplicationException,DatabaseException;
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public List search(LanguageTranslationDTO dto)throws ApplicationException,DatabaseException;
	public List search(LanguageTranslationDTO dto,int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public LanguageTranslationDTO findByPK(long pk)throws ApplicationException,DatabaseException;
	public LanguageTranslationDTO findBySourceLanguage(String sourceLanguage)throws ApplicationException;
	public LanguageTranslationDTO findByTargetLanguage(String targetLanguage)throws ApplicationException;

}
