package com.recharge;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.util.HTTPRequestUtils;

public class ExampleSurgicalDebugFilterBean extends ZuulFilter{
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

	        try {
				RequestContext.getCurrentContext().setRouteHost(new URL("http://example.com"));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        if (HTTPRequestUtils.getInstance().getQueryParams() == null) {
	            RequestContext.getCurrentContext().setRequestQueryParams(new HashMap<String, List<String>>());
	        }
	        List<String> listArr = new ArrayList<String>();
	        listArr.add("true");
	        HTTPRequestUtils.getInstance().getQueryParams().put("debugRequest", listArr);
	        RequestContext.getCurrentContext().setDebugRequest(true);
	        RequestContext.getCurrentContext().setZuulEngineRan();
			return listArr;

	    }

}
