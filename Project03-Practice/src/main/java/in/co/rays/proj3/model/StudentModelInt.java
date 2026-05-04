package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.StudentDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface StudentModelInt {
	
	public Long add(StudentDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void delete(StudentDTO dto)throws ApplicationException,DatabaseException;
	public Long save(StudentDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void update(StudentDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public List list()throws ApplicationException,DatabaseException;
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public List search(StudentDTO dto)throws ApplicationException,DatabaseException;
	public List search(StudentDTO dto,int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public StudentDTO findByPK(long pk)throws ApplicationException,DatabaseException;
	public StudentDTO findByEmail(String email)throws ApplicationException;

}
