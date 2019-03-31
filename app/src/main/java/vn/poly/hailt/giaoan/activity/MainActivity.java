package vn.poly.hailt.giaoan.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import vn.poly.hailt.giaoan.R;
import vn.poly.hailt.giaoan.adapter.SubjectAdapter;
import vn.poly.hailt.giaoan.database.SubjectDAO;
import vn.poly.hailt.giaoan.model.Subject;

public class MainActivity extends AppCompatActivity {

    private Button mBtnAddSubject;
    private SubjectDAO mSubjectDAO;
    private List<Subject> mSubjects;
    private SubjectAdapter mSubjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initActions();

    }

    private void initActions() {
        mBtnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddSubjectActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        mBtnAddSubject = findViewById(R.id.btnAddSubject);
    }

    private void deleteSubject(int position) {
        Subject subject = mSubjects.get(position);
        if (mSubjectDAO.deleteSubject(subject) > 0) {
            Toast.makeText(MainActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
            mSubjects.remove(subject);
            mSubjectAdapter.notifyItemChanged(position);
        } else {
            Toast.makeText(MainActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private void editSubject(final int position) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_subject);
        dialog.setCanceledOnTouchOutside(false);

        final EditText edtSubjectName = dialog.findViewById(R.id.edtSubjectName);
        final EditText edtSeason = dialog.findViewById(R.id.edtSeason);
        Button btnEdit = dialog.findViewById(R.id.btnEdit);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sName = edtSubjectName.getText().toString().trim();
                String season = edtSeason.getText().toString().trim();

                Subject subject = mSubjects.get(position);
                edtSubjectName.setText(subject.getsName());
                edtSeason.setText(subject.getSeason());
                subject.setsName(sName);
                subject.setSeason(season);

                if (mSubjectDAO.updateSubject(subject) > 0) {
                    Toast.makeText(MainActivity.this, "Đã sửa", Toast.LENGTH_SHORT).show();
                    mSubjects.set(position, subject);
                    mSubjectAdapter.notifyItemChanged(position);
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Sửa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView mRvSubject = findViewById(R.id.rvSubject);

        mSubjectDAO = new SubjectDAO(this);
        mSubjects = mSubjectDAO.getAllSubjects();
        mSubjectAdapter = new SubjectAdapter(this, mSubjects);
        mSubjectAdapter.setOnItemClickActionListener(new SubjectAdapter.OnItemClickActionListener() {

            @Override
            public void onItem(int position) {
                Subject subject = mSubjects.get(position);
                Toast.makeText(MainActivity.this,
                        subject.getsId() + "\n" + subject.getsName() + "\n" + subject.getSeason(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEdit(int position) {
                editSubject(position);
            }

            @Override
            public void onDelete(int position) {
                deleteSubject(position);
            }
        });
        mRvSubject.setLayoutManager(new LinearLayoutManager(this));
        mRvSubject.setAdapter(mSubjectAdapter);
    }
}
