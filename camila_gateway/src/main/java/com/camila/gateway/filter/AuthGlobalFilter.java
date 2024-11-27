package com.camila.gateway.filter;

import cn.hutool.core.text.AntPathMatcher;
import com.camila.common.utils.CollUtils;
import com.camila.gateway.config.AuthProperties;
import com.camila.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthProperties.class)
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final JwtTool jwtTool;
    private final AuthProperties authProperties;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();




    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if(isExclude(request.getPath().toString())){
            return chain.filter(exchange);
        }
        String token = null;
        List<String> list = request.getHeaders().get("authorization");
        if(!CollUtils.isEmpty(list)){
            token = list.get(0);
        }
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        log.info("**用户ID:{}",userId);
        //将用户Id,加入网关请求后续的微服务的请求头中
        //微服务收到请求后，从头中取出，即可使用
        String finalUserId = userId.toString();
        ServerWebExchange build = exchange.mutate().request(data -> data.header("userId", finalUserId))
                .build();
        return chain.filter(build);
    }

    private boolean isExclude(String path) {
        for (String excludePath : authProperties.getExcludePaths()) {
            if (antPathMatcher.match(excludePath,path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
