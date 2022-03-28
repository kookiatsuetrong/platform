package web;

import javax.servlet.http.HttpSession;
import javax.persistence.EntityManager;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class Platform {
	
	@RequestMapping("/")
	String showHome(HttpSession session) {
		return "home";
	}
	
}
