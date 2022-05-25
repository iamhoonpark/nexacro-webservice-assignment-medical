package com.nexacro.sample.service;

import java.util.List;
import java.util.Map;

public interface MedicalService {
	
	//──────────────────────────────ApprovalList───────────────────────────────//
	List<Map<String, Object>> selectListMap(Map<String, Object> searchMap) throws Exception;
	int updateAccynMap(Map<String, Object> searchMap) throws Exception;
	
	//────────────────────────────InsertCommonMedical──────────────────────────//
	void insertCommonMedical(Map<String, Object> searchMap) throws Exception;
	
	//────────────────────────────getAccnoInfo─────────────────────────────────//
	Map<String, Object> getAccnoInfo(String accno) throws Exception;
	
	//────────────────────────────getUinfoUpdate───────────────────────────────//
	int getUinfoUpdate(Map<String, Object> searchMap) throws Exception;
	
}
