package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface CollegeModelInt {
	
	public Long add(CollegeDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void delete(CollegeDTO dto)throws ApplicationException,DatabaseException;
	public Long save(CollegeDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void update(CollegeDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public List list()throws ApplicationException,DatabaseException;
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public List search(CollegeDTO dto)throws ApplicationException,DatabaseException;
	public List search(CollegeDTO dto,int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public CollegeDTO findByPK(long pk)throws ApplicationException,DatabaseException;
	public CollegeDTO findByName(String name)throws ApplicationException;

}
