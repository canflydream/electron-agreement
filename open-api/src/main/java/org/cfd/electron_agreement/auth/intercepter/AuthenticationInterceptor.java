package org.cfd.electron_agreement.auth.intercepter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.cfd.electron_agreement.annotation.PassToken;
import org.cfd.electron_agreement.exception.AuthException;
import org.cfd.electron_agreement.auth.service.IUserService;
import org.cfd.electron_agreement.utils.JWTUtils;
import org.cfd.electron_agreement.beans.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final IUserService userService;

    @Autowired
    public AuthenticationInterceptor(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authentication pre handle ...");
        String token = request.getHeader("token");

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Method method = handlerMethod.getMethod();

        if(method.isAnnotationPresent(PassToken.class)){
            PassToken annotation = method.getAnnotation(PassToken.class);
            boolean required = annotation.required();
            if(required){
                return true;
            }
        }

        String loginId = JWTUtils.getLoginId(token);

        User user=userService.findUserByLoginId(loginId);
        if(user==null){
            throw new AuthException(403,"invalid user");
        }

        JWTVerifier build = JWT.require(Algorithm.HMAC256(user.getPrivateKey())).build();
        try {
            build.verify(token);
        }catch (Exception e){
            throw new AuthException(403,e.getMessage());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        log.info("Authentication interceptor post handle ...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        log.info("Authentication interceptor after completion ...");
    }
}
