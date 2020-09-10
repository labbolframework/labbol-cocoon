/**
 * 
 */
package com.labbol.cocoon.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import com.labbol.cocoon.Cocoon;
import com.labbol.cocoon.exception.CocoonExceptionResolver;
import com.labbol.cocoon.login.LoginInterceptor;
import com.labbol.cocoon.login.TestLoginCheckInterceptor;
import com.labbol.cocoon.path.ModuleServiceSetServletContextListener;
import com.labbol.cocoon.path.ProjectPathSetServletContextListener;
import com.labbol.cocoon.platform.service.servlet.DefaultServletContextSetModuleService;
import com.labbol.cocoon.platform.service.servlet.ServletContextSetModuleService;
import com.labbol.core.Labbol;
import com.labbol.core.platform.service.manage.ModuleServiceManager;

/**
 * 配置平台 bean
 * 
 * @since 1.0.0
 */
public class CocoonConfiguration {

	/**
	 * @return 默认的异常处理器
	 */
	@Bean
	@ConditionalOnMissingBean(CocoonExceptionResolver.class)
	public CocoonExceptionResolver defaultExceptionResolver() {
		return new CocoonExceptionResolver();
	}

	/**
	 * @return servlet 上下文设置模块服务
	 */
	@Bean
	@ConditionalOnMissingBean(ServletContextSetModuleService.class)
	public ServletContextSetModuleService ServletContextSetModuleService(ModuleServiceManager moduleServiceManager) {
		return new DefaultServletContextSetModuleService(moduleServiceManager);
	}

	/**
	 * @return 模块服务设置Servlet上下文监听
	 */
	@Bean
	@ConditionalOnProperty(prefix = Cocoon.COCOON_PROPERTIES_PREFIX, name = "moduleServiceSetListener", havingValue = "true", matchIfMissing = false)
	@ConditionalOnMissingBean(ModuleServiceSetServletContextListener.class)
	public ModuleServiceSetServletContextListener moduleServiceSetServletContextListener() {
		return new ModuleServiceSetServletContextListener();
	}

	/**
	 * @return 项目路径设置Servlet上下文监听
	 */
	@Bean
	@ConditionalOnProperty(prefix = Cocoon.COCOON_PROPERTIES_PREFIX, name = "projectPathSetListener", havingValue = "true", matchIfMissing = false)
	@ConditionalOnMissingBean(ProjectPathSetServletContextListener.class)
	public ProjectPathSetServletContextListener projectPathSetServletContextListener() {
		return new ProjectPathSetServletContextListener();
	}

	/**
	 * 登录session会话验证模式
	 * 
	 * @return session拦截器
	 */
	@Bean
	@ConditionalOnProperty(prefix = Labbol.LABBOL_PROPERTIES_PREFIX, name = "loginMode", havingValue = "session", matchIfMissing = false)
	@Order(10000)
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	/**
	 * @return 开发人员开发时的模式。取消登录验证等
	 */
	@Bean
	@ConditionalOnProperty(prefix = Labbol.LABBOL_PROPERTIES_PREFIX, name = "loginMode", havingValue = "test", matchIfMissing = false)
	@Order(10000)
	public TestLoginCheckInterceptor testLoginCheckInterceptor() {
		return new TestLoginCheckInterceptor();
	}

}
