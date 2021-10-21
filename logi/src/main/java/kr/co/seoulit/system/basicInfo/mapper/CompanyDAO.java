package kr.co.seoulit.system.basicInfo.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.system.basicInfo.to.CompanyTO;
@Mapper
public interface CompanyDAO {
	
	public ArrayList<CompanyTO> selectCompanyList();
	
	public void insertCompany(CompanyTO bean);
	
	public void updateCompany(CompanyTO bean);

	public void deleteCompany(CompanyTO bean);
	
}
