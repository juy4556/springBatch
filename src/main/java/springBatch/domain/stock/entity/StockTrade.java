package springbatch.domain.stock.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springbatch.domain.account.entity.Account;
import springbatch.domain.entity.BaseEntity;
import springbatch.domain.entity.StatusType;
import springbatch.domain.entity.StockTradeType;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockTrade extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private Long amount;

    private Long averagePrice;
    private StockTradeType type;

    public StockTrade(Long id, Account account, Stock stock, Long amount, Long averagePrice, StockTradeType type) {
        super(StatusType.ACTIVATE.getStatus());
        this.id = id;
        this.account = account;
        this.stock = stock;
        this.amount = amount;
        this.averagePrice = averagePrice;
        this.type = type;
    }
}
