package logic.parseTransaction;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {
    @XmlElement(name="name")
    private String transactionName;

    @XmlElement(name="descriprtion")
    private String transactionDescriprtion;

    @XmlAttribute (name="id")
    private int transactionID;

    @XmlElement (name="currency")
    private String currency;

    @XmlElement(name="amount")
    private Double transactionAmount;

    @XmlElement(name="date")
    private String date;

//    @XmlElement(name="direction")
//    private Boolean transactionDirection;

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionDescriprtion() {
        return transactionDescriprtion;
    }

    public void setTransactionDescriprtion(String transactionDescriprtion) {
        this.transactionDescriprtion = transactionDescriprtion;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    //    public Boolean getTransactionDirection() {
//        return transactionDirection;
//    }
//
//    public void setTransactionDirection(Boolean transactionDirection) {
//        this.transactionDirection = transactionDirection;
//    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Название: ").append(transactionName).append('\n');
        sb.append("Описание: ").append(transactionDescriprtion).append('\n');
        sb.append("Id:  ").append(transactionID).append('\n');
        sb.append("Валюта: ").append(currency).append('\n');
        sb.append("Сумма: ").append(transactionAmount).append('\n');
        sb.append("Дата: ").append(date);
        return sb.toString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
