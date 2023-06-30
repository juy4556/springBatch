package springbatch.domain.stock.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springbatch.domain.entity.BaseEntity;
import springbatch.domain.entity.StatusType;
import springbatch.domain.stock.entity.Stock;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockPrice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;
    private Float price;

    public StockPrice(Stock stock, Float price) {
        super(StatusType.ACTIVATE.getStatus());
        this.stock = stock;
        this.price = price;
        stock.getStockPrices().add(this);
    }
}
