package com.hq.CloudPlatform.CA.annotations;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.mapper.UserMapper;
import com.hq.CloudPlatform.CA.restful.view.JsonViewObject;
@Aspect
@Component
public class SystemVerificationAspect{

	@Autowired
    private UserMapper userMapper;

	 protected JsonViewObject jsonView = new JsonViewObject();

	// 切点
	@Pointcut("@annotation(com.hq.CloudPlatform.CA.annotations.SystemVerification)")
	public void verificationAspect() {
		System.out.println("AOP初始化");
		// TODO Auto-generated constructor stub
	}


		@Before("verificationAspect()")
		public void before(JoinPoint joinPoint,HttpServletResponse response)  throws ClassNotFoundException, ServiceException {
			ResourceBundle rb = ResourceBundle.getBundle("regEx");
			String targetName = joinPoint.getTarget().getClass().getName();
			Map<String,String> parmMap = new HashMap();
			Object[] obj = joinPoint.getArgs();
			Arrays.asList(((String) obj[0]).replace("{", "").replace("}", "").split(",")).forEach(x -> {
				String[] strs = x.split(":");
				if(strs.length > 1)
					parmMap.put(strs[0].replaceAll("\"", "").trim(), strs[1]);
			});
			Class<?> targetClass = Class.forName(targetName);
			Object[] arguments = joinPoint.getArgs();
			String methodName = joinPoint.getSignature().getName();
			Method[] methods = targetClass.getMethods();
			String description = "";
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						description = method.getAnnotation(SystemVerification.class).description();
						break;
					}
				}
			}
			List<String> descriptions = Arrays.asList(description.split(","));
			List<String> strs = Arrays.asList(rb.getString("strs").split(","));
			Map<String,String> result = new HashMap();
			for(String str:strs){
				if(descriptions.contains(str)){
					String msg = parmMap.get(str) != null?parmMap.get(str):null;
					if(msg == null || !Pattern.compile(rb.getString(str)).matcher(msg.replaceAll("\"", "").trim()).matches()){
						response.setCharacterEncoding("UTF_8");
						response.setContentType("application/json; charset=utf-8");
						 PrintWriter out = null;
						 try {
						        out = response.getWriter();
						        out.append(JSON.toJSONStringWithDateFormat(jsonView.successPack(rb.getString(str+"Name")+"不合法，请重试"), "yyyy-MM-dd HH:mm:ss"));
						    } catch (Exception e) {
						        e.printStackTrace();
						    } finally {
						        if (out != null) {
						            out.close();
						        }
						    }
						 throw new ServiceException(rb.getString(str+"Name")+"不合法，请重试");
					}
				}

			}
		}
}
