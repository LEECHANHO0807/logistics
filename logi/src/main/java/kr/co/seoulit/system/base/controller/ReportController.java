package kr.co.seoulit.system.base.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.seoulit.system.base.serviceFacade.BaseServiceFacade;
import kr.co.seoulit.system.base.to.ContractReportTO;
import kr.co.seoulit.system.base.to.EstimateReportTO;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/base/*")
public class ReportController {

	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();
	
	@Autowired
	private BaseServiceFacade baseSF;

	@RequestMapping(value = "/report.pdf", params = "method=estimateReport")
	public ModelAndView estimateReport(HttpServletRequest request, HttpServletResponse response) {

		String estimateNo = request.getParameter("orderDraftNo");
		
		try {
			String sourceFileName = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"Estimate.jasper").getAbsolutePath();
			ArrayList<EstimateReportTO> estimateList = baseSF.getEstimateReport(estimateNo);

			JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(estimateList);
			JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, modelMap, source);
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename=Estimate.pdf;");

		} catch (Exception e) {
			e.printStackTrace();
		}

		modelAndView = new ModelAndView("estimatePdfView", modelMap);
		return modelAndView;
	}
	
	@RequestMapping("/report.do")
	public ModelAndView contractReport(HttpServletRequest request, HttpServletResponse response) {

		String contractNo = request.getParameter("orderDraftNo");

		try {
			String sourceFileName = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"Contract.jasper").getAbsolutePath();
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+sourceFileName);
			ArrayList<ContractReportTO> contractList = baseSF.getContractReport(contractNo);

			JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(contractList);
			//modelMap.put("source", source);
			//modelMap.put("format", "pdf");
			JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, modelMap, source);
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename=Contract.pdf;");

		} catch (Exception e) {

			e.printStackTrace();
		}

		modelAndView = new ModelAndView("contractPdfView", modelMap);
		return modelAndView;
	}
}