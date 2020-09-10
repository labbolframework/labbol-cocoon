/**
 * 
 */
package com.labbol.cocoon.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import com.labbol.cocoon.Cocoon;
import com.labbol.cocoon.platform.dict.controller.DictCodeController;
import com.labbol.cocoon.platform.icon.controller.IconCodeController;
import com.labbol.cocoon.platform.service.controller.ModuleServiceInterfaceCodeController;

/**
 * 代码控制器自动配置
 * 
 * @since 1.0.0
 */
@ConditionalOnProperty(prefix = Cocoon.COCOON_PROPERTIES_PREFIX, name = "codeController", havingValue = "true", matchIfMissing = false)
public class CodeControllerAutoConfiguration {

	/**
	 * @return 模块服务代码控制器
	 */
	@Bean
	public ModuleServiceInterfaceCodeController moduleServiceInterfaceCodeController() {
		return new ModuleServiceInterfaceCodeController();
	}

	/**
	 * @return 字典代码控制器
	 */
	@Bean
	public DictCodeController dictCodeController() {
		return new DictCodeController();
	}

	/**
	 * @return 图标代码控制器
	 */
	@Bean
	public IconCodeController iconCodeController() {
		return new IconCodeController();
	}

}
