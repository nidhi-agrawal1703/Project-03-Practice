package in.co.rays.proj3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.AccountDTO;
import in.co.rays.proj3.dto.AccountDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public class AccountModelHibImpl extends BaseModel<AccountDTO> implements AccountModelInt {

	public AccountModelHibImpl() {
		super(AccountDTO.class);
	}
	
	@Override
	public Long add(AccountDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		AccountDTO existDto = null;
		existDto = findByAccNumber(dto.getAccNumber());
		if (existDto != null) {
			throw new DuplicateRecordException("Duplicate Account Number found");
		}

		return super.add(dto);

	}

	@Override
	public void delete(AccountDTO dto) throws ApplicationException, DatabaseException {
		super.delete(dto);
	}

	@Override
	public Long save(AccountDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Long id=dto.getId();
		
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
			
		}
		return id;
	}

	@Override
	public void update(AccountDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {

		AccountDTO existDto = null;
		existDto = findByAccNumber(dto.getAccNumber());
		if (existDto != null && existDto.getId()!=dto.getId()) {
			throw new DuplicateRecordException("Duplicate Account Number found");
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
	public void whereCondition(AccountDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if (dto.getAccNumber() != null && dto.getAccNumber().length() > 0) {
				criteria.add(Restrictions.like("accNumber", dto.getAccNumber() + "%"));
			}
			if(dto.getHolderName()!=null && dto.getHolderName().length()>0) {
				criteria.add(Restrictions.like("holderName", dto.getHolderName()+"%"));
			}
		}
	}
	
	@Override
	public List search(AccountDTO dto) throws ApplicationException, DatabaseException {
		return search(dto,0,0);
	}

	@Override
	public List search(AccountDTO dto, int pageNo, int pageSize) {
		return super.search(dto, pageNo, pageSize);
	}

	@Override
	public AccountDTO findByPK(long pk) throws ApplicationException, DatabaseException {
		AccountDTO dto=super.findByPK(pk);
		return dto;
	}

	@Override
	public AccountDTO findByAccNumber(String accNumber) throws ApplicationException {
		AccountDTO dto=super.findByUniqueKey("accNumber", accNumber);
		return dto;
	}

}
