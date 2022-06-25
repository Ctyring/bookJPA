package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
/**
 * book实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="book")
public class Book implements Serializable{

	@Id
	private Long id;
	private String isbn;//isbn

	private String category;//category
	private String des;//des
	private Integer inventory;//inventory
	private String name;//name
	private String picture;//picture
	private String press;//press
	private Float salePrice;//price
	private Float purchasePrice;//price
	private Boolean isPurchase;//is_purchase
	private Boolean isSale;//is_sale

	@Transient
	private String categoryName;
	@Transient
	private String pressName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String inventoryName) {
		this.categoryName = inventoryName;
	}

	public String getPressName() {
		return pressName;
	}

	public void setPressName(String pressName) {
		this.pressName = pressName;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}

	public Float getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
	}

	public Float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Boolean getIsPurchase() {
		return isPurchase;
	}
	public void setIsPurchase(Boolean isPurchase) {
		this.isPurchase = isPurchase;
	}

	public Boolean getIsSale() {
		return isSale;
	}
	public void setIsSale(Boolean isSale) {
		this.isSale = isSale;
	}


}
