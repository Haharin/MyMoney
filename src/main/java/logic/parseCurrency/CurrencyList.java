package logic.parseCurrency;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="currencies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyList {

    @XmlElement(name="currency")
    private List<Currency> currencyList;

    public List<Currency> getCurrencyList(){
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CurrencyList{");
        sb.append("currencyList=").append(currencyList);
        sb.append('}');
        return sb.toString();
    }
}
