package kr.co.seoulit.system.base.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kr.co.seoulit.system.base.to.CovidPublicHospitalTO;

@RestController
@RequestMapping("/base/*")
public class ApiExplorer{
	
	ModelMap modelMap = new ModelMap();
	
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null) {
			return null;
		}
		return nValue.getNodeValue();
	}
	@RequestMapping(value="/openApi.do", method=RequestMethod.GET)
	public ModelMap handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<CovidPublicHospitalTO> array= new ArrayList<>(); 
		CovidPublicHospitalTO bean = null;

		try {
			String url = "http://apis.data.go.kr/B551182/pubReliefHospService/getpubReliefHospList?"
						+"ServiceKey=PoiEUxb5gNXGz7uTcSrhfAX4BLF35nsQJj8rWpS971o08a3KGcihSLqJ%2B9duC1sGEp1x7y33hCZpdzZS0BTC1Q%3D%3D&"
						+"pageNo=1&numOfRows=40&spclAdmTyCd=A0";
			
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				bean = new CovidPublicHospitalTO();
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					bean.setAdtFrDd(getTagValue("adtFrDd", eElement));
					bean.setHospTyTpCd(getTagValue("hospTyTpCd", eElement));
					bean.setSgguNm(getTagValue("sgguNm", eElement));
					bean.setSidoNm(getTagValue("sidoNm", eElement));
					bean.setSpclAdmTyCd(getTagValue("spclAdmTyCd", eElement));
					bean.setTelno(getTagValue("telno", eElement));
					bean.setYadmNm(getTagValue("yadmNm", eElement));
					
					array.add(bean);
				}
				System.out.println("병원명 : "+bean.getYadmNm());
	        }
			
			System.out.println(array);
			
			modelMap.put("openApiHospital" , array);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelMap;
	}
}
