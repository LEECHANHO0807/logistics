package kr.co.seoulit.logistics.production.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import kr.co.seoulit.logistics.production.serviceFacade.ProductionServiceFacade;
import kr.co.seoulit.logistics.production.to.MrpGatheringTO;
import kr.co.seoulit.logistics.production.to.MrpTO;

@RestController
@RequestMapping("/production/*")
public class MrpController{
	@Autowired
	private ProductionServiceFacade productionSF;
	
	// gson 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
	
	private ModelMap modelMap = new ModelMap();
	
	@RequestMapping(value="/getMrpList.do", method=RequestMethod.POST)
	public ModelMap getMrpList(HttpServletRequest request, HttpServletResponse response) {
		String mrpGatheringStatusCondition = request.getParameter("mrpGatheringStatusCondition");	
		String dateSearchCondition = request.getParameter("dateSearchCondition");
		String mrpStartDate = request.getParameter("mrpStartDate");
		String mrpEndDate = request.getParameter("mrpEndDate");
		String mrpGatheringNo = request.getParameter("mrpGatheringNo");
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<MrpTO> mrpList = null;
			if(mrpGatheringStatusCondition != null ) {
				//여기 null이라는 스트링값이 담겨저왔으니 null은 아님. 객체가있는상태.
				
				mrpList = productionSF.searchMrpList(mrpGatheringStatusCondition);
				
			} else if (dateSearchCondition != null) {
				
				mrpList = productionSF.searchMrpList(dateSearchCondition, mrpStartDate, mrpEndDate);
				
			} else if (mrpGatheringNo != null) {
				
				mrpList = productionSF.searchMrpListAsMrpGatheringNo(mrpGatheringNo);
				
			}
			
			modelMap.put("gridRowJson", mrpList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
			 * finally { System.out.println("getMrpList - 취합대기 검색/취합 클릭시 값확인 : "
			 * +mrpGatheringStatusCondition); out.println(gson.toJson(modelMap));
			 * out.close(); }
			 */
		return modelMap;
	}
	
	@RequestMapping(value="/openMrp.do", method=RequestMethod.POST)
	public HashMap<String,Object> openMrp(HttpServletRequest request, HttpServletResponse response) {
		String mpsNoListStr = request.getParameter("mpsNoList");
		System.out.println("mpsNoListStr 값확인 : "+mpsNoListStr);
	
		ArrayList<String> mpsNoArr = gson.fromJson(mpsNoListStr,
				new TypeToken<ArrayList<String>>() { }.getType());		
		//제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을  명시하기위해서 TypeToken을 사용
		System.out.println("mpsNoArr 값확인 : "+mpsNoArr);
		HashMap<String, Object> resultMap = new HashMap<>();
				//PrintWriter out = null;
		try {
			//out = response.getWriter();

			resultMap = productionSF.openMrp(mpsNoArr);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		} /*
			 * finally { out.println(gson.toJson(resultMap)); out.close(); }
			 */
		return resultMap;
	}

	@RequestMapping(value="/registerMrp.do", method=RequestMethod.POST)
	public ModelMap registerMrp(HttpServletRequest request) {

		String batchList = request.getParameter("batchList");  // mrp 모의전개 정보 
		String mrpRegisterDate = request.getParameter("mrpRegisterDate");  // 소요량 전개 일자 

		ArrayList<String> mpsList	= gson.fromJson(batchList, 
				new TypeToken<ArrayList<String>>() { }.getType()); // 각각의 json이 to객체가 되어 AraryList에 담김
		//제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을  명시하기위해서 TypeToken을 사용
		System.out.println("mrpRegisterDate"+mrpRegisterDate);
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> resultMap = productionSF.registerMrp(mrpRegisterDate, mpsList);	 
		System.out.println("resultMap : "+resultMap);
		
		modelMap.put("result", resultMap);
		modelMap.put("errorCode", 1);
		modelMap.put("errorMsg", "성공");
		return modelMap;
	}
	
	@RequestMapping(value="/getMrpGatheringList.do", method=RequestMethod.POST)
	public ModelMap getMrpGatheringList(HttpServletRequest request, HttpServletResponse response) {
		String mrpNoList = request.getParameter("mrpNoList");
		ArrayList<String> mrpNoArr = gson.fromJson(mrpNoList,
				new TypeToken<ArrayList<String>>() { }.getType());				
			//제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을  명시하기위해서 TypeToken을 사용
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<MrpGatheringTO> mrpGatheringList = productionSF.getMrpGathering(mrpNoArr);
			
			modelMap.put("gridRowJson", mrpGatheringList);
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
	
	@RequestMapping(value="/registerMrpGathering.do", method=RequestMethod.POST)
	public ModelMap registerMrpGathering(HttpServletRequest request, HttpServletResponse response) {

		String mrpGatheringRegisterDate = request.getParameter("mrpGatheringRegisterDate"); //선택한날짜
		String mrpNoList = request.getParameter("mrpNoList");
		String mrpNoAndItemCodeList = request.getParameter("mrpNoAndItemCodeList");
		ArrayList<String> mrpNoArr = gson.fromJson(mrpNoList,
				new TypeToken<ArrayList<String>>() { }.getType());
		HashMap<String, String> mrpNoAndItemCodeMap =  gson.fromJson(mrpNoAndItemCodeList, //mprNO : ItemCode 
              new TypeToken<HashMap<String, String>>() { }.getType());
		try {
			HashMap<String, Object> resultMap  = productionSF.registerMrpGathering(mrpGatheringRegisterDate, mrpNoArr,mrpNoAndItemCodeMap);	 
//														선택한날짜                  				getRowData		MRP-NO : DK-AP01
			
			
			System.out.println("resultMap : " + resultMap);
			modelMap.put("result", resultMap);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		}catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}
	
	@RequestMapping(value="/searchMrpGathering.do", method=RequestMethod.POST)
	public ModelMap searchMrpGathering(HttpServletRequest request, HttpServletResponse response) {
		String searchDateCondition = request.getParameter("searchDateCondition");
		String startDate = request.getParameter("mrpGatheringStartDate");
		String endDate = request.getParameter("mrpGatheringEndDate");
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<MrpGatheringTO> mrpGatheringList = 
					productionSF.searchMrpGatheringList(searchDateCondition, startDate, endDate);
			
			modelMap.put("gridRowJson", mrpGatheringList);
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
