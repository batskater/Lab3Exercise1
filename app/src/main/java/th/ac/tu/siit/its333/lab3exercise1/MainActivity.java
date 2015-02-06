package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();
        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public void AddCourseClicked(View v){
        Intent i = new Intent(this, CourseActivity.class);
        startActivityForResult(i,1);
    }

    public void ShowCourseClicked(View v){
        Intent i = new Intent(this, CourseListActivity.class);
        String courseList = "";
        for (int j=0;j<listCodes.size();j++){
            courseList += listCodes.get(j) + "( " + listCredits.get(j).toString() + "Credits ) = " + listGrades.get(j) + "\n";
        }
        i.putExtra("courseList",courseList);
        startActivity(i);
    }
    public void ResetClicked(View v){
        listGrades.clear();
        listCredits.clear();
        listCodes.clear();
        calculate();

        TextView gpa = (TextView)findViewById(R.id.tvGPA);
        gpa.setText("0.0");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String code = data.getStringExtra("coursecode");
                int credit = data.getIntExtra("coursecredit",0);
                double grade = data.getDoubleExtra("grade",0.0);
                listCodes.add(code);
                listCredits.add(credit);
                listGrades.add(Double.toString(grade));

                calculate();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculate(){
        int i;
        cr = 0;
        gp = 0;
        gpa = 0;
        for(i=0;i<listCodes.size();i++){
            cr += listCredits.get(i);
            gp += Double.parseDouble(listGrades.get(i))*listCredits.get(i);
        }
        gpa = gp/cr;
        TextView tvGp = (TextView)findViewById(R.id.tvGP);
        TextView tvCr = (TextView)findViewById(R.id.tvCR);
        TextView tvGpa = (TextView)findViewById(R.id.tvGPA);

        tvGp.setText(Double.toString(gp));
        tvCr.setText(Integer.toString(cr));
        tvGpa.setText(Double.toString(gpa));
    }
}
