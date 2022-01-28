package web;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Controller;
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
			HttpSession session) {
		String s =  "select m from Member m     " +
					"where m.email = :email     " +
					"and m.password = :password ";
		EntityManager manager = Common.getManager();
		Query query = manager.createQuery(s);
		query.setParameter("email", email);
		query.setParameter("password", Common.encrypt(password));
		ArrayList result = (ArrayList)query.getResultList();
		boolean success = true;
		if (result.size() == 1) {
			Member m = (Member)result.get(0);
			// TODO: Check member type, 
			// "unknown" must be activate before continue
			// some kind of member will see special page
			session.setAttribute("member", m);
		} else {
			success = false;
		}
		manager.close();
		return success ? "redirect:/member-profile" :
						 "redirect:/member-login";
	}
}
