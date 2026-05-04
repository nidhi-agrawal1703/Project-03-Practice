package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.ContactDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface ContactModelInt {
	
	public Long add(ContactDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void delete(ContactDTO dto)throws ApplicationException,DatabaseException;
	public Long save(ContactDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void update(ContactDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public List list()throws ApplicationException,DatabaseException;
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public List search(ContactDTO dto)throws ApplicationException,DatabaseException;
	public List search(ContactDTO dto,int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public ContactDTO findByPK(long pk)throws ApplicationException,DatabaseException;
	public ContactDTO findByEmail(String email)throws ApplicationException;

}
