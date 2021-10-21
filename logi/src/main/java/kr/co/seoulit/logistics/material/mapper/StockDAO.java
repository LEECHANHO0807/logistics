package kr.co.seoulit.logistics.material.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.logistics.material.to.StockLogTO;
import kr.co.seoulit.logistics.material.to.StockTO;

@Mapper
public interface StockDAO {
	
	public ArrayList<StockTO> selectStockList();
	
	public ArrayList<StockLogTO> selectStockLogList(HashMap<String, String> param);
	
	public HashMap<String,Object> warehousing(HashMap<String, String> param);
	
	/*
	 * public HashMap<String, Object> updatesafetyAllowance(String itemCode, String
	 * itemName, String safetyAllowanceAmount);
	 */
}
