package kr.co.seoulit.system.basicInfo.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.system.basicInfo.applicationService.*;
import kr.co.seoulit.system.basicInfo.to.CompanyTO;
import kr.co.seoulit.system.basicInfo.to.CustomerTO;
import kr.co.seoulit.system.basicInfo.to.DepartmentTO;
import kr.co.seoulit.system.basicInfo.to.FinancialAccountAssociatesTO;
import kr.co.seoulit.system.basicInfo.to.WorkplaceTO;

@Service
public class BasicInfoServiceFacadeImpl implements BasicInfoServiceFacade{
	
	//참조 변수 선언
	@Autowired
	private CustomerApplicationService customerAS;
	@Autowired
	private FinancialAccountAssociatesApplicationService associatsAS;
	@Autowired
	private CompanyApplicationService companyAS;
	@Autowired
	private WorkplaceApplicationService workplaceAS;
	@Autowired
	private DepartmentApplicationService deptAS;
	
    @Override
    public ArrayList<CustomerTO> getCustomerList(String searchCondition, String companyCode, String workplaceCode) {

        ArrayList<CustomerTO> customerList = null;
            customerList = customerAS.getCustomerList(searchCondition, companyCode, workplaceCode);
           
        return customerList;
    }

    @Override
    public HashMap<String, Object> batchCustomerListProcess(ArrayList<CustomerTO> customerList) {

        HashMap<String, Object> resultMap = null;
        resultMap = customerAS.batchCustomerListProcess(customerList);
        
        return resultMap;

    }

    @Override
    public ArrayList<FinancialAccountAssociatesTO> getFinancialAccountAssociatesList(String searchCondition,
                                                                                     String workplaceCode) {

        ArrayList<FinancialAccountAssociatesTO> financialAccountAssociatesList = null;
        financialAccountAssociatesList = associatsAS.getFinancialAccountAssociatesList(searchCondition,
                    workplaceCode);

        return financialAccountAssociatesList;

    }

    @Override
    public HashMap<String, Object> batchFinancialAccountAssociatesListProcess(
            ArrayList<FinancialAccountAssociatesTO> financialAccountAssociatesList) {
       
        HashMap<String, Object> resultMap = null;
            resultMap = associatsAS.batchFinancialAccountAssociatesListProcess(financialAccountAssociatesList);

        return resultMap;
    }
    @Override
	public ArrayList<CompanyTO> getCompanyList() {
		ArrayList<CompanyTO> companyList = null;
		companyList = companyAS.getCompanyList();
		return companyList;
	}

	@Override
	public ArrayList<WorkplaceTO> getWorkplaceList(String companyCode) {

		ArrayList<WorkplaceTO> workplaceList = null;
			workplaceList = workplaceAS.getWorkplaceList(companyCode);

		return workplaceList;
	}

	@Override
	public ArrayList<DepartmentTO> getDepartmentList(String searchCondition, String companyCode,
			String workplaceCode) {
		ArrayList<DepartmentTO> departmentList = null;
		departmentList = deptAS.getDepartmentList(searchCondition, companyCode, workplaceCode);

		return departmentList;
	}

	@Override
	public HashMap<String, Object> batchCompanyListProcess(ArrayList<CompanyTO> companyList) {
		HashMap<String, Object> resultMap = null;
		resultMap = companyAS.batchCompanyListProcess(companyList);

		return resultMap;
	}

	@Override
	public HashMap<String, Object> batchWorkplaceListProcess(ArrayList<WorkplaceTO> workplaceList) {
		HashMap<String, Object> resultMap = null;
			resultMap = workplaceAS.batchWorkplaceListProcess(workplaceList);

		return resultMap;
	}

	@Override
	public HashMap<String, Object> batchDepartmentListProcess(ArrayList<DepartmentTO> departmentList) {
		HashMap<String, Object> resultMap = null;
		resultMap = deptAS.batchDepartmentListProcess(departmentList);

		return resultMap;
	}

}
