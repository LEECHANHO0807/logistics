package kr.co.seoulit.logistics.material.controller;

//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

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

import kr.co.seoulit.logistics.material.serviceFacade.MaterialServiceFacade;
import kr.co.seoulit.logistics.material.to.StockLogTO;
import kr.co.seoulit.logistics.material.to.StockTO;

@RestController
@RequestMapping("/material/*")
public class StockController{

	// serviceFacade 참조변수 선언
	@Autowired
	private MaterialServiceFacade materialSF;

	private ModelMap modelMap = new ModelMap();

	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
	@RequestMapping(value="/searchStockList.do" , method=RequestMethod.POST)
	public ModelMap searchStockList(HttpServletRequest request, HttpServletResponse response) {
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<StockTO> stockList = materialSF.getStockList();

			 modelMap.put("gridRowJson", stockList);
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
	@RequestMapping(value="/searchStockLogList.do" , method=RequestMethod.POST)
	public ModelMap searchStockLogList(HttpServletRequest request, HttpServletResponse response) {
		//PrintWriter out = null;
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		try {
			//out = response.getWriter();
			ArrayList<StockLogTO> stockLogList = materialSF.getStockLogList(startDate,endDate);

			modelMap.put("gridRowJson", stockLogList);
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
	@RequestMapping(value="/warehousing.do", method=RequestMethod.POST)
	public HashMap<String, Object> warehousing(HttpServletRequest request, HttpServletResponse response) {
		String orderNoListStr = request.getParameter("orderNoList");
		ArrayList<String> orderNoArr = gson.fromJson(orderNoListStr,new TypeToken<ArrayList<String>>(){}.getType());	
		HashMap<String, Object> resultMap = new HashMap<>();
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			resultMap = materialSF.warehousing(orderNoArr);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		} /*
			 * finally { out.println(gson.toJson(resultMap)); out.close(); }
			 */
		return resultMap;
	}
	
	/*
	 * public ModelMap safetyAllowanceAmountChange(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * String itemCode = request.getParameter("itemCode"); String itemName =
	 * request.getParameter("itemName"); String safetyAllowanceAmount =
	 * request.getParameter("safetyAllowanceAmount");
	 * 
	 * System.out.println("itemCode:"+itemCode+"itemName:"+itemName+
	 * "safetyAllowanceAmount:"+safetyAllowanceAmount);
	 * 
	 * HashMap<String, Object> resultMap = new HashMap<>();
	 * 
	 * PrintWriter out = null;
	 * 
	 * try { out = response.getWriter();
	 * 
	 * resultMap = materialSF.changeSafetyAllowanceAmount(itemCode,itemName,
	 * safetyAllowanceAmount);
	 * 
	 * } catch (IOException e1) { e1.printStackTrace(); resultMap.put("errorCode",
	 * -1); resultMap.put("errorMsg", e1.getMessage());
	 * 
	 * } catch (DataAccessException e2) { e2.printStackTrace();
	 * resultMap.put("errorCode", -2); resultMap.put("errorMsg", e2.getMessage());
	 * 
	 * } finally {
	 * 
	 * out.println(gson.toJson(resultMap)); out.close(); } return null; }
	 */
}
