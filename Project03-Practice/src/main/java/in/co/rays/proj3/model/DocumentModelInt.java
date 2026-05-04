package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.DocumentDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface DocumentModelInt {
	
	public Long add(DocumentDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void delete(DocumentDTO dto)throws ApplicationException,DatabaseException;
	public Long save(DocumentDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void update(DocumentDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public List list()throws ApplicationException,DatabaseException;
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public List search(DocumentDTO dto)throws ApplicationException,DatabaseException;
	public List search(DocumentDTO dto,int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public DocumentDTO findByPK(long pk)throws ApplicationException,DatabaseException;
	public DocumentDTO findByDocumentName(String documentName)throws ApplicationException;
}
