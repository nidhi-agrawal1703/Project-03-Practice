package in.co.rays.proj3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.LanguageTranslationDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public class LanguageTranslationModelHibImpl extends BaseModel<LanguageTranslationDTO> implements LanguageTranslationModelInt {

	public LanguageTranslationModelHibImpl() {
		super(LanguageTranslationDTO.class);
	}
	
	@Override
	public Long add(LanguageTranslationDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		//LanguageTranslationDTO sourceDto = null;
		//sourceDto = findBySourceLanguage(dto.getSourceLanguage());
		
		//LanguageTranslationDTO targetDto = null;
		//targetDto = findByTargetLanguage(dto.getTargetLanguage());
		
		//if(sourceDto!=null ) {
			//if(targetDto!=null) {
				//throw new DuplicateRecordException("Duplicate Source Language to Target Languge found");
			//}
			
		//}
		//if (existDto != null) {
		//	throw new DuplicateRecordException("Duplicate Source Language Code found");
		//}

		return super.add(dto);

	}

	@Override
	public void delete(LanguageTranslationDTO dto) throws ApplicationException, DatabaseException {
		super.delete(dto);
	}

	@Override
	public Long save(LanguageTranslationDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Long id=dto.getId();
		
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
			
		}
		return id;
	}

	@Override
	public void update(LanguageTranslationDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {

		//LanguageTranslationDTO existDto = null;
		//existDto = findBySourceLanguage(dto.getSourceLanguage());
		//if (existDto != null && existDto.getId()!=dto.getId()) {
		//	throw new DuplicateRecordException("Duplicate Source Language Code found");
		//}

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
	public void whereCondition(LanguageTranslationDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if (dto.getSourceLanguage() != null && dto.getSourceLanguage().length() > 0) {
				criteria.add(Restrictions.like("sourceLanguage", dto.getSourceLanguage() + "%"));
			}
			if(dto.getTargetLanguage()!=null && dto.getTargetLanguage().length()>0) {
				criteria.add(Restrictions.like("targetLanguage", dto.getTargetLanguage()+"%"));
			}
		}
	}
	
	@Override
	public List search(LanguageTranslationDTO dto) throws ApplicationException, DatabaseException {
		return search(dto,0,0);
	}

	@Override
	public List search(LanguageTranslationDTO dto, int pageNo, int pageSize) {
		return super.search(dto, pageNo, pageSize);
	}

	@Override
	public LanguageTranslationDTO findByPK(long pk) throws ApplicationException, DatabaseException {
		LanguageTranslationDTO dto=super.findByPK(pk);
		return dto;
	}

	
	@Override
	public LanguageTranslationDTO findBySourceLanguage(String sourceLanguage) throws ApplicationException {
		LanguageTranslationDTO dto=super.findByUniqueKey("sourceLanguage", sourceLanguage);
		return dto;
	}
	
	@Override
	public LanguageTranslationDTO findByTargetLanguage(String targetLanguage) throws ApplicationException {
		LanguageTranslationDTO dto=super.findByUniqueKey("targetLanguage", targetLanguage);
		return dto;
	}
}
