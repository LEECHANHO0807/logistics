package kr.co.seoulit.logistics.material.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.logistics.material.to.OrderInfoTO;

@Mapper
public interface OrderDAO {
	
	 public HashMap<String,Object> getOrderList(HashMap<String, String> param);
	 
	 public HashMap<String,Object> getOrderDialogInfo(HashMap<String, String> param);
	 
	 public ArrayList<OrderInfoTO> getOrderInfoListOnDelivery();
	 
	 public ArrayList<OrderInfoTO> getOrderInfoList(HashMap<String, String> param);

	 public HashMap<String,Object> order(HashMap<String, String> param);	 
	 
	 public HashMap<String,Object> optionOrder(HashMap<String, String> param);
}
