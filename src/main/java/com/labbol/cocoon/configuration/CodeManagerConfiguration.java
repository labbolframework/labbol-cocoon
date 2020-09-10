package com.labbol.cocoon.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.labbol.cocoon.platform.dict.code.CacheableDictCodeManager;
import com.labbol.cocoon.platform.dict.code.DictCodeManager;
import com.labbol.cocoon.platform.dict.code.impl.DefaultDictCodeManager;
import com.labbol.cocoon.platform.icon.code.CacheableIconCodeManager;
import com.labbol.cocoon.platform.icon.code.IconCodeManager;
import com.labbol.cocoon.platform.icon.code.impl.DefaultIconCodeManager;
import com.labbol.cocoon.platform.service.code.CacheableModuleServiceInterfaceCodeManager;
import com.labbol.cocoon.platform.service.code.ModuleServiceInterfaceCodeManager;
import com.labbol.cocoon.platform.service.code.impl.DefaultModuleServiceInterfaceCodeManager;
import com.labbol.core.platform.dict.manage.DictManager;
import com.labbol.core.platform.icon.manage.IconManager;
import com.labbol.core.platform.service.manage.ModuleServiceManager;

public class CodeManagerConfiguration {

	// ==================================================字典==================================================

	/**
	 * 字典代码管理器
	 */
	@Bean
	@ConditionalOnMissingBean(DictCodeManager.class)
	public DefaultDictCodeManager defaultDictCodeManager(DictManager dictManager) {
		return new DefaultDictCodeManager(dictManager);
	}

	/**
	 * 可缓存的字典代码管理器
	 */
	@Bean
	@Primary
	@ConditionalOnMissingBean(CacheableDictCodeManager.class)
	public CacheableDictCodeManager cacheableDictCodeManager(DictCodeManager dictCodeManager) {
		return new CacheableDictCodeManager(dictCodeManager);
	}

	// ==================================================图标==================================================

	/**
	 * 图标代码管理器
	 */
	@Bean
	@ConditionalOnMissingBean(IconCodeManager.class)
	public DefaultIconCodeManager defaultIconCodeManager(IconManager iconManager) {
		return new DefaultIconCodeManager(iconManager);
	}

	/**
	 * 可缓存的图标代码管理器
	 */
	@Bean
	@Primary
	@ConditionalOnMissingBean(CacheableIconCodeManager.class)
	public CacheableIconCodeManager cacheableIconCodeManager(IconCodeManager iconCodeManager) {
		return new CacheableIconCodeManager(iconCodeManager);
	}

	// ==================================================模块服务==================================================

	/**
	 * 模块服务接口代码管理器
	 */
	@Bean
	@ConditionalOnMissingBean(ModuleServiceInterfaceCodeManager.class)
	public DefaultModuleServiceInterfaceCodeManager defaultModuleServiceInterfaceCodeManager(
			ModuleServiceManager moduleServiceManager) {
		return new DefaultModuleServiceInterfaceCodeManager(moduleServiceManager);
	}

	/**
	 * 可缓存的模块服务接口代码管理器
	 */
	@Bean
	@Primary
	@ConditionalOnMissingBean(CacheableModuleServiceInterfaceCodeManager.class)
	public CacheableModuleServiceInterfaceCodeManager cacheableModuleServiceInterfaceCodeManager(
			ModuleServiceInterfaceCodeManager moduleServiceInterfaceCodeManager) {
		return new CacheableModuleServiceInterfaceCodeManager(moduleServiceInterfaceCodeManager);
	}

}
