package com.example.android_week7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvStudent;
    List<Student> listStudent;
    AdapterStudent adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);
        lvStudent = findViewById(R.id.lvStudent);

//         Inserting Contacts
//        Log.d("Insert: ", "Inserting ..");
//        db.addStudent(new Student("Đỗ Anh Bôn"));
//        db.addStudent(new Student("Hoàng Quốc Cường"));
//        db.addStudent(new Student("Phạm Minh Dũng"));
//        db.addStudent(new Student("Châu Hoàng Duy"));
//        db.addStudent(new Student("Nguyễn Đình Hảo"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        listStudent = db.getAllStudents();

        adapter = new AdapterStudent(this, R.layout.row_item, listStudent);
        lvStudent.setAdapter(adapter);

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        // Add Item When click button
        Button btnAdd = findViewById(R.id.btnAdd);
        EditText edtName = findViewById(R.id.edtName);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtName.getText().toString().trim().length() < 1) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên bạn muốn thêm", Toast.LENGTH_SHORT).show();
                    return;
                }
                Student student = new Student(edtName.getText().toString().trim());
                db.addStudent(student);
                listStudent.add(student);
                Log.d("Add", "Add Complicated ");
            }
        });
    }
}