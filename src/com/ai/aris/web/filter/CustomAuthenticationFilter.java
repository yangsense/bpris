package com.ai.aris.web.filter;

import com.ai.common.util.CommonUtil;
import org.jasig.cas.client.authentication.*;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.util.ReflectUtils;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2016/1/19
 * Time: 11:22
 * Email:zhangfz3@asiainfo.com
 */
public class CustomAuthenticationFilter extends AbstractCasFilter {
    /**
     * The URL to the CAS Server login.
     */
    private String casServerLoginUrl;

    /**
     * Whether to send the renew request or not.
     */
    private boolean renew = false;

    /**
     * Whether to send the gateway request or not.
     */
    private boolean gateway = false;

    private GatewayResolver gatewayStorage = new DefaultGatewayResolverImpl();

    private AuthenticationRedirectStrategy authenticationRedirectStrategy = new DefaultAuthenticationRedirectStrategy();

    private UrlPatternMatcherStrategy ignoreUrlPatternMatcherStrategyClass = null;

    private static final Map<String, Class<? extends UrlPatternMatcherStrategy>> PATTERN_MATCHER_TYPES =
            new HashMap<String, Class<? extends UrlPatternMatcherStrategy>>();

    static {
        PATTERN_MATCHER_TYPES.put("CONTAINS", ContainsPatternUrlPatternMatcherStrategy.class);
        PATTERN_MATCHER_TYPES.put("REGEX", RegexUrlPatternMatcherStrategy.class);
        PATTERN_MATCHER_TYPES.put("EXACT", ExactUrlPatternMatcherStrategy.class);
    }

    protected void initInternal(final FilterConfig filterConfig) throws ServletException {
        if (!isIgnoreInitConfiguration()) {
            super.initInternal(filterConfig);
            setCasServerLoginUrl(getPropertyFromInitParams(filterConfig, "casServerLoginUrl", null));
            logger.trace("Loaded CasServerLoginUrl parameter: {}", this.casServerLoginUrl);
            setRenew(parseBoolean(getPropertyFromInitParams(filterConfig, "renew", "false")));
            logger.trace("Loaded renew parameter: {}", this.renew);
            setGateway(parseBoolean(getPropertyFromInitParams(filterConfig, "gateway", "false")));
            logger.trace("Loaded gateway parameter: {}", this.gateway);

            final String ignorePattern = getPropertyFromInitParams(filterConfig, "ignorePattern", null);
            logger.trace("Loaded ignorePattern parameter: {}", ignorePattern);

            final String ignoreUrlPatternType = getPropertyFromInitParams(filterConfig, "ignoreUrlPatternType", "REGEX");
            logger.trace("Loaded ignoreUrlPatternType parameter: {}", ignoreUrlPatternType);

            if (ignorePattern != null) {
                final Class<? extends UrlPatternMatcherStrategy> ignoreUrlMatcherClass = PATTERN_MATCHER_TYPES.get(ignoreUrlPatternType);
                if (ignoreUrlMatcherClass != null) {
                    this.ignoreUrlPatternMatcherStrategyClass = ReflectUtils.newInstance(ignoreUrlMatcherClass.getName());
                } else {
                    try {
                        logger.trace("Assuming {} is a qualified class name...", ignoreUrlPatternType);
                        this.ignoreUrlPatternMatcherStrategyClass = ReflectUtils.newInstance(ignoreUrlPatternType);
                    } catch (final IllegalArgumentException e) {
                        logger.error("Could not instantiate class [{}]", ignoreUrlPatternType, e);
                    }
                }
                if (this.ignoreUrlPatternMatcherStrategyClass != null) {
                    this.ignoreUrlPatternMatcherStrategyClass.setPattern(ignorePattern);
                }
            }

            final String gatewayStorageClass = getPropertyFromInitParams(filterConfig, "gatewayStorageClass", null);

            if (gatewayStorageClass != null) {
                this.gatewayStorage = ReflectUtils.newInstance(gatewayStorageClass);
            }

            final String authenticationRedirectStrategyClass = getPropertyFromInitParams(filterConfig,
                    "authenticationRedirectStrategyClass", null);

            if (authenticationRedirectStrategyClass != null) {
                this.authenticationRedirectStrategy = ReflectUtils.newInstance(authenticationRedirectStrategyClass);
            }

            customInit(filterConfig);
        }
    }

    public void init() {
        super.init();
        CommonUtils.assertNotNull(this.casServerLoginUrl, "casServerLoginUrl cannot be null.");
    }

    public final void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                               final FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (isRequestUrlExcluded(request)) {
            logger.debug("Request is ignored.");
            filterChain.doFilter(request, response);
            return;
        }

        final HttpSession session = request.getSession(false);
        final Assertion assertion = session != null ? (Assertion) session.getAttribute(CONST_CAS_ASSERTION) : null;

        if (assertion != null) {
            filterChain.doFilter(request, response);
            return;
        }

