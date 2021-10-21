package kr.co.seoulit.logistics.production.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.logistics.production.to.MrpInsertInfoTO;
import kr.co.seoulit.logistics.production.to.MrpTO;
@Mapper
public interface MrpDAO {

	public ArrayList<MrpTO> selectMrpListAll(HashMap<String, String> param) ;
	
	public ArrayList<MrpTO> selectMrpList(HashMap<String, String> param); 

	public ArrayList<MrpTO> selectMrpListAsMrpGatheringNo(String mrpGatheringNo);
	
	public HashMap<String,Object> openMrp(HashMap<String, String> param);
	
	public int selectMrpCount(String mrpRegisterDate);

	public void insertMrp(MrpTO TO);
	
	public void updateMrp(MrpTO TO);
	
	public void  changeMrpGatheringStatus(HashMap<String, String> param);
	
	public void deleteMrp(MrpTO TO);
	
	public MrpInsertInfoTO insertMrpList(HashMap<String, Object> params);
	
}
