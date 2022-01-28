package web;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity @Table(name="members")
public class Member {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int code;
	
	@Column(unique=true, nullable=false)
	public String email;
	
	@Column(nullable=false)
	String password;
	
	@Column(name="first_name", nullable=false)
	String firstName;
	
	@Column(name="last_name", nullable=false)
	String lastName;
	
	@Column(nullable=false)
	String type = "unknown"; // unknown, member, staff, administrator
	
	@ManyToOne
	Team team;
	
	public boolean isAdministrator() { return "Administrator".equals(type); }
	public String  getFirstName()    { return firstName;                    }
	public String  getLastName()     { return lastName;                     }
	public String  getEmail()        { return email;                        }
	public String  getType()         { return type;                         }
	public Team    getTeam()         { return team;                         }
}

