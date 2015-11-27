package nu.geeks.cartelempire;

import java.util.ArrayList;

/**
 * Created by Rodriguez on 2015-11-25.
 */
public class Map {

    private int zoom;
    private ArrayList<Block> blocks;

    public Map(){
        blocks = new ArrayList<Block>();
        zoom = 1;
    }

    public Staff addStaff(int blockIndex ,Staff staff){
        Block currentBlock=blocks.get(blockIndex);
        if(currentBlock.blockInUse)return null;

        currentBlock.addStaff(staff);

        return staff;
    }

    public boolean addBuilding(int blockIndex, Building building){
        Block currentBlock = blocks.get(blockIndex);
        if(currentBlock.blockInUse)return false;

        currentBlock.addBuilding(building);

        return true;
    }

    public boolean removeBlock(int index){
        if(!blocks.get(index).blockInUse) return false;
        blocks.get(index).cleanBlock();
        return true;
    }


    public ArrayList<Block> getBlocks() {
        return blocks;
    }

}
