package vn.poly.hailt.giaoan.common;

public interface Constant {

    String DB_NAME = "Subject";
    int DB_VERSION = 1;

    String SUBJECT_TABLE = "subjects";

    String COLUMN_SUBJECT_ID = "id";
    String COLUMN_SUBJECT_NAME = "name";
    String COLUMN_SEASON = "season";

    String CREATE_SUBJECT_TABLE =
            "CREATE TABLE " + SUBJECT_TABLE + "("
                    + COLUMN_SUBJECT_ID + " TEXT PRIMARY KEY, "
                    + COLUMN_SUBJECT_NAME + " TEXT, "
                    + COLUMN_SEASON + " TEXT"
                    + ")";

}
