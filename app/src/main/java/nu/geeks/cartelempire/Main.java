package nu.geeks.cartelempire;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

public class Main extends Activity implements View.OnClickListener {



    Button menuB;
    TextView moneyTV, influenceTV;
    Game game;
    ListView list;

    ImageView pick1, pick2, pick3, pick4, pick5, pot1, pot2, pot3, pot4, pot5;


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

        menuB = (Button) findViewById(R.id.menuButton);
        moneyTV =(TextView) findViewById(R.id.money);
        influenceTV=(TextView) findViewById(R.id.influence);

        initializeStaffViews();




        /*
        staffAdapter = new ArrayAdapter<Staff>(this, android.R.layout.simple_list_item_2, android.R.id.text1, game.staffList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(game.staffList.get(position).type + " remaining work: " + game.staffList.get(position).remainingWork);
                text2.setText("Earns: " + game.staffList.get(position).revenue + " Salary: -" + game.staffList.get(position).salary);

                return view;
            }
        };

        list.setAdapter(staffAdapter);
        */
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
        /*
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                game.staffList.get(position).paySalary();
            }
        });
        */

                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                final MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.action_hireDealer:

                                for(ImageView v : potViews){
                                    if(v.getVisibility() == View.INVISIBLE){
                                        game.addStaff(v.getId(), new PotDealer());
                                        v.setVisibility(View.VISIBLE);
                                        break;
                                    }
                                }

                                game.updateMoneyIncrement();
                                //staffAdapter.notifyDataSetChanged();
                                break;

                            case R.id.action_hirePickPocket:

                                for(ImageView v : pickViews){
                                    if(v.getVisibility() == View.INVISIBLE){
                                        game.addStaff(v.getId(), new PickPocket());
                                        v.setVisibility(View.VISIBLE);
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
                            s.paySalary();
                        }
                    })
                    .show()
        }
            game.staffHashMap.get(v.getId()).paySalary();
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
