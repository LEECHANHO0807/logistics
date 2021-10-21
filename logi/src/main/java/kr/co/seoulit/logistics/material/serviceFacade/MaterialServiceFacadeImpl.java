package kr.co.seoulit.logistics.material.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.logistics.material.applicationService.BomApplicationService;
import kr.co.seoulit.logistics.material.applicationService.OrderApplicationService;
import kr.co.seoulit.logistics.material.applicationService.StockApplicationService;
import kr.co.seoulit.logistics.material.to.BomDeployTO;
import kr.co.seoulit.logistics.material.to.BomInfoTO;
import kr.co.seoulit.logistics.material.to.BomTO;
import kr.co.seoulit.logistics.material.to.OrderInfoTO;
import kr.co.seoulit.logistics.material.to.StockLogTO;
import kr.co.seoulit.logistics.material.to.StockTO;

@Service
public class MaterialServiceFacadeImpl implements MaterialServiceFacade {

	// 참조변수 선언
	@Autowired
	private BomApplicationService bomAS;
	@Autowired
	private OrderApplicationService orderAS;
	@Autowired
	private StockApplicationService stockAS;


	@Override
	public ArrayList<BomDeployTO> getBomDeployList(String deployCondition, String itemCode,
			String itemClassificationCondition) {
		ArrayList<BomDeployTO> bomDeployList = null;
			bomDeployList = bomAS.getBomDeployList(deployCondition, itemCode, itemClassificationCondition);
		
		return bomDeployList;
	}

	@Override
	public ArrayList<BomInfoTO> getBomInfoList(String parentItemCode) {
		ArrayList<BomInfoTO> bomInfoList = null;
			bomInfoList = bomAS.getBomInfoList(parentItemCode);

		return bomInfoList;
	}

	@Override
	public HashMap<String,Object> getOrderList(String startDate, String endDate) {
        HashMap<String,Object> resultMap = null;
			resultMap = orderAS.getOrderList(startDate, endDate);

		return resultMap;
	}

	@Override
	public ArrayList<BomInfoTO> getAllItemWithBomRegisterAvailable() {
		ArrayList<BomInfoTO> allItemWithBomRegisterAvailable = null;
			allItemWithBomRegisterAvailable = bomAS.getAllItemWithBomRegisterAvailable();

		return allItemWithBomRegisterAvailable;
	}

	@Override
	public HashMap<String, Object> batchBomListProcess(ArrayList<BomTO> batchBomList) {
		HashMap<String, Object> resultMap = null;
			resultMap = bomAS.batchBomListProcess(batchBomList);


		return resultMap;

	}

	@Override
	public HashMap<String,Object> getOrderDialogInfo(ArrayList<String> mrpNoArr) {

        HashMap<String,Object> resultMap = null;
			resultMap = orderAS.getOrderDialogInfo(mrpNoArr);
		
		return resultMap;

	}

	@Override
	public HashMap<String,Object> order(ArrayList<String> mrpGaNoArr) {
        HashMap<String,Object> resultMap = null;
			resultMap = orderAS.order(mrpGaNoArr);

    	return resultMap;
		
	}

	@Override
	public HashMap<String,Object> optionOrder(String itemCode, String itemAmount) {
        HashMap<String,Object> resultMap = null;
			resultMap = orderAS.optionOrder(itemCode, itemAmount);
		
    	return resultMap;
		
	}

	@Override
	public ArrayList<StockTO> getStockList() {
		ArrayList<StockTO> stockList = null;
			stockList = stockAS.getStockList();

		return stockList;
	}

	@Override
	public ArrayList<StockLogTO> getStockLogList(String startDate, String endDate) {
		ArrayList<StockLogTO> stockLogList = null;
			stockLogList = stockAS.getStockLogList(startDate, endDate);

		return stockLogList;
	}

	@Override
	public ArrayList<OrderInfoTO> getOrderInfoListOnDelivery() {
		ArrayList<OrderInfoTO> orderInfoListOnDelivery = null;
			orderInfoListOnDelivery = orderAS.getOrderInfoListOnDelivery();

		return orderInfoListOnDelivery;

	}

	@Override
	public HashMap<String,Object> warehousing(ArrayList<String> orderNoArr) {
        HashMap<String,Object> resultMap = null;
			resultMap = stockAS.warehousing(orderNoArr);

    	return resultMap;
	}

	@Override
	public ArrayList<OrderInfoTO> getOrderInfoList(String startDate, String endDate) {
		ArrayList<OrderInfoTO> orderInfoList  = null;
			orderInfoList = orderAS.getOrderInfoList(startDate,endDate);

		return orderInfoList;

	}
	/*
	 * @Override public HashMap<String, Object> changeSafetyAllowanceAmount(String
	 * itemCode, String itemName, String safetyAllowanceAmount) { HashMap<String,
	 * Object> resultMap = null;
	 * 
	 * 
	 * try {
	 * 
	 * resultMap = stockAS.changeSafetyAllowanceAmount(itemCode, itemName,
	 * safetyAllowanceAmount);
	 * 
	 * 
	 * } catch (DataAccessException e) { e.printStackTrace(); throw e; } return
	 * resultMap; }
	 */
}
