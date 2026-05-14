package in.co.rays.proj3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.NFTAssetDTO;
import in.co.rays.proj3.dto.NFTAssetDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public class NFTAssetModelHibImpl extends BaseModel<NFTAssetDTO> implements NFTAssetModelInt {

	public NFTAssetModelHibImpl() {
		super(NFTAssetDTO.class);
	}
	
	@Override
	public Long add(NFTAssetDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		NFTAssetDTO existDto = null;
		existDto = findByNFTCode(dto.getNftCode());
		if (existDto != null) {
			throw new DuplicateRecordException("Duplicate NFTAsset Code found");
		}

		return super.add(dto);

	}

	@Override
	public void delete(NFTAssetDTO dto) throws ApplicationException, DatabaseException {
		super.delete(dto);
	}

	@Override
	public Long save(NFTAssetDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Long id=dto.getId();
		
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
			
		}
		return id;
	}

	@Override
	public void update(NFTAssetDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {

		NFTAssetDTO existDto = null;
		existDto = findByNFTCode(dto.getNftCode());
		if (existDto != null && existDto.getId()!=dto.getId()) {
			throw new DuplicateRecordException("Duplicate NFTAsset Code found");
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
	public void whereCondition(NFTAssetDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if (dto.getNftCode() != null && dto.getNftCode().length() > 0) {
				criteria.add(Restrictions.like("nftCode", dto.getNftCode() + "%"));
			}
			if(dto.getAssetName()!=null && dto.getAssetName().length()>0) {
				criteria.add(Restrictions.like("assetName", dto.getAssetName()+"%"));
			}
		}
	}
	
	@Override
	public List search(NFTAssetDTO dto) throws ApplicationException, DatabaseException {
		return search(dto,0,0);
	}

	@Override
	public List search(NFTAssetDTO dto, int pageNo, int pageSize) {
		return super.search(dto, pageNo, pageSize);
	}

	@Override
	public NFTAssetDTO findByPK(long pk) throws ApplicationException, DatabaseException {
		NFTAssetDTO dto=super.findByPK(pk);
		return dto;
	}

	
	@Override
	public NFTAssetDTO findByNFTCode(String nftCode) throws ApplicationException {
		NFTAssetDTO dto=super.findByUniqueKey("nftCode", nftCode);
		return dto;
	}

}
