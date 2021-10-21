package kr.co.seoulit.logistics.production.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import kr.co.seoulit.logistics.production.applicationService.MpsApplicationService;
import kr.co.seoulit.logistics.production.applicationService.MrpApplicationService;
import kr.co.seoulit.logistics.production.applicationService.WorkOrderApplicationService;
import kr.co.seoulit.logistics.production.to.ContractDetailInMpsAvailableTO;
import kr.co.seoulit.logistics.production.to.MpsTO;
import kr.co.seoulit.logistics.production.to.MrpGatheringTO;
import kr.co.seoulit.logistics.production.to.MrpTO;
import kr.co.seoulit.logistics.production.to.ProductionPerformanceInfoTO;
import kr.co.seoulit.logistics.production.to.SalesPlanInMpsAvailableTO;
import kr.co.seoulit.logistics.production.to.WorkOrderInfoTO;

@Service
public class ProductionServiceFacadeImpl implements ProductionServiceFacade {
	@Autowired
	private MpsApplicationService mpsAS;
	@Autowired
	private MrpApplicationService mrpAS;
	@Autowired
	private WorkOrderApplicationService workOrderAS;

	private ModelMap modelMap = new ModelMap();
	
	@Override
	public ArrayList<MpsTO> getMpsList(String startDate, String endDate, String includeMrpApply) {
		ArrayList<MpsTO> mpsTOList = null;
			mpsTOList = mpsAS.getMpsList(startDate, endDate, includeMrpApply);

		return mpsTOList;
	}

	@Override
	public ArrayList<ContractDetailInMpsAvailableTO> getContractDetailListInMpsAvailable(String searchCondition,
			String startDate, String endDate) {
		ArrayList<ContractDetailInMpsAvailableTO> contractDetailInMpsAvailableList = null;
			contractDetailInMpsAvailableList = mpsAS.getContractDetailListInMpsAvailable(searchCondition, startDate,
					endDate);

		return contractDetailInMpsAvailableList;

	}

	@Override
	public ArrayList<SalesPlanInMpsAvailableTO> getSalesPlanListInMpsAvailable(String searchCondition,
			String startDate, String endDate) {
		ArrayList<SalesPlanInMpsAvailableTO> salesPlanInMpsAvailableList = null;

			salesPlanInMpsAvailableList = mpsAS.getSalesPlanListInMpsAvailable(searchCondition, startDate, endDate);

		return salesPlanInMpsAvailableList;

	}

	@Override
	public HashMap<String, Object> convertContractDetailToMps(
			ArrayList<ContractDetailInMpsAvailableTO> contractDetailInMpsAvailableList) {
		HashMap<String, Object> resultMap = null;

			resultMap = mpsAS.convertContractDetailToMps(contractDetailInMpsAvailableList);

		return resultMap;

	}

	@Override
	public HashMap<String, Object> convertSalesPlanToMps(
			ArrayList<SalesPlanInMpsAvailableTO> contractDetailInMpsAvailableList) {
		HashMap<String, Object> resultMap = null;
			resultMap = mpsAS.convertSalesPlanToMps(contractDetailInMpsAvailableList);

		return resultMap;

	}

	@Override
	public HashMap<String, Object> batchMpsListProcess(ArrayList<MpsTO> mpsTOList) {
		HashMap<String, Object> resultMap = null;
			resultMap = mpsAS.batchMpsListProcess(mpsTOList);
		
		return resultMap;

	}

	@Override
	public ArrayList<MrpTO> searchMrpList(String mrpGatheringStatusCondition) {
		ArrayList<MrpTO> mrpList = null;
			mrpList = mrpAS.searchMrpList(mrpGatheringStatusCondition);

		return mrpList;

	}

	@Override
	public ArrayList<MrpTO> searchMrpList(String dateSearchCondtion, String startDate, String endDate) {
		ArrayList<MrpTO> mrpList = null;
			mrpList = mrpAS.searchMrpList(dateSearchCondtion, startDate, endDate);

		return mrpList;
	}

	@Override
	public ArrayList<MrpTO> searchMrpListAsMrpGatheringNo(String mrpGatheringNo) {
		ArrayList<MrpTO> mrpList = null;
			mrpList = mrpAS.searchMrpListAsMrpGatheringNo(mrpGatheringNo);

		return mrpList;
	}

