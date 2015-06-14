package com.example.ibrahiemmohammad.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibrahiemmohammad.myapplication.school.Address;
import com.example.ibrahiemmohammad.myapplication.school.ParentInformation;
import com.example.ibrahiemmohammad.myapplication.school.PersonalDetail;

import org.w3c.dom.Text;


public class StudentdetailActivityActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentdetail_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        //Bundle studentBundle = getIntent().getExtras();
       // String name = studentBundle.getString("name");
        //Parcelable student = studentBundle.getParcelable("student");

        fillStudentDetails();
    }

    private void fillStudentDetails(){
        String Id = CurrentSelection.getSelectedStudent().Id;
        PersonalDetail Details = CurrentSelection.getSelectedStudent().Details;
        ParentInformation Parent1 = CurrentSelection.getSelectedStudent().Parent1;
        ParentInformation Parent2 = CurrentSelection.getSelectedStudent().Parent2;
        Address Contact = CurrentSelection.getSelectedStudent().Contact;

        TextView studentId = (TextView)findViewById(R.id.txtStudentId);
        studentId.setText(Id);

        TextView studentGrade = (TextView)findViewById(R.id.txtGrade);
        studentGrade.setText(CurrentSelection.getSelectedGrade());

        TextView studentName = (TextView)findViewById(R.id.txtStudentName);
        studentName.setText(Details.getFullName());

        TextView studentAddress1 = (TextView)findViewById(R.id.txtAddress1);
        studentAddress1.setText(Contact.getAddress1());

        TextView studentAddress2 = (TextView)findViewById(R.id.txtAddress2);
        studentAddress2.setText(Contact.getAddress2());

        TextView homePhone = (TextView)findViewById(R.id.txtPhoneHome);
        homePhone.setText(Details.Phone.Home);

        TextView cellPhone = (TextView)findViewById(R.id.txtPhoneCell);
        cellPhone.setText(Details.Phone.Cell);

        TextView fatherName = (TextView)findViewById(R.id.txtFatherName);
        fatherName.setText(Parent1.Details.getFullName());

        TextView motherName = (TextView)findViewById(R.id.txtMotherName);
        motherName.setText(Parent2.Details.getFullName());

        TextView fatherWork = (TextView)findViewById(R.id.txtFatherWork);
        fatherWork.setText(Parent1.WorkName);

        TextView motherWork = (TextView)findViewById(R.id.txtMotherWork);
        motherWork.setText(Parent2.WorkName);

        TextView fatherWorkAddress1 = (TextView)findViewById(R.id.txtFatherWorkAddress1);
        fatherWorkAddress1.setText(Parent1.WorkAddress.getAddress1());

        TextView motherWorkAddress1 = (TextView)findViewById(R.id.txtMotherWorkAddress1);
        motherWorkAddress1.setText(Parent2.WorkAddress.getAddress1());

        TextView fatherWorkAddress2 = (TextView)findViewById(R.id.txtFatherWorkAddress2);
        fatherWorkAddress2.setText(Parent1.WorkAddress.getAddress2());

        TextView motherWorkAddress2 = (TextView)findViewById(R.id.txtMotherWorkAddress2);
        motherWorkAddress2.setText(Parent2.WorkAddress.getAddress2());

        TextView fatherWorkPhone = (TextView)findViewById(R.id.txtFatherWorkPhone);
        fatherWorkPhone.setText(Parent1.WorkPhone.Work);

        TextView motherWorkPhone = (TextView)findViewById(R.id.txtMotherWorkPhone);
        motherWorkPhone.setText(Parent2.WorkPhone.Work);

        TextView fatherAnnualIncome = (TextView)findViewById(R.id.txtFatherIncome);
        fatherAnnualIncome.setText(Integer.toString(Parent1.AnnualIncome));

        TextView motherAnnualIncome = (TextView)findViewById(R.id.txtMotherIncome);
        motherAnnualIncome.setText(Integer.toString(Parent2.AnnualIncome));


        //TODO: Fill in for Mother and Father; play with format.

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_studentdetail, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_studentdetail, container, false);
            return rootView;
        }
    }
}
