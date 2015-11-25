package nu.geeks.cartelempire;

/**
 * Created by Rodriguez on 2015-11-25.
 */
public class Block {

    boolean blockInUse;
    int blockNumber;
    Staff staff;
    Building building;

    public Block(int blockNumber){
        this.blockNumber = blockNumber;
        blockInUse = false;
        staff = null;
        building = null;
    }

    public boolean isStaffBlock(){
        return staff != null;
    }

    public boolean isBuildingBlock(){
        return building != null;
    }

    //returns false if already occupied
    public boolean addBuilding(Building building){
        if(blockInUse) return false;
        this.building = building;
        blockInUse = true;
        return true;
    }

    public boolean addStaff(Staff staff){
        if(blockInUse) return false;
        this.staff = staff;
        blockInUse = true;


        return true;
    }

    public void cleanBlock(){
        staff = null;
        building = null;
        blockInUse = false;
    }

}
