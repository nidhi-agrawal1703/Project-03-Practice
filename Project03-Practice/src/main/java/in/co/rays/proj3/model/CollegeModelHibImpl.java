package in.co.rays.proj3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public class CollegeModelHibImpl extends BaseModel<CollegeDTO> implements CollegeModelInt {

	public CollegeModelHibImpl() {
		super(CollegeDTO.class);
	}
	
	@Override
	public Long add(CollegeDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		CollegeDTO existDto = null;
		existDto = findByName(dto.getName());
		if (existDto != null) {
			throw new DuplicateRecordException("Duplicate College Name found");
		}

		return super.add(dto);

	}

	@Override
	public void delete(CollegeDTO dto) throws ApplicationException, DatabaseException {
		super.delete(dto);
	}

	@Override
	public Long save(CollegeDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Long id=dto.getId();
		
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
			
		}
		return id;
	}

	@Override
	public void update(CollegeDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {

		CollegeDTO existDto = null;
		existDto = findByName(dto.getName());
		if (existDto != null && existDto.getId()!=dto.getId()) {
			throw new DuplicateRecordException("Duplicate College Name found");
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
	public void whereCondition(CollegeDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if(dto.getName()!=null && dto.getName().length()>0) {
				criteria.add(Restrictions.like("name",dto.getName()+"%"));
			}
			if(dto.getAddress()!=null && dto.getAddress().length()>0) {
				criteria.add(Restrictions.like("address",dto.getAddress()+"%"));
			}
			if(dto.getState()!=null && dto.getState().length()>0) {
				criteria.add(Restrictions.like("state",dto.getState()+"%"));
			}
			if(dto.getCity()!=null && dto.getCity().length()>0) {
				criteria.add(Restrictions.like("city",dto.getCity()+"%"));
			}
		}
	}
	
	@Override
	public List search(CollegeDTO dto) throws ApplicationException, DatabaseException {
		return search(dto,0,0);
	}

	@Override
	public List search(CollegeDTO dto, int pageNo, int pageSize) {
		return super.search(dto, pageNo, pageSize);
	}

	@Override
	public CollegeDTO findByPK(long pk) throws ApplicationException, DatabaseException {
		CollegeDTO dto=super.findByPK(pk);
		return dto;
	}

	@Override
	public CollegeDTO findByName(String name) throws ApplicationException {
		CollegeDTO dto=super.findByUniqueKey("name", name);
		return dto;
	}


}
