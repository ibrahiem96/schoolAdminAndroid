package com.example.ibrahiemmohammad.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.ibrahiemmohammad.myapplication.registration.GradesToRegister;
import com.example.ibrahiemmohammad.myapplication.registration.StudentRegistration;
import com.example.ibrahiemmohammad.myapplication.school.Admissions;
import com.example.ibrahiemmohammad.myapplication.school.Student;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class registration_view extends ActionBarActivity {


    private  TableLayout mainTable;
    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_view);
        mainTable = (TableLayout)findViewById(R.id.mainTable);
        layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        calculateTotalFees();
        addStudentSections();
    }

    private void calculateTotalFees(){

        StudentRegistration registration = new StudentRegistration(SchoolData.getRegistration());
        int totalFees = registration.calculateFees(getGrades());
        TextView txtTotalFees = (TextView)findViewById(R.id.txtTotalFees);
        txtTotalFees.setText(Integer.toString(totalFees));

        TextView txtLabFees = (TextView)findViewById((R.id.txtLabFees));
        txtLabFees.setText(Integer.toString(registration.getLabFee()));

        TextView txtAdminFees = (TextView)findViewById((R.id.txtAdminFees));
        txtAdminFees.setText(Integer.toString(SchoolData.getRegistration().ReturningStudentFee.FeeList.get(1).Administration));

    }

    private ArrayList<GradesToRegister> getGrades(){
        ArrayList<GradesToRegister> grades = new ArrayList<GradesToRegister>();
        for (Map.Entry<String, CheckedStudent> entry : SelectedStudents.getStudentList().entrySet()){
            grades.add(new GradesToRegister(Integer.parseInt(entry.getValue().getSelectedGrade()), entry.getValue().getSelectedStudent().isNew));
        }
        return grades;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration_view, menu);
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

    private void inflateStudentView(int layoutResourceId, int viewId){
        View dynamicTable = layoutInflater.inflate(layoutResourceId, null);
        dynamicTable.setId(viewId);
        ViewGroup.LayoutParams lpView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mainTable.addView(dynamicTable);

    }


    private TableLayout fillStudentView1(Student student, int grade, int viewId){

        TableLayout tableLayout = fillStudentView3(student, grade, viewId);

        TextView txtGradFees = (TextView)tableLayout.findViewById(R.id.txtGraduationFees);
        txtGradFees.setText(Integer.toString(SchoolData.getRegistration().ReturningStudentFee.FeeList.get(grade).Graduation));


        return tableLayout;
    }

    private TableLayout fillStudentView2(Student student, int grade, int viewId){

        TableLayout tableLayout = fillStudentView3(student, grade, viewId);

        TextView txtBookFees = (TextView)tableLayout.findViewById(R.id.txtBookFees);
        txtBookFees.setText(Integer.toString(SchoolData.getRegistration().ReturningStudentFee.FeeList.get(grade).Book));
        return tableLayout;
    }

    private TableLayout fillStudentView3(Student student, int grade, int viewId){

        TableLayout tableLayout = (TableLayout)findViewById(viewId);
        TextView txtName = (TextView)tableLayout.findViewById(R.id.txtStudentName);
        txtName.setText(student.Details.getFullName());

        TextView txtGrade = (TextView)tableLayout.findViewById(R.id.txtGrade);
        txtGrade.setText("Grade: "+Integer.toString(grade));

        TextView txtRegFees = (TextView)tableLayout.findViewById(R.id.txtRegistrationFees);
        txtRegFees.setText(Integer.toString(student.isNew ?
                SchoolData.getRegistration().NewStudentFee.FeeList.get(grade).Registration :
                SchoolData.getRegistration().ReturningStudentFee.FeeList.get(grade).Registration));

        if (student.isNew){
            TextView txtPlacementFees = (TextView)tableLayout.findViewById(R.id.txtPlacementFees);
            txtPlacementFees.setText(Integer.toString(SchoolData.getRegistration().NewStudentFee.FeeList.get(grade).Placement));
        }
        return tableLayout;
     }

     private void addStudentSections(){
         int counter = 0;
         for (Map.Entry<String, CheckedStudent> entry : SelectedStudents.getStudentList().entrySet()){
             TableLayout layout=null;
             switch(Integer.parseInt(entry.getValue().getSelectedGrade())){
                 case -1:
                 case 6:
                 case 7:
                 case 9:
                 case 10:
                 case 11:
                     inflateStudentView(entry.getValue().getSelectedStudent().isNew? R.layout.student_view6 :R.layout.student_view3, counter);
                     layout = fillStudentView3(entry.getValue().getSelectedStudent(), Integer.parseInt(entry.getValue().getSelectedGrade()), counter);
                     break;

                 case 0:
                 case 1:
                 case 2:
                 case 3:
                 case 4:
                 case 5:
                     inflateStudentView(entry.getValue().getSelectedStudent().isNew? R.layout.student_view5 :R.layout.student_view2, counter);
                     layout = fillStudentView2(entry.getValue().getSelectedStudent(), Integer.parseInt(entry.getValue().getSelectedGrade()), counter);
                     break;

                 case 8:
                 case 12:
                     inflateStudentView(entry.getValue().getSelectedStudent().isNew? R.layout.student_view4 :R.layout.student_view1, counter);
                     layout = fillStudentView1(entry.getValue().getSelectedStudent(), Integer.parseInt(entry.getValue().getSelectedGrade()), counter);
                     break;


             }
            if (layout!=null){
                if (counter%2==0){
                    layout.setBackgroundColor(Color.LTGRAY);
                }
                else{
                    layout.setBackgroundColor(Color.YELLOW);
                }
                counter++;
              }
            }

     }

    public void acceptRegistration(View view) {
        ArrayList<String> registeredStudents = new ArrayList<String>();
        for (Map.Entry<String, CheckedStudent> entry : SelectedStudents.getStudentList().entrySet()) {
            String registeredData = entry.getValue().getSelectedStudent().Id+", "+entry.getValue().getSelectedGrade()+","+ Calendar.getInstance().get(Calendar.YEAR);
            registeredStudents.add(registeredData);
            ExternalCommunication.sendText(entry.getValue().getSelectedStudent(), entry.getValue().getSelectedGrade(), this);
        }
        SchoolData.saveRegisteredData(this, registeredStudents, Context.MODE_APPEND);
        ArrayList<String> data = SchoolData.getRegisteredData(this);
        int count = data.size();
    }
}
