/**
 * 
 */
package com.labbol.cocoon.controller;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.yelong.commons.io.FileUtilsE;
import org.yelong.core.annotation.Nullable;

import com.labbol.cocoon.msg.JsonMsg;
import com.labbol.core.model.BaseFileModel;

/**
 * @author PengFei
 */
@Deprecated
public abstract class BaseFileController<M extends BaseFileModel<M>> extends BaseCrudSupportController<M> {

	/**
	 * 文件上传
	 * 
	 * @deprecated
	 * @see #getMultipartFile(String)
	 */
	@ResponseBody
	@RequestMapping(value = "upload")
	public Object upload(@ModelAttribute M model, MultipartFile file) throws Exception {
		JsonMsg msg = new JsonMsg(true, "保存成功");
		File newFile = null;
		if (null != file) {
			String fileName = file.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			newFile = new File(getSaveFilePath(model, fileName, fileType));
			file.transferTo(newFile);
			model.setFilePath(newFile.getAbsolutePath());
			model.setFileName(fileName);
			model.setFileType(fileType);
			model.setFileSize(Long.valueOf(newFile.length()));
		}
		boolean validateUpload = validateUpload(model, newFile, msg);
		if (!validateUpload) {
			throw new Exception(msg.getMsg());
		}
		beforeUpload(model, newFile);
		saveModel(model);
		afterUpload(model, newFile);
		return toJson(msg);
	}

	/**
	 * 文件下载
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("download")
	public void download(@ModelAttribute M model) throws Exception {
		String id = model.getId();
		if (StringUtils.isEmpty(id)) {
			throw new Exception("不存在的记录");
		}
		model = (M) modelService.findFirstBySqlModel(model.getClass(), model);
		if (null == model) {
			throw new Exception("不存在的记录");
		}
		String filePath = model.getFilePath();
		FileUtilsE.requireNonExist(filePath, filePath + "文件不存在");
		responseFile(new File(filePath), model.getFileName());
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("existFile")
	public Object existFile(@ModelAttribute M model) throws Exception {
		JsonMsg msg = new JsonMsg(false, "文件不存在");
		model = (M) modelService.findFirstBySqlModel(model.getClass(), model);
		if (null != model) {
			String filePath = model.getFilePath();
			if (StringUtils.isNotBlank(filePath) || FileUtilsE.exists(model.getFilePath())) {
				msg.setSuccess(true);
				msg.setMsg("文件存在");
			}
		}
		return toJson(msg);
	}

	/**
	 * 文件上传验证
	 */
	protected boolean validateUpload(M model, @Nullable File file, JsonMsg msg) {
		return true;
	}

	/**
	 * 上传之前
	 */
	protected void beforeUpload(M model, @Nullable File file) {

	}

	/**
	 * 上传之后
	 */
	protected void afterUpload(M model, @Nullable File file) {

	}

	/**
	 * 获取保存文件路径 此方法仅会在存在文件时被调用
	 * 
	 * @param model
	 * @param fileName 文件名称
	 * @param fileType 文件类型
	 * @return 文件保存路径
	 */
	protected abstract String getSaveFilePath(M model, @Nullable String fileName, @Nullable String fileType);

}
