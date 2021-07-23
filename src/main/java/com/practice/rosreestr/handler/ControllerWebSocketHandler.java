package com.practice.rosreestr.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.practice.rosreestr.wscontroller.WebSocketController;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

@RequiredArgsConstructor
public class ControllerWebSocketHandler extends TextWebSocketHandler implements WebSocketHandler {

    private final WebSocketController controller;

    private final Object exceptionHandler;

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
        try {
            Object answer = controller.getAnswer(message.getPayload());
            sendAnswer(session, answer);
        } catch (Exception e) {
            handleException(session, e);
        }
    }

    private void sendAnswer(WebSocketSession session, Object answer) {
        try {
            try {
                String answerJSON = new JsonMapper().writeValueAsString(answer);
                session.sendMessage(new TextMessage(answerJSON));
            } catch (JsonProcessingException e) {
                session.close(CloseStatus.SERVER_ERROR);
                e.printStackTrace();
            }
        } catch (IOException ignored) {

        }
    }

    private void handleException(WebSocketSession session, Exception exception) throws Exception {
        Method handlingMethod = findHandlingMethod(exception);
        if (Objects.isNull(handlingMethod)) {
            throw exception;
        }
        Object answer = handlingMethod.invoke(exceptionHandler, exception);
        sendAnswer(session, answer);
    }

    private Method findHandlingMethod(Exception exception) {
        Method[] handleMethods = exceptionHandler.getClass().getMethods();
        for (Method method : handleMethods) {
            ExceptionHandler annotation = method.getAnnotation(ExceptionHandler.class);
            if (Objects.nonNull(annotation) && checkMethod(annotation, exception)) {
                return method;
            }
        }
        return null;
    }

    private boolean checkMethod(ExceptionHandler annotation, Exception exception) {
        Class<? extends Throwable>[] handlingExceptions = annotation.value();
        for (Class<? extends Throwable> handlingException : handlingExceptions) {
            if (handlingException.equals(exception.getClass())) {
                return true;
            }
        }
        return false;
    }

}
