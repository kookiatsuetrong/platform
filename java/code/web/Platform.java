package web;
import javax.persistence.EntityManager;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class Platform {
	
	@RequestMapping("/")
	String showHome()
	{
		return "home";
	}
	
	@RequestMapping("/member-login")
	String showLogInPage()
	{
		return "login";
	}
	
	@RequestMapping("/member-logout")
	String showLogOutPage()
	{
		return "logout";
	}
}
