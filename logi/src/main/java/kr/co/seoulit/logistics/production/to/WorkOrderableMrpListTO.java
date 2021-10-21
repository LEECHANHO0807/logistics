package kr.co.seoulit.logistics.production.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WorkOrderableMrpListTO {

	private String mrpGatheringNo;	
	private String itemClassification;	
	private String itemCode;
	private String itemName;	
	private String unitOfMrp;	
	private int requiredAmount;	
	private String orderDate;
	private String requiredDate;
	
}
