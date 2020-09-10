/**
 * 
 */
package com.labbol.cocoon.extjs;

import java.util.LinkedHashMap;
import java.util.List;

import org.yelong.commons.util.map.MapWrapper;

/**
 * extjs store tree 的数据支持
 * 
 * @author PengFei
 * @since 1.0.7
 * @see TreeStoreData
 */
@Deprecated
public class StoreTreeData<T> extends MapWrapper<String, Object> {

	public static final String ID_NAEM = "id";

	public static final String TEXT_NAEM = "text";

	public static final String ICONCLS_NAEM = "iconCls";

	public static final String CHECKED_NAEM = "checked";

	public static final String EXPANDED_NAEM = "expanded";

	public static final String CHILDREN_NAME = "children";

	public static final String LEAF_NAEM = "leaf";

	private transient final T data;

	public StoreTreeData() {
		this(null);
	}

	public StoreTreeData(T data) {
		super(new LinkedHashMap<String, Object>());
		this.data = data;
	}

	public String getId() {
		return (String) get(ID_NAEM);
	}

	public void setId(String id) {
		put(ID_NAEM, id);
	}

	public String getText() {
		return (String) get(TEXT_NAEM);
	}

	public void setText(String text) {
		put(TEXT_NAEM, text);
	}

	public String getIconCls() {
		return (String) get(ICONCLS_NAEM);
	}

	public void setIconCls(String iconCls) {
		put(ICONCLS_NAEM, iconCls);
	}

	public Boolean getChecked() {
		return (Boolean) get(CHECKED_NAEM);
	}

	public void setChecked(Boolean checked) {
		put(CHECKED_NAEM, checked);
	}

	public Boolean getExpanded() {
		return (Boolean) get(EXPANDED_NAEM);
	}

	public void setExpanded(Boolean expanded) {
		put(EXPANDED_NAEM, expanded);
	}

	@SuppressWarnings("unchecked")
	public List<StoreTreeData<T>> getChildrens() {
		return (List<StoreTreeData<T>>) get(CHILDREN_NAME);
	}

	public void setChildrens(List<StoreTreeData<T>> childrens) {
		put(CHILDREN_NAME, childrens);
	}

	/**
	 * 添加一个子
	 * 
	 * @param children 子节点
	 */
	public void addChildren(StoreTreeData<T> children) {
		getChildrens().add(children);
	}

	public Boolean getLeaf() {
		return (Boolean) get(LEAF_NAEM);
	}

	public void setLeaf(Boolean leaf) {
		put(LEAF_NAEM, leaf);
	}

	/**
	 * 添加一个属性
	 * 
	 * @param name  属性名称
	 * @param value 属性值
	 * @see #put(String, Object)
	 */
	@Deprecated
	public void addAttribute(String name, Object value) {
		put(name, value);
	}

	/**
	 * 添加一个属性 与 {@link #addAttribute(String, Object)}相同。使用方便
	 * 
	 * @param name  属性名称
	 * @param value 属性值
	 * @see #put(String, Object)
	 */
	@Deprecated
	public void setAttribute(String name, Object value) {
		put(name, value);
	}

	public T getData() {
		return data;
	}

}
