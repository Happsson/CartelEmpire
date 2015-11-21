package nu.geeks.cartelempire;

import java.util.ArrayList;

/**
 * Created by hannespa on 15-11-21.
 */
public class Game {


    private int currentMoney;
    private int currentInfluence;
    private int zoomLevel;
    private int moneyIncrement;
    private int influenceIncrement;
    private ArrayList<Personnel> personnelList;

    public Game(){
        currentInfluence = 0;
        currentMoney = 0;
        zoomLevel = 1;
        personnelList = new ArrayList<>();
        moneyIncrement = 0;
    }


    public void tick(){
        currentMoney += moneyIncrement;
        currentInfluence += influenceIncrement;
    }

    public void uppdateMoneyincrement(){
        int calc = 0;
        for(Personnel p : personnelList){
            calc += p.getRevenue();
        }
        moneyIncrement = calc;
    }



}
