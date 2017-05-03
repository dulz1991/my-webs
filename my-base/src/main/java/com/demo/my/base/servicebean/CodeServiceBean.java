package com.demo.my.base.servicebean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.my.base.bean.Code;
import com.demo.my.base.bean.CodeMenu;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.mybatis.mapper.ds1mapper.CodeMapper;
import com.demo.my.base.mybatis.mapper.ds1mapper.CodeMenuMapper;
import com.demo.my.base.mybatis.mapper.ds1mapper.CodeSubMenuMapper;
import com.demo.my.base.redis.RedisService;
import com.demo.my.base.service.AbstractService;

@Service("codeServiceBean")  
public class CodeServiceBean extends AbstractService {
	
	@Autowired
	private CodeMapper codeMapper;
	
	@Autowired
	private CodeMenuMapper codeMenuMapper;
	
	@Autowired
	private CodeSubMenuMapper codeSubMenuMapper;
	
	@Autowired
	@Qualifier("redisService")
	private RedisService redisService;

	/*以下是code部分*/
	public void insert(Code code) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("fatherId", code.getFatherId());
		List<Code> codes = super.getBeanListByParm(null, parmMap, KeyConstant.MAPPER_CODE);
		if(codes.isEmpty()){
			code.setItemOrder(1L);
		} else {
			int index = codes.size() - 1;
			Long itemOrder = codes.get(index).getItemOrder();
			code.setItemOrder(itemOrder + 1);
		}
		codeMapper.insert(code);
	}

	public Map<String, Object> doMove(Long id, String type) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Code code = (Code) super.getById(id, KeyConstant.MAPPER_CODE);
		if (code == null) {
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.TIP_NOT_EXIST);
		}
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("fatherId", code.getFatherId());
		parmMap.put("codeId", code.getCodeId());
		List<Code> codes = super.getBeanListByParm(null, parmMap, KeyConstant.MAPPER_CODE);
		Code codeUp = new Code();
		Code codeDown = new Code();
		int size = codes.size();
		for (int i = 0; i<size; i++) {
			if (codes.get(i).getId().equals(id)) {
				if (type.equals("up")) {
					if (i == 0) {
						return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_MOVE_FIRST);
					}
					codeUp.setId(codes.get(i).getId());
					codeUp.setItemOrder(codes.get(i-1).getItemOrder());
					codeUp.setCodeId(codes.get(i).getCodeId());
					codeUp.setFatherId(codes.get(i).getFatherId());
					codeDown.setId(codes.get(i-1).getId());
					codeDown.setItemOrder(codes.get(i).getItemOrder());
					codeDown.setCodeId(codes.get(i-1).getCodeId());
					codeDown.setFatherId(codes.get(i-1).getFatherId());
				} else if (type.equals("down")) {
					if ((i + 1) == size) {
						return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_MOVE_LAST);
					}
					codeDown.setId(codes.get(i).getId());
					codeDown.setItemOrder(codes.get(i+1).getItemOrder());
					codeDown.setCodeId(codes.get(i).getCodeId());
					codeDown.setFatherId(codes.get(i).getFatherId());
					codeUp.setId(codes.get(i+1).getId());
					codeUp.setItemOrder(codes.get(i).getItemOrder());
					codeUp.setCodeId(codes.get(i+1).getCodeId());
					codeUp.setFatherId(codes.get(i+1).getFatherId());
				}
				super.update(codeUp, KeyConstant.MAPPER_CODE);
				super.update(codeDown, KeyConstant.MAPPER_CODE);
				break;
			}
		}
		resMap.put(ErrorConstant.ERROR_NO, ErrorConstant.ERROR_200);
		return resMap;
	}
	
	/*以下是code menu部分*/
	public Map<String, Object> doMenuMove(Long menuId, String type) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		CodeMenu codeMenu = (CodeMenu) super.getById(menuId, KeyConstant.MAPPER_CODE_MENU);
		if (codeMenu == null) {
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.TIP_NOT_EXIST);
		}
		List<CodeMenu> codeMenus = this.getBeanListByParm(null, null, KeyConstant.MAPPER_CODE_MENU);
		CodeMenu codeMenuUp = new CodeMenu();
		CodeMenu codeMenuDown = new CodeMenu();
		int size = codeMenus.size();
		for (int i = 0; i<size; i++) {
			if (codeMenus.get(i).getId().equals(menuId)) {
				if (type.equals("up")) {
					if (i == 0) {
						return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_MOVE_FIRST);
					}
					codeMenuUp.setId(codeMenus.get(i).getId());
					codeMenuUp.setName(codeMenus.get(i).getName());
					codeMenuUp.setOrderBy(codeMenus.get(i-1).getOrderBy());
					codeMenuDown.setId(codeMenus.get(i-1).getId());
					codeMenuDown.setName(codeMenus.get(i-1).getName());
					codeMenuDown.setOrderBy(codeMenus.get(i).getOrderBy());
				} else if (type.equals("down")) {
					if ((i + 1) == size) {
						return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_MOVE_LAST);
					}
					codeMenuDown.setId(codeMenus.get(i).getId());
					codeMenuDown.setName(codeMenus.get(i).getName());
					codeMenuDown.setOrderBy(codeMenus.get(i+1).getOrderBy());
					codeMenuUp.setId(codeMenus.get(i+1).getId());
					codeMenuUp.setName(codeMenus.get(i+1).getName());
					codeMenuUp.setOrderBy(codeMenus.get(i).getOrderBy());
				}
				super.update(codeMenuUp, KeyConstant.MAPPER_CODE_MENU);
				super.update(codeMenuDown, KeyConstant.MAPPER_CODE_MENU);
				break;
			}
		}
		resMap.put(ErrorConstant.ERROR_NO, ErrorConstant.ERROR_200);
		return resMap;
	}
	
}
