package com.alibaba.chord.service.base.shiro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * @Author: zhang
 * @Time:2017/6/18
 */
@Slf4j
public class AnyRolesAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o)
        throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        if (null != o) {
            Set<String> rolesSet = new HashSet<>(Arrays.asList((String[])o));
            if (!rolesSet.isEmpty()) {
                for (String role : rolesSet) {
                    if (subject.hasRole(role)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}