package com.kucom.simsim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@logExecutionTime
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.info("/ (GET)");
		return "index";
	}
	
	@logExecutionTime
	@GetMapping(value = "/login")
	public void login() {
		logger.info("/login (GET)");
	}
	
	@GetMapping(value = "/SimChat")
	public String GetChatting() {
		logger.info("/SimChat (GET)");
		return "ChatForm";
	}
}
