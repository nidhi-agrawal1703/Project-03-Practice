package in.co.rays.proj3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.StudentDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public class StudentModelHibImpl extends BaseModel<StudentDTO> implements StudentModelInt {

	public StudentModelHibImpl() {
		super(StudentDTO.class);
	}

	@Override
	public Long add(StudentDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		StudentDTO existDto = null;
		existDto = findByEmail(dto.getEmail());
		if (existDto != null) {
			throw new DuplicateRecordException("Duplicate Student Email  found");
		}
		
		return super.add(dto);

	}

	@Override
	public void delete(StudentDTO dto) throws ApplicationException, DatabaseException {
		super.delete(dto);
	}

	@Override
	public Long save(StudentDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Long id=dto.getId();
		
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
			
		}
		return id;
	}

	@Override
	public void update(StudentDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {

		StudentDTO existDto = null;
		existDto = findByEmail(dto.getEmail());
		if (existDto != null && existDto.getId()!=dto.getId()) {
			throw new DuplicateRecordException("Duplicate Student Email found");
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
	public void whereCondition(StudentDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if(dto.getFirstName()!=null && dto.getFirstName().length()>0) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName()+"%"));
			}
			if(dto.getLastName()!=null && dto.getLastName().length()>0) {
				criteria.add(Restrictions.like("lastName", dto.getLastName()+"%"));
			}
			if(dto.getEmail()!=null && dto.getEmail().length()>0) {
				criteria.add(Restrictions.like("email", dto.getEmail()+"%"));
			}
			if(dto.getDob()!=null && dto.getDob().getDate()>0) {
				criteria.add(Restrictions.eq("dob", dto.getDob()));
			}
			
			if(dto.getMobileNo()!=null && dto.getMobileNo().length()>0) {
				criteria.add(Restrictions.eq("mobileNo", dto.getMobileNo()));
			}
		}
	}
	
	@Override
	public List search(StudentDTO dto) throws ApplicationException, DatabaseException {
		return search(dto,0,0);
	}

	@Override
	public List search(StudentDTO dto, int pageNo, int pageSize) {
		return super.search(dto, pageNo, pageSize);
	}

	@Override
	public StudentDTO findByPK(long pk) throws ApplicationException, DatabaseException {
		StudentDTO dto=super.findByPK(pk);
		return dto;
	}

	@Override
	public StudentDTO findByEmail(String email) throws ApplicationException {
		StudentDTO dto=super.findByUniqueKey("email", email);
		return dto;
	}

}
