package kr.co.seoulit.logistics.sales.controller;

//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import kr.co.seoulit.logistics.sales.serviceFacade.SalesServiceFacade;
import kr.co.seoulit.logistics.sales.to.ContractInfoTO;
import kr.co.seoulit.logistics.sales.to.DeliveryInfoTO;

@RestController
@RequestMapping("/sales/*")
public class DeliveryController{
	@Autowired
	private SalesServiceFacade salesSF;
	
	private ModelMap modelMap = new ModelMap();
	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 변환
	@RequestMapping(value="/searchDeliveryInfoList.do", method=RequestMethod.POST)
	public ModelMap searchDeliveryInfoList(HttpServletRequest request, HttpServletResponse response) {
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<DeliveryInfoTO> deliveryInfoList = salesSF.getDeliveryInfoList();

			modelMap.put("gridRowJson", deliveryInfoList);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
			 * finally { out.println(gson.toJson(modelMap)); out.close(); }
			 */
		return modelMap;
	}

	// batchListProcess
	@RequestMapping(value="/batchListProcess.do", method=RequestMethod.POST)
	public ModelMap batchListProcess(HttpServletRequest request, HttpServletResponse response) {
		String batchList = request.getParameter("batchList");
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			List<DeliveryInfoTO> deliveryTOList = gson.fromJson(batchList, new TypeToken<ArrayList<DeliveryInfoTO>>() {
			}.getType());
			System.out.println(deliveryTOList);
			HashMap<String, Object> resultMap = salesSF.batchDeliveryListProcess(deliveryTOList);
			modelMap.put("result", resultMap);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
			 * finally { out.println(gson.toJson(modelMap)); out.close(); }
			 */
		return modelMap;
	}
	
	@RequestMapping(value="/searchDeliverableContractList.do", method=RequestMethod.POST)
	public ModelMap searchDeliverableContractList(HttpServletRequest request, HttpServletResponse response) {
		String searchCondition = request.getParameter("searchCondition");	// 기간 or 거래처
		String startDate = request.getParameter("startDate");				// 시작날
		String endDate = request.getParameter("endDate");					// 종료날
		String customerCode = request.getParameter("customerCode");			
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<ContractInfoTO> deliverableContractList = null;
			if (searchCondition.equals("searchByDate")) {//기간검색
				String[] paramArray = { startDate, endDate };
				deliverableContractList = salesSF.getDeliverableContractList("searchByDate", paramArray);
			} else if (searchCondition.equals("searchByCustomer")) {//거래처검색
				String[] paramArray = { customerCode };
				deliverableContractList = salesSF.getDeliverableContractList("searchByCustomer", paramArray);
			}
			modelMap.put("gridRowJson", deliverableContractList);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
			 * finally { out.println(gson.toJson(modelMap)); out.close(); }
			 */
		return modelMap;
	}
	@RequestMapping(value="/deliver.do", method=RequestMethod.POST)
	public ModelMap deliver(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> resultMap = new HashMap<>();
		//PrintWriter out = null;
		String contractDetailNo = request.getParameter("contractDetailNo");
		try {
			//out = response.getWriter();
			resultMap = salesSF.deliver(contractDetailNo);
			modelMap.put("gridRowJson", resultMap);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "성공");
		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
			 * finally { out.println(gson.toJson(resultMap)); out.close(); }
			 */
		return modelMap;
	}

}
