package applications;

import java.util.*;

public class HandleApplications {

	
	public void addSkills(String... names) throws ApplicationException {
		
	}
	public void addPosition(String name, String... skillNames) throws ApplicationException {
		
	}
	public Skill getSkill(String name) {return null;}
	public Position getPosition(String name) {return null;}
	
	public void addApplicant(String name, String capabilities) throws ApplicationException {
		
	}
	public String getCapabilities(String applicantName) throws ApplicationException {
		return null;
	}
	
	public void enterApplication(String applicantName, String positionName) throws ApplicationException {
		
	}
	
	public int setWinner(String applicantName, String positionName) throws ApplicationException {
		return 0;
	}
	
	public SortedMap<String, Long> skill_nApplicants() {
		return null;
	}
	public String maxPosition() {
		return null;
	}
}

