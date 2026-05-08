package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.ReportDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface ReportModelInt {
	
	public Long add(ReportDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void delete(ReportDTO dto)throws ApplicationException,DatabaseException;
	public Long save(ReportDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void update(ReportDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public List list()throws ApplicationException,DatabaseException;
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public List search(ReportDTO dto)throws ApplicationException,DatabaseException;
	public List search(ReportDTO dto,int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public ReportDTO findByPK(long pk)throws ApplicationException,DatabaseException;
	public ReportDTO findByReportName(String reportName)throws ApplicationException;

}
