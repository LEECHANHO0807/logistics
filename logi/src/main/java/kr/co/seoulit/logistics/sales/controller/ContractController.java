package kr.co.seoulit.logistics.sales.controller;

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

import kr.co.seoulit.logistics.sales.serviceFacade.SalesServiceFacade;
import kr.co.seoulit.logistics.sales.to.ContractDetailTO;
import kr.co.seoulit.logistics.sales.to.ContractInfoTO;
import kr.co.seoulit.logistics.sales.to.ContractTO;
import kr.co.seoulit.logistics.sales.to.EstimateTO;

@RestController
@RequestMapping("/sales/*")
public class ContractController {
	@Autowired
	private SalesServiceFacade salesSF;
	//private ModelMap ModelMap;
	private ModelMap modelMap = new ModelMap();

	// GSON 
	private static Gson gson = new GsonBuilder().serializeNulls().create(); 
	
	@RequestMapping(value= "/searchContract.do", method=RequestMethod.POST)
	public ModelMap searchContract(HttpServletRequest request, HttpServletResponse response) {
		String searchCondition = request.getParameter("searchCondition");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String customerCode = request.getParameter("customerCode");
		//PrintWriter out = null;
		try {
			//out = response.getWriter();

			ArrayList<ContractInfoTO> contractInfoTOList = null;

			if (searchCondition.equals("searchByDate")) {

				String[] paramArray = { startDate, endDate };
				contractInfoTOList = salesSF.getContractList("searchByDate", paramArray);

			} else if (searchCondition.equals("searchByCustomer")) {

				String[] paramArray = { customerCode };
				contractInfoTOList = salesSF.getContractList("searchByCustomer", paramArray);

			}

			modelMap.put("gridRowJson", contractInfoTOList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "�꽦怨�");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
			 * finally { out.println(gson.toJson(modelMap)); out.close(); }
			 */
		return modelMap;
	}
	@RequestMapping(value="/searchContractNO.do", method=RequestMethod.POST)
	public ModelMap searchContractNO(HttpServletRequest request, HttpServletResponse response) {
		String searchCondition = request.getParameter("searchCondition");
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<ContractInfoTO> contractInfoTOList = null;
			if (searchCondition.equals("searchByDate")) {
				String customerCode = "";
				String[] paramArray = { customerCode };
				contractInfoTOList = salesSF.getContractList("searchByCustomer", paramArray);

			}

			modelMap.put("gridRowJson", contractInfoTOList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "�꽦怨�");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
			 * finally { out.println(gson.toJson(modelMap)); out.close(); }
			 */
		return modelMap;
	}
	@RequestMapping(value="/searchContractDetail.do", method=RequestMethod.POST)
	public ModelMap searchContractDetail(HttpServletRequest request, HttpServletResponse response) {
		String contractNo = request.getParameter("contractNo");
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<ContractDetailTO> contractDetailTOList = salesSF.getContractDetailList(contractNo);

			modelMap.put("gridRowJson", contractDetailTOList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "�꽦怨�");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
			 * finally { out.println(gson.toJson(modelMap)); out.close(); }
			 */
		return modelMap;
	}
	@RequestMapping(value= "/searchEstimateInContractAvailable.do", method=RequestMethod.POST)
	public ModelMap searchEstimateInContractAvailable(HttpServletRequest request, HttpServletResponse response) {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<EstimateTO> estimateListInContractAvailable = salesSF
					.getEstimateListInContractAvailable(startDate, endDate);

			modelMap.put("gridRowJson", estimateListInContractAvailable);
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
	
	@RequestMapping(value="/addNewContract.do", method=RequestMethod.POST)
	public HashMap<String, Object> addNewContract(HttpServletRequest request, HttpServletResponse response) {
		String batchList = request.getParameter("batchList");
		String contractDate = request.getParameter("contractDate");//오늘날짜
		String personCodeInCharge = request.getParameter("personCodeInCharge");//EMP-01
		System.out.println("@batchList="+batchList+"@contractDate"+contractDate+"@personCodeInCharge"+personCodeInCharge);
		HashMap<String, Object> resultMap = new HashMap<>();
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ContractTO workingContractTO = gson.fromJson(batchList, ContractTO.class);
			resultMap = salesSF.addNewContract(contractDate, personCodeInCharge,workingContractTO);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		} /*
			 * finally { out.println(gson.toJson(resultMap)); out.close(); }
			 */
		return resultMap;
	}
	
	@RequestMapping(value= "/cancleEstimate.do" , method=RequestMethod.POST)
	public ModelMap cancleEstimate(HttpServletRequest request, HttpServletResponse response) {
		String estimateNo = request.getParameter("estimateNo");
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			salesSF.changeContractStatusInEstimate(estimateNo, "N");
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "�꽦怨�");
			modelMap.put("cancledEstimateNo", estimateNo);
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
