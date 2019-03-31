package vn.poly.hailt.giaoan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.poly.hailt.giaoan.common.Constant;
import vn.poly.hailt.giaoan.model.Subject;

public class SubjectDAO implements Constant {

    private SQLiteDatabase mDatabase;
    private DbHelper mDbHelper;

    public SubjectDAO(Context context) {
        mDbHelper = new DbHelper(context);
    }

    public long insertSubject(Subject subject) {
        mDatabase = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECT_ID, subject.getsId());
        values.put(COLUMN_SUBJECT_NAME, subject.getsName());
        values.put(COLUMN_SEASON, subject.getSeason());

        long id = mDatabase.insert(SUBJECT_TABLE, null, values);

        mDatabase.close();

        return id;
    }

    public long deleteSubject(Subject subject) {
        mDatabase = mDbHelper.getWritableDatabase();

        long id = mDatabase.delete(SUBJECT_TABLE, COLUMN_SUBJECT_ID + " = ?", new String[]{subject.getsId()});

        mDbHelper.close();

        return id;
    }

    public long updateSubject(Subject subject) {
        mDatabase = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECT_NAME, subject.getsName());
        values.put(COLUMN_SEASON, subject.getSeason());

        long id = mDatabase.update(SUBJECT_TABLE, values, COLUMN_SUBJECT_ID + " = ?", new String[]{subject.getsId()});

        mDatabase.close();

        return id;
    }

    public List<Subject> getAllSubjects() {
        mDatabase = mDbHelper.getReadableDatabase();
        List<Subject> subjects = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SUBJECT_TABLE;

        Cursor cursor = mDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Subject subject = new Subject();
                subject.setsId(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_ID)));
                subject.setsName(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_NAME)));
                subject.setSeason(cursor.getString(cursor.getColumnIndex(COLUMN_SEASON)));

                subjects.add(subject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();

        return subjects;
    }

}
