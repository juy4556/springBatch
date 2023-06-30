package springbatch.domain.stock.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springbatch.domain.entity.BaseEntity;
import springbatch.domain.entity.StatusType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "stock")
    private final List<StockPrice> stockPrices = new ArrayList<>();

    public Stock(String name) {
        super(StatusType.ACTIVATE.getStatus());
        this.name = name;
    }
}
