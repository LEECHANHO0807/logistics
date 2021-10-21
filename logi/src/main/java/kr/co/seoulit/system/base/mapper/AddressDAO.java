package kr.co.seoulit.system.base.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.system.base.to.AddressTO;

@Mapper
public interface AddressDAO {

	public String selectSidoCode(String sidoName);
	
	public ArrayList<AddressTO> selectRoadNameAddressList(HashMap<String, String> param);
	
	public ArrayList<AddressTO> selectJibunAddressList(HashMap<String, String> param);

	
}
