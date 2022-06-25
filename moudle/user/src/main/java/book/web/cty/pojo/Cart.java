package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * cart实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="cart")
public class Cart implements Serializable{

	@Id
	private Long id;//id


	
	private Float countPrice;//count_price
	private Long userId;//user_id

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Float getCountPrice() {
		return countPrice;
	}
	public void setCountPrice(Float countPrice) {
		this.countPrice = countPrice;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}


	
}
