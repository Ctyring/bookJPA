package book.web.cty.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cart_id")
	@OrderBy
	private Set<CartDetails> cartDetails;
	
}
