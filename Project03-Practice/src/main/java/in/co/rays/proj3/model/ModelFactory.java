package in.co.rays.proj3.model;

import java.util.HashMap;
import java.util.ResourceBundle;

import in.co.rays.proj3.dto.UserDTO;

public class ModelFactory {
	
	
	private static ResourceBundle rb=ResourceBundle.getBundle("in.co.rays.proj3.bundle.system");
	private static final String DATABASE=rb.getString("DATABASE");
	private static ModelFactory mFactory=null;
	private static HashMap modelCache=new HashMap();
	
	private ModelFactory() {}
	
	public static ModelFactory getInstance() {
		if(mFactory==null) {
			mFactory=new ModelFactory();
		}
		return mFactory;
	}
	
	public UserModelInt getUserModel() {
		UserModelInt userModel=(UserModelInt)modelCache.get("userModel");
		if(userModel==null) {
			if("Hibernate".equals(DATABASE)) {
				userModel=new UserModelHibImpl();
			}
			modelCache.put("userModel", userModel);
		}

		return userModel;
	}
	
	public RoleModelInt getRoleModel() {
		RoleModelInt roleModel=(RoleModelInt)modelCache.get("roleModel");
		if(roleModel==null) {
			if("Hibernate".equals(DATABASE)) {
				roleModel=new RoleModelHibImpl();
			}
			modelCache.put("roleModel", roleModel);
		}

		return roleModel;
	}
	
	public ContactModelInt getContactModel() {
		ContactModelInt contactModel=(ContactModelInt)modelCache.get("contactModel");
		if(contactModel==null) {
			if("Hibernate".equals(DATABASE)) {
				contactModel=new ContactModelHibImpl();
			}
			modelCache.put("contactModel", contactModel);
		}

		return contactModel;
	}
	
	public SettingsModelInt getSettingsModel() {
		SettingsModelInt settingsModel=(SettingsModelInt)modelCache.get("settingsModel");
		if(settingsModel==null) {
			if("Hibernate".equals(DATABASE)) {
				settingsModel=new SettingsModelHibImpl();
			}
			modelCache.put("settingsModel", settingsModel);
		}

		return settingsModel;
	}
	
	public AllowModelInt getAllowModel() {
		AllowModelInt allowModel=(AllowModelInt)modelCache.get("allowModel");
		if(allowModel==null) {
			if("Hibernate".equals(DATABASE)) {
				allowModel=new AllowModelHibImpl();
			}
			modelCache.put("allowModel", allowModel);
		}

		return allowModel;
	}
	
	public DocumentModelInt getDocumentModel() {
		DocumentModelInt documentModel=(DocumentModelInt)modelCache.get("documentModel");
		if(documentModel==null) {
			if("Hibernate".equals(DATABASE)) {
				documentModel=new DocumentModelHibImpl();
			}
			modelCache.put("documentModel", documentModel);
		}

		return documentModel;
	}
	
	public CollegeModelInt getCollegeModel() {
		CollegeModelInt collegeModel=(CollegeModelInt)modelCache.get("collegeModel");
		if(collegeModel==null) {
			if("Hibernate".equals(DATABASE)) {
				collegeModel=new CollegeModelHibImpl();
			}
			modelCache.put("collegeModel", collegeModel);
		}

		return collegeModel;
	}
	
	public StudentModelInt getStudentModel() {
		StudentModelInt studentModel=(StudentModelInt)modelCache.get("studentModel");
		if(studentModel==null) {
			if("Hibernate".equals(DATABASE)) {
				studentModel=new StudentModelHibImpl();
			}
			modelCache.put("studentModel", studentModel);
		}

		return studentModel;
	}
	
	public ReportModelInt getReportModel() {
		ReportModelInt reportModel=(ReportModelInt)modelCache.get("reportModel");
		if(reportModel==null) {
			if("Hibernate".equals(DATABASE)) {
				reportModel=new ReportModelHibImpl();
			}
			modelCache.put("reportModel", reportModel);
		}

		return reportModel;
	}
	
	public LanguageTranslationModelInt getLanguageTranslationModel() {
		LanguageTranslationModelInt languageTranslationModel=(LanguageTranslationModelInt)modelCache.get("languageTranslationModel");
		if(languageTranslationModel==null) {
			if("Hibernate".equals(DATABASE)) {
				languageTranslationModel=new LanguageTranslationModelHibImpl();
			}
			modelCache.put("languageTranslationModel", languageTranslationModel);
		}

		return languageTranslationModel;
	}
	
	
	public UsageModelInt getUsageModel() {
		UsageModelInt usageModel=(UsageModelInt)modelCache.get("usageModel");
		if(usageModel==null) {
			if("Hibernate".equals(DATABASE)) {
				usageModel=new UsageModelHibImpl();
			}
			modelCache.put("usageModel",usageModel);
		}

		return usageModel;
	}
}
