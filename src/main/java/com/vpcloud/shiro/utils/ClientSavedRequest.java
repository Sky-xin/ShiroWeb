package com.vpcloud.shiro.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.SavedRequest;

public class ClientSavedRequest extends SavedRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8042166058456751779L;
	
	private String scheme;
	private String domain;
	private String contextPath;
	private String backUrl;
	private int port;
	
	public ClientSavedRequest(HttpServletRequest request,String backUrl) {
		super(request);
		this.scheme = request.getScheme();
		this.backUrl = backUrl;
		this.domain = request.getServerName();
		this.port = request.getServerPort();
		this.contextPath = request.getContextPath();
	}
	
	public String getRequestUrl(){
		
		String requestURI = getBackUrl();
		//设置了返回的Url
		if(backUrl!=null){
			//且设置的是http请求连接，着直接转发即可
			if(backUrl.toLowerCase().startsWith("http://")||backUrl.toLowerCase().startsWith("https://")){
				return backUrl;
			//若请求的是controller 的 url 则需要获取上下文,否则直接返回
			}else if (!backUrl.startsWith(contextPath)) {
				requestURI = contextPath+backUrl;
			}else {
				requestURI = backUrl;
			}
		}
		//生成连接请求头
		StringBuilder  requestUrl = new StringBuilder(scheme);
		requestUrl.append("://");
		requestUrl.append(domain);
		//生产域名
		if("http".equalsIgnoreCase(scheme)&&port!=80){
			requestUrl.append(":").append(String.valueOf(port));
		}else if("https".equalsIgnoreCase(scheme)&&port!=443){
			requestUrl.append(":").append(String.valueOf(port));
		}
		//拼上重定向到的地址（带上下文）
		requestUrl.append(requestURI);
		//如果successUrl没值，且有查询参数，拼上
		if (backUrl==null&&getQueryString()!=null) {
			requestUrl.append("?").append(getQueryString());
		}
		return requestUrl.toString();
		
	}
	
	public String getScheme() {
		return scheme;
	}

	public String getDomain() {
		return domain;
	}

	public String getContextPath() {
		return contextPath;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public int getPort() {
		return port;
	}

	@Override
	public String toString() {
		return "ClientSavedRequest [scheme=" + scheme + ", domain=" + domain + ", contextPath=" + contextPath
				+ ", backUrl=" + backUrl + ", port=" + port + "]";
	}
	
}
