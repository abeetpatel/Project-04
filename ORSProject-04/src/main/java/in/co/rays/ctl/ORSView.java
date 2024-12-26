package in.co.rays.ctl;

public interface ORSView {

	public String APP_CONTEXT = "/ORSProject-04";

	public String PAGE_FOLDER = "/jsp";

	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";
	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";

	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String USER_CTL = APP_CONTEXT + "/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/UserListCtl";
	public String ROLE_CTL = APP_CONTEXT + "/RoleCtl";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/RoleListCtl";
	public String COLLEGE_CTL = APP_CONTEXT + "/CollegeCtl";
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/CollegeListCtl";
}