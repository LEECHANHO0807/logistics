package kr.co.seoulit.logistics.production.controller;

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

import kr.co.seoulit.logistics.production.serviceFacade.ProductionServiceFacade;
import kr.co.seoulit.logistics.production.to.ProductionPerformanceInfoTO;
import kr.co.seoulit.logistics.production.to.WorkOrderInfoTO;

@RestController
@RequestMapping("/production/*")
public class WorkOrderController{
	@Autowired
	private ProductionServiceFacade productionSF;
	
	private ModelMap modelMap = new ModelMap();
	private static Gson gson = new GsonBuilder().serializeNulls().create();

	@RequestMapping(value="/getWorkOrderableMrpList.do" , method=RequestMethod.GET)
	public ModelMap getWorkOrderableMrpList(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			modelMap = (ModelMap)productionSF.getWorkOrderableMrpList();

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}
	
	@RequestMapping(value="/showWorkOrderDialog.do", method=RequestMethod.GET)
	public HashMap<String, Object> showWorkOrderDialog(HttpServletRequest request, HttpServletResponse response) {
		
		String mrpGatheringNo = request.getParameter("mrpGatheringNo");
		HashMap<String,Object> resultMap = new HashMap<>();
		try {
			
			resultMap = productionSF.getWorkOrderSimulationList(mrpGatheringNo);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}
	
	@RequestMapping(value="/workOrder.do", method=RequestMethod.POST)
	public HashMap<String, Object> workOrder(HttpServletRequest request, HttpServletResponse response) {
		String mrpGatheringNo= request.getParameter("mrpGatheringNo");//?????????????????????
		String workPlaceCode = request.getParameter("workPlaceCode"); //???????????????
		String productionProcess = request.getParameter("productionProcessCode"); //??????????????????:PP002
		System.out.println("???????????????" + workPlaceCode + " : " + "?????????????????? : " + productionProcess);
		HashMap<String,Object> resultMap = new HashMap<>();
		
		try {
											//	  BRC-01		PP002
			resultMap = productionSF.workOrder(workPlaceCode,productionProcess,mrpGatheringNo);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		} 
		return resultMap;
	}
	@RequestMapping(value="/showWorkOrderInfoList.do", method=RequestMethod.GET)
	public ModelMap showWorkOrderInfoList(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<WorkOrderInfoTO> workOrderInfoList = null;
		try {
			
			workOrderInfoList = productionSF.getWorkOrderInfoList();

			modelMap.put("gridRowJson", workOrderInfoList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "??????");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}
	@RequestMapping(value="/workOrderCompletion.do", method=RequestMethod.POST)
	public HashMap<String, Object> workOrderCompletion(HttpServletRequest request, HttpServletResponse response) {
		String workOrderNo=request.getParameter("workOrderNo");
		String actualCompletionAmount=request.getParameter("actualCompletionAmount");
		HashMap<String, Object> resultMap = new HashMap<>();
		
		try {
			
			resultMap = productionSF.workOrderCompletion(workOrderNo,actualCompletionAmount);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}
	@RequestMapping(value="/getProductionPerformanceInfoList.do", method=RequestMethod.POST)
	public ModelMap getProductionPerformanceInfoList(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<ProductionPerformanceInfoTO> productionPerformanceInfoList = null;
		try {
			
			productionPerformanceInfoList = productionSF.getProductionPerformanceInfoList();

			modelMap.put("gridRowJson", productionPerformanceInfoList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "??????");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} 
		return modelMap;
	}
	
	@RequestMapping(value="/showWorkSiteSituation.do", method=RequestMethod.GET)
	public HashMap<String, Object> showWorkSiteSituation(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> resultMap = new HashMap<>();
		
		String workSiteCourse = request.getParameter("workSiteCourse");//???????????????:RawMaterials,????????????:Production,??????????????????:SiteExamine
		String workOrderNo = request.getParameter("workOrderNo");//????????????????????????	
		String itemClassIfication = request.getParameter("itemClassIfication");//????????????:?????????,?????????,?????????	
		try {
			
			resultMap = productionSF.showWorkSiteSituation(workSiteCourse,workOrderNo,itemClassIfication);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}
	
	@RequestMapping(value="/workCompletion.do", method=RequestMethod.POST)
	public ModelMap workCompletion(HttpServletRequest request, HttpServletResponse response) {
		
		String workOrderNo = request.getParameter("workOrderNo"); //??????????????????
		String itemCode = request.getParameter("itemCode"); //?????????????????? DK-01 , DK-AP01
		String itemCodeList = request.getParameter("itemCodeList"); //?????????????????? 
		ArrayList<String> itemCodeListArr = gson.fromJson(itemCodeList,
				new TypeToken<ArrayList<String>>() {}.getType());
		//????????? ???????????? ??????????????? ???????????? ?????? ??????????????????  ????????????????????? TypeToken??? ??????
		try {
			

			productionSF.workCompletion(workOrderNo,itemCode,itemCodeListArr);

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} 
		return modelMap;
	}
	@RequestMapping(value="/workSiteLog.do", method=RequestMethod.POST)
	public HashMap<String, Object> workSiteLogList(HttpServletRequest request, HttpServletResponse response) {
		String workSiteLogDate = request.getParameter("workSiteLogDate");
		HashMap<String, Object> resultMap = new HashMap<>();
		
		try {
			
			resultMap=productionSF.workSiteLogList(workSiteLogDate);
			
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		} 
		return resultMap;
	}
	
}
