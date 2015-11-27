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
    private ArrayList<Staff> staffList;
    private int[] slots;
    private int usableSlots;


    private Map map;

    public Game(){
        currentInfluence = 0;
        currentMoney = 0;
        zoomLevel = 1;
        staffList = new ArrayList<>();
        moneyIncrement = 0;
        slots = new int[100];
        usableSlots = 5;

    }


    public void tick(){
        for(Staff s : staffList){
            s.tick();
        }
        currentMoney += moneyIncrement;
        currentInfluence += influenceIncrement;
    }

    public void updateMoneyIncrement(){
        int calc = 0;
        for(Staff p : staffList){
            calc += p.revenue;

        }
        moneyIncrement = calc;
    }

    public void paySalary(Staff p){
        currentMoney -= p.salary;

    }

    public void hirePersonnel(Staff p){
        //pay first salary
        currentMoney -= p.salary;
        updateMoneyIncrement();

    }

    public boolean addStaff(  int blockIndex,  Staff staff  ){
        if(  map.addStaff(  blockIndex,  staff  )==null  ){
            staffList.add(  staff  ); //assuming here that arraylist appends
            return true;
        }
        return false; //failed attempt to add new staff member
    }

    public boolean removeStaffOrBuilding(  int blockIndex  ){
        if( map.removeBlock(  blockIndex  )  ){

        }
        return false;
    }


}































