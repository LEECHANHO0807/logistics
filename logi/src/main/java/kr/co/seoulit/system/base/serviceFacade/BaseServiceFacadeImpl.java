package kr.co.seoulit.system.base.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.system.base.applicationService.AddressApplicationService;
import kr.co.seoulit.system.base.applicationService.CodeApplicationService;
import kr.co.seoulit.system.base.applicationService.ReportApplicationService;
import kr.co.seoulit.system.base.to.AddressTO;
import kr.co.seoulit.system.base.to.CodeDetailTO;
import kr.co.seoulit.system.base.to.CodeTO;
import kr.co.seoulit.system.base.to.ContractReportTO;
import kr.co.seoulit.system.base.to.EstimateReportTO;

@Service
public class BaseServiceFacadeImpl implements BaseServiceFacade {

	@Autowired
	private CodeApplicationService codeAS;
	@Autowired
	private AddressApplicationService addressAS;
	@Autowired
	private ReportApplicationService reportAS;
	
	@Override
	public ArrayList<CodeDetailTO> getDetailCodeList(String divisionCode) {
		ArrayList<CodeDetailTO> codeDetailList = null;
		
			codeDetailList = codeAS.getDetailCodeList(divisionCode);
		
		return codeDetailList;
	}

	@Override
	public ArrayList<CodeTO> getCodeList() {
		ArrayList<CodeTO> codeList = null;
		
			codeList = codeAS.getCodeList();
		
		return codeList;
	}

	@Override
	public Boolean checkCodeDuplication(String divisionCode, String newDetailCode) {
		Boolean flag = false;
		
			flag = codeAS.checkCodeDuplication(divisionCode, newDetailCode);
		
		return flag;
	}

	@Override
	public HashMap<String, Object> batchCodeListProcess(ArrayList<CodeTO> codeList) {
		HashMap<String, Object> resultMap = null;
		
			resultMap = codeAS.batchCodeListProcess(codeList);
		
		return resultMap;
	}

	@Override
	public HashMap<String, Object> batchDetailCodeListProcess(ArrayList<CodeDetailTO> detailCodeList) {
		HashMap<String, Object> resultMap = null;
		
			resultMap = codeAS.batchDetailCodeListProcess(detailCodeList);
		
		return resultMap;
	}

	@Override
	public HashMap<String, Object> changeCodeUseCheckProcess(ArrayList<CodeDetailTO> detailCodeList) {
		HashMap<String, Object> resultMap = null;
		
			resultMap = codeAS.changeCodeUseCheckProcess(detailCodeList);
		
		return resultMap;
	}

	@Override
	public ArrayList<AddressTO> getAddressList(String sidoName, String searchAddressType, String searchValue, String mainNumber) {
		ArrayList<AddressTO> addressList = null;
		
			addressList = addressAS.getAddressList(sidoName, searchAddressType, searchValue, mainNumber);
		
		return addressList;
	}
	@Override
	public ArrayList<EstimateReportTO> getEstimateReport(String estimateNo) {

		return reportAS.getEstimateReport(estimateNo);

	}

	@Override
	public ArrayList<ContractReportTO> getContractReport(String contractNo) {

		return reportAS.getContractReport(contractNo);

	}
	
	
}
