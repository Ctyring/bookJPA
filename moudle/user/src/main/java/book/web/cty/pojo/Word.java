package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * word实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="user_word")
public class Word implements Serializable{

	@Id
	private Long id;//id


	
	private String tag;//tag
	private Long userId;//user_id

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}


	
}
