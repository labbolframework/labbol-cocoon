/**
 * 
 */
package com.labbol.cocoon.scan;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.labbol.cocoon.exception.custom.CustomErrorController;
import com.labbol.cocoon.exception.custom.DefaultCustomErrorController;

/**
 * 
 * @since
 */
@Configuration
public class CocoonComponentScanConfiguration {

	@Bean
	@ConditionalOnMissingBean(value = CustomErrorController.class)
	public DefaultCustomErrorController customErrorController() {
		return new DefaultCustomErrorController();
	}

}
