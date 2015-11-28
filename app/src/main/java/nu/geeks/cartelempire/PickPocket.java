package nu.geeks.cartelempire;

/**
 * Created by hannespa on 15-11-21.
 */
public class PickPocket extends Staff{

    String type = "PickPocket";
    int salary = 5;
    int influence = -1;
    int totalWork = 30;
    int revenue = 1;
    int capacity;
    int capacityLeft;

  //  public Staff(String type, int salary, int revenue, int influence, int totalWork) {

    public PickPocket(){
        super("PickPocket",5,1,-1,30);
        capacity = 30;
        capacityLeft=30;
    }

    public void tick(){
        super.tick();
        if(capacityLeft>0)capacityLeft--;
    }

}
