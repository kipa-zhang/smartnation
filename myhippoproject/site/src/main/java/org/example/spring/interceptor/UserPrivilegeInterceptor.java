/**
 * 
 */
package org.example.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @ClassName: UserPrivilegeInterceptor
 * @Description: TODO()
 * @author Huang.Jilong
 * @date 2013-11-12 10:51:12
 * 
 */
//@Component
public class UserPrivilegeInterceptor extends HandlerInterceptorAdapter {
//	@Autowired
//	private ICookieService cookieService;
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>(
			"StopWatch-StartTime");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//实现了跨域
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.setHeader("Content-Type", "text/javascript");
		long beginTime = System.currentTimeMillis();// 1、开始时间
		startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）
		System.out.println("QueryString:"+request.getQueryString());
		System.out.println("URI:"+request.getRequestURI());
		HttpSession session = request.getSession();
		Object _loginInfo = session.getAttribute("loginInfo");
		
		return true;// 继续流程
	}

	

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		long endTime = System.currentTimeMillis();// 2、结束时间
		long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
		long consumeTime = endTime - beginTime;// 3、消耗的时间
		System.out.println(String.format("%s consume %d millis",request.getRequestURI(), consumeTime));
		if (consumeTime > 500) {// 此处认为处理时间超过500毫秒的请求为慢请求
//			System.out.println(String.format("%s consume %d millis",
//					request.getRequestURI(), consumeTime));
		}
	}
}