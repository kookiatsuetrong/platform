package web;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class Register {
	
	@RequestMapping("/member-register")
	String showRegisterPage()
	{
		// TODO: If this user has been login, 
		// redirect to his or her profile page
		return "register";
	}
	
	@PostMapping("/member-register")
	String registerNewMember(
			String email,
			@RequestParam("first-name") String first,
			@RequestParam("last-name") String last,
			String password)
	{
		// member_register_invalid_email
		// member_register_invalid_first_name
		// member_register_invalid_last_name
		// member_register_invalid_password
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";

		boolean p0 = email   .matches("^(.+)@(.+)$");
		boolean p1 = password.matches(pattern);
		boolean p2 = first   .matches("^.{2,20}$");
		boolean p3 = last    .matches("^.{2,20}$");
		
		if (p0 || p1 || p2 || p3) {
			return "redirect:/member-register-error";
		}

		Member m    = new Member();
		m.email     = email;
		m.firstName = first;
		m.lastName  = last;
		m.password  = Common.encrypt(password);
		boolean success = true;
		EntityManager manager = Storage.getManager();
		try {
			manager.getTransaction().begin();
			manager.persist(m);
			manager.getTransaction().commit();
		} catch (Exception e) {
			success = false;
		}
		manager.close();
		
		if (success) {
			// TODO: Send activation code
			// but if system email is not set, 
			// auto activate the account
			return "redirect:/member-register-success";
		} else {
			// member_register_duplicated_email
			return "redirect:/member-register-error";
		}
	}
	
	@RequestMapping("/member-register-success")
	String showRegisterSuccess(Model m) {
		m.addAttribute("title", "Registration Successfully");
		m.addAttribute("detail", "Please go to you mailbox to activate " +
								 "your account.");
		return "display";
	}
	
	@RequestMapping("/member-register-error")
	String showRegisterError(Model m) {
		m.addAttribute("title", "Registration Failed");
		m.addAttribute("detail", "Unable to register with your email.");
		return "display";
	}
}

/*
^                 # start-of-string
(?=.*[0-9])       # a digit must occur at least once
(?=.*[a-z])       # a lower case letter must occur at least once
(?=.*[A-Z])       # an upper case letter must occur at least once
(?=.*[!@#$%^&*])  # a special character must occur at least once
(?=\S+$)          # no whitespace allowed in the entire string
.{8,}             # anything, at least eight places though
$                 # end-of-string

Your password must contain:
At least one upper case English letter (i.e. A-Z)
At least one lower case English letter (i.e. a-z)
At least one digit (i.e. 0-9)
Minimum eight characters in length

// ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\S+$).{8,}$
String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";

boolean p0 = email   .matches("^(.+)@(.+)$");
boolean p1 = password.matches(passwordPattern);
boolean p2 = first   .matches("^.{2,20}$");
boolean p3 = last    .matches("^.{2,20}$");
*/
