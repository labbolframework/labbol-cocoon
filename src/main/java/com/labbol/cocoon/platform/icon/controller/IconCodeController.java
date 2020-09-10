/**
 * 
 */
package com.labbol.cocoon.platform.icon.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labbol.cocoon.platform.icon.code.IconCodeManager;
import com.labbol.core.check.login.LoginValidate;
import com.labbol.core.controller.BaseCoreController;

/**
 * 获取图标CSS、ExtJS
 * 
 * @since 1.0
 */
@Controller
@LoginValidate(validate = false)
public class IconCodeController extends BaseCoreController {

	@Resource
	private IconCodeManager iconCodeManager;

	/**
	 * @return 图标ExtJS
	 * @throws Exception 代码生成失败
	 */
	@ResponseBody
	@RequestMapping(value = "icon/icon")
	public String icon() throws Exception {
		return iconCodeManager.getExtJSCode();
	}

	/**
	 * @return 图标CSS
	 * @throws Exception 代码生成失败
	 */
	@ResponseBody
	@RequestMapping(value = "icon/iconCss")
	public String iconCss() throws Exception {
		return iconCodeManager.getCSSCode();
	}

}
