package cn.bidlink.report.app.feignConfig.feignext;

import cn.bidlink.framework.boot.web.context.UserContext;
import feign.Client;
import feign.Request;
import feign.Response;
import org.springframework.cloud.netflix.feign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.netflix.feign.ribbon.LoadBalancerFeignClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 王炎林
 */
public class LocalizationLoadBalancerFeignClient extends LoadBalancerFeignClient {

    static final Request.Options DEFAULT_OPTIONS = new Request.Options();

    private final Client delegate;
    private CachingSpringLoadBalancerFactory lbClientFactory;
    private SpringClientFactory clientFactory;

    public LocalizationLoadBalancerFeignClient(Client delegate, CachingSpringLoadBalancerFactory lbClientFactory, SpringClientFactory clientFactory) {
        super(delegate, lbClientFactory, clientFactory);
        this.delegate = delegate;
        this.lbClientFactory = lbClientFactory;
        this.clientFactory = clientFactory;
    }

    @Override
    public Response execute(Request requestOri, Request.Options options) throws IOException {
        Long companyId = UserContext.getCompanyId();
        Set<Long> companyIdSet = new HashSet<>();
        companyIdSet.add(11113174597L);

        //todo 上线前删除掉此行代码
        //companyId = 11113174597L;

        if ( companyIdSet.contains(companyId) ) {
            String url = requestOri.url();
            String[] s = url.split("[/]");
            if ( s.length > 2 ) {
                String server_id = s[2];
                String newUrl = url.replaceFirst(server_id, server_id + "-" + companyId);
                Request request = Request.create(requestOri.method(),newUrl,requestOri.headers(),requestOri.body(),requestOri.charset());
                return super.execute(request,options);
            }
        }
        return  super.execute(requestOri,options);
    }


   /* @Override
    public Response execute(Request requestOri, Request.Options options) throws IOException {
        Long companyId = UserContext.getCompanyId();
        System.out.println(companyId);
        String url = requestOri.url();
        //String newUrl = url.replace("order-cloud", "purchase-cloud");
        Request request = Request.create(requestOri.method(),url,requestOri.headers(),requestOri.body(),requestOri.charset());

        URI asUri = URI.create(request.url());
        String clientName = asUri.getHost();
        URI uriWithoutHost = cleanUrl(request.url(), clientName);
        IClientConfig requestConfig = getClientConfig(options, clientName);

        Server server = new Server("127.0.0.1",8767);
        URI finalUri = reconstructURIWithServer(server, request.getUri());
        return lbClient(clientName).executeWithLoadBalancer(request,
                requestConfig).toResponse();
    }

    public URI reconstructURIWithServer(Server server, URI original) {
        String host = server.getHost();
        int port = server .getPort();
        if (host.equals(original.getHost())
                && port == original.getPort()) {
            return original;
        }
        String scheme = original.getScheme();
        if (scheme == null) {
            scheme = deriveSchemeAndPortFromPartialUri(original);
        }

        try {
            StringBuilder sb = new StringBuilder();
            sb.append(scheme).append("://");
            if (!Strings.isNullOrEmpty(original.getRawUserInfo())) {
                sb.append(original.getRawUserInfo()).append("@");
            }
            sb.append(host);
            if (port >= 0) {
                sb.append(":").append(port);
            }
            sb.append(original.getRawPath());
            if (!Strings.isNullOrEmpty(original.getRawQuery())) {
                sb.append("?").append(original.getRawQuery());
            }
            if (!Strings.isNullOrEmpty(original.getRawFragment())) {
                sb.append("#").append(original.getRawFragment());
            }
            URI newURI = new URI(sb.toString());
            return newURI;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected String deriveSchemeAndPortFromPartialUri(URI uri) {
        boolean isSecure = false;
        String scheme = uri.getScheme();
        if (scheme != null) {
            isSecure =  scheme.equalsIgnoreCase("https");
        }
        int port = uri.getPort();
        if (port < 0 && !isSecure){
            port = 80;
        } else if (port < 0 && isSecure){
            port = 443;
        }
        if (scheme == null){
            if (isSecure) {
                scheme = "https";
            } else {
                scheme = "http";
            }
        }
        return scheme;
    }


    static URI cleanUrl(String originalUrl, String host) {
        return URI.create(originalUrl.replaceFirst(host, ""));
    }

    private FeignLoadBalancer lbClient(String clientName) {
        return this.lbClientFactory.create(clientName);
    }

    IClientConfig getClientConfig(Request.Options options, String clientName) {
        IClientConfig requestConfig;
        if (options == DEFAULT_OPTIONS) {
            requestConfig = this.clientFactory.getClientConfig(clientName);
        } else {
            requestConfig = new LocalizationClientConfig(options);
        }
        return requestConfig;
    }


    static class LocalizationClientConfig extends DefaultClientConfigImpl {

        public LocalizationClientConfig(Request.Options options) {
            setProperty(CommonClientConfigKey.ConnectTimeout,
                    options.connectTimeoutMillis());
            setProperty(CommonClientConfigKey.ReadTimeout, options.readTimeoutMillis());
        }

        @Override
        public void loadProperties(String clientName) {
        }

        @Override
        public void loadDefaultValues() {
        }

    }*/
}
