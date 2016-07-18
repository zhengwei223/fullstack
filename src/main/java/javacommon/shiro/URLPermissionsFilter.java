package javacommon.shiro;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于URL的权限判断过滤器
 * <p>
 * 自动根据URL产生所谓的权限字符串
 * <p>
 * URL举例：<p>
 * eg:/User/create.do?***=*** -->权限字符串：User:create<p>
 * eg:/pages/SysJob/create.jsp-->SysJob:create
 * @author zhengwei lastmodified 2013年9月28日
 * 
 */
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {
	private static final Logger LOG = LoggerFactory.getLogger(URLPermissionsFilter.class);
	/**
	 * @param mappedValue
	 *            指的是在声明url时指定的权限字符串，如/User/create.do=perms[User:create].
	 *            我们要动态产生这个权限字符串，所以这个配置对我们没用
	 */
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		try {
			return super.isAccessAllowed(request, response,
					buildPermissions(request));
		} catch (IllegalUrlException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据请求URL产生权限字符串，这里只产生，而比对的事交给Realm
	 * 
	 * @param request
	 * @return
	 * @throws IllegalUrlException
	 */
	protected String[] buildPermissions(ServletRequest request)
			throws IllegalUrlException {
		String[] perms = new String[1];
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletPath();
		Pattern p = Pattern.compile("/(.*?)/(.*?)\\..*");
		Matcher m = p.matcher(path);
		if(m.matches()){
			path = m.group(2);
		}
		/*int dotIndex = -1;
		if ((dotIndex=path.indexOf("."))!=-1) {
			path = path.substring(1, dotIndex);
		}*/
		
		LOG.debug("request path:"+path);
		/*2016-7-16 权限字符串是斜杠分割的*/
		//path = path.replaceAll("/", ":");
		perms[0] = path;
		LOG.debug("mappedValue permission:"+path);
		return perms;
	}
}