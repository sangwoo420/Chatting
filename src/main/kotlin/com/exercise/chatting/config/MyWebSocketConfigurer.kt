package com.exercise.chatting.config

import com.exercise.chatting.handler.MyTextWebSocketHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class MyWebSocketConfigurer(private val myTextWebSocketHandler: MyTextWebSocketHandler) : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(myTextWebSocketHandler, "/ws")
            .setAllowedOriginPatterns("*")
    }

}