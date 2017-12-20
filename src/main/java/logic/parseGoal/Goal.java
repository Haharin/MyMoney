package logic.parseGoal;

import logic.parseMoney.Money;
import logic.parseCurrency.Currency;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;

@XmlRootElement(name = "goalSum")
@XmlAccessorType(XmlAccessType.FIELD)
public class Goal {

    @XmlAttribute(name="id")
    private int id;

    @XmlElement(name="name")
    private String name;

    @XmlElement(name="goalSum")
    private Double goalSum;    //Цель

    @XmlElement(name="actualDate")
    private String actualDateString;            //Реальная дата достижения цели

    @XmlElement(name="complete")
    private Boolean complete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Double getGoalSum() {
        return goalSum;
    }

    public void setGoalSum(Double goalSum) {
        this.goalSum = goalSum;
    }

    public Boolean getComplete() {
        return complete;
    }


    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Цель ");
        sb.append("номер ").append(id);
        sb.append(", \'").append(name).append('\'');
        sb.append(". Сумма: ").append(goalSum);
        if(complete){
            sb.append(" Цель достигнута, поздравляем! ");
            sb.append(actualDateString).append(".");
        }
        return sb.toString();
    }

    public void setActualDateString(String actualDateString) {
        this.actualDateString = actualDateString;
    }

    public String getActualDateString() {
        return actualDateString;
    }
}
