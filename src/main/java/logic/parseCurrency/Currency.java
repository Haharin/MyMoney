package logic.parseCurrency;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;


@XmlRootElement(name = "currency")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency {

    private static ArrayList<Currency> currencyList = new ArrayList();

    @XmlAttribute(name="id")
    private int id;

    @XmlElement(name="name")
    private String name;

    @XmlElement(name="rate")
    private Double rate; // in RUR

    Currency(){
        currencyList.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<Currency> getCurrencyList() {
        return currencyList;
    }

    public static Currency findCurrencyByName(String name){
        Currency tmp = null;
        for (Currency currency: currencyList){
            if (currency.getName().equals(name)){
                tmp = currency;
                break;
            }
        }
        return tmp;
    }

    public static Currency findCurrencyByID(int id){
        Currency tmp = null;
        for (Currency currency: currencyList){
            if (currency.getId() == id){
                tmp = currency;
                break;
            }
        }
        return tmp;
    }

    public static Double convert(Double amount, String currencyName){
        return amount * findCurrencyByName(currencyName).getRate();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Currency{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", rate=").append(rate);
        sb.append('}');
        return sb.toString();
    }
}
