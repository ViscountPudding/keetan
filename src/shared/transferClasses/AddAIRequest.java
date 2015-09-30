package shared.transferClasses;

import java.io.Serializable;

public class AddAIRequest implements Serializable {
	
	protected String AIType;
	
	public AddAIRequest(String type){
		AIType = type;
	}

	public String getAIType() {
		return AIType;
	}

	public void setAIType(String aIType) {
		AIType = aIType;
	}

}
