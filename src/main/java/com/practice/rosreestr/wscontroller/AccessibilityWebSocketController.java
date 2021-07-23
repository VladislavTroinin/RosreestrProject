package com.practice.rosreestr.wscontroller;

import com.practice.rosreestr.annotation.WebSocketEndpoint;
import com.practice.rosreestr.dto.AccessibilityDto;
import com.practice.rosreestr.exception.WebServiceNotAccessibleException;
import com.practice.rosreestr.service.AccessibilityServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
@WebSocketEndpoint("/accessibility")
public class AccessibilityWebSocketController implements WebSocketController {

    private final AccessibilityServiceImp accessibilityService;

    @Override
    public Object getAnswer(String payload) throws Exception {
        return accessibilityService.getAccessibility();
    }

}
