package travelPlanPJ.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
	
	@MessageMapping("/chat/{chatNum}")
	@SendTo("/topic/chat/{chatNum}")
	public String handleMessage(String message) {
		return message;
	}

}
