package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * advertise实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="advertise")
public class Advertise implements Serializable{

	@Id
	private Long id;//id


	
	private String picture;//picture

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}


	
}
