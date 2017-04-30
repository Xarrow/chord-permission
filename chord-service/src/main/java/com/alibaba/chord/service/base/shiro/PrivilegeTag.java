//package shiro;
//
//import java.util.Map;
//import java.util.Set;
//
//import javax.servlet.ServletContext;
//import javax.servlet.jsp.PageContext;
//import javax.servlet.jsp.tagext.TagSupport;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.slf4j.LoggerFactory;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.support.SimpleCacheManager;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//import PermissionService;
//import CommonsValue;
//
///**
// * Created by wb-zj268791 on 2017/3/30.
// */
//public class PrivilegeTag extends TagSupport {
//    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TagSupport.class);
//    private static final long serialVersionUID = 1L;
//
//    private String privilege;
//
//    private CacheManager cacheManager;
//
//    private PermissionService permissionService;
//
//    public String getPrivilege() {
//        return privilege;
//    }
//
//    public void setPrivilege(String privilege) {
//        this.privilege = privilege;
//    }
//
//    @Override
//    public int doStartTag() {
//        PageContext pageContext = this.pageContext;
//        ServletContext servletContext = pageContext.getServletContext();
//        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
//
//        //根据类型获取bean
//        this.permissionService = wac.getBean(PermissionService.class);
//        this.cacheManager = wac.getBean(SimpleCacheManager.class);
//        if (isPermitFromCache()) {
//            return EVAL_BODY_AGAIN;
//        } else {
//            return SKIP_BODY;
//        }
//    }
//
//    /**
//     * 取出缓存校验
//     *
//     * @return
//     */
//    private boolean isPermitFromCache() {
//        Subject subject = SecurityUtils.getSubject();
//        if (null == subject) {
//            return false;
//        }
//        String userKey = subject.getPrincipal().toString();
//        Cache cache = cacheManager.getCache(CommonsValue.CACHE_NAME);
//        if (null == cache) {
//            return false;
//        }
//        Map map = cache.get(userKey, Map.class);
//        if (null == map) {
//            return false;
//        }
//        Set<String> permissionSet = (Set<String>)map.get("permissionSet");
//        if (null == permissionSet) {
//            return false;
//        }
//
//        //如果缓存中校验失败，则调用数据库查询校验
//        return permissionSet.contains(privilege) || isPermit();
//    }
//
//    /**
//     * 实时查询数据库校验
//     *
//     * @return
//     */
//    private boolean isPermit() {
//        Subject subject = SecurityUtils.getSubject();
//        if (null == subject) {
//            return false;
//        }
//        String userKey = subject.getPrincipal().toString();
//        return permissionService.findPermissionByUserKey(userKey).contains(privilege);
//    }
//
//}
