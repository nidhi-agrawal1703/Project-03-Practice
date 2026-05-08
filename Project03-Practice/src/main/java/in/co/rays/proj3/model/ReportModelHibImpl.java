package in.co.rays.proj3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.ReportDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public class ReportModelHibImpl extends BaseModel<ReportDTO> implements ReportModelInt {

	public ReportModelHibImpl() {
		super(ReportDTO.class);
	}

	@Override
	public Long add(ReportDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		ReportDTO existDto = null;
		existDto = findByReportName(dto.getReportName());
		if (existDto != null) {
			throw new DuplicateRecordException("Duplicate Report Name found");
		}

		return super.add(dto);

	}

	@Override
	public void delete(ReportDTO dto) throws ApplicationException, DatabaseException {
		super.delete(dto);
	}

	@Override
	public Long save(ReportDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Long id=dto.getId();
		
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
			
		}
		return id;
	}

	@Override
	public void update(ReportDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {

		ReportDTO existDto = null;
		existDto = findByReportName(dto.getReportName());
		if (existDto != null && existDto.getId()!=dto.getId()) {
			throw new DuplicateRecordException("Duplicate Report Name found");
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
	public void whereCondition(ReportDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if (dto.getReportName() != null && dto.getReportName().length() > 0) {
				criteria.add(Restrictions.like("reportName", dto.getReportName() + "%"));
			}
			if(dto.getReportStatus()!=null && dto.getReportStatus().length()>0) {
				criteria.add(Restrictions.like("reportStatus", dto.getReportStatus()+"%"));
			}
		}
	}
	
	@Override
	public List search(ReportDTO dto) throws ApplicationException, DatabaseException {
		return search(dto,0,0);
	}

	@Override
	public List search(ReportDTO dto, int pageNo, int pageSize) {
		return super.search(dto, pageNo, pageSize);
	}

	@Override
	public ReportDTO findByPK(long pk) throws ApplicationException, DatabaseException {
		ReportDTO dto=super.findByPK(pk);
		return dto;
	}

	
	@Override
	public ReportDTO findByReportName(String reportName) throws ApplicationException {
		ReportDTO dto=super.findByUniqueKey("reportName", reportName);
		return dto;
	}
}
