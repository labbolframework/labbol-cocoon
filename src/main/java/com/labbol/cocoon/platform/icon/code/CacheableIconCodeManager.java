package com.labbol.cocoon.platform.icon.code;

import org.yelong.core.cache.CacheManager;

import com.labbol.cocoon.platform.support.AbstractCacheableCodeManager;
import com.labbol.core.platform.icon.manage.CacheableIconManager;
import com.labbol.core.platform.icon.manage.IconManager;

/**
 * 可缓存的图标代码管理器
 * 
 * @since 2.0
 */
public class CacheableIconCodeManager extends AbstractCacheableCodeManager implements IconCodeManager {

	protected final IconCodeManager iconCodeManager;

	public CacheableIconCodeManager(IconCodeManager iconCodeManager) {
		this.iconCodeManager = iconCodeManager;
	}

	@Override
	public String getCSSCode() throws IconCodeManageException {
		return putCacheObjIfAbsentAndSupportCache("ICON_CSS_CODE", x -> iconCodeManager.getCSSCode());
	}

	@Override
	public String getExtJSCode() throws IconCodeManageException {
		return putCacheObjIfAbsentAndSupportCache("ICON_EXTJS_CODE", x -> iconCodeManager.getExtJSCode());
	}

	@Override
	public IconManager getIconManager() {
		return iconCodeManager.getIconManager();
	}

	@Override
	protected CacheManager getCacheManager() {
		IconManager iconManager = getIconManager();
		if (iconManager instanceof CacheableIconManager) {
			return ((CacheableIconManager) iconManager).getCacheManager();
		}
		return null;
	}

}
