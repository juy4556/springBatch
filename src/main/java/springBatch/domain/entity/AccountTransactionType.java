package springbatch.domain.entity;

public enum AccountTransactionType {
    REMITTANCE(0, "REMITTANCE"),
    PAYMENT(1, "PAYMENT"),
    DEPOSIT(2, "DEPOSIT"),
    WITHDRAW(3, "WITHDRAW");
    final int number;
    final String type;

    AccountTransactionType(int number, String type) {
        this.number = number;
        this.type = type;
    }
}
