package logic.parseMoney;

import logic.StringToDateParser;
import logic.parseCurrency.Currency;
import logic.parseTransaction.Transaction;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@XmlRootElement(name = "wallet")
@XmlAccessorType(XmlAccessType.FIELD)
public class Money {
    public Money(){
        setAmount(0.0);
        currency = Currency.findCurrencyByID(0).getName();
        lastUpdate = StringToDateParser.doIt("20-01-1996-01-01-00");               // Убрать!
        lastUpdateString = StringToDateParser.doBack(lastUpdate);

    }
    @XmlElement(name="amount")
    private Double amount;

    @XmlElement(name="lastUpdate")
    private String lastUpdateString;

    @XmlElement(name="currency")
    private String currency;

    @XmlTransient
    private LocalDateTime lastUpdate;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    private void fix(){
        lastUpdate = StringToDateParser.doIt(lastUpdateString);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateString() {
        return lastUpdateString;
    }

    public void setLastUpdateString(String lastUpdateString) {
        this.lastUpdateString = lastUpdateString;
    }

    public void update(List<Transaction> transactions){
        fix();
        Integer count = 0;
        Double temp, tempAmount = 0.0;
        for (Transaction transaction: transactions) {
            if(StringToDateParser.doIt(transaction.getDate()).isAfter(lastUpdate)){
                temp = Currency.convert(transaction.getTransactionAmount(), transaction.getCurrency());
                System.out.println(lastUpdate);
                amount += temp;
                tempAmount += temp;
                count++;
            }
        }
        lastUpdate = LocalDateTime.now();
        lastUpdateString = StringToDateParser.doBack(lastUpdate);
        System.out.println("Количество новых транзакций - " + count + ".");
        if(count > 0){
            System.out.format("На общую сумму %.2f RUR.\n", tempAmount);
        }
    }
}
