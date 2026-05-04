package in.co.rays.proj3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.SettingsDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public class SettingsModelHibImpl extends BaseModel<SettingsDTO> implements SettingsModelInt{

	public SettingsModelHibImpl() {
		super(SettingsDTO.class);
	}

	@Override
	public Long add(SettingsDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		SettingsDTO existDto = null;
		existDto = findBySettingName(dto.getSettingName());
		if (existDto != null) {
			throw new DuplicateRecordException("Duplicate Setting Name found");
		}

		return super.add(dto);

	}

	@Override
	public void delete(SettingsDTO dto) throws ApplicationException, DatabaseException {
		super.delete(dto);
	}

	@Override
	public Long save(SettingsDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Long id=dto.getId();
		
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
			
		}
		return id;
	}

	@Override
	public void update(SettingsDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {

		SettingsDTO existDto = null;
		existDto = findBySettingName(dto.getSettingName());
		if (existDto != null && existDto.getId()!=dto.getId()) {
			throw new DuplicateRecordException("Duplicate Setting Name found");
		}

		super.update(dto);

	}

	@Override
	public List list() throws ApplicationException, DatabaseException {
		return list(0,0);
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException, DatabaseException {
		return super.list(pageNo, pageSize);
	}
	
	@Override
	public void whereCondition(SettingsDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if (dto.getSettingName() != null && dto.getSettingName().length() > 0) {
				criteria.add(Restrictions.like("settingName", dto.getSettingName() + "%"));
			}
			if(dto.getSettingValue()!=null && dto.getSettingValue().length()>0) {
				criteria.add(Restrictions.like("settingValue", dto.getSettingValue()+"%"));
			}
		}
	}
	
	@Override
	public List search(SettingsDTO dto) throws ApplicationException, DatabaseException {
		return search(dto,0,0);
	}

	@Override
	public List search(SettingsDTO dto, int pageNo, int pageSize) {
		return super.search(dto, pageNo, pageSize);
	}

	@Override
	public SettingsDTO findByPK(long pk) throws ApplicationException, DatabaseException {
		SettingsDTO dto=super.findByPK(pk);
		return dto;
	}

	@Override
	public SettingsDTO findBySettingName(String settingName) throws ApplicationException {
		SettingsDTO dto=super.findByUniqueKey("settingName", settingName);
		return dto;
	}

}
