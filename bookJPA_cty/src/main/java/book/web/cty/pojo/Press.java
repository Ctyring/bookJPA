package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * press实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="press")
public class Press implements Serializable{

	@Id
	private Long id;//id


	
	private String name;//name

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
}
