package book.web.cty.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author cty
 * @date 2022/6/25
 */
@Entity
@Table(name = "cart_details")
@Data
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//id
    private String ISBN;
    private String name;
    private Integer inventory = 0;
    private String des;
    private String bookCondition;
    private String author;
    private String category;
    private String press;
    private String version;
    private String picture;
    private Float purchasePrice;
    private Float countPrice;
    private Long cartId;//id
}