        final String serviceUrl = constructServiceUrl(request, response);
        final String ticket = retrieveTicketFromRequest(request);
        final boolean wasGatewayed = this.gateway && this.gatewayStorage.hasGatewayedAlready(request, serviceUrl);

        if (CommonUtils.isNotBlank(ticket) || wasGatewayed) {
            filterChain.doFilter(request, response);
            return;
        }

        final String modifiedServiceUrl;

        logger.debug("no ticket and no assertion found");
        if (this.gateway) {
            logger.debug("setting gateway attribute in session");
            modifiedServiceUrl = this.gatewayStorage.storeGatewayInformation(request, serviceUrl);
        } else {
            modifiedServiceUrl = serviceUrl;
        }

        logger.debug("Constructed service url: {}", modifiedServiceUrl);

        final String urlToRedirectTo = CommonUtils.constructRedirectUrl(this.casServerLoginUrl,
                getServiceParameterName(), modifiedServiceUrl, this.renew, this.gateway);

        logger.debug("redirecting to \"{}\"", urlToRedirectTo);

        logger.debug("判断拼接的过程,参数, 最终拼接好的地址为: \"" + urlToRedirectTo + "\"");

        String url = request.getRequestURL().toString();
        logger.debug("url------request.getRequestURL().toString()=---------:" + url);
        String contextPath = request.getContextPath();
        logger.debug("contextPath ---------request.getContextPath()=-------:" + contextPath);

        url = url.substring(0, (url.indexOf(contextPath)+contextPath.length()));
        logger.debug("url = ------session消失,截取到项目的url---" + url);
        String urls = urlToRedirectTo;

        //判断是否是第一次转到.
        if("".equals(url)||url==null||url.length()==0){

            logger.debug("url--第一次为空,不截取-----" + url);
            urls = urlToRedirectTo;
        }else{
            urls = urls.substring(0, (urls.indexOf("service=")+8)) + URLEncoder.encode(url,"UTF-8");
        }

        logger.debug("urls --最终输入到浏览器的地址是-----------" + urls);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<script languge='javascript'>window.open ('"+urls+"/','_top')</script>");
        //this.authenticationRedirectStrategy.redirect(request, response, urlToRedirectTo);
    }

    public final void setRenew(final boolean renew) {
        this.renew = renew;
    }

    public final void setGateway(final boolean gateway) {
        this.gateway = gateway;
    }

    public final void setCasServerLoginUrl(final String casServerLoginUrl) {
        this.casServerLoginUrl = casServerLoginUrl;
    }

    public final void setGatewayStorage(final GatewayResolver gatewayStorage) {
        this.gatewayStorage = gatewayStorage;
    }

    private boolean isRequestUrlExcluded(final HttpServletRequest request) {
        if(isRequestExternalExcluded(request)){
            return true;
        }
        if (this.ignoreUrlPatternMatcherStrategyClass == null) {
            return false;
        }

        final StringBuffer urlBuffer = request.getRequestURL();
        if (request.getQueryString() != null) {
            urlBuffer.append("?").append(request.getQueryString());
        }
        final String requestUri = urlBuffer.toString();
        return this.ignoreUrlPatternMatcherStrategyClass.matches(requestUri);
    }

    /**
     * 自定义过滤部分，需要在CASFilter中添加includePaths标识需要过滤的URL,添加excludePaths来标识要放过的URL
     * */
    private  String[] includePatterns;
    private  String[] excludePatterns;
    private PathMatcher pathMatcher = new AntPathMatcher();
    UrlPathHelper urlPathHelper =  new UrlPathHelper();
    private final void customInit(final FilterConfig filterConfig){
        String includePaths = getPropertyFromInitParams(filterConfig, "includePaths", null);
        if(CommonUtil.isNonNull(includePaths)) {
            String[] includes = includePaths.split("\n");
            includePatterns = new String[includes.length];
            for (int i = 0; i < includes.length; i++) {
                includePatterns[i] = includes[i].trim();
            }
        }
        final String excludePaths = getPropertyFromInitParams(filterConfig, "excludePaths", null);
        if(CommonUtil.isNonNull(excludePaths)) {
            String[] excludes = excludePaths.split("\n");
            excludePatterns = new String[excludes.length];
            for (int i = 0; i < excludes.length; i++) {
                excludePatterns[i] = excludes[i].trim();
            }
        }
    }

    private boolean isRequestExternalExcluded(HttpServletRequest req){
        return !matches(req);
    }

    public boolean matches(HttpServletRequest request) {
        String lookupPath = urlPathHelper.getLookupPathForRequest(request);
        if (this.excludePatterns != null) {
            for (String pattern : this.excludePatterns) {
                if (pathMatcher.match(pattern, lookupPath)) {
                    return false;
                }
            }
        }
        if (this.includePatterns == null) {
            return true;
        }
        else {
            for (String pattern : this.includePatterns) {
                if (pathMatcher.match(pattern, lookupPath)) {
                    return true;
                }
            }
            return false;
        }
    }
}
