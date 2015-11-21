package nu.geeks.cartelempire;

/**
 * Created by hannespa on 15-11-21.
 */
public class Personnel {


    private String type;
    private int salary;
    private int revenue;
    private int influence;

    public Personnel(String type, int salary, int revenue, int influence){

        this.type = type;

        this.salary = salary;
        this.revenue = revenue;
        this.influence = influence;

    }

    public int getRevenue(){
        return revenue - salary;
    }

    public int getInfluence(){
        return salary;
    }

    public void setInfluence(int influence){
        this.influence = influence;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }

    public void setRevenue(int revenue){
        this.revenue = revenue;
    }

}
