package kr.co.seoulit.logistics.material.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logistics.material.mapper.StockDAO;
import kr.co.seoulit.logistics.material.to.StockLogTO;
import kr.co.seoulit.logistics.material.to.StockTO;

@Component
public class StockApplicationServiceImpl implements StockApplicationService{
	// DAO 참조변수 선언
		@Autowired
		private StockDAO stockDAO;

		@Override
		public ArrayList<StockTO> getStockList() {
			ArrayList<StockTO> stockList = null;

				stockList = stockDAO.selectStockList();

			return stockList;
		}
	
		@Override
		public ArrayList<StockLogTO> getStockLogList(String startDate,String endDate) {

			HashMap<String, String> param = new HashMap<>();
			param.put("startDate", startDate);
			param.put("endDate", endDate);

			return stockDAO.selectStockLogList(param);
		}

		@Override
		public HashMap<String,Object> warehousing(ArrayList<String> orderNoArr) {

			String orderNoList = orderNoArr.toString().replace("[", "").replace("]", "");

			HashMap<String, String> param = new HashMap<>();
			param.put("orderNoList", orderNoList);

			stockDAO.warehousing(param);

			HashMap<String, Object> resultMap = new HashMap<>();
			resultMap.put("errorCode", param.get("ERROR_CODE"));
			resultMap.put("errorMsg", param.get("ERROR_MSG"));

			return resultMap;
		}
		/*
		 * @Override public HashMap<String, Object> changeSafetyAllowanceAmount(String
		 * itemCode, String itemName, String safetyAllowanceAmount) {
		 * 
		 * HashMap<String, Object> resultMap = null;
		 * 
		 * try {
		 * 
		 * resultMap =
		 * stockDAO.updatesafetyAllowance(itemCode,itemName,safetyAllowanceAmount);
		 * 
		 * } catch (DataAccessException e) { throw e; } return resultMap; }
		 */
		
}
