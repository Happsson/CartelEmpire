package nu.geeks.cartelempire;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

public class Main extends Activity implements View.OnClickListener {



    Button menuB;
    TextView moneyTV, influenceTV;
    Game game;
    ListView list;
    ProgressBar ppick1, ppick2, ppick3, ppick4, ppick5, ppot1, ppot2,ppot3,ppot4,ppot5;
    ImageView pick1, pick2, pick3, pick4, pick5, pot1, pot2, pot3, pot4, pot5;

    ArrayList<ProgressBar> potBars;
    ArrayList<ProgressBar> pickBars;
    ArrayList<ImageView> potViews;
    ArrayList<ImageView> pickViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        game = new Game();
        setContentView(R.layout.activity_main);
        potViews = new ArrayList<>();
        pickViews = new ArrayList<>();
        potBars = new ArrayList<>();
        pickBars = new ArrayList<>();

        menuB = (Button) findViewById(R.id.menuButton);
        moneyTV =(TextView) findViewById(R.id.money);
        influenceTV=(TextView) findViewById(R.id.influence);

        initializeStaffViews();
        animation();
        createTicker();
        menuB.setOnClickListener(this);


    }

    private void initializeStaffViews() {
        pick1 = (ImageView) findViewById(R.id.pick1);
        pick2 = (ImageView) findViewById(R.id.pick2);
        pick3 = (ImageView) findViewById(R.id.pick3);
        pick4 = (ImageView) findViewById(R.id.pick4);
        pick5 = (ImageView) findViewById(R.id.pick5);

        pot1 = (ImageView) findViewById(R.id.pot1);
        pot2 = (ImageView) findViewById(R.id.pot2);
        pot3 = (ImageView) findViewById(R.id.pot3);
        pot4 = (ImageView) findViewById(R.id.pot4);
        pot5 = (ImageView) findViewById(R.id.pot5);

        ppick1 = (ProgressBar) findViewById(R.id.ppick1);
        ppick2 = (ProgressBar) findViewById(R.id.ppick2);
        ppick3 = (ProgressBar) findViewById(R.id.ppick3);
        ppick4 = (ProgressBar) findViewById(R.id.ppick4);
        ppick5 = (ProgressBar) findViewById(R.id.ppick5);

        ppot1 = (ProgressBar) findViewById(R.id.ppot1);
        ppot2 = (ProgressBar) findViewById(R.id.ppot2);
        ppot3 = (ProgressBar) findViewById(R.id.ppot3);
        ppot4 = (ProgressBar) findViewById(R.id.ppot4);
        ppot5 = (ProgressBar) findViewById(R.id.ppot5);

        ppick1.setVisibility(View.INVISIBLE);
        ppick2.setVisibility(View.INVISIBLE);
        ppick3.setVisibility(View.INVISIBLE);
        ppick4.setVisibility(View.INVISIBLE);
        ppick5.setVisibility(View.INVISIBLE);

        ppot1.setVisibility(View.INVISIBLE);
        ppot2.setVisibility(View.INVISIBLE);
        ppot3.setVisibility(View.INVISIBLE);
        ppot4.setVisibility(View.INVISIBLE);
        ppot5.setVisibility(View.INVISIBLE);

        potBars.add(ppot1);
        potBars.add(ppot2);
        potBars.add(ppot3);
        potBars.add(ppot4);
        potBars.add(ppot5);

        pickBars.add(ppick1);
        pickBars.add(ppick2);
        pickBars.add(ppick3);
        pickBars.add(ppick4);
        pickBars.add(ppick5);


        pick1.setVisibility(View.INVISIBLE);
        pick2.setVisibility(View.INVISIBLE);
        pick3.setVisibility(View.INVISIBLE);
        pick4.setVisibility(View.INVISIBLE);
        pick5.setVisibility(View.INVISIBLE);
        pot1.setVisibility(View.INVISIBLE);
        pot2.setVisibility(View.INVISIBLE);
        pot3.setVisibility(View.INVISIBLE);
        pot4.setVisibility(View.INVISIBLE);
        pot5.setVisibility(View.INVISIBLE);
        potViews.add(pot1);
        potViews.add(pot2);
        potViews.add(pot3);
        potViews.add(pot4);
        potViews.add(pot5);
        pickViews.add(pick1);
        pickViews.add(pick2);
        pickViews.add(pick3);
        pickViews.add(pick4);
        pickViews.add(pick5);

        for(ImageView v : pickViews){
            v.setOnClickListener(this);
        }
        for(ImageView v : potViews){
            v.setOnClickListener(this);
        }
    }

    private void handleMenuClick(View v) {


                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                final MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.action_hireDealer:

                                for(int i = 0; i  < potViews.size(); i++){
                                    if(potViews.get(i).getVisibility() == View.INVISIBLE){
                                        game.addStaff(potViews.get(i).getId(), new PotDealer());
                                        potViews.get(i).setVisibility(View.VISIBLE);
                                        potBars.get(i).setVisibility(View.VISIBLE);
                                        potBars.get(i).setMax(game.staffHashMap.get(potViews.get(i).getId()).totalWork);
                                        potBars.get(i).setProgress(game.staffHashMap.get(potViews.get(i).getId()).remainingWork);
                                        break;
                                    }
                                }

                                game.updateMoneyIncrement();
                                //staffAdapter.notifyDataSetChanged();
                                break;

                            case R.id.action_hirePickPocket:

                                for(int i = 0; i  < pickViews.size(); i++){
                                    if(pickViews.get(i).getVisibility() == View.INVISIBLE){
                                        game.addStaff(pickViews.get(i).getId(), new PickPocket());
                                        pickViews.get(i).setVisibility(View.VISIBLE);
                                        pickBars.get(i).setVisibility(View.VISIBLE);
                                        pickBars.get(i).setMax(game.staffHashMap.get(pickViews.get(i).getId()).totalWork);
                                        pickBars.get(i).setProgress(game.staffHashMap.get(pickViews.get(i).getId()).remainingWork);
                                        break;
                                    }
                                }

                                game.updateMoneyIncrement();
                                break;

                            case R.id.action_hireProstitute:
                                game.addStaff(0,new Prostitute());
                        }

                        return false;
                    }
                });
    }






    void createTicker(){
        CountDownTimer ticker = new CountDownTimer(300, 1 ) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                game.tick();
              //  staffAdapter.notifyDataSetChanged();
                updateGraphics();
               start();
            }
        }.start();
    }


    void updateGraphics(){
        moneyTV.setText("Money: "+game.currentMoney);
        influenceTV.setText("Influence: "+game.currentInfluence);
        for(int i = 0; i < pickBars.size(); i++){
            if(pickBars.get(i).getVisibility() == View.VISIBLE){
                pickBars.get(i).setProgress(game.staffHashMap.get(pickViews.get(i).getId()).remainingWork);
            }
        }
        for(int i = 0; i < potBars.size(); i++){
            if(potBars.get(i).getVisibility() == View.VISIBLE){
                potBars.get(i).setProgress(game.staffHashMap.get(potViews.get(i).getId()).remainingWork);
            }
        }
    }

    void animation(){
        CountDownTimer anim = new CountDownTimer(40,1) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                for(ImageView v : potViews){
                    Random r = new Random();
                    float rot = r.nextFloat() * 4;
                    v.setRotation(v.getRotation() + rot-2);
                }
                for(ImageView v : pickViews){
                    Random r = new Random();
                    float rot = r.nextFloat() *4;
                    v.setRotation(v.getRotation() + rot-2);
                }
            start();
            }
        }.start();
    }

    void handleStaffClick(View v){

        if(v.getVisibility() == View.VISIBLE){

            final Staff s = game.staffHashMap.get(v.getId());
            new AlertDialog.Builder(Main.this)
                    .setTitle(s.type)
                    .setMessage("Salary: " + s.salary + "\nWork remaining: " + s.remainingWork + "\nRevenue: " + s.revenue)
                    .setPositiveButton("Pay salary", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            game.paySalary(s);
                           // s.paySalary();
                        }
                    })
                    .show();
        }

    }



    @Override
    public void onClick(View v) {
       // Log.d("STAFF", "click");
        switch(v.getId()){
            case R.id.menuButton:
                handleMenuClick(v);
                break;

            case R.id.pick1:
            case R.id.pick2:
            case R.id.pick3:
            case R.id.pick4:
            case R.id.pick5:
            case R.id.pot1:
            case R.id.pot2:
            case R.id.pot3:
            case R.id.pot4:
            case R.id.pot5:
                handleStaffClick(v);
                break;

        }
    }
}
