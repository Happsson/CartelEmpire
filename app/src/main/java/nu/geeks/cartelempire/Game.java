package nu.geeks.cartelempire;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hannespa on 15-11-21.
 */
public class Game {


     int currentMoney;
     int currentInfluence;
    private int zoomLevel;
    private int moneyIncrement;
    private int influenceIncrement;
    ArrayList<Staff> staffList;
    HashMap<Integer, Staff> staffHashMap;
    ArrayList<Integer> staffIds;

    private int[] slots;
    private int usableSlots;




    public Game(){
        currentInfluence = 0;
        currentMoney = 10;
        zoomLevel = 1;
        staffList = new ArrayList<>();
        staffHashMap = new HashMap<>();
        staffIds = new ArrayList<>();
        moneyIncrement = 0;
        slots = new int[100];
        usableSlots = 5;


    }

    /**
     * Tjo
     */
    public void tick(){
        updateMoneyIncrement();
        for(Integer id : staffIds){
            staffHashMap.get(id).tick();
            //staffHashMap.get(id).paySalary();
           // Log.d("STAFF", s.type);
        }
        currentMoney += moneyIncrement;
        currentInfluence += influenceIncrement;
    }

    public void updateMoneyIncrement(){
        int calc = 0;
        for(Integer id : staffIds){
            if(staffHashMap.get(id).remainingWork > 0) calc += staffHashMap.get(id).revenue;
        }
        moneyIncrement = calc;
    }

    public void paySalary(Staff p){
        if(currentMoney>=p.salary){
            if(p.paySalary()){
                currentMoney-=p.salary;
            }
        }
    }



    public boolean addStaff(  int id,  Staff staff  ){
        if(!staffHashMap.containsKey(id)){
            staffHashMap.put(id, staff);
            staffIds.add(id);
            currentMoney -= staff.salary;
            return true;
        }
        return false;

        /*
        if(  map.addStaff(  blockIndex,  staff  )==null  ){
            if(currentMoney >= staff.salary) {
                staffList.add(staff); //assuming here that arraylist appends
                currentMoney -= staff.salary;
            }
            return true;
        }
        return false; //failed attempt to add new staff member

    */
    }




}































