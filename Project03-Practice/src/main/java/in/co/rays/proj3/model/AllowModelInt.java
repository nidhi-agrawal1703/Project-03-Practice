package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.AllowDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface AllowModelInt {


	public Long add(AllowDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void delete(AllowDTO dto)throws ApplicationException,DatabaseException;
	public Long save(AllowDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void update(AllowDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public List list()throws ApplicationException,DatabaseException;
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public List search(AllowDTO dto)throws ApplicationException,DatabaseException;
	public List search(AllowDTO dto,int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public AllowDTO findByPK(long pk)throws ApplicationException,DatabaseException;
	public AllowDTO findByAllowCode(String allowCode)throws ApplicationException;
}
