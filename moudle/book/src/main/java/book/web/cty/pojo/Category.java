package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * category实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="category")
public class Category implements Serializable{

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
