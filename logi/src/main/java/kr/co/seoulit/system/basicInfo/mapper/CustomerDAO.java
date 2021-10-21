package kr.co.seoulit.system.basicInfo.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.system.basicInfo.to.CustomerTO;
@Mapper
public interface CustomerDAO {

	public ArrayList<CustomerTO> selectCustomerListByCompany();

	public ArrayList<CustomerTO> selectCustomerListByWorkplace(String workplaceCode);
	
	public void insertCustomer(CustomerTO bean);

	public void updateCustomer(CustomerTO bean);

	public void deleteCustomer(CustomerTO bean);
}
