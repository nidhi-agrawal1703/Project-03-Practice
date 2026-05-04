package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface RoleModelInt {

	public Long add(RoleDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void delete(RoleDTO dto)throws ApplicationException,DatabaseException;
	public Long save(RoleDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void update(RoleDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public List list()throws ApplicationException,DatabaseException;
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public List search(RoleDTO dto)throws ApplicationException,DatabaseException;
	public List search(RoleDTO dto,int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public RoleDTO findByPK(long pk)throws ApplicationException,DatabaseException;
	public RoleDTO findByName(String name)throws ApplicationException;
	
}
