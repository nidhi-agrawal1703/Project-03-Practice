package in.co.rays.proj3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.UsageDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public class UsageModelHibImpl extends BaseModel<UsageDTO> implements UsageModelInt{

	public UsageModelHibImpl() {
		super(UsageDTO.class);
	}
	
	@Override
	public Long add(UsageDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		UsageDTO existDto = null;
		existDto=findByUsageCode(dto.getUsageCode());
			if (existDto != null) {
			throw new DuplicateRecordException("Duplicate Usage Code found");
		}

		return super.add(dto);

	}

	@Override
	public void delete(UsageDTO dto) throws ApplicationException, DatabaseException {
		super.delete(dto);
	}

	@Override
	public Long save(UsageDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Long id=dto.getId();
		
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
			
		}
		return id;
	}

	@Override
	public void update(UsageDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {

		UsageDTO existDto = null;
		existDto=findByUsageCode(dto.getUsageCode());
		if (existDto != null && existDto.getId()!=dto.getId()) {
			throw new DuplicateRecordException("Duplicate Usage Code found");
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
	public void whereCondition(UsageDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if (dto.getUserName() != null && dto.getUserName().length() > 0) {
				criteria.add(Restrictions.like("userName", dto.getUserName() + "%"));
			}
			if(dto.getUsageCode()!=null && dto.getUsageCode().length()>0) {
				criteria.add(Restrictions.like("usageCode", dto.getUsageCode() + "%"));
			}
		}
	}
	
	@Override
	public List search(UsageDTO dto) throws ApplicationException, DatabaseException {
		return search(dto,0,0);
	}

	@Override
	public List search(UsageDTO dto, int pageNo, int pageSize) {
		return super.search(dto, pageNo, pageSize);
	}
	
	@Override
	public UsageDTO findByPK(long pk) throws ApplicationException, DatabaseException {
		UsageDTO dto=super.findByPK(pk);
		return dto;
	}
	
	@Override
	public UsageDTO findByUsageCode(String usageCode) throws ApplicationException {
		UsageDTO dto=super.findByUniqueKey("usageCode", usageCode);
		return dto;
	}

}
