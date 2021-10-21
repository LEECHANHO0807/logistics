package kr.co.seoulit.logistics.sales.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.logistics.sales.applicationService.ContractApplicationService;
import kr.co.seoulit.logistics.sales.applicationService.DeliveryApplicationService;
import kr.co.seoulit.logistics.sales.applicationService.EstimateApplicationService;
import kr.co.seoulit.logistics.sales.applicationService.SalesPlanApplicationService;
import kr.co.seoulit.logistics.sales.to.ContractDetailTO;
import kr.co.seoulit.logistics.sales.to.ContractInfoTO;
import kr.co.seoulit.logistics.sales.to.ContractTO;
import kr.co.seoulit.logistics.sales.to.DeliveryInfoTO;
import kr.co.seoulit.logistics.sales.to.EstimateDetailTO;
import kr.co.seoulit.logistics.sales.to.EstimateTO;
import kr.co.seoulit.logistics.sales.to.SalesPlanTO;

@Service
public class SalesServiceFacadeImpl implements SalesServiceFacade {
	@Autowired
	private EstimateApplicationService estimateAS;
	@Autowired
	private ContractApplicationService contractAS;
	@Autowired
	private SalesPlanApplicationService salesPlanAS;
	@Autowired
	private DeliveryApplicationService deliveryAS;
	
	@Override
	public ArrayList<EstimateTO> getEstimateList(String dateSearchCondition, String startDate, String endDate) {
		ArrayList<EstimateTO> estimateTOList = null;
			estimateTOList = estimateAS.getEstimateList(dateSearchCondition, startDate, endDate);

		return estimateTOList;
	}

	@Override
	public ArrayList<EstimateDetailTO> getEstimateDetailList(String estimateNo) {
		ArrayList<EstimateDetailTO> estimateDetailTOList = null;
			estimateDetailTOList = estimateAS.getEstimateDetailList(estimateNo);

		return estimateDetailTOList;
	}

	@Override
	public HashMap<String, Object> addNewEstimate(String estimateDate, EstimateTO newEstimateTO) {
		HashMap<String, Object> resultMap = null;
			resultMap = estimateAS.addNewEstimate(estimateDate, newEstimateTO);
			System.out.println("              SalesSF에서resultMap값은 : " + resultMap);
			//신청한 날짜 뒤에 견적등록 갯수 번호
			// SalesSF에서resultMap값은 : {DELETE=[], 
									//	newEstimateNo=ES2020100702, 
									//	INSERT=[ES2020100702-01, ES2020100702-02], 
									//	UPDATE=[]}
		return resultMap;
	}
	
	@Override
	public HashMap<String, Object> batchEstimateDetailListProcess(ArrayList<EstimateDetailTO> estimateDetailTOList) {
		HashMap<String, Object> resultMap = null;
			resultMap = estimateAS.batchEstimateDetailListProcess(estimateDetailTOList);
		
		return resultMap;
	}

	@Override
	public ArrayList<ContractInfoTO> getContractList(String searchCondition, String[] paramArray) {
		ArrayList<ContractInfoTO> contractInfoTOList = null;
			contractInfoTOList = contractAS.getContractList(searchCondition, paramArray);

		return contractInfoTOList;
	}

	@Override
	public ArrayList<ContractInfoTO> getDeliverableContractList(String searchCondition, String[] paramArray) {
		ArrayList<ContractInfoTO> deliverableContractList = null;
			deliverableContractList = contractAS.getDeliverableContractList(searchCondition, paramArray);

		return deliverableContractList;
	}
	
	@Override
	public ArrayList<ContractDetailTO> getContractDetailList(String estimateNo) {
		ArrayList<ContractDetailTO> contractDetailTOList = null;
			contractDetailTOList = contractAS.getContractDetailList(estimateNo);

		return contractDetailTOList;
	}

	@Override
	public ArrayList<EstimateTO> getEstimateListInContractAvailable(String startDate, String endDate) {
		ArrayList<EstimateTO> estimateListInContractAvailable = null;
			estimateListInContractAvailable = contractAS.getEstimateListInContractAvailable(startDate, endDate);
	
		return estimateListInContractAvailable;
	}

	@Override
	public HashMap<String, Object> addNewContract(String contractDate, String personCodeInCharge,
			ContractTO workingContractTO) {
		HashMap<String, Object> resultMap = null;
			resultMap = contractAS.addNewContract(contractDate, personCodeInCharge, workingContractTO);
	
		return resultMap;
	}

	@Override
	public HashMap<String, Object> batchContractDetailListProcess(ArrayList<ContractDetailTO> contractDetailTOList) {
		HashMap<String, Object> resultMap = null;
			resultMap = contractAS.batchContractDetailListProcess(contractDetailTOList);
	
		return resultMap;
	}

	@Override
	public void changeContractStatusInEstimate(String estimateNo, String contractStatus) {
									 //estimateNo, "N"
			contractAS.changeContractStatusInEstimate(estimateNo, contractStatus);
	
	}

	@Override
	public ArrayList<SalesPlanTO> getSalesPlanList(String dateSearchCondition, String startDate, String endDate) {
		ArrayList<SalesPlanTO> salesPlanTOList = null;
			salesPlanTOList = salesPlanAS.getSalesPlanList(dateSearchCondition, startDate, endDate);

		return salesPlanTOList;
	}

	@Override
	public HashMap<String, Object> batchSalesPlanListProcess(ArrayList<SalesPlanTO> salesPlanTOList) {
		HashMap<String, Object> resultMap = null;
			resultMap = salesPlanAS.batchSalesPlanListProcess(salesPlanTOList);
		
		return resultMap;
	}

	@Override
	public ArrayList<DeliveryInfoTO> getDeliveryInfoList() {
		ArrayList<DeliveryInfoTO> deliveryInfoList = null;
			deliveryInfoList = deliveryAS.getDeliveryInfoList();

		return deliveryInfoList;
	}

	@Override
	public HashMap<String, Object> batchDeliveryListProcess(List<DeliveryInfoTO> deliveryTOList) {
		HashMap<String, Object> resultMap = null;
			resultMap = deliveryAS.batchDeliveryListProcess(deliveryTOList);

		return resultMap;
	}

	@Override
	public HashMap<String, Object> deliver(String contractDetailNo) {
        HashMap<String,Object> resultMap = null;
			resultMap = deliveryAS.deliver(contractDetailNo);

		return resultMap;
	}
	
}
