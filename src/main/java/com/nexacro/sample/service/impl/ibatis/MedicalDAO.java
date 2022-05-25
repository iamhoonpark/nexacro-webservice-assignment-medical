package com.nexacro.sample.service.impl.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nexacro.spring.dao.ibatis.NexacroIbatisAbstractDAO;

@Repository("medicalDAO")
public class MedicalDAO extends NexacroIbatisAbstractDAO {
	
	//──────────────────────────────ApprovalList───────────────────────────────//
	public List<Map<String, Object>> selectListMap(Map<String, Object> searchMap) {
		return (List<Map<String, Object>>) list("nexacroMedical.selectListMap", searchMap);
	}
	
	public int updateAccynMap(Map<String, Object> searchMap) {		
		return  update("nexacroMedical.updateAccynMap", searchMap);
	}
	
	//────────────────────────────InsertCommonMedical──────────────────────────//
	public void insertCommonMedical(Map<String, Object> searchMap) {
		insert("nexacroMedical.insertCommonMedical", searchMap);
	}
	
	//────────────────────────────getAccnoInfo─────────────────────────────────//
	public Map<String, Object> getAccnoInfo(String accno) {
		return (Map<String, Object>) select("nexacroMedical.getAccnoInfo", accno) ;
	}
	
	//────────────────────────────getUinfoUpdate───────────────────────────────//
	public int getUinfoUpdate(Map<String, Object> searchMap) {
		return update("nexacroMedical.getUinfoUpdate", searchMap);
	}
	
}
