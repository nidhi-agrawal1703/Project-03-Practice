package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.NFTAssetDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface NFTAssetModelInt {
	
	public Long add(NFTAssetDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void delete(NFTAssetDTO dto)throws ApplicationException,DatabaseException;
	public Long save(NFTAssetDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public void update(NFTAssetDTO dto)throws ApplicationException,DuplicateRecordException,DatabaseException;
	public List list()throws ApplicationException,DatabaseException;
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public List search(NFTAssetDTO dto)throws ApplicationException,DatabaseException;
	public List search(NFTAssetDTO dto,int pageNo,int pageSize)throws ApplicationException,DatabaseException;
	public NFTAssetDTO findByPK(long pk)throws ApplicationException,DatabaseException;
	public NFTAssetDTO findByNFTCode(String nftCode)throws ApplicationException;

}
