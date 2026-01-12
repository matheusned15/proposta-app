package com.nedware.proposta_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint para o handshake WebSocket/STOMP (ajuste o path conforme o front)
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*");
        // Se você usa SockJS no front:
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Habilita broker simples publicável no(s) prefixo(s) abaixo
        registry.enableSimpleBroker("/propostas");
        // Prefixo para destinos de app (quando enviar via @MessageMapping, etc.)
        registry.setApplicationDestinationPrefixes("/app");
    }
}

