package in.co.rays.proj3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.DocumentDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public class DocumentModelHibImpl extends BaseModel<DocumentDTO> implements DocumentModelInt {

	public DocumentModelHibImpl() {
		super(DocumentDTO.class);
	}

	@Override
	public Long add(DocumentDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		DocumentDTO existDto = null;
		existDto = findByDocumentName(dto.getDocumentName());
		if (existDto != null) {
			throw new DuplicateRecordException("Duplicate Document Name found");
		}

		return super.add(dto);

	}

	@Override
	public void delete(DocumentDTO dto) throws ApplicationException, DatabaseException {
		super.delete(dto);
	}

	@Override
	public Long save(DocumentDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Long id=dto.getId();
		
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
			
		}
		return id;
	}

	@Override
	public void update(DocumentDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {

		DocumentDTO existDto = null;
		existDto =findByDocumentName(dto.getDocumentName());
		if (existDto != null && existDto.getId()!=dto.getId()) {
			throw new DuplicateRecordException("Duplicate Document Name found");
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
	public void whereCondition(DocumentDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if (dto.getDocumentName() != null && dto.getDocumentName().length() > 0) {
				criteria.add(Restrictions.like("documentName", dto.getDocumentName() + "%"));
			}
			if(dto.getDocumentType()!=null && dto.getDocumentType().length()>0) {
				criteria.add(Restrictions.like("documentType", dto.getDocumentType()+"%"));
			}
		}
	}
	
	@Override
	public List search(DocumentDTO dto) throws ApplicationException, DatabaseException {
		return search(dto,0,0);
	}

	@Override
	public List search(DocumentDTO dto, int pageNo, int pageSize) {
		return super.search(dto, pageNo, pageSize);
	}

	@Override
	public DocumentDTO findByPK(long pk) throws ApplicationException, DatabaseException {
		DocumentDTO dto=super.findByPK(pk);
		return dto;
	}

	
	@Override
	public DocumentDTO findByDocumentName(String documentName) throws ApplicationException {
		DocumentDTO dto=super.findByUniqueKey("documentName", documentName);
		return dto;
	}

}
