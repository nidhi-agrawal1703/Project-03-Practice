package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.AccountDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface AccountModelInt {
	
	public Long add(AccountDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void delete(AccountDTO dto)throws ApplicationException,DatabaseException;
	public Long save(AccountDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void update(AccountDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public List list()throws ApplicationException,DatabaseException;
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public List search(AccountDTO dto)throws ApplicationException,DatabaseException;
	public List search(AccountDTO dto,int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public AccountDTO findByPK(long pk)throws ApplicationException,DatabaseException;
	public AccountDTO findByAccNumber(String accNumber)throws ApplicationException;

}
