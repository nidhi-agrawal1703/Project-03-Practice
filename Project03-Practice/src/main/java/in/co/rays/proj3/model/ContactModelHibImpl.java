package in.co.rays.proj3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.ContactDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public class ContactModelHibImpl extends BaseModel<ContactDTO> implements ContactModelInt {

	public ContactModelHibImpl() {
		super(ContactDTO.class);
	}
	
	@Override
	public Long add(ContactDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		ContactDTO existDto = null;
		existDto = findByEmail(dto.getEmail());
		if (existDto != null) {
			throw new DuplicateRecordException("Duplicate Email found");
		}

		return super.add(dto);

	}

	@Override
	public void delete(ContactDTO dto) throws ApplicationException, DatabaseException {
		super.delete(dto);
	}

	@Override
	public Long save(ContactDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Long id=dto.getId();
		
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
			
		}
		return id;
	}

	@Override
	public void update(ContactDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {

		ContactDTO existDto = null;
		existDto = findByEmail(dto.getEmail());
		if (existDto != null && existDto.getId()!=dto.getId()) {
			throw new DuplicateRecordException("Duplicate Email found");
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
	public void whereCondition(ContactDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if (dto.getName() != null && dto.getName().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if(dto.getEmail()!=null && dto.getEmail().length()>0) {
				criteria.add(Restrictions.like("email", dto.getEmail()+"%"));
			}
		}
	}
	
	@Override
	public List search(ContactDTO dto) throws ApplicationException, DatabaseException {
		return search(dto,0,0);
	}

	@Override
	public List search(ContactDTO dto, int pageNo, int pageSize) {
		return super.search(dto, pageNo, pageSize);
	}

	@Override
	public ContactDTO findByPK(long pk) throws ApplicationException, DatabaseException {
		ContactDTO dto=super.findByPK(pk);
		return dto;
	}

	@Override
	public ContactDTO findByEmail(String email) throws ApplicationException {
		ContactDTO dto=super.findByUniqueKey("email", email);
		return dto;
	}

}
