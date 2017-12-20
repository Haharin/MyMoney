package logic.parseGoal;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="goals")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoalList {

    @XmlElement(name="goal")
    private List<Goal> goalList;

    public List<Goal> getGoalList(){
        return goalList;
    }

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
    }

    public Goal findGoalById(Integer ind){
        for(Goal goal: goalList){
            if (goal.getId() == ind){
                return goal;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CurrencyList{");
        sb.append("goalList=").append(goalList);
        sb.append('}');
        return sb.toString();
    }

}
