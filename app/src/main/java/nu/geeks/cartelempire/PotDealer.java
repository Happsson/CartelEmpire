package nu.geeks.cartelempire;

/**
 * Created by hannespa on 15-11-21.
 */
public class PotDealer extends Staff{


    String type = "PotDealer";
    int salary = 10;
    int influence = 1;
    int totalWork = 100;
    int revenue = 1;
    int capacity;
    int capacityLeft;

    public PotDealer() {
        //Call super with values.
        super("PotDealer",10,1,1,15);
        capacity = 100;
        capacityLeft = 100;
    }

    @Override
    public void tick() {
        super.tick();
        if(capacityLeft > 0) capacityLeft--;
    }
}
