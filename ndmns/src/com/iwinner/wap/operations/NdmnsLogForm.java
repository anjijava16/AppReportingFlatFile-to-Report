package com.iwinner.wap.operations;

/**
 * It is NdmnsLogForm class contain form info
 * @author anji
 * @version 1.0
 * @since 10th,oct 2014
 *
 */
public class NdmnsLogForm {
	private String dateOfAccess;
	private String opId;
	private String methodId;
	private String msisdn;
	private String returnMessage;

	public String getDateOfAccess() {
		return dateOfAccess;
	}

	public void setDateOfAccess(String dateOfAccess) {
		this.dateOfAccess = dateOfAccess;
	}

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getMethodId() {
		return methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public NdmnsLogForm() {

	}

	public NdmnsLogForm(String dateOfAccess, String opId, String methodId,
			String msisdn, String returnMessage) {
		super();
		this.dateOfAccess = dateOfAccess;
		this.opId = opId;
		this.methodId = methodId;
		this.msisdn = msisdn;
		this.returnMessage = returnMessage;
	}


}
