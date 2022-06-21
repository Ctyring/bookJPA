package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * role实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="role")
public class Role implements Serializable{

	@Id
	private Long id;//id


	
	private String chName;//ch_name
	private String des;//des
	private String name;//name

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
}
