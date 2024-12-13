package com.camila.gateway.loadbalance;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.config.GatewayLoadBalancerProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_SCHEME_PREFIX_ATTR;

public class VersionLoadBalancerClientFilter extends ReactiveLoadBalancerClientFilter {


    private static final Log log = LogFactory.getLog(VersionLoadBalancerClientFilter.class);

    private final LoadBalancerClientFactory clientFactory;
    private final GatewayLoadBalancerProperties properties;


    public VersionLoadBalancerClientFilter(LoadBalancerClientFactory clientFactory, GatewayLoadBalancerProperties properties) {
        super(clientFactory, properties);
        this.clientFactory = clientFactory;
        this.properties = properties;
    }

    @Override
    public int getOrder() {
        return super.getOrder()+1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI url = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        String schemePrefix = exchange.getAttribute(GATEWAY_SCHEME_PREFIX_ATTR);
        log.info("*****url: "+url.toString());
        log.info("*****schemePrefix: "+schemePrefix);
        return super.filter(exchange, chain);
    }
}
