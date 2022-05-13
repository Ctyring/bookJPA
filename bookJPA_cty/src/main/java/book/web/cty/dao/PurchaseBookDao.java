package book.web.cty.dao;

import book.web.cty.pojo.Book;
import book.web.cty.pojo.PurchaseBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PurchaseBookDao extends JpaRepository<PurchaseBook,Long>, JpaSpecificationExecutor<PurchaseBook> {
}
