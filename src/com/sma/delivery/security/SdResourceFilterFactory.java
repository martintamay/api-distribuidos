package com.sma.delivery.security;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory;
import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;

@Component
@Provider
public class SdResourceFilterFactory extends RolesAllowedResourceFilterFactory {
	@Autowired
	private SdSecurityContextFilter securityContextFilter;

	@Override
	public List<ResourceFilter> create(AbstractMethod am) {
		List<ResourceFilter> rolesFilters = super.create(am);
		if (null == rolesFilters) {
			rolesFilters = new ArrayList<>();
		}

		final List<ResourceFilter> filters = new ArrayList<>(rolesFilters);
		filters.add(securityContextFilter);
		return filters;
	}
}
