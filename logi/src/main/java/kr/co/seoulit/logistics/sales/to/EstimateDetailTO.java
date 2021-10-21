package kr.co.seoulit.logistics.sales.to;

import kr.co.seoulit.system.base.to.BaseTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class EstimateDetailTO extends BaseTO {
	private String unitOfEstimate;
	private String estimateNo;
	private int unitPriceOfEstimate;
	private String estimateDetailNo;
	private int sumPriceOfEstimate;
	private String description;
	private String itemCode;
	private int estimateAmount;
	private String dueDateOfEstimate;
	private String itemName;
	public String getUnitOfEstimate() {
		return unitOfEstimate;
	}

}