package com.practice.rosreestr.configuration;

import com.practice.rosreestr.annotation.WebSocketEndpoint;
import com.practice.rosreestr.handler.GlobalWebSocketControllerExceptionHandler;
import com.practice.rosreestr.wscontroller.WebSocketController;
import com.practice.rosreestr.handler.ControllerWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final List<WebSocketController> controllers;

    private final GlobalWebSocketControllerExceptionHandler exceptionHandler;

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry webSocketHandlerRegistry) {
        for (WebSocketController controller : controllers) {
            WebSocketEndpoint endpointAnnotation = controller.getClass().getAnnotation(WebSocketEndpoint.class);
            String endpointString = Objects.isNull(endpointAnnotation) ? "" : endpointAnnotation.value();
            webSocketHandlerRegistry.addHandler(
                    new ControllerWebSocketHandler(controller, exceptionHandler),
                    "/ws" + endpointString
            );
        }
    }

}
