package me.rajdeepdeb.notification.interceptor;

import me.rajdeepdeb.notification.constants.RequestHeaderConstants;
import me.rajdeepdeb.notification.model.Client;
import me.rajdeepdeb.notification.service.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ClientAuthenticatorInterceptor implements HandlerInterceptor {

    @Autowired
    ISubscriptionService subscriptionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(RequestHeaderConstants.HEADER_AUTH);
        System.out.println(token);
        Client client = subscriptionService.getClientForToken(token);
        if (client == null || client.getSubscription().isExpired()) {
            response.setStatus(401);
            return false;
        }
        return true;
    }


}
