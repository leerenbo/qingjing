package com.lrb.saas.core.model;

import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.lrb.saas.core.annotation.SAASField;
import com.lrb.saas.core.message.request.SAASRequest;
import com.lrb.saas.core.util.UUIDUtil;

public class Standard {
	@Id
	@SAASField(name = "主键")
	private String id;
	@SAASField(name = "创建时间")
	private Date sys_createDateTime;
	@SAASField(name = "修改时间")
	private Date sys_modifyDateTime;
	@SAASField(name = "被逻辑删除", remark = "true为删除,false为未删除,数据查询不显示逻辑删除数据")
	@NotNull
	private Boolean sys_deleted = false;
	@Version
	@org.springframework.data.annotation.Version
	@SAASField(name = "数据库版本号", remark = "修改操作请将sys_version值带回,进行乐观锁判断")
	private Integer sys_version;
	@SAASField(name = "创建数据的用户id")
	private String sys_createMemberid;
	@SAASField(name = "修改数据的用户id")
	private String sys_modifyMemberid;
	@SAASField(name = "数据所属租户")
	private String sys_tenantid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getSys_createDateTime() {
		return sys_createDateTime;
	}

	public void setSys_createDateTime(Date sys_createDateTime) {
		this.sys_createDateTime = sys_createDateTime;
	}

	public Date getSys_modifyDateTime() {
		return sys_modifyDateTime;
	}

	public void setSys_modifyDateTime(Date sys_modifyDateTime) {
		this.sys_modifyDateTime = sys_modifyDateTime;
	}

	public Boolean getSys_deleted() {
		return sys_deleted;
	}

	public void setSys_deleted(Boolean sys_deleted) {
		this.sys_deleted = sys_deleted;
	}

	public Integer getSys_version() {
		return sys_version;
	}

	public void setSys_version(Integer sys_version) {
		this.sys_version = sys_version;
	}

	public String getSys_createMemberid() {
		return sys_createMemberid;
	}

	public void setSys_createMemberid(String sys_createMemberid) {
		this.sys_createMemberid = sys_createMemberid;
	}

	public String getSys_modifyMemberid() {
		return sys_modifyMemberid;
	}

	public void setSys_modifyMemberid(String sys_modifyMemberid) {
		this.sys_modifyMemberid = sys_modifyMemberid;
	}

	public String getSys_tenantid() {
		return sys_tenantid;
	}

	public void setSys_tenantid(String sys_tenantid) {
		this.sys_tenantid = sys_tenantid;
	}

	public static void initEntity(Object entity, SAASRequest request) {
		Field idField = FieldUtils.getField(entity.getClass(), "id", true);
		Field sys_createDateTimeField = FieldUtils.getField(entity.getClass(), "sys_createDateTime", true);
		Field sys_deletedField = FieldUtils.getField(entity.getClass(), "sys_deleted", true);
		Field sys_versionField = FieldUtils.getField(entity.getClass(), "sys_version", true);
		Field sys_createMemberidField = FieldUtils.getField(entity.getClass(), "sys_createMemberid", true);
		Field sys_tenantidField = FieldUtils.getField(entity.getClass(), "sys_tenantid", true);

		try {
			if (idField != null) {
				if (FieldUtils.readField(idField, entity) == null || FieldUtils.readField(idField, entity).equals("")) {
					FieldUtils.writeField(idField, entity, UUIDUtil.randomUUIDString(), true);
				}
			}

			if (sys_createDateTimeField != null) {
				if (FieldUtils.readField(sys_createDateTimeField, entity) == null) {
					FieldUtils.writeField(sys_createDateTimeField, entity, new Date(), true);
				}
			}

			if (sys_deletedField != null) {
				if (FieldUtils.readField(sys_deletedField, entity) == null) {
					FieldUtils.writeField(sys_deletedField, entity, false, true);
				}
			}

			if (sys_versionField != null) {
				if (FieldUtils.readField(sys_versionField, entity) == null) {
					FieldUtils.writeField(sys_versionField, entity, 0, true);
				}
			}

			if (sys_createMemberidField != null) {
				if (FieldUtils.readField(sys_createMemberidField, entity) == null) {
					// TODO 把token解析成memberid
					FieldUtils.writeField(sys_createMemberidField, entity, request.getToken(), true);
				}
			}

			if (sys_tenantidField != null) {
				if (FieldUtils.readField(sys_tenantidField, entity) == null) {
					FieldUtils.writeField(sys_tenantidField, entity, request.getTenant(), true);
				}
			}

			Field sys_modifyMemberidField = FieldUtils.getField(entity.getClass(), "sys_modifyMemberid", true);
			Field sys_modifyDateTimeField = FieldUtils.getField(entity.getClass(), "sys_modifyDateTime", true);

			// TODO 把token解析成memberid
			if (sys_modifyMemberidField != null) {
				FieldUtils.writeField(sys_modifyMemberidField, entity, request.getToken(), true);
			}
			if (sys_modifyDateTimeField != null) {
				FieldUtils.writeField(sys_modifyDateTimeField, entity, new Date(), true);
			}

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
