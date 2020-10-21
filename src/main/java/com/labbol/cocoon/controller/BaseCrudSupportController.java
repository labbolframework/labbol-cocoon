/**
 * 
 */
package com.labbol.cocoon.controller;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.yelong.core.jdbc.sql.sort.support.Sort;
import org.yelong.core.model.Modelable;
import org.yelong.core.model.sql.SqlModel;

import com.github.pagehelper.PageInfo;
import com.labbol.core.queryinfo.QuerySortInfo;
import com.labbol.core.queryinfo.filter.QueryFilterInfo;
import com.labbol.core.queryinfo.sort.QuerySortInfos;

import dream.first.base.model.DreamFirstBaseModelable;

/**
 * @author PengFei
 */
public abstract class BaseCrudSupportController<M extends DreamFirstBaseModelable> extends BaseCocoonCrudController<M> {

	@Override
	protected void saveModel(M model) throws Exception {
		modelService.saveSelective(model);
	}

	@Override
	protected void modifyModel(M model) throws Exception {
		modelService.modifySelectiveById(model);
	}

	@Override
	protected boolean isNew(M model) {
		return StringUtils.isBlank(model.getId());
	}

	@Override
	protected boolean deleteModel(String deleteIds) throws Exception {
		return modelService.removeByIds(getModelClass(), deleteIds.split(",")) > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected final PageInfo<?> queryModel(M model, List<QueryFilterInfo> queryFilterInfos,
			List<QuerySortInfo> querySortInfos, Integer pageNum, Integer pageSize) throws Exception {
		SqlModel<M> sqlModel;
		// 如果bind的model是SqlModel的子类则直接使用，否则实例化SqlModel
		if (model instanceof SqlModel) {
			sqlModel = (SqlModel<M>) model;
		} else {
			sqlModel = new SqlModel<>(model);
		}
		if (CollectionUtils.isNotEmpty(querySortInfos)) {
			List<Sort> sorts = QuerySortInfos.toSort(querySortInfos);
			if (model instanceof SqlModel) {
				sorts.forEach(x -> addModelSortField(model, x.getColumn(), x.getDirection()));
			} else {
				sorts.forEach(x -> addModelSortField(sqlModel, x.getColumn(), x.getDirection()));
			}
		}
		if (CollectionUtils.isNotEmpty(queryFilterInfos)) {
			sqlModel.addConditions(modelService.getQueryFilterInfoResolver().resolve(queryFilterInfos));
		}
		if (model instanceof SqlModel) {
			return queryModel(model, pageNum, pageSize);
		} else {
			return queryModel(sqlModel, pageNum, pageSize);
		}
	}

	/**
	 * 添加model 的排序字段 重写此方法实现不同的字段添加别名等功能
	 * 
	 * @param <M>       model type
	 * @param model     model
	 * @param sortField 排序字段
	 * @param sortOrder 排序方向
	 * @since 1.0.7
	 * @deprecated model不一定是 SqlModel
	 * @see #addModelSortField(SqlModel, String, String)
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	protected void addModelSortField(M model, String sortField, String sortOrder) {
		if (model instanceof SqlModel) {
			addModelSortField((SqlModel<M>) model, sortField, sortOrder);
		} else {
			throw new RuntimeException(model + "不是SQLModel。不支持添加排序条件");
		}
	}

	/**
	 * 添加SqlModel的排序字段。重写此方法可以实现不同的字段添加别名等功能
	 * 
	 * @param sqlModel  SqlModel
	 * @param sortField 排序字典
	 * @param sortOrder 排序方向
	 */
	protected void addModelSortField(SqlModel<M> sqlModel, String sortField, String sortOrder) {
		sqlModel.addSortField(sortField, sortOrder);
	}

	/**
	 * 重写此方法，覆盖查询功能
	 * 
	 * @param model    sqlModel
	 * @param pageNum  页码
	 * @param pageSize 页面大小
	 * @return 查询信息
	 * @deprecated model不一定为 SqlModel。
	 *             {@link #queryModel(SqlModel, Integer, Integer)}
	 * @see #queryModel(SqlModel, Integer, Integer)
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	@Deprecated
	protected PageInfo<?> queryModel(M model, Integer pageNum, Integer pageSize) throws Exception {
//		Class<? extends BaseModel> c = model.getClass();
//		return new PageInfo<>(modelService.findPageBySqlModel(c,model, pageNum, pageSize));
		if (model instanceof SqlModel) {
			return queryModel((SqlModel<M>) model, pageNum, pageSize);
		} else {
			return queryModel(new SqlModel<M>(model), pageNum, pageSize);
		}
	}

	/**
	 * 重写此方法，覆盖查询功能
	 * 
	 * @param sqlModel SqlModel
	 * @param pageNum  页码
	 * @param pageSize 页面大小
	 * @return 查询分页信息
	 * @throws Exception 查询异常
	 */
	protected PageInfo<?> queryModel(SqlModel<M> sqlModel, Integer pageNum, Integer pageSize) throws Exception {
		Class<? extends Modelable> modelClass = getModelClass();
		return new PageInfo<>(modelService.findPageBySqlModel(modelClass, sqlModel, pageNum, pageSize));
	}

	@Override
	protected M retrieveModel(M model) throws Exception {
		SqlModel<M> sqlModel = new SqlModel<>(model);
		Class<M> modelClass = getModelClass();
		return modelService.findFirstBySqlModel(modelClass, sqlModel);
	}

	@SuppressWarnings("unchecked")
	protected Class<M> getModelClass() {
		Map<TypeVariable<?>, Type> typeArguments = TypeUtils.getTypeArguments(getClass(),
				BaseCrudSupportController.class);
		if (MapUtils.isNotEmpty(typeArguments)) {
			for (Entry<TypeVariable<?>, Type> entry : typeArguments.entrySet()) {
				if (entry.getKey().getName().contentEquals("M")) {
					return (Class<M>) entry.getValue();
				}
			}
		}
		throw new RuntimeException("未发现泛型model");
	}

}
