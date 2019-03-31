package vn.poly.hailt.giaoan.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.poly.hailt.giaoan.R;
import vn.poly.hailt.giaoan.database.SubjectDAO;
import vn.poly.hailt.giaoan.model.Subject;

public class AddSubjectActivity extends AppCompatActivity {

    private EditText mEdtSubjectID;
    private EditText mEdtSubjectName;
    private EditText mEdtSeason;
    private Button mBtnAdd;
    private SubjectDAO mSubjectDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        mSubjectDAO = new SubjectDAO(this);
        initViews();
        initActions();
    }

    private void initViews() {
        mEdtSubjectID = findViewById(R.id.edtSubjectID);
        mEdtSubjectName = findViewById(R.id.edtSubjectName);
        mEdtSeason = findViewById(R.id.edtSeason);
        mBtnAdd = findViewById(R.id.btnAdd);
    }

    private void initActions() {
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSubject();
            }
        });
    }

    private void addSubject() {
        String sId = mEdtSubjectID.getText().toString().trim();
        String sName = mEdtSubjectName.getText().toString().trim();
        String season = mEdtSeason.getText().toString().trim();

        Subject subject = new Subject(sId, sName, season);

        if (mSubjectDAO.insertSubject(subject) > 0) {
            Toast.makeText(this, "Đã thêm", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
        }
    }

}
