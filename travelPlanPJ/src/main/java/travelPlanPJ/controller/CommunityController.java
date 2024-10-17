package travelPlanPJ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "community")
public class CommunityController {
	
	
	@RequestMapping(value = "communityHome", method = RequestMethod.GET)
	public String home() {
		return "thymeleaf/community/communityHome";
	}

	@RequestMapping(value = "boardList", method = RequestMethod.GET)
	public String boardList(Model model) {
		
		return "thymeleaf/community/boardList";
	}
}
