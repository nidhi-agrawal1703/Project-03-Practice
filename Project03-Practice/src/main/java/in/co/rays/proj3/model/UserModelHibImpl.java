package in.co.rays.proj3.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.AllowDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.PrimaryKeyNotFoundException;
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.util.EmailBuilder;
import in.co.rays.proj3.util.EmailMessage;
import in.co.rays.proj3.util.EmailUtility;
import in.co.rays.proj3.util.HibDataSource;

/**
 * Implementation of Hibernate version of User Model  
 * @author Nidhi
 *
 */
public class UserModelHibImpl extends BaseModel<UserDTO> implements UserModelInt {

	public UserModelHibImpl() {
		super(UserDTO.class);
	}

	@Override
	public Long add(UserDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		UserDTO existDto = null;
		existDto = findByLogin(dto.getLogin());
		if (existDto != null) {
			throw new DuplicateRecordException("Duplicate Login Id found");
		}

		return super.add(dto);
	}
	
	@Override
	public void update(UserDTO dto) throws ApplicationException, DuplicateRecordException, DatabaseException {
		
		Session session = null;
		Transaction tx = null;
		UserDTO existDto = findByLogin(dto.getLogin());

		// Check if login id already exists
		if (existDto != null && existDto.getId() != dto.getId()) {
			throw new DuplicateRecordException("Login Id already exists");
		}
		super.update(dto);
	}
	
	@Override
	public void delete(UserDTO dto) throws ApplicationException, DatabaseException {
		
		super.delete(dto);
	}
	
	@Override
	public void whereCondition(UserDTO dto, Criteria criteria) {
		
		if(dto!=null) {
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
			}
			if(dto.getLastName()!=null && dto.getLastName().length()>0) {
				criteria.add(Restrictions.like("lastName", dto.getLastName()+"%"));
			}
			if(dto.getRoleId()>0) {
				criteria.add(Restrictions.eq("roleId", dto.getRoleId()));
			}
		}
	}

	@Override
	public List search(UserDTO dto) throws ApplicationException, DatabaseException {
		return search(dto, 0, 0);
	}
	
	public List search(UserDTO dto,int pageNo,int pageSize){
		return super.search(dto, pageNo, pageSize);
	}
	
	
	@Override
	public List list() throws ApplicationException, DatabaseException {
		
		return list(0, 0);
	}
	
	public List list(int pageNo,int pageSize)throws ApplicationException,DatabaseException{
		return super.list(pageNo, pageSize);
	}
	
	public UserDTO findByPK(long pk)throws ApplicationException, DatabaseException{
		UserDTO dto=super.findByPK(pk);
		return dto;
	}
				
	@Override
	public boolean changePassword(long id, String newPassword, String oldPassword)
			throws ApplicationException, RecordNotFoundException {
		
		boolean flag=false;
		UserDTO dtoExist=null;
		
		dtoExist=findByPK(id);
		
		if(dtoExist!=null && dtoExist.getPassword().equals(oldPassword)) {
			dtoExist.setPassword(newPassword);
			dtoExist.setConfirmPassword(newPassword);
			try {
				update(dtoExist);
			} catch (Exception e) {
				e.printStackTrace();
			}
			flag=true;
		}else {
			throw new RecordNotFoundException("Login Id Does Not Exist");
		}
		
		//Code for sending email
		
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", dtoExist.getLogin());
		map.put("password", dtoExist.getPassword());
		map.put("firstName", dtoExist.getFirstName());
		map.put("lastName", dtoExist.getLastName());

		String message = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dtoExist.getLogin());
		msg.setSubject("Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return flag;
	}

	@Override
	public UserDTO authenticate(String login, String password)
			throws ApplicationException, RecordNotFoundException, DatabaseException {
		
		UserDTO dto=null;
		try {
			Session session=null;
			session=HibDataSource.getSession();
			Query q=session.createQuery("from UserDTO where login=? and password=?");
			q.setString(0,login);
			q.setString(1, password);
			List list=q.list();
			
			if(list.size()>0) {
				dto=(UserDTO)list.get(0);
			}else {
				dto=null;
				throw new RecordNotFoundException("User Not Found");
			}
		} catch (HibernateException e) {
			BaseModel.handleException(e);
		}
		return dto;
	}

	@Override
	public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException {
		UserDTO userData=findByLogin(login);
		boolean flag=false;
		
		if(userData==null) {
			throw new RecordNotFoundException("Login Id Not Found");
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", login);
		map.put("password", userData.getPassword());
		map.put("firstName", userData.getFirstName());
		map.put("lastName", userData.getLastName());
		String message = EmailBuilder.getForgetPasswordMessage(map);
		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("SUNARYS ORS Password reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
		System.out.println(flag);
		flag = true;
		
		return flag;
	}

	@Override
	public boolean resetPassword(UserDTO dto) throws ApplicationException, RecordNotFoundException, DatabaseException {
		String newPassword = String.valueOf(new Date().getTime()).substring(0, 4);
		UserDTO userData=findByPK(dto.getId());
		userData.setPassword(newPassword);
		
		try {
			update(userData);
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
			return false;
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());

		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dto.getLogin());
		msg.setSubject("Password has been reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		
		return true;
	}

	@Override
	public long registerUser(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		
		long pk=add(dto);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dto.getLogin());
		msg.setSubject("Registration is successful for ORS Project SUNRAYS Technologies");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		
		return pk;
	}

	@Override
	public List getRoles(UserDTO dto) throws ApplicationException {
		return null;
	}

	@Override
	public UserDTO findByLogin(String login) throws ApplicationException {
		
		UserDTO dto=super.findByUniqueKey("login", login);
		return dto;
	}

	


}
