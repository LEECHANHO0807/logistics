package kr.co.seoulit.system.base.applicationService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.system.base.mapper.ReportDAO;
import kr.co.seoulit.system.base.to.ContractReportTO;
import kr.co.seoulit.system.base.to.EstimateReportTO;

@Component
public class ReportApplicationServiceImpl implements ReportApplicationService {
	
	@Autowired
	private ReportDAO reportDAO;

	@Override
	public ArrayList<EstimateReportTO> getEstimateReport(String estimateNo) {

		ArrayList<EstimateReportTO> estimateList = reportDAO.selectEstimateReport(estimateNo);

		return estimateList;

	}

	@Override
	public ArrayList<ContractReportTO> getContractReport(String contractNo) {

		ArrayList<ContractReportTO> contractList = reportDAO.selectContractReport(contractNo);

		return contractList;
	}

}
