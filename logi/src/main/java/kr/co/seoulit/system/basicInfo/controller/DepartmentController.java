package kr.co.seoulit.system.basicInfo.controller;

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

import kr.co.seoulit.system.basicInfo.serviceFacade.BasicInfoServiceFacade;
import kr.co.seoulit.system.basicInfo.to.DepartmentTO;

@RestController
@RequestMapping("/basicInfo/*")
public class DepartmentController {

	// serviceFacade 참조변수 선언
	@Autowired
	private BasicInfoServiceFacade basicInfoSF;


	private ModelMap modelMap = new ModelMap();
	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 JSON 변환

	@RequestMapping(value = "/searchDepartment.do", method = RequestMethod.POST)
	public ModelMap searchDepartmentList(HttpServletRequest request, HttpServletResponse response) {

		String searchCondition = request.getParameter("searchCondition");
		String companyCode = request.getParameter("companyCode");
		String workplaceCode = request.getParameter("workplaceCode");
		
		ArrayList<DepartmentTO> departmentList = null;


		//PrintWriter out = null;

		try {

			//out = response.getWriter();

			departmentList = basicInfoSF.getDepartmentList(searchCondition, companyCode, workplaceCode);

			modelMap.put("gridRowJson", departmentList);
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
	@RequestMapping(value="/batchDepartmentListProcess.do", method = RequestMethod.POST)
	public ModelMap batchListProcess(HttpServletRequest request, HttpServletResponse response) {

		String batchList = request.getParameter("batchList");
		ArrayList<DepartmentTO> departmentList = gson.fromJson(batchList, new TypeToken<ArrayList<DepartmentTO>>() {
		}.getType());
		//PrintWriter out = null;

		try {

			//out = response.getWriter();

			HashMap<String, Object> resultMap = basicInfoSF.batchDepartmentListProcess(departmentList);

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
}
