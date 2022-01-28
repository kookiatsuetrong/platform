package web;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity @Table(name="activates")
class Activate {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int code;
	
	@OneToOne
	Member member;
	
	String secret;
}
