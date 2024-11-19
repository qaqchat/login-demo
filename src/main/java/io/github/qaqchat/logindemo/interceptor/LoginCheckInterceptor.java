package io.github.qaqchat.logindemo.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.qaqchat.logindemo.pojo.vo.Result;
import io.github.qaqchat.logindemo.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Log4j2
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                jwtUtil.parseJwt(token);
            } catch (ExpiredJwtException e) {
                log.info("Token has expired");
                responseHandler(HttpServletResponse.SC_UNAUTHORIZED, "登录过期啦，请重新登录 QAQ", response);
                return false;
            } catch (MalformedJwtException e) {
                log.info("Invalid token format");
                responseHandler(HttpServletResponse.SC_UNAUTHORIZED, "出错啦 QAQ", response);
                return false;
            } catch (Exception e) {
                log.error(e.getMessage());
                responseHandler(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "出错啦 QAQ", response);
                return false;
            }
        } else {
            log.info("Missing or invalid Authorization header");
            responseHandler(HttpServletResponse.SC_UNAUTHORIZED, "没有登录，请登录 QAQ", response);
            return false;
        }

        return true;
    }

    private void responseHandler(int code, String msg, HttpServletResponse response) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Result<Object> result = Result.failure(msg);
        response.setStatus(code);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
