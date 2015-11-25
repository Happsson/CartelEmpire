package nu.geeks.cartelempire;

/**
 * Created by Rodriguez on 2015-11-25.
 */
public class Staff {

    String type;
    int salary;
    int revenue;
    int influence;
    int remainingWork;
    int totalWork;


    public Staff(String type, int salary, int revenue, int influence, int totalWork) {
        this.type = type;
        this.salary = salary;
        this.revenue = revenue;
        this.influence = influence;
        this.remainingWork = totalWork;
        this.totalWork = totalWork;
    }

    public void tick(){
        if(remainingWork > 0){
            remainingWork--;
        }
    }

    public boolean paySalary(){
        if(remainingWork > 0){
            return false;
        }else{
            remainingWork = totalWork;
            return true;
        }
    }

}
