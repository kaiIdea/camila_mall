package com.camila.gateway.loadbalance;


import org.springframework.cloud.gateway.config.GatewayLoadBalancerProperties;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class VersionLoadBalancerConfiguration {


    @Bean
    public VersionLoadbalancer getVersionLoadbalancer(Environment environment,
                                                      LoadBalancerClientFactory loadBalancerClientFactory){
        System.out.println("***************************已加载自定义路由");
        String property = environment.getProperty(loadBalancerClientFactory.PROPERTY_NAME);
        return new VersionLoadbalancer(property,loadBalancerClientFactory.getLazyProvider(property, ServiceInstanceListSupplier.class));
    }

    @Bean
    public ReactiveLoadBalancerClientFilter reactiveLoadBalancerClientFilter(LoadBalancerClientFactory clientFactory,
                                                                             GatewayLoadBalancerProperties properties) {
        System.out.println();
        return new VersionLoadBalancerClientFilter(clientFactory, properties);
    }
}
