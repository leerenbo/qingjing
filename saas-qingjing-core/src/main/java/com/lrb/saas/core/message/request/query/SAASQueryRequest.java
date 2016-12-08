package com.lrb.saas.core.message.request.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import com.lrb.saas.core.message.request.query.filter.OperatorFilter;
import com.lrb.saas.core.message.request.query.projection.Projection;
import com.lrb.saas.core.message.request.query.sort.Order;

public class SAASQueryRequest {
	private Class clazz;
	private List<OperatorFilter> filters = new ArrayList<OperatorFilter>();
	private List<Projection> projections = new ArrayList<Projection>();
	private Integer limit;
	private Integer skip;
	private List<Order> sorts = new ArrayList<Order>();

	public Map<String, Object> toHqlParams() {
		return filters.stream().map((filter) -> filter.toHqlParams(clazz)).reduce((m1, m2) -> {
			m1.putAll(m2);
			return m1;
		}).orElse(new HashMap<>());
	}

	public String toHql() {
		String re = "";
		if (projections.size() > 0) {
			String includeProjections = projections.stream()
					.filter((projection) -> projection.getClude() == Projection.Clude.INCLUDE)
					.map((projection) -> projection.getFieldName()).collect(Collectors.joining(","));
			re = " select " + includeProjections;
		}

		re = re + " from " + clazz.getTypeName() + " as _t";
		if (filters.size() > 0) {
			re = re + filters.stream().filter((filter) -> {
				if (filter.toHqlJoin(clazz) != null) {
					return true;
				}
				;
				return false;
			}).map((filter) -> {
				return filter.toHqlJoin(clazz);
			}).collect(Collectors.joining());
		}

		if (filters.size() > 0) {
			re = re + " where " + filters.stream().map((filter) -> {
				return filter.toHqlCondition();
			}).collect(Collectors.joining(" and "));
		}

		if (sorts.size() > 0) {
			re = re + " order by " + sorts.stream().map((order) -> {
				return " " + order.getFieldName() + " " + order.getDirection().name();
			}).collect(Collectors.joining(","));
		}
		return re;
	}

	public Query toMongoQuery() {
		Query query = new Query();
		if (filters.size() > 0) {
			filters.stream().forEach((filter -> {
				query.addCriteria(filter.toMongoCriteria());
			}));
		}
		if (skip != null) {
			query.skip(skip);
		}
		if (limit != null) {
			query.limit(limit);
		}
		if (sorts.size() > 0) {
			query.with(new Sort(sorts.stream().map((order) -> {
				return new Sort.Order(Sort.Direction.valueOf(order.getDirection().name()), order.getFieldName());
			}).collect(Collectors.toList())));
		}
		if (projections.size() > 0) {
			projections.stream().filter((projection) -> projection.getClude() == Projection.Clude.INCLUDE)
					.forEach((projection) -> {
						query.fields().include(projection.getFieldName());
					});
			projections.stream().filter((projection) -> projection.getClude() == Projection.Clude.EXCLUDE)
					.forEach((projection) -> {
						query.fields().exclude(projection.getFieldName());
					});
		}
		return query;
	}

	public List<OperatorFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<OperatorFilter> filters) {
		this.filters = filters;
	}

	public List<Projection> getProjections() {
		return projections;
	}

	public void setProjections(List<Projection> projections) {
		this.projections = projections;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public List<Order> getSorts() {
		return sorts;
	}

	public void setSorts(List<Order> sorts) {
		this.sorts = sorts;
	}

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
}
