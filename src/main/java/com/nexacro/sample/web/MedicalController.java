package com.nexacro.sample.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.sample.service.MedicalService;
import com.nexacro.uiadapter17.spring.core.annotation.ParamDataSet;
import com.nexacro.uiadapter17.spring.core.annotation.ParamVariable;
import com.nexacro.uiadapter17.spring.core.data.NexacroResult;

/* 20220-02-17 박상훈 */

@Controller
public class MedicalController {
	
	//DI
	@Autowired
	@Resource(name = "medicalService")
	private MedicalService medicalService;
		
	//Set logger
	private static final Logger logger = LoggerFactory.getLogger(MedicalController.class);
	
	//Get class name for logger
	private final String className = this.getClass().toString();
	
	//──────────────────────────────ApprovalList───────────────────────────────//
	//approvalList
	@RequestMapping(value = "/approvalList.do")
	public NexacroResult approvalList(
			@ParamDataSet(name = "ds_search", required = false) Map<String, Object> searchMap) throws Exception {
		System.out.println("approvalList의 searchMap: "+searchMap);
		NexacroResult result = new NexacroResult();
		System.out.println("approvalList의 result"+result.toString());
		List<Map<String, Object>> list = medicalService.selectListMap(searchMap);
		result.addDataSet("ds_approvalList", list);
		return result;
	}
	
	//accynUpdate
	@RequestMapping(value = "/accynUpdate.do")
	public NexacroResult accynUpdate(
			@ParamDataSet(name = "ds_approvalList", required = false) Map<String, Object> searchMap) throws Exception {
		System.out.println("accynUpdate의 searchMap: " + searchMap);
		int update = medicalService.updateAccynMap(searchMap);
		NexacroResult result = new NexacroResult();
		return result;
	}
	
	//────────────────────────────InsertCommonMedical──────────────────────────//
	@RequestMapping(value = "/insertCommonMedical.do")
	public NexacroResult insertCommonMedical(
				@ParamDataSet(name = "ds_manufacture", required = false) Map<String, Object> searchMap) throws Exception {
		System.out.println("insertManufacture의 searchMap: " + searchMap);
		NexacroResult result = new NexacroResult();
		medicalService.insertCommonMedical(searchMap);
		return result;
	}
	
	//────────────────────────────getAccnoInfo─────────────────────────────────//
	@RequestMapping(value = "/getAccnoInfo.do")
	public NexacroResult getAccnoInfo(@ParamVariable(name = "accno", required = false) String accno) throws Exception {
		System.out.println("getAccnoInfo의 searchMap" + accno);
		Map<String, Object>  selectResult = medicalService.getAccnoInfo(accno);		
		NexacroResult result = new NexacroResult();
		result.addDataSet("ds_manufacture", selectResult);		
		return result;
	}
	
	//────────────────────────────getUinfoUpdate───────────────────────────────//
	@RequestMapping(value = "/getUinfoUpdate")
	public NexacroResult getUinfoUpdate(
			@ParamDataSet(name = "ds_manufacture", required = false) Map<String, Object> searchMap
			) throws Exception {
		int update = medicalService.getUinfoUpdate(searchMap);
		NexacroResult result = new NexacroResult();
		return result;
	}
	
	
}
