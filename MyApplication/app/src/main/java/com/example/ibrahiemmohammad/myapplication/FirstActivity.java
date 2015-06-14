package com.example.ibrahiemmohammad.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ibrahiemmohammad.myapplication.school.School;
import com.example.ibrahiemmohammad.myapplication.school.Student;

import java.util.HashMap;
import java.util.Map;

public class FirstActivity extends ActionBarActivity {

    SearchView studentSearch;
    Spinner optionList;
    ListView studentList;

    int checkedItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        wireUpStudentSearchView();
        setSearchOptions();
        SchoolData.load(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
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

    private void setSearchOptions(){

        optionList = (Spinner) findViewById(R.id.lvSearchOptions);
        String[] Options = getResources().getStringArray(R.array.searchOption);
        ArrayAdapter<String> OptionsAdapter;
        OptionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Options);
        OptionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionList.setAdapter(OptionsAdapter);

    }

    private String[] getStudentInfo(HashMap<Student, Integer> students){
        String[] studentInfo = new String[students.size()];
        int counter = 0;
        for (Map.Entry<Student, Integer> entry : students.entrySet()){
              Student student = entry.getKey();
              int grade = entry.getValue();
              studentInfo[counter++] = student.Id+" - "+student.Details.getFullName()+" (Grade: "+ grade+")";

        }
        return studentInfo;
    }

    private void fillStudents(final HashMap<Student, Integer> students){

        studentList = (ListView) findViewById(R.id.lvStudents);
        String[] Options = getStudentInfo(students);
        StudentListAdapter OptionsAdapter = new StudentListAdapter(Options, students);
        studentList.setAdapter(OptionsAdapter);
        studentList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }


    private void launchStudentDetail(String selectedItem, HashMap<Student, Integer> students){
        Intent intent = new Intent(this, StudentdetailActivityActivity.class);
        startActivity(intent);
    }

    private boolean getStudent(String selectedItem, HashMap<Student, Integer> students){
        int index = selectedItem.indexOf(" -");
        String Id = selectedItem.substring(0, index);
        for (Map.Entry<Student, Integer> entry : students.entrySet()){
            Student student = entry.getKey();
            if (student.Id.matches(Id)){
                CurrentSelection.setSelectedStudent(student);
                CurrentSelection.setSelectedGrade(entry.getValue());

                return true;
            }
        }
        return false;
    }
    private CheckedStudent getCheckedStudent(String selectedItem, HashMap<Student, Integer> students){

        CheckedStudent current = null;

        int index = selectedItem.indexOf(" -");
        String Id = selectedItem.substring(0, index);
        for (Map.Entry<Student, Integer> entry : students.entrySet()){
            Student student = entry.getKey();
            if (student.Id.matches(Id)){
                current = new CheckedStudent();
                current.setSelectedStudent(student);
                current.setSelectedGrade(entry.getValue());
            }
        }
        return current;
    }
    private void wireUpStudentSearchView(){

        studentSearch = (SearchView) findViewById(R.id.svStudents);
        studentSearch.setQueryHint("Search Student");

        studentSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               Object selectedOption =  optionList.getSelectedItem();
                HashMap<Student, Integer> result = SchoolData.getStudentLib().findStudent(query, selectedOption.toString());
                if (result.size()==0) {
                    Toast.makeText(getBaseContext(), "Search by: " + selectedOption.toString() + " " + query + "->" + result.size(), Toast.LENGTH_SHORT).show();
                }
                else {
                    fillStudents(result);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void toggleRegisterButton(Boolean clickable){
        Button registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setClickable(clickable);
        if (clickable) {
            registerButton.setTextColor(Color.RED);
        }
        else{
            registerButton.setTextColor(Color.GRAY);
        }
    }

    class StudentListAdapter extends ArrayAdapter<String>{
        HashMap<Student, Integer> matchedStudents;
        StudentListAdapter(String[] items, HashMap<Student, Integer> students){
            super(FirstActivity.this, R.layout.layout_item, R.id.listItem, items);
            matchedStudents = students;
        }
        public View getView(int position, View convertView, ViewGroup parent){
            View row = super.getView(position, convertView, parent);
            StudentListHolder holder = (StudentListHolder)row.getTag();

            if (holder==null){
                holder = new StudentListHolder(row);
                row.setTag(holder);
            }
            getStudent((String) holder.checkBox.getTag(), matchedStudents);
            setRegistrationOptions(row, CurrentSelection.getSelectedStudent().Id, CurrentSelection.getSelectedGrade());

            holder.detailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button item = (Button) v.findViewById(R.id.detailsButton);
                    if (getStudent((String) item.getTag(), matchedStudents)) {
                        launchStudentDetail((String) item.getTag(), matchedStudents);
                    }
                }
            });

            holder.unlockButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageButton unlock = (ImageButton) v.findViewById(R.id.imageButton);
                    if (getStudent((String) unlock.getTag(), matchedStudents)) {
                        SchoolData.deleteRegistrationData(CurrentSelection.getSelectedStudent().Id, CurrentSelection.getSelectedGrade(),FirstActivity.this);
                        toggleImageButton(v, false);
                        toggleCheckbox((View)v.getParent(), false);

                    }
                }
            });

            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    CheckBox item = (CheckBox)buttonView.findViewById(R.id.checkItem);
                    if(isChecked){
                        checkedItemCount++;
                        toggleRegisterButton(true);
                        updateStudentList((String) item.getTag(), matchedStudents);
                    }
                    else{
                        checkedItemCount--;
                        SelectedStudents.remove((String) item.getTag());
                        if(checkedItemCount==0){
                            toggleRegisterButton(false);
                        }
                    }
                }
            });

            return row;
        }

        private void setRegistrationOptions(View row, String studentID, String grade){
            Boolean isRegistered = SchoolData.wasStudentRegistered(studentID, grade, FirstActivity.this);
            toggleImageButton(row, isRegistered);
            toggleCheckbox(row, isRegistered);
        }

        private void toggleImageButton(View row, Boolean toggleValue){
            ImageButton unlock = (ImageButton)row.findViewById(R.id.imageButton);
            unlock.setEnabled(toggleValue);
        }

        private void toggleCheckbox(View row, Boolean toggleValue){
            CheckBox option = (CheckBox)row.findViewById(R.id.checkItem);
            option.setEnabled(!toggleValue);
            option.setChecked(false);
        }
    }

    private void updateStudentList(String selectedItem, HashMap<Student, Integer> students){
        CheckedStudent current = getCheckedStudent(selectedItem, students);
        SelectedStudents.add(selectedItem, current);
    }


    public void doRegistration (View v){
        launchRegistrationView();
    }

    private void launchRegistrationView(){
        Intent intent = new Intent(this, registration_view.class);
        startActivity(intent);
    }
}

