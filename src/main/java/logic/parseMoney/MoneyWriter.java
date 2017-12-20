package logic.parseMoney;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class MoneyWriter {
    public void write(Money money, String fileName) {
        try {

            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Money.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(money, file);
            //jaxbMarshaller.marshal(money, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
