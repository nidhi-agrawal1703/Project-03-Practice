package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.UsageDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface UsageModelInt {
	
	public Long add(UsageDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void delete(UsageDTO dto)throws ApplicationException,DatabaseException;
	public Long save(UsageDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void update(UsageDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public List list()throws ApplicationException,DatabaseException;
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public List search(UsageDTO dto)throws ApplicationException,DatabaseException;
	public List search(UsageDTO dto,int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public UsageDTO findByPK(long pk)throws ApplicationException,DatabaseException;
	public UsageDTO findByUsageCode(String usageCode)throws ApplicationException;


}
