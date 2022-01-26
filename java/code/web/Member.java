package web;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity @Table(name="members")
public class Member {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int number;
	
	@Column(unique=true, nullable=false)
	public String email;
	
	@Column(nullable=false)
	public String password;
	
	@Column(name="first_name", nullable=false)
	public String firstName;
	
	@Column(name="last_name", nullable=false)
	public String lastName;
	
	@Column(nullable=false)
	public String type = "new"; // new, member, staff, administrator
	
	@ManyToOne
	public Group group;
	
	public boolean isAdministrator() {
		return "Administrator".equals(type);
	}
}

