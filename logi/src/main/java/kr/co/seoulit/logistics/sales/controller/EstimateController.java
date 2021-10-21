package kr.co.seoulit.logistics.sales.controller;

import java.io.PrintWriter;
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

import kr.co.seoulit.logistics.sales.serviceFacade.SalesServiceFacade;
import kr.co.seoulit.logistics.sales.to.EstimateDetailTO;
import kr.co.seoulit.logistics.sales.to.EstimateTO;

@RestController
@RequestMapping("/sales/*")
public class EstimateController{
	@Autowired
	private SalesServiceFacade salesSF;
	
	private ModelMap modelMap = new ModelMap();
	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
	@RequestMapping(value="/searchEstimate.do", method=RequestMethod.POST)
	public ModelMap searchEstimateInfo(HttpServletRequest request, HttpServletResponse response) {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String dateSearchCondition = request.getParameter("dateSearchCondition");
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<EstimateTO> estimateTOList = salesSF.getEstimateList(dateSearchCondition, startDate, endDate);

			modelMap.put("gridRowJson", estimateTOList);
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
	@RequestMapping(value= "/searchEstimateDetailInfo.do", method=RequestMethod.POST)
	public ModelMap searchEstimateDetailInfo(HttpServletRequest request, HttpServletResponse response) {
		String estimateNo = request.getParameter("estimateNo");
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<EstimateDetailTO> estimateDetailTOList = salesSF.getEstimateDetailList(estimateNo);

			modelMap.put("gridRowJson", estimateDetailTOList);
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
	@RequestMapping(value="/addNewEstimate.do", method=RequestMethod.POST)
	public ModelMap addNewEstimate(HttpServletRequest request, HttpServletResponse response) {
		String estimateDate = request.getParameter("estimateDate");
		System.out.println("            estimateDate : " + estimateDate); //견적일자
		String newEstimateInfo = request.getParameter("newEstimateInfo"); //견적과 견적상세의 데이터값의 배열
		System.out.println("            newEstimateInfo : " + newEstimateInfo);
		EstimateTO newEstimateTO = gson.fromJson(newEstimateInfo, EstimateTO.class);
		// newEstimateInfo 는 뷰단에서 보낸 json 문자열 => 문자열을 자바 객체로 변환
		PrintWriter out = null;
		try {
			out = response.getWriter();
			HashMap<String, Object> resultList = salesSF.addNewEstimate(estimateDate, newEstimateTO);
			//견적일자와 견적,견적상세의 json객체를 EstimateTO로 변환한 newEstimateTO를 map에 담음
			modelMap.put("result", resultList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} 
			 finally { out.println(gson.toJson(modelMap));// 자바 객체를 json 형식의 문자열로 바꿈
			  out.close(); }
			 
		return null;
	}
	@RequestMapping(value="/batchEstimateDetailListProcess.do", method= RequestMethod.POST)
	public ModelMap batchListProcess(HttpServletRequest request, HttpServletResponse response) {
		String batchList = request.getParameter("batchList");
		ArrayList<EstimateDetailTO> estimateDetailTOList = gson.fromJson(batchList,
				new TypeToken<ArrayList<EstimateDetailTO>>() {
				}.getType());
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			HashMap<String, Object> resultList = salesSF.batchEstimateDetailListProcess(estimateDetailTOList);

			modelMap.put("result", resultList);
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

}
