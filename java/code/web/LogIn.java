package web;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class LogIn {
	
	@RequestMapping("/member-login")
	String showLogInPage(HttpSession session) {
		Member m = (Member)session.getAttribute("member");
		if (m == null) {
			return "member-login";
		} else {
			return "redirect:/member-profile";
		}
	}
	
	@PostMapping("/member-login")
	String checkPassword(
			String email, 
			String password,
			HttpSession session,
			Model model) {
		String s =  "select m from Member m     " +
					"where m.email = :email     " +
					"and m.password = :password ";
		EntityManager manager = Common.getManager();
		Query query = manager.createQuery(s);
		query.setParameter("email", email);
		query.setParameter("password", Common.encrypt(password));
		ArrayList result = (ArrayList)query.getResultList();
		boolean success = false;
		if (result.size() == 1) {
			Member m = (Member)result.get(0);
			switch (m.type) {
				case "unknown":					
					model.addAttribute("title",  "Unable to log in");
					model.addAttribute("detail", "Please activate from your " +
												"email before continue.");
					break;
				default:
					success = true;
					session.setAttribute("member", m);
			}
		} else {
			model.addAttribute("title",  "Unable to log in");
			model.addAttribute("detail", "Invalid email or password. " +
									"Please click <a href='/member-login'>" +
									"here</a> to try again.");
		}
		manager.close();
		
		if (success) {
			return "redirect:/member-profile";
		} else {
			return "display";
		}
	}
}
