package kr.co.seoulit.system.base.applicationService;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.system.base.mapper.AddressDAO;
import kr.co.seoulit.system.base.to.AddressTO;

@Component
public class AddressApplicationServiceImpl implements AddressApplicationService {
	// DAO 참조변수
		@Autowired
		private AddressDAO addressDAO;


	@Override
	public ArrayList<AddressTO> getAddressList(String sidoName, String searchAddressType, String searchValue,
			String mainNumber) {

			ArrayList<AddressTO> addressList = null;
			String sidoCode = addressDAO.selectSidoCode(sidoName);
			HashMap<String, String> param = new HashMap<>();

			switch (searchAddressType) {

			case "roadNameAddress":

				String buildingMainNumber = mainNumber;
				param = new HashMap<>();

				param.put("sidoCode", sidoCode);
				param.put("searchValue", searchValue);
				param.put("buildingMainNumber", buildingMainNumber);

				addressList = addressDAO.selectRoadNameAddressList(param);

				break;

			case "jibunAddress":

				String jibunMainAddress = mainNumber;
				param = new HashMap<>();

				param.put("sidoCode", sidoCode);
				param.put("searchValue", searchValue);
				param.put("jibunMainAddress", jibunMainAddress);

				addressList = addressDAO.selectJibunAddressList(param);

				break;

			}

		
		return addressList;

	}

}
