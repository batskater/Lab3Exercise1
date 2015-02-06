package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class CourseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
    }
    public void AddCourse(View v){
        double grade = 0;
        Intent res = new Intent();
        EditText cc = (EditText)findViewById(R.id.etCode);
        EditText cr = (EditText)findViewById(R.id.etCR);
        RadioGroup rg = (RadioGroup)findViewById(R.id.Radiogroup);
        //RadioButton rb = (RadioButton)findViewById(rg.getCheckedRadioButtonId());
        int gradesel = rg.getCheckedRadioButtonId();
        switch(gradesel){
            case R.id.rbA:
                grade = 4.0;
                break;
            case R.id.rbBP:
                grade = 3.5;
                break;
            case R.id.rbB:
                grade = 3.0;
                break;
            case R.id.rbCP:
                grade = 2.5;
                break;
            case R.id.rbC:
                grade = 2.0;
                break;
            case R.id.rbDP:
                grade = 1.5;
                break;
            case R.id.rbD:
                grade = 1.0;break;
            case R.id.rbF:
                grade = 0.0;
                break;
            default:
                break;
        }
        res.putExtra("coursecode", cc.getText().toString());
        res.putExtra("coursecredit", Integer.parseInt(cr.getText().toString()));
        res.putExtra("grade",grade);
        setResult(RESULT_OK, res);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);
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
}
