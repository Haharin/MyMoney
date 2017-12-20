package logic.parseTransaction;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class TransactionParser {

//    public Transaction parse(String fileName){
//        try{
//            File file = new File(fileName);
//            JAXBContext jaxbContext = JAXBContext.newInstance(Transaction.class);
//
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            return (Transaction) jaxbUnmarshaller.unmarshal(file);
//
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TransactionList parseList (String fileName){
        try{
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(TransactionList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            TransactionList parsedTransactionList = (TransactionList)jaxbUnmarshaller.unmarshal(file);

            return parsedTransactionList;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
