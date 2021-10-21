package kr.co.seoulit.logistics.material.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logistics.material.mapper.OrderDAO;
import kr.co.seoulit.logistics.material.to.OrderInfoTO;

@Component
public class OrderApplicationServiceImpl implements OrderApplicationService {

	// DAO 참조변수 선언
	@Autowired
	private OrderDAO orderDAO;

	@Override
	public HashMap<String,Object> getOrderList(String startDate, String endDate) {
		HashMap<String, String> param = new HashMap<>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);

		orderDAO.getOrderList(param);

		HashMap<String, Object> resultMap = new HashMap<>();

		resultMap.put("gridRowJson", param.get("RESULT"));
		resultMap.put("errorCode", param.get("ERROR_CODE"));
		resultMap.put("errorMsg", param.get("ERROR_MSG"));

		return resultMap;
	}

	@Override
	public HashMap<String,Object> getOrderDialogInfo(ArrayList<String> mrpNoArr) {
		String mrpNoList = mrpNoArr.toString().replace("[", "").replace("]", "");

		HashMap<String, String> param = new HashMap<>();
		param.put("mrpNoList", mrpNoList);

		orderDAO.getOrderDialogInfo(param);

		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("gridRowJson", param.get("RESULT"));
		resultMap.put("errorCode", param.get("ERROR_CODE"));
		resultMap.put("errorMsg", param.get("ERROR_MSG"));

		return resultMap;
	}

	@Override
	public HashMap<String,Object> order(ArrayList<String> mrpGaNoArr) {

		String mpsNoList = mrpGaNoArr.toString().replace("[", "").replace("]", "");

		HashMap<String, String> param = new HashMap<>();
		param.put("mpsNoList", mpsNoList);

		HashMap<String, Object> resultMap = orderDAO.order(param);
		
		resultMap.put("errorCode", param.get("ERROR_CODE"));
		resultMap.put("errorMsg", param.get("ERROR_MSG"));

		return resultMap;
		
	}

	@Override
	public HashMap<String,Object> optionOrder(String itemCode, String itemAmount) {

		HashMap<String, String> param = new HashMap<>();
		param.put("itemCode", itemCode);
		param.put("itemAmount", itemAmount);

		orderDAO.optionOrder(param);

		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("errorCode", param.get("ERROR_CODE"));
		resultMap.put("errorMsg", param.get("ERROR_MSG"));

		return resultMap;
	}

	@Override
	public ArrayList<OrderInfoTO> getOrderInfoListOnDelivery() {
		ArrayList<OrderInfoTO> orderInfoListOnDelivery = null;

			orderInfoListOnDelivery = orderDAO.getOrderInfoListOnDelivery();

		return orderInfoListOnDelivery;
	}

	@Override
	public ArrayList<OrderInfoTO> getOrderInfoList(String startDate, String endDate) {
		HashMap<String, String> param = new HashMap<>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);

		return orderDAO.getOrderInfoList(param);
	}
}
