package in.co.rays.bean;

import java.util.Date;

public class StaffMemberBean extends BaseBean {

	private Long identifier;
	private String fullName;
	private Date joiningDate;
	private String division;
	private String previousEmployer;

	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getPreviousEmployer() {
		return previousEmployer;
	}

	public void setPreviousEmployer(String previousEmployer) {
		this.previousEmployer = previousEmployer;
	}

	@Override
	public String getKey() {
		return identifier + "";
	}

	@Override
	public String getValue() {
		return fullName;
	}

}
