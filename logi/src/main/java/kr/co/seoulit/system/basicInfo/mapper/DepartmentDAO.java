package kr.co.seoulit.system.basicInfo.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.system.basicInfo.to.DepartmentTO;
@Mapper
public interface DepartmentDAO {

	public ArrayList<DepartmentTO> selectDepartmentListByCompany(String companyCode);

	public ArrayList<DepartmentTO> selectDepartmentListByWorkplace(String workplaceCode);

	public void insertDepartment(DepartmentTO bean);

	public void updateDepartment(DepartmentTO bean);

	public void deleteDepartment(DepartmentTO bean);
}
