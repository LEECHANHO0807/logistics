package kr.co.seoulit.logistics.production.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WorkSiteLog {
	private String workOrderNo;
	private String itemCode;
	private String itemName;
	private String reaeson;
	private String workStieName;
	private String workDate;
	private String productionProcessCode;
	private String productionProcessName;
	
}
