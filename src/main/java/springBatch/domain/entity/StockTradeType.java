package springbatch.domain.entity;

import lombok.Getter;

@Getter
public enum StockTradeType {
    BUY(0, "BUY"),
    SELL(1, "SELL");

    final int number;
    final String type;

    StockTradeType(int number, String type) {
        this.number = number;
        this.type = type;
    }
}
