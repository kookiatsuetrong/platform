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
	
	// http://localhost:8864/member-activate?code=4&secret=XYZ
	
	@RequestMapping("/member-activate")
	String activate(String secret, String code, Model model) {
		boolean success = true;
		EntityManager manager = Common.getManager();
		try {
			int number = Integer.parseInt(code);
			Activate a = manager.find(Activate.class, number);
			if (a == null) {
				success = false;
			} else {
				Member m = manager.find(Member.class, a.member.code);
				m.type = "member";
				manager.getTransaction().begin();
				manager.persist(m);
				manager.remove(a);
				manager.getTransaction().commit();
			}
		} catch (Exception e) { 
			success = false;
		}
		manager.close();
		
		if (success) {
			model.addAttribute("title", "Activation Succesfully");
			model.addAttribute("detail", "Your account has been activated");
		} else {
			model.addAttribute("title", "Activation Error");
			model.addAttribute("detail", "Your account has not been activated");
		}
		return "display";
	}
	
	@RequestMapping("/member-profile")
	String showProfilePage(HttpSession session) {
		Member m = (Member)session.getAttribute("member");
		if (m == null) {
			return "redirect:/member-login";
		} else {
			return "member-profile";
		}
	}
	
	@RequestMapping("/member-logout")
	String showLogOutPage(Model model, HttpSession session) {
		session.removeAttribute("member");
		session.invalidate();
		model.addAttribute("title", "Logged Out");
		model.addAttribute("detail", "You have been logged out successfully");
		return "display";
	}
}
