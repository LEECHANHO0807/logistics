package kr.co.seoulit.logistics.logisticsInfo.to;

import kr.co.seoulit.system.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WarehouseTO extends BaseTO {
	private String warehouseCode;
	private String warehouseName;
	private String warehouseUseOrNot;
	private String description;

}