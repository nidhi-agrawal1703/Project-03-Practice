package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.SettingsDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface SettingsModelInt {
	
	public Long add(SettingsDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void delete(SettingsDTO dto)throws ApplicationException,DatabaseException;
	public Long save(SettingsDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void update(SettingsDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public List list()throws ApplicationException,DatabaseException;
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public List search(SettingsDTO dto)throws ApplicationException,DatabaseException;
	public List search(SettingsDTO dto,int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public SettingsDTO findByPK(long pk)throws ApplicationException,DatabaseException;
	public SettingsDTO findBySettingName(String settingName)throws ApplicationException;
}
