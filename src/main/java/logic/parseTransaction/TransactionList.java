package logic.parseTransaction;
import logic.StringToDateParser;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="transactions")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionList {

    @XmlElement(name="transaction")
    private List<Transaction> transactionList;

    public List<Transaction> getTransactionList(){
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> getTransactionListInPeriod(LocalDateTime start, LocalDateTime end){
        List<Transaction> temp = new ArrayList<Transaction>();
        for (Transaction transaction: transactionList) {
            LocalDateTime tempDate = StringToDateParser.doIt(transaction.getDate());
            if (tempDate.isAfter(start) && tempDate.isBefore(end)){
                temp.add(transaction);
            }
        }
        return temp;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransactionList{");
        sb.append("transactionList=").append(transactionList);
        sb.append('}');
        return sb.toString();
    }
}
