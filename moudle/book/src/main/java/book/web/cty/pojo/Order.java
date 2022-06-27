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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//id

	private Float countPrice;//count_price
	private String orderTime;//order_time
	private Long userId;//user_id
	private int payment = 0;
	private int status = 0;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = OrderDetails.class)
	@JoinColumn(name = "order_id")
	@OrderBy
	private Set<OrderDetails> orderDetails;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

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

	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
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

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", countPrice=" + countPrice +
				", orderTime='" + orderTime + '\'' +
				", userId=" + userId +
				", payment=" + payment +
				", status=" + status +
				", orderDetails=" + orderDetails +
				'}';
	}
}
