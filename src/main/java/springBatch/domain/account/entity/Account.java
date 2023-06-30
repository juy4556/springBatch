package springbatch.domain.account.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springbatch.domain.entity.BaseEntity;
import springbatch.domain.entity.StatusType;
import springbatch.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String bankName;

    private String accountNumber;

    private Long balance;

    public Account(User user, String bankName, String accountNumber, Long balance) {
        super(StatusType.ACTIVATE.getStatus());
        this.user = user;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
