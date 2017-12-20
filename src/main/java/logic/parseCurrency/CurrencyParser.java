package logic.parseCurrency;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class CurrencyParser {
    public CurrencyList parseList (String fileName){
        try{
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CurrencyList parsedTransactionList = (CurrencyList)jaxbUnmarshaller.unmarshal(file);

            return parsedTransactionList;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
