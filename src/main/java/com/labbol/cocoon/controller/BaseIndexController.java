/**
 * 
 */
package com.labbol.cocoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author PengFei
 */
@Controller
@Deprecated
public abstract class BaseIndexController extends BaseCocoonController {

	/**
	 * 设置token到cookie中。 所有跳转页面的均重新设置token
	 */
	@ModelAttribute
	public void setTokenCookie() {
		// super.setTokenCookie();
	}

}
