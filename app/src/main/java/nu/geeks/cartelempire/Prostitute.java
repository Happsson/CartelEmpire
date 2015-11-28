package nu.geeks.cartelempire;

/**
 * Created by roddan on 11/28/2015.
 */
public class Prostitute extends Staff{

    String type = "Prostitute";
    int salary = 100;
    int influence = 10;
    int totalWork = 20;
    int revenue = 2;
    int capacity;
    int capacityLeft;

    //public Staff(String type, int salary, int revenue, int influence, int totalWork) {
    public Prostitute(){
        super("Prostitute",100,2,10,20);
        capacity = 20;
        capacityLeft=20;
    }

    public void tick(){
        super.tick();
        if(capacityLeft>0)capacityLeft--;
    }
}
