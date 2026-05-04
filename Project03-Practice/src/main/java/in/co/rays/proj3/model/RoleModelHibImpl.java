package in.co.rays.proj3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public class RoleModelHibImpl extends BaseModel<RoleDTO> implements RoleModelInt {

	public RoleModelHibImpl() {
		super(RoleDTO.class);
	}

	@Override
	public Long add(RoleDTO dto) throws ApplicationException, DuplicateRecordException,DatabaseException {
		RoleDTO existDto = null;
		existDto = findByName(dto.getName());
		if (existDto != null) {
			throw new DuplicateRecordException("Duplicate Name found");
		}

		return super.add(dto);
	}
	
	@Override
	public void update(RoleDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Session session = null;
		Transaction tx = null;
		RoleDTO existDto = findByName(dto.getName());

		// Check if name id already exists
		if (existDto != null && existDto.getId() != dto.getId()) {
			throw new DuplicateRecordException("Name already exists");
		}
		super.update(dto);
	}
	
	@Override
	public Long save(RoleDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Long id=dto.getId();
		
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
			
		}
		return id;
	}
	
	@Override
	public void delete(RoleDTO dto) throws ApplicationException, DatabaseException {
		
		super.delete(dto);
	}
	
	@Override
	public void whereCondition(RoleDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if(dto.getName()!=null && dto.getName().length()>0) {
				criteria.add(Restrictions.like("name", dto.getName()+"%"));
			}
		}
	}
	
	
	@Override
	public List search(RoleDTO dto) throws ApplicationException, DatabaseException {
		return search(dto, 0, 0);
	}
	
	public List search(RoleDTO dto,int pageNo,int pageSize){
		return super.search(dto, pageNo, pageSize);
	}
	
	@Override
	public List list() throws ApplicationException, DatabaseException {
		
		return list(0, 0);
	}
	
	@Override
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException{
		return super.list(pageNo, pageSize);
	}

		
	@Override
	public RoleDTO findByPK(long pk)throws ApplicationException, DatabaseException{
		RoleDTO dto=super.findByPK(pk);
		return dto;
	}

	@Override
	public RoleDTO findByName(String name) throws ApplicationException {
		RoleDTO dto=super.findByUniqueKey("name", name);
		return dto;
	}

	

}
