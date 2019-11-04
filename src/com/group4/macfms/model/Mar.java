package com.group4.macfms.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.group4.macfms.data.MarDAO;

public class Mar {
	private String marNumber;
	private String facilityType;
	private String reservationId;
	private String reportedBy;
	private String urgency;
	private String description;
	private String dateCreated;
	private String assignedTo;
	private String assignedDate;
	private String estimatedTime;
	private String marStatus;
	
	// These variables are for Rule Check Validations
	private boolean repairerAvailable;
	private int marCountsPerDay;
	private int marCountPerWeek;
	private int marStatusCode;
	
	

	public void setMar(String marNumber, String facilityType, String reservationId, String reportedBy,
			String urgency, String description, String dateCreated, String assignedTo, String assignedDate,
			String estimatedTime, String marStatus) {
		setMarNumber(marNumber);
		setFacilityType(facilityType);
		setReservationId(reservationId);
		setReportedBy(reportedBy);
		setUrgency(urgency);
		setDescription(description);
		setDateCreated(dateCreated);
		setAssignedTo(assignedTo);
		setAssignedDate(assignedDate);
		setEstimatedTime(estimatedTime);
		setMarStatus(marStatus);		
	}
	
	public String getMarNumber() {
		return marNumber;
	}
	public void setMarNumber(String marNumber) {
		this.marNumber = marNumber;
	}
	public String getFacilityType() {
		return facilityType;
	}
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}
	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	public String getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}
	public String getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public String getMarStatus() {
		return marStatus;
	}
	public void setMarStatus(String marStatus) {
		this.marStatus = marStatus;
	}
	
	
	public boolean isRepairerAvailable() {
		return repairerAvailable;
	}

	public void setRepairerAvailable(boolean repairerAvailable) {
		this.repairerAvailable = repairerAvailable;
	}

	public int getMarCountsPerDay() {
		return marCountsPerDay;
	}

	public void setMarCountsPerDay(int marCountsPerDay) {
		this.marCountsPerDay = marCountsPerDay;
	}

	public int getMarCountPerWeek() {
		return marCountPerWeek;
	}

	public void setMarCountPerWeek(int marCountPerWeek) {
		this.marCountPerWeek = marCountPerWeek;
	}

	
	
	public int getMarStatusCode() {
		return marStatusCode;
	}

	public void setMarStatusCode(int marStatusCode) {
		this.marStatusCode = marStatusCode;
	}

	public void validateDescription(Mar mar, MarErrorMsgs errorMsg) {
		errorMsg.setDescriptionError(validateDescription(mar.getDescription()));
		
	}
	
	public  String validateDescription(String description) {
        String result;
        if(description.isEmpty()) {
                result =  "Please provide us the description of the problem";
//            return "Please provide us the description of the problem";
        }
       
        else if(description.length()>50) {
                result =  "Description length should not exceed 50 characters";
        }
        else {
        	result = "";
        }
       
        return result;
    }
	
	
	public void validateAssignedTo(Mar mar, MarErrorMsgs errorMsg) {
		errorMsg.setAssignedToError(validateAssignedTo(mar.getAssignedTo()));
		
	}
	
	public  String validateAssignedTo(String assignedTo) {
		String result;
		if(assignedTo.equals(""))
			result =  "Please provide the repairer name to assign the MAR";
		else 
			result = "";
		return result;
	}
	
	
	
	public void validateAssignRuleCheck(Mar mar, MarErrorMsgs errorMsg) {
		errorMsg.setAssignRuleCheckError(validateAssignRuleCheck(mar));
		
	}
	
	public  String validateAssignRuleCheck(Mar mar) {
		String result = "";
		
		// checks if repairer exists in DB and is available today based on his his schedule
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        String day = simpleDateformat.format(now);
        
		boolean userAvailable = MarDAO.checkUniqueUsername(mar.getAssignedTo(), day);
		mar.setRepairerAvailable(userAvailable);
		
		
		
		if (mar.isRepairerAvailable() && mar.getMarCountsPerDay() < 5  && mar.getMarCountPerWeek() < 10) {
			mar.setMarStatus("Assigned");
			result = "Mar has been assigned to the repairer "+mar.getAssignedTo();
		} else if (!mar.isRepairerAvailable()) {
			mar.setMarStatus("Unassigned");
			result = "No such user found";
		} else if (mar.getMarCountsPerDay() >= 5  && mar.getMarCountPerWeek() < 10) {
			mar.setMarStatus("Unassigned");
			result = "The repairer has exceeded 5 counts per day";
		} else {
			mar.setMarStatus("Unassigned");
			result = "The repairer has exceeded 10 counts per week";
		} 
		
		
		return result;
	}
	
	
	public void validateAssignedToStatus(Mar mar, MarErrorMsgs errorMsg, int status) {
		errorMsg.setAssignMarError(validateAssignedToStatus(mar,status));
		
	}
	
	public  String validateAssignedToStatus(Mar mar,int assignedToStatus) {
		String result = "";
		if(assignedToStatus == 0) {
			mar.setMarStatus("Unassigned");
			result =  "Please enter a valid repairer";
		}
		else if(assignedToStatus == 1) {
			mar.setMarStatus("Assigned");
			result =  "MAR Assigned successfully";
		}
//		if(assignedToStatus == 2)
		else  {
			mar.setMarStatus("Unassigned");
			result = "MAR cannot be assigned as it exceeds its Daily Weekly Count";
		} 
		
		return result;
	}
	
	
	
}
