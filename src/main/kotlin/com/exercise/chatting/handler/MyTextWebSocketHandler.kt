package com.exercise.chatting.handler

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class MyTextWebSocketHandler : TextWebSocketHandler() {

    private val logger = LoggerFactory.getLogger(MyTextWebSocketHandler::class.java)

    private val sessionMap = HashMap<String, WebSocketSession>()

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        logger.info("Message From Client : ${message.payload}")
        sessionMap.forEach { (_, v) ->
            v.sendMessage(message)
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        logger.info("Session Established : ${session.id}")
        sessionMap[session.id] = session
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        logger.info("Session Closed : ${sessionMap.remove(session.id)}")
    }

}