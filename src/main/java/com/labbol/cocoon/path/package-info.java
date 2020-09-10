
/**
 * static resources path 静态资源路径
 * 
 * application.yml 配置属性：
 * 属性均以{@link org.yelong.labbol.cocoon.Cocoon#COCOON_PROPERTIES_PREFIX}为前缀
 * 
 * 1、staticResourcesPathMode 静态资源路径模式，根据不同的模式来配置静态资源的路径
 * 		值：a、"default" : 根据配置文件的设置的路径（默认值）
 * 			b、 "database" : 根据数据库进行配置
 * 
 * 2、moduleServiceName 模块服务名称。仅在staticResourcesPathMode = database 时有效，设置从数据库获取的模块名称
 * 
 * 3、staticResourcesRootPath 静态资源根路径。尽在staticResourcesPathMode = default或者为空时有效，直接设置静态资源的根路径
 * 
 * 
 */
package com.labbol.cocoon.path;