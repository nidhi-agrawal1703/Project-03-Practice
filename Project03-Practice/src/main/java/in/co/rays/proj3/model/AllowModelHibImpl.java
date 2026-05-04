package in.co.rays.proj3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.AllowDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public class AllowModelHibImpl extends BaseModel<AllowDTO> implements AllowModelInt {

	public AllowModelHibImpl() {
		super(AllowDTO.class);
	}

	@Override
	public Long add(AllowDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		AllowDTO existDto = null;
		existDto = findByAllowCode(dto.getAllowCode());
		if (existDto != null) {
			throw new DuplicateRecordException("Duplicate Allow Code found");
		}

		return super.add(dto);

	}

	@Override
	public void delete(AllowDTO dto) throws ApplicationException, DatabaseException {
		super.delete(dto);
	}

	@Override
	public Long save(AllowDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Long id=dto.getId();
		
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
			
		}
		return id;
	}

	@Override
	public void update(AllowDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {

		AllowDTO existDto = null;
		existDto = findByAllowCode(dto.getAllowCode());
		if (existDto != null && existDto.getId()!=dto.getId()) {
			throw new DuplicateRecordException("Duplicate Allow Code found");
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
	public void whereCondition(AllowDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if (dto.getAllowCode() != null && dto.getAllowCode().length() > 0) {
				criteria.add(Restrictions.like("allowCode", dto.getAllowCode() + "%"));
			}
			if(dto.getUserName()!=null && dto.getUserName().length()>0) {
				criteria.add(Restrictions.like("userName", dto.getUserName()+"%"));
			}
		}
	}
	
	@Override
	public List search(AllowDTO dto) throws ApplicationException, DatabaseException {
		return search(dto,0,0);
	}

	@Override
	public List search(AllowDTO dto, int pageNo, int pageSize) {
		return super.search(dto, pageNo, pageSize);
	}

	@Override
	public AllowDTO findByPK(long pk) throws ApplicationException, DatabaseException {
		AllowDTO dto=super.findByPK(pk);
		return dto;
	}

	
	@Override
	public AllowDTO findByAllowCode(String allowCode) throws ApplicationException {
		AllowDTO dto=super.findByUniqueKey("allowCode", allowCode);
		return dto;
	}

}
