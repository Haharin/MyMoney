package logic.parseGoal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class GoalParser {

    public GoalList parseList (String fileName){
        try{
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(GoalList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GoalList parsedTransactionList = (GoalList)jaxbUnmarshaller.unmarshal(file);

            return parsedTransactionList;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
