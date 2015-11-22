package nu.geeks.cartelempire;

/**
 * Created by hannespa on 15-11-21.
 */
public class Personnel {


    String type;
    int salary;
    int revenue;
    int influence;
    int remainingWork;

    public Personnel(String type, int salary, int revenue, int influence, int remainingWork) {

        this.type = type;

        this.salary = salary;
        this.revenue = revenue;
        this.influence = influence;
        this.remainingWork = remainingWork;

    }
}
