package springbatch.domain.account.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springbatch.domain.entity.BaseEntity;
import springbatch.domain.entity.StatusType;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private Long amount;

    private String receiverAccountNumber;

    public AccountTransaction(Account account, Long amount, String receiverAccountNumber) {
        super(StatusType.ACTIVATE.getStatus());
        this.account = account;
        this.amount = amount;
        this.receiverAccountNumber = receiverAccountNumber;
    }
}
