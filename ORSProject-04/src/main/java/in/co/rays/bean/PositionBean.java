   package in.co.rays.bean;

import java.util.Date;

public class PositionBean extends BaseBean {

	private long identifier;
	private String designation;
	private Date openingDate;
	private String requiredExperince;
	private String condition;

	public long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(long identifier) {
		this.identifier = identifier;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public String getRequiredExperince() {
		return requiredExperince;
	}

	public void setRequiredExperince(String requiredExperince) {
		this.requiredExperince = requiredExperince;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return identifier + "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return designation;
	}

}
