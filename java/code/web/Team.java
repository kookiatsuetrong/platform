package web;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity @Table(name="teams")
class Team {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int code;
	
	@Column(unique=true, nullable=false)
	String name;
	
	// @Column(name="email_server")
	// String emailServer;
	
	String type = "unknown";
}
