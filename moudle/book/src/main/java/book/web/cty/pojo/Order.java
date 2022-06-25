package book.web.cty.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * order实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="sell_order")
public class Order implements Serializable{

	@Id
	private Long id;//id

	private Float countPrice;//count_price
	private java.util.Date orderTime;//order_time
	private Long userId;//user_id
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "order_id")
	@OrderBy
	private Set<OrderDetails> orderDetails;
	
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

	public java.util.Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(java.util.Date orderTime) {
		this.orderTime = orderTime;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
