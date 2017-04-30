package interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wb-zj268791 on 2017/4/1.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
        throws Exception {
        try {
            String userKey = httpServletRequest.getParameter("userKey");
            //String aesPassword = httpServletRequest.getParameter("password");
            String password = org.apache.shiro.codec.Base64.decodeToString(httpServletRequest.getParameter("password"))
                .split("->")[1];
            String reqToken = httpServletRequest.getParameter("token");
            //客户端在没有sessionId的情况下会产生NP
            Object sessionTokenObject = httpServletRequest.getSession().getAttribute("token");
            if (StringUtils.isBlank(reqToken) ||
                null == sessionTokenObject ||
                !reqToken.equals(sessionTokenObject.toString())) {
                httpServletResponse.sendRedirect("/login");
                return false;
            }
            if (StringUtils.isBlank(userKey)) {
                return false;
            }
            if (StringUtils.isBlank(password)) {
                return false;
            }

            //aes解密
            //String password = EncipherUtil.aesDecryptByToken(aesPassword, reqToken.substring(0, 16));

            //shiro
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userKey, password);
            subject.login(token);
        } catch (AuthenticationException e) {
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/login");
            String resultString = "$.alert({'title':'提示','content': '账号或者密码错误!'})";
            httpServletRequest.setAttribute("result", resultString);
            dispatcher.forward(httpServletRequest, httpServletResponse);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
