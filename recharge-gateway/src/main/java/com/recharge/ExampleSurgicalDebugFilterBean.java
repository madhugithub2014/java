package com.recharge;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ExampleSurgicalDebugFilterBean extends ZuulFilter {
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 96;
	}

	public boolean shouldFilter() {
		RequestContext.getCurrentContext().getRequest().getRequestURI().matches("/api/redirect.*");
		return true;
	}

	public Object run() {

		return null;
	}

}
