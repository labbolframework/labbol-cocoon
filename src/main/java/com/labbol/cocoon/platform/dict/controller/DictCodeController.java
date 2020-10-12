/**
 * 
 */
package com.labbol.cocoon.platform.dict.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labbol.cocoon.platform.dict.code.DictCodeManager;
import com.labbol.core.check.login.LoginValidate;
import com.labbol.core.controller.BaseCoreController;

/**
 * 字典模板控制器
 * 
 * @since 1.0
 */
@LoginValidate(validate = false)
@Controller
public class DictCodeController extends BaseCoreController {

	@Resource
	private DictCodeManager dictExtjsManager;

	/**
	 * 获取字典JS
	 * 
	 * @return 字典JS
	 * @throws Exception 生成字典JS异常
	 */
	@ResponseBody
	@RequestMapping(value = "dict/dict")
	public String dict() throws Exception {
		String dictTypes = getRequest().getParameter("dictTypes");
		if (StringUtils.isBlank(dictTypes)) {
			return dictExtjsManager.getExtJSCode();
		} else {
			return dictExtjsManager.getExtJSCode(dictTypes.split(","));
		}
	}

}
