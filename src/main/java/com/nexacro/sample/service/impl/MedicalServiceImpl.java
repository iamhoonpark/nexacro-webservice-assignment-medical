package com.nexacro.sample.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexacro.sample.service.MedicalService;
import com.nexacro.sample.service.impl.ibatis.MedicalDAO;

@Service("medicalService")
public class MedicalServiceImpl implements MedicalService {
	
	//DI
	@Autowired
	@Resource(name = "medicalDAO")
	private MedicalDAO medicalDAO;
	
	//──────────────────────────────ApprovalList───────────────────────────────//
	@Override
	public List<Map<String, Object>> selectListMap(Map<String, Object> searchMap) throws Exception {
		return medicalDAO.selectListMap(searchMap);
	}
	
	@Override
	public int updateAccynMap(Map<String, Object> searchMap) throws Exception {
		return medicalDAO.updateAccynMap(searchMap);
	}
	
	//────────────────────────────InsertCommonMedical──────────────────────────//
	@Override
	public void insertCommonMedical(Map<String, Object> searchMap) throws Exception {
		medicalDAO.insertCommonMedical(searchMap);
	}
	
	//────────────────────────────getAccnoInfo─────────────────────────────────//
	@Override
	public Map<String, Object> getAccnoInfo(String accno) throws Exception {
		return medicalDAO.getAccnoInfo(accno);
	}
	
	//────────────────────────────getUinfoUpdate───────────────────────────────//
	@Override
	public int getUinfoUpdate(Map<String, Object> searchMap) throws Exception {
		return medicalDAO.getUinfoUpdate(searchMap);
	}
	
}
