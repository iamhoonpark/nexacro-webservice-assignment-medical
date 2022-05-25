package com.nexacro.sample.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nexacro.sample.service.AdminService;
import com.nexacro.sample.vo.DetailCDVO;
import com.nexacro.uiadapter17.spring.core.annotation.ParamDataSet;
import com.nexacro.uiadapter17.spring.core.annotation.ParamVariable;
import com.nexacro.uiadapter17.spring.core.data.NexacroResult;


/**
 * <pre>
 * 
 * @title
 * @desc 관리자 공통코드 처리. - Controller Login Class
 * @package com.nexacro.sample.web
 * 
 *          <pre>
 * @author 이미혜
 * @since 2019. 9. 16.
 * @version 1.0
 * 
 * 
 */

@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	@Resource(name = "adminService")
	private AdminService adminService;

	@Resource
	private Validator validator;
	// 유효성 검사?

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(this.validator);
	};
	

	/*********************************************그룹코드**************************************************************/
	@RequestMapping(value = "/comcodelist.do")
	public NexacroResult selectMap(@ParamDataSet(name = "ds_Search", required = false) Map<String, Object> searchMap) {
		List<Map<String, Object>> list = adminService.selectCDListMap(searchMap);
		NexacroResult result = new NexacroResult();
		//System.out.println(list.size());
		result.addDataSet("ds_GroupCD", list);
		return result;
	};

	@RequestMapping(value = "/GruopCDupdateMap.do")
	public NexacroResult GruopInsert(@ParamDataSet(name = "ds_ResultGroupCD", required = false) List<Map<String, Object>> groupUpdate) {
		adminService.GcodeUpdateMap(groupUpdate);
		NexacroResult result = new NexacroResult();
		return result;
	};
	
	/********************************************상세코드**************************************************************/
	@RequestMapping(value="/detailCDlist.do")
	public NexacroResult DetailCDListVO(@ParamVariable(name="group_code",required=false)String group_code){
		System.out.println("controller send=========================="+group_code);
		List<DetailCDVO> selectDListVO=adminService.DetailCDVO(group_code);
		NexacroResult result = new NexacroResult();
		result.addDataSet("ds_ResultDetailCD",selectDListVO);
		return result;
	};
	
	@RequestMapping(value = "/DetailCDupdateMap.do")
	public NexacroResult DetailInsert(@ParamDataSet(name = "ds_ResultDetailCD", required = false)List<Map<String, Object>> DetailUpdate) {
	
		adminService.DetailUpdate(DetailUpdate);
		NexacroResult result = new NexacroResult();	
	
		return result;
	};
	

	/********************************************의료기기리스트************************************************************/
	//의료기기 리스트 출력을 위한 컨트롤러, inData가 없기 때문에 인자가 없음	
	//검색조건의 값을 받아오기에 인자 추가 후 메서드에 Map객체 삭제
	@RequestMapping(value="/medicalDeviceList.do")
	public NexacroResult medicalDeviceList(@ParamDataSet(name="ds_MdSearch", required=false) Map<String, Object> searchMap) {
		
		//그래서 해당 Map객체를 List 컬렉션, 인자 역시 비어있는 값
		List<Map<String, Object>> list = adminService.selectMDList(searchMap);
		
		//데이터 넘어오는지 확인
		System.out.println(searchMap);
		
		//NexacroResult객체 선언 및 초기화
		NexacroResult result = new NexacroResult();
		
		//selectList가 담긴 변수 list를 Key값으로 세팅하여 처리결과로 넥사크로에 보냄
		result.addDataSet("ds_ResultMedicalDevices",list);
		
		//리턴
		return result;
	};
	
	/* 
	@RequestMapping(value="/mdListFromAccyn.do")
	public NexacroResult mdListFromAccyn(@ParamDataSet(name="accynNmb", required=false) Map<String, Object> searchMap) {
		System.out.println("accyn값: "+searchMap);
		List<Map<String,Object>> list = adminService.selectmdListFromAccyn(searchMap);
		NexacroResult result = new NexacroResult();
		result.addDataSet("ds_ResultMedicalDevices", list);
		return result;
	}*/


}
