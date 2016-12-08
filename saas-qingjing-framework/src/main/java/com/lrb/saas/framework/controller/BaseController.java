package com.lrb.saas.framework.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.Entity;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.lrb.saas.core.annotation.SAASInterface;
import com.lrb.saas.core.message.request.SAASRequest;
import com.lrb.saas.core.message.request.query.SAASQueryRequest;
import com.lrb.saas.core.message.response.InvalidInfo;
import com.lrb.saas.core.message.response.SAASQueryResponse;
import com.lrb.saas.core.message.response.SAASResponse;
import com.lrb.saas.core.message.response.SAASResponse.Result;
import com.lrb.saas.core.model.Standard;
import com.lrb.saas.core.util.GenericsUtils;
import com.lrb.saas.core.validation.group.DELETE;
import com.lrb.saas.core.validation.group.PATCH;
import com.lrb.saas.core.validation.group.POST;
import com.lrb.saas.core.validation.group.PUT;
import com.lrb.saas.framework.service.BaseService;
import com.lrb.saas.framework.util.BeanUtils;

public class BaseController<T> {

	BaseService<T> baseService;

	@Resource
	ApplicationContext ac;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostConstruct
	public void Init() {
		Class clazz = GenericsUtils.getSuperClassGenricType(this.getClass());
		if (clazz.isAnnotationPresent(Entity.class)) {
			if (clazz.isAnnotationPresent(Document.class)) {
				baseService = (BaseService<T>) ac.getBean("baseHibernateMongoServiceImpl");
			} else {
				baseService = (BaseService<T>) ac.getBean("baseHibernateServiceImpl");
			}
		} else {
			if (clazz.isAnnotationPresent(Document.class)) {
				baseService = (BaseService<T>) ac.getBean("baseMongoServiceImpl");
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// @GetMapping
	@SAASInterface(remark = "查询")
	public SAASResponse<T> get(String id) throws Exception {
		SAASResponse saasResponse = new SAASResponse<>();
		if (id == null) {
			saasResponse.setResult(Result.invalid);
			saasResponse.setInvalidInfos(new ArrayList<InvalidInfo>());
			InvalidInfo ii = new InvalidInfo();
			ii.setParameterName("id");
			ii.setMessage("id不能为空");
			saasResponse.getInvalidInfos().add(ii);
			return saasResponse;
		}
		T t = (T) baseService.getById(GenericsUtils.getSuperClassGenricType(this.getClass()), id);
		saasResponse.setBody(t);
		saasResponse.setMessage("查询成功");
		saasResponse.setResult(Result.success);
		return saasResponse;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SAASInterface(remark = "筛选")
	// @RequestMapping("query")
	public SAASQueryResponse<T> query(@RequestBody SAASRequest<SAASQueryRequest> request) throws Exception {
		SAASQueryResponse saasQueryResponse = new SAASQueryResponse<>();
		SAASQueryRequest queryRequest = request.getBody();
		if (queryRequest == null) {
			queryRequest = new SAASQueryRequest();
		}
		queryRequest.setClazz(GenericsUtils.getSuperClassGenricType(this.getClass()));
		saasQueryResponse.setBody(baseService.query(queryRequest));
		saasQueryResponse.setResult(Result.success);
		saasQueryResponse.setMessage("查询成功");
		return saasQueryResponse;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	// @PostMapping
	@SAASInterface(remark = "新增")
	public SAASResponse post(@Validated(POST.class) @RequestBody SAASRequest<T> request, BindingResult bindingResult)
			throws Exception {
		SAASResponse saasResponse = new SAASResponse<>();
		if (bindingResult.hasFieldErrors()) {
			saasResponse.setResult(Result.invalid);
			saasResponse.setInvalidInfos(bindingResult.getFieldErrors().stream().map((FieldError fe) -> {
				InvalidInfo invalidInfo = new InvalidInfo();
				invalidInfo.setParameterName(fe.getField());
				invalidInfo.setMessage(fe.getDefaultMessage());
				return invalidInfo;
			}).collect(Collectors.toList()));
			return saasResponse;
		}
		Standard.initEntity(request.getBody(), request);
		baseService.save(request.getBody());
		saasResponse.setResult(Result.success);
		saasResponse.setMessage("新增成功");
		return saasResponse;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	// @PutMapping
	@SAASInterface(remark = "完全修改")
	public SAASResponse put(@Validated(PUT.class) @RequestBody SAASRequest<T> request, BindingResult bindingResult)
			throws Exception {
		SAASResponse saasResponse = new SAASResponse<>();
		if (bindingResult.hasFieldErrors()) {
			saasResponse.setResult(Result.invalid);
			saasResponse.setInvalidInfos(bindingResult.getFieldErrors().stream().map((FieldError fe) -> {
				InvalidInfo invalidInfo = new InvalidInfo();
				invalidInfo.setParameterName(fe.getField());
				invalidInfo.setMessage(fe.getDefaultMessage());
				return invalidInfo;
			}).collect(Collectors.toList()));
			return saasResponse;
		}
		Standard.initEntity(request.getBody(), request);
		baseService.merge(request.getBody());
		saasResponse.setResult(Result.success);
		saasResponse.setMessage("修改成功");
		return saasResponse;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// @PatchMapping
	@SAASInterface(remark = "部分修改")
	public SAASResponse patch(@Validated(PATCH.class) @RequestBody SAASRequest<T> request, BindingResult bindingResult)
			throws Exception {
		SAASResponse saasResponse = new SAASResponse<>();
		if (bindingResult.hasFieldErrors()) {
			saasResponse.setResult(Result.invalid);
			saasResponse.setInvalidInfos(bindingResult.getFieldErrors().stream().map((FieldError fe) -> {
				InvalidInfo invalidInfo = new InvalidInfo();
				invalidInfo.setParameterName(fe.getField());
				invalidInfo.setMessage(fe.getDefaultMessage());
				return invalidInfo;
			}).collect(Collectors.toList()));
			return saasResponse;
		}
		Standard.initEntity(request.getBody(), request);
		T t = (T) baseService.getById(GenericsUtils.getSuperClassGenricType(this.getClass()),
				(String) FieldUtils.readField(request.getBody(), "id", true));
		if (t != null) {
			BeanUtils.copyNotNullProperties(request.getBody(), t);
			baseService.saveOrUpdate(t);
			saasResponse.setResult(Result.success);
			saasResponse.setMessage("修改成功");
		} else {
			saasResponse.setResult(Result.fail);
			saasResponse.setMessage("修改失败，没有该数据");
		}
		return saasResponse;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// @DeleteMapping(consumes="application/json")
	@SAASInterface(remark = "删除")
	public SAASResponse delete(@Validated(DELETE.class) @RequestBody SAASRequest<T> request,
			BindingResult bindingResult) throws Exception {
		SAASResponse saasResponse = new SAASResponse<>();
		if (bindingResult.hasFieldErrors()) {
			saasResponse.setResult(Result.invalid);
			saasResponse.setInvalidInfos(bindingResult.getFieldErrors().stream().map((FieldError fe) -> {
				InvalidInfo invalidInfo = new InvalidInfo();
				invalidInfo.setParameterName(fe.getField());
				invalidInfo.setMessage(fe.getDefaultMessage());
				return invalidInfo;
			}).collect(Collectors.toList()));
			return saasResponse;
		}
		Standard.initEntity(request.getBody(), request);
		T t = (T) baseService.getById(GenericsUtils.getSuperClassGenricType(this.getClass()),
				(String) FieldUtils.readField(request.getBody(), "id", true));
		if (t != null) {
			FieldUtils.writeField(t, "sys_deleted", true, true);
			baseService.saveOrUpdate(t);
			saasResponse.setMessage("删除成功");
			saasResponse.setResult(Result.success);
		} else {
			saasResponse.setMessage("删除失败，没有该数据");
			saasResponse.setResult(Result.fail);
		}
		return saasResponse;
	}

	// @DeleteMapping(consumes="text/plain")
	@SAASInterface(remark = "批量删除")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SAASResponse deleteAll(String[] ids) throws Exception {
		SAASResponse<ArrayList<String>> saasResponse = new SAASResponse<ArrayList<String>>();
		saasResponse.setBody(new ArrayList<String>());
		Arrays.stream(ids).forEach(id -> {
			T t = (T) baseService.getById(GenericsUtils.getSuperClassGenricType(this.getClass()), id);
			// TODO 批量删除没有修改 sys_modifyMemberid和sys_modifyDateTime
			if (t != null) {
				Field sys_deletedField = FieldUtils.getField(GenericsUtils.getSuperClassGenricType(getClass()),
						"sys_deleted", true);
				if (sys_deletedField != null) {
					try {
						FieldUtils.writeField(t, "sys_deleted", true, true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					baseService.saveOrUpdate(t);
				}else{
					baseService.delete(t);
				}
				saasResponse.setMessage("删除成功");
				saasResponse.setResult(Result.success);
			} else {
				saasResponse.setMessage("删除失败，有数据删除失败");
				saasResponse.setResult(Result.fail);
				saasResponse.getBody().add(id);
			}

		});
		return saasResponse;
	}

}
