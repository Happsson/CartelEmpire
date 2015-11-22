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
    private int[] slots;
    private int usableSlots;

    public Game(){
        currentInfluence = 0;
        currentMoney = 0;
        zoomLevel = 1;
        personnelList = new ArrayList<>();
        moneyIncrement = 0;
        slots = new int[100];
        usableSlots = 5;

    }

    public void zoomOut(){
        zoomLevel++;
        usableSlots *= 2;
    }

    public void tick(){
        currentMoney += moneyIncrement;
        currentInfluence += influenceIncrement;
    }

    public void updateMoneyincrement(){
        int calc = 0;
        for(Personnel p : personnelList){
            calc += p.revenue;
        }
        moneyIncrement = calc;
    }

    public void paySalary(Personnel p){
        currentMoney -= p.salary;

    }

    public void hirePersonnel(Personnel p){
        //pay first salary
        currentMoney -= p.salary;
        updateMoneyincrement();

    }



}