	@Override
	public ArrayList<MrpGatheringTO> searchMrpGatheringList(String dateSearchCondtion, String startDate,
			String endDate) {
		ArrayList<MrpGatheringTO> mrpGatheringList = null;
			mrpGatheringList = mrpAS.searchMrpGatheringList(dateSearchCondtion, startDate, endDate);
	
		return mrpGatheringList;
	}

	@Override
	public HashMap<String, Object> openMrp(ArrayList<String> mpsNoArr) {
		HashMap<String, Object> resultMap = null;
			resultMap = mrpAS.openMrp(mpsNoArr);

		return resultMap;
	}
	
	@Override
	public HashMap<String, Object> registerMrp(String mrpRegisterDate, ArrayList<String> mpsList) {
		HashMap<String, Object> resultMap = null;
			resultMap = mrpAS.registerMrp(mrpRegisterDate, mpsList);

		return resultMap;
	}

	@Override
	public HashMap<String, Object> batchMrpListProcess(ArrayList<MrpTO> mrpTOList) {
		HashMap<String, Object> resultMap = null;
			resultMap = mrpAS.batchMrpListProcess(mrpTOList);

		return resultMap;
	}

	@Override
	public ArrayList<MrpGatheringTO> getMrpGathering(ArrayList<String> mrpNoArr) {
		ArrayList<MrpGatheringTO> mrpGatheringList = null;
			mrpGatheringList = mrpAS.getMrpGathering(mrpNoArr);

		return mrpGatheringList;
	}

	@Override
	public HashMap<String, Object> registerMrpGathering(String mrpGatheringRegisterDate,ArrayList<String> mrpNoArr,HashMap<String, String> mrpNoAndItemCodeMap) {
		return mrpAS.registerMrpGathering(mrpGatheringRegisterDate, mrpNoArr,mrpNoAndItemCodeMap);
	}

	@Override
	public ModelMap getWorkOrderableMrpList() {
        
			modelMap = (ModelMap) workOrderAS.getWorkOrderableMrpList();

		return modelMap;
		
	}

	@Override
	public HashMap<String,Object> getWorkOrderSimulationList(String mrpGatheringNo) {
        HashMap<String,Object> resultMap = null;
			resultMap = workOrderAS.getWorkOrderSimulationList(mrpGatheringNo);

		return resultMap;
	}

	@Override
	public HashMap<String,Object> workOrder(String workPlaceCode,String productionProcess,String mrpGatheringNo) {
	
        HashMap<String,Object> resultMap = null;
			resultMap = workOrderAS.workOrder(workPlaceCode,productionProcess,mrpGatheringNo);

    	return resultMap;
		
	}

	@Override
	public ArrayList<WorkOrderInfoTO> getWorkOrderInfoList() {
		ArrayList<WorkOrderInfoTO> workOrderInfoList = null;
			workOrderInfoList = workOrderAS.getWorkOrderInfoList();

		return workOrderInfoList;
		
	}

	@Override
	public HashMap<String,Object> workOrderCompletion(String workOrderNo,String actualCompletionAmount) {
        HashMap<String,Object> resultMap = null;		
			resultMap = workOrderAS.workOrderCompletion(workOrderNo,actualCompletionAmount);

    	return resultMap;
		
	}

	@Override
	public ArrayList<ProductionPerformanceInfoTO> getProductionPerformanceInfoList() {
		ArrayList<ProductionPerformanceInfoTO> productionPerformanceInfoList = null;
		productionPerformanceInfoList = workOrderAS.getProductionPerformanceInfoList();
		
		return productionPerformanceInfoList;
	}

	@Override
	public HashMap<String,Object> showWorkSiteSituation(String workSiteCourse,String workOrderNo,String itemClassIfication) {
		HashMap<String,Object> showWorkSiteSituation = null;
			showWorkSiteSituation = workOrderAS.showWorkSiteSituation(workSiteCourse,workOrderNo,itemClassIfication);

		return showWorkSiteSituation;

	}

	@Override
	public void workCompletion(String workOrderNo, String itemCode ,  ArrayList<String> itemCodeListArr) {
			workOrderAS.workCompletion(workOrderNo,itemCode,itemCodeListArr);
	}

	@Override
	public HashMap<String, Object> workSiteLogList(String workSiteLogDate) {
		HashMap<String, Object> resultMap = new HashMap<>();
			resultMap=workOrderAS.workSiteLogList(workSiteLogDate);

		return resultMap;
	}

	
}
