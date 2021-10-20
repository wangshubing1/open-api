package cn.com.belle.bdc.openapi.conf.filter;

import cn.com.belle.bdc.openapi.common.Constants;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class SignFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(SignFilter.class);
    private String[] excludedUris;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化后被调用一次
        excludedUris = filterConfig.getInitParameter("exclusions").split(",");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String url=httpRequest.getRequestURI();
        Map<String,String[]> params = request.getParameterMap();
        if (isExcludedUri(url)){
            chain.doFilter(request, response);
        } else if (params.isEmpty()||!params.containsKey(Constants.SIGN_NAME)) {
            logger.error("缺少方法签名");
            Map<String, String> map = Maps.newHashMap();
            map.put("code", "9999");
            map.put("msg", "没有访问权限，如需要访问，请联系管理员!");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(JSONObject.toJSON(map));
            return;

        }else {
            // 这里将原始request替换为包装后的request，此后所有进入controller的request均为包装后的
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // 被销毁时调用一次
    }

    private boolean isExcludedUri(String uri) {
        if (excludedUris == null || excludedUris.length <= 0) {
            return false;
        }
        for (String ex : excludedUris) {
            uri = uri.trim();
            ex = ex.trim();
            if (uri.toLowerCase().matches(ex.toLowerCase().replace("*",".*")))
                return true;
        }
        return false;
    }
}
