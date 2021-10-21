package kr.co.seoulit.logistics.sales.to;

import java.util.ArrayList;

import kr.co.seoulit.system.base.to.BaseTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContractInfoTO extends BaseTO {

	private String contractNo;
	private String estimateNo;
	private String contractType;
	private String contractTypeName;
	private String customerCode;
	private String customerName;
	private String estimateDate;
	private String contractDate;
	private String contractRequester;
	private String personCodeInCharge;
	private String empNameInCharge;
	private String description;
	private ArrayList<ContractDetailTO> contractDetailTOList;
	private String deliveryCompletionStatus;

}
