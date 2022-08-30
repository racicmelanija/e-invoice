package com.einvoice.companyservice.annotation;

import com.einvoice.companyservice.exception.UnauthorizedException;
import com.nimbusds.jose.shaded.json.JSONArray;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AuthorizationChecker {

    @Around("@annotation(authorize)")
    public Object validator(ProceedingJoinPoint call, Authorize authorize) throws Throwable {
        JwtAuthenticationToken jwt = (JwtAuthenticationToken) call.getArgs()[0];
        JSONArray userRoles = (JSONArray) jwt.getTokenAttributes().get("roles");
        String[] authorizedRoles = authorize.roles();

        if(userRoles.isEmpty() || !jwt.isAuthenticated()){
            throw new UnauthorizedException();
        }

        if(Arrays.stream(authorizedRoles).anyMatch(userRoles::contains)){
            return call.proceed();
        }

        throw new UnauthorizedException();
    }
}
