package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * upmsPost实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="e_upms_post")
public class UpmsPost implements Serializable{

	@Id
	private Long id;//id


	
	private String code;//code
	private String name;//name
	private Integer weight;//weight

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}


	
}
