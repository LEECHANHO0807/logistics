package kr.co.seoulit.system.authorityManager.to;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuTO {
	private String menuCode;
	private String parentMenuCode;
	private String menuName;
	private String menuLevel;
	private String menuURL;
	private String menuStatus;
	private String childMenu;
	private String navMenu;
	private String navMenuName;
	
}
