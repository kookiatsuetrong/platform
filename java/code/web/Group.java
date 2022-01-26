package web;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity @Table(name="groups")
class Group {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int number;
	
	@Column(unique=true, nullable=false)
	String name;
	
	String type = "unknown";
}
