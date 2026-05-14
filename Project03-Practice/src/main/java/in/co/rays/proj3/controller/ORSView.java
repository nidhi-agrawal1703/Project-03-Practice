package in.co.rays.proj3.controller;


/**
 * ORSView contains path to all the controllers and their views.
 * ORSView is responsible for removing hard-coding and loose-coupling.
 * 
 * @author Nidhi
 * @version 1.0	
 */
public interface ORSView {

	public String APP_CONTEXT = "/Project03-Practice";

	public String PAGE_FOLDER = "/jsp";
	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";

	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";

	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";

	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";

	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";

	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";

	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";
	
	public String CONTACT_VIEW = PAGE_FOLDER + "/ContactView.jsp";
	public String CONTACT_CTL = APP_CONTEXT + "/ctl/ContactCtl";

	public String CONTACT_LIST_VIEW = PAGE_FOLDER + "/ContactListView.jsp";
	public String CONTACT_LIST_CTL = APP_CONTEXT + "/ctl/ContactListCtl";
	
	public String SETTINGS_VIEW = PAGE_FOLDER + "/SettingsView.jsp";
	public String SETTINGS_CTL = APP_CONTEXT + "/ctl/SettingsCtl";

	public String SETTINGS_LIST_VIEW = PAGE_FOLDER + "/SettingsListView.jsp";
	public String SETTINGS_LIST_CTL = APP_CONTEXT + "/ctl/SettingsListCtl";
	
	public String ALLOW_VIEW = PAGE_FOLDER + "/AllowView.jsp";
	public String ALLOW_CTL = APP_CONTEXT + "/ctl/AllowCtl";

	public String ALLOW_LIST_VIEW = PAGE_FOLDER + "/AllowListView.jsp";
	public String ALLOW_LIST_CTL = APP_CONTEXT + "/ctl/AllowListCtl";
	
	public String DOCUMENT_VIEW = PAGE_FOLDER + "/DocumentView.jsp";
	public String DOCUMENT_CTL = APP_CONTEXT + "/ctl/DocumentCtl";

	public String DOCUMENT_LIST_VIEW = PAGE_FOLDER + "/DocumentListView.jsp";
	public String DOCUMENT_LIST_CTL = APP_CONTEXT + "/ctl/DocumentListCtl";

	public String UASGE_VIEW = PAGE_FOLDER + "/UsageView.jsp";
	public String USAGE_CTL = APP_CONTEXT + "/ctl/UsageCtl";

	public String USAGE_LIST_VIEW = PAGE_FOLDER + "/UsageListView.jsp";
	public String USAGE_LIST_CTL = APP_CONTEXT + "/ctl/UsageListCtl";

	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";

	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";
	
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";

	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";
	
	public String REPORT_VIEW = PAGE_FOLDER + "/ReportView.jsp";
	public String REPORT_CTL = APP_CONTEXT + "/ctl/ReportCtl";

	public String REPORT_LIST_VIEW = PAGE_FOLDER + "/ReportListView.jsp";
	public String REPORT_LIST_CTL = APP_CONTEXT + "/ctl/ReportListCtl";

	public String LANGUAGETRANSLATION_VIEW = PAGE_FOLDER + "/LanguageTranslationView.jsp";
	public String LANGUAGETRANSLATION_CTL = APP_CONTEXT + "/ctl/LanguageTranslationCtl";

	public String LANGUAGETRANSLATION_LIST_VIEW = PAGE_FOLDER + "/LanguageTranslationListView.jsp";
	public String LANGUAGETRANSLATION_LIST_CTL = APP_CONTEXT + "/ctl/LanguageTranslationListCtl";	

	public String ACCOUNT_VIEW = PAGE_FOLDER + "/AccountView.jsp";
	public String ACCOUNT_CTL = APP_CONTEXT + "/ctl/AccountCtl";

	public String ACCOUNT_LIST_VIEW = PAGE_FOLDER + "/AccountListView.jsp";
	public String ACCOUNT_LIST_CTL = APP_CONTEXT + "/ctl/AccountListCtl";
	
	public String NFTASSET_VIEW = PAGE_FOLDER + "/NFTAssetView.jsp";
	public String NFTASSET_CTL = APP_CONTEXT + "/ctl/NFTAssetCtl";

	public String NFTASSET_LIST_VIEW = PAGE_FOLDER + "/NFTAssetListView.jsp";
	public String NFTASSET_LIST_CTL = APP_CONTEXT + "/ctl/NFTAssetListCtl";
	
	public String ERROR_VIEW = PAGE_FOLDER + "/ErrorView.jsp";
	public String ERROR_CTL = APP_CONTEXT + "/ErrorCtl";
	
	}