package com.example.admin.tablayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.tablayout.Data.DataEvents;
import com.example.admin.tablayout.Data.DataExchange;
import com.example.admin.tablayout.Data.DataMember;

import java.util.ArrayList;

public class GroupSQLite extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "group.db";
    final static String TABLE_1 = "table_member";
    final static String COLUM_1_T1 = "id_member";
    final static String COLUM_2_T1 = "name_member";
    final static String COLUM_3_T1 = "phone_member";
    final static String COLUM_4_T1 = "address_memeber";
    final static String COLUM_5_T1 = "image_memeber";
    final static String COLUM_6_T1 = "date_memeber";
    final static String TABLE_2 = "table_events";
    final static String COLUM_1_T2 = "id_events";
    final static String COLUM_2_T2 = "name_events";
    final static String COLUM_3_T2 = "address_events";
    final static String COLUM_4_T2 = "image_events";
    final static String COLUM_5_T2 = "date_events";
    final static String TABLE_3 = "table_member_join_events";
    final static String COLUM_1_T3 = "id_events";
    final static String COLUM_2_T3 = "id_member";
    final static String TABLE_4 = "add_exchange_to_events";
    final static String COLUM_1_T4 = "id_exchange";
    final static String COLUM_2_T4 = "id_event";
    final static String COLUM_3_T4 = "name_exchange";
    final static String COLUM_4_T4 = "amount_exchange";
    final static String COLUM_5_T4 = "money_exchange";


    public GroupSQLite(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_1 + "(" + COLUM_1_T1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUM_2_T1 + " TEXT NOT NULL," +
                COLUM_3_T1 + " TEXT NOT NULL, " + COLUM_4_T1 + " TEXT NOT NULL, " + COLUM_5_T1 + " TEXT NOT NULL, " + COLUM_6_T1 + " LONG NOT NULL)");

        db.execSQL("create table " + TABLE_2 + "(" + COLUM_1_T2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUM_2_T2 + " TEXT NOT NULL, " +
                COLUM_3_T2 + " TEXT NOT NULL, " + COLUM_4_T2 + " INTEGER NOT NULL, " + COLUM_5_T2 + " LONG NOT NULL)");

        db.execSQL("create table " + TABLE_3 + "(" + COLUM_1_T3 + " INTEGER NOT NULL, " + COLUM_2_T3 + " INTEGER NOT NULL)");
        db.execSQL("create table " + TABLE_4 + "(" + COLUM_1_T4 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUM_2_T4 + " INTEGER NOT NULL ," + COLUM_3_T4 + " TEXT NOT NULL ," + COLUM_4_T4 + " INTEGER NOT NULL, " + COLUM_5_T4 + " INTEGER NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Handling table 1: add, delete, update, getAllMember

    private ContentValues contentValuesMember(String nameMember, String phoneMember, String addressMember, String imageMember, long dateMember) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_2_T1, nameMember);
        contentValues.put(COLUM_3_T1, phoneMember);
        contentValues.put(COLUM_4_T1, addressMember);
        contentValues.put(COLUM_5_T1, imageMember);
        contentValues.put(COLUM_6_T1, dateMember);
        return contentValues;
    }

    public void addNewMember(String nameMember, String phoneMember, String addressMember, String imageMember, long dateMember) {
        SQLiteDatabase database = getWritableDatabase();
        database.insert(TABLE_1, null, contentValuesMember(nameMember, phoneMember, addressMember, imageMember, dateMember));
    }

    public void deleteMember(int id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_1, COLUM_1_T1 + "= " + id, null);
    }

    public void updateMember(int id, String nameMember, String phoneMember, String addressMember, String imageMember, long dateMember) {
        SQLiteDatabase database = getWritableDatabase();
        database.update(TABLE_1, contentValuesMember(nameMember, phoneMember, addressMember, imageMember, dateMember), COLUM_1_T1 + "= " + id, null);
    }

    public ArrayList<DataMember> getAllMember() {
        ArrayList<DataMember> list = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.query(TABLE_1, null, null, null, null, null, COLUM_2_T1 + " ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int idMember = cursor.getInt(0);
            String nameMember = cursor.getString(1);
            String phoneMember = cursor.getString(2);
            String addressMember = cursor.getString(3);
            String imageMember = cursor.getString(4);
            long dateMember = cursor.getLong(5);
            list.add(new DataMember(idMember, nameMember, phoneMember, addressMember, imageMember, dateMember));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    //  handling table 2: add, delete, update, getAllMember


    private ContentValues contentValuesEvent(String nameEvents, String addressEvents, int imageEvent, long dateEvent) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_2_T2, nameEvents);
        contentValues.put(COLUM_3_T2, addressEvents);
        contentValues.put(COLUM_4_T2, imageEvent);
        contentValues.put(COLUM_5_T2, dateEvent);
        return contentValues;
    }

    public void addNewEvent(String nameEvents, String addressEvents, int imageEvent, long dateEvent) {
        SQLiteDatabase database = getWritableDatabase();
        database.insert(TABLE_2, null, contentValuesEvent(nameEvents, addressEvents, imageEvent, dateEvent));
    }

    public void deleteEvent(int idEvent) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_2, COLUM_1_T2 + " = " + idEvent, null);
    }

    public void updateEvent(int idEvent, String nameEvents, String addressEvents, int imageEvent, long dateEvent) {
        SQLiteDatabase database = getWritableDatabase();
        database.update(TABLE_2, contentValuesEvent(nameEvents, addressEvents, imageEvent, dateEvent), COLUM_1_T2 + "= " + idEvent, null);
    }

    public ArrayList<DataEvents> getAllEvent() {
        ArrayList<DataEvents> list = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.query(TABLE_2, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int idEvent = cursor.getInt(0);
            String nameEvent = cursor.getString(1);
            String addressEvent = cursor.getString(2);
            int imageEvent = cursor.getInt(3);
            long dateEvent = cursor.getLong(4);
            list.add(new DataEvents(idEvent, nameEvent, addressEvent, imageEvent, dateEvent));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    // Handling table 3: add, delete

    public void addMemberJoinEvent(int idEvent, int idMember) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_1_T3, idEvent);
        contentValues.put(COLUM_2_T3, idMember);
        database.insert(TABLE_3, null, contentValues);
    }

    public void deleteMemberJoinedEvent(int idEvent, int idMember) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_3, COLUM_1_T3 + " = " + idEvent + " AND " + COLUM_2_T3 + " = " + idMember, null);
    }

    // lấy danh sách tất cả các thành viên đã tham gia vào 1 sự kiện bất kì

    public ArrayList<DataMember> getMemberJoinedEvent(int idEvent) {
        SQLiteDatabase database = getWritableDatabase();
        ArrayList<DataMember> list = new ArrayList<>();
        String table = TABLE_3 + " INNER JOIN " + TABLE_1 + " ON " + TABLE_1 + "." + COLUM_1_T1 + " = " + TABLE_3 + "." + COLUM_2_T3;
        String[] colum = {TABLE_1 + "." + COLUM_1_T1, COLUM_2_T1, COLUM_3_T1, COLUM_4_T1, COLUM_5_T1, COLUM_6_T1};
        String where = TABLE_3 + "." + COLUM_1_T3 + " = " + idEvent;
        Cursor cursor = database.query(table, colum, where, null, null, null, COLUM_2_T1 + " ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int idMember = cursor.getInt(0);
            String nameMember = cursor.getString(1);
            String phoneMember = cursor.getString(2);
            String addressMember = cursor.getString(3);
            String imageMember = cursor.getString(4);
            long dateMember = cursor.getLong(5);
            list.add(new DataMember(idMember, nameMember, phoneMember, addressMember, imageMember, dateMember));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    // Handling table 4: add, delete, update, getall

    private ContentValues contentValuesExchange(int idEvent, String nameExchange, int amountExchange, int moneyExchange) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_2_T4, idEvent);
        contentValues.put(COLUM_3_T4, nameExchange);
        contentValues.put(COLUM_4_T4, amountExchange);
        contentValues.put(COLUM_5_T4, moneyExchange);
        return contentValues;
    }

    public void addNewExchange(int idEvent, String nameExchange, int amountExchange, int moneyExchange) {
        SQLiteDatabase database = getWritableDatabase();
        database.insert(TABLE_4, null, contentValuesExchange(idEvent, nameExchange, amountExchange, moneyExchange));
    }

    public void deleteExchange(int idExchange) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_4, COLUM_1_T4 + " = " + idExchange, null);
    }

    public void updateExchange(int idEvent, int idExchange, String nameExchange, int amountExchange, int moneyExchange) {
        SQLiteDatabase database = getWritableDatabase();
        database.update(TABLE_4, contentValuesExchange(idEvent, nameExchange, amountExchange, moneyExchange), COLUM_1_T4 + " = " + idExchange, null);
    }

    public ArrayList<DataExchange> getAllExchange(int idEvent) {
        ArrayList<DataExchange> list = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.query(TABLE_4, null, COLUM_2_T4 + " = " + idEvent, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int idExchange = cursor.getInt(0);
            String nameExchange = cursor.getString(2);
            int amountExchange = cursor.getInt(3);
            int moneyExchange = cursor.getInt(4);
            list.add(new DataExchange(idExchange, idEvent, nameExchange, amountExchange, moneyExchange));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    // lấy tất cả danh sách các sự kiện mà 1 thành viên bất kì đã tham gia

    public ArrayList<DataEvents> getEventMemberJoined(int idMember) {
        SQLiteDatabase database = getWritableDatabase();
        ArrayList<DataEvents> list = new ArrayList<>();
        String table = TABLE_2 + " INNER JOIN " + TABLE_3 + " ON " + TABLE_3 + "." + COLUM_1_T3 + " = " + TABLE_2 + "." + COLUM_1_T2;
        String[] colum = {TABLE_2 + "." + COLUM_1_T2, COLUM_2_T2, COLUM_3_T2, COLUM_4_T2, COLUM_5_T2};
        String where = TABLE_3 + "." + COLUM_2_T3 + " = " + idMember;
        Cursor cursor = database.query(table, colum, where, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int idEvent = cursor.getInt(0);
            String nameEvent = cursor.getString(1);
            String addressEvent = cursor.getString(2);
            int imageEvent = cursor.getInt(3);
            long dateEvent = cursor.getLong(4);
            list.add(new DataEvents(idEvent, nameEvent, addressEvent, imageEvent, dateEvent));
            cursor.moveToNext();
        }
        cursor.close();
        return list;

    }

    //get total contribute of member for group
    public int totalContributeOfMember(int idMember) {
        SQLiteDatabase database = getWritableDatabase();
        int total = 0;
        String table = TABLE_2 + " INNER JOIN " + TABLE_3 + " ON " + TABLE_3 + "." + COLUM_1_T3 + " = " + TABLE_2 + "." + COLUM_1_T2;
        String[] colum = {TABLE_2 + "." + COLUM_1_T2};
        String where = TABLE_3 + "." + COLUM_2_T3 + " = " + idMember;
        Cursor cursor = database.query(table, colum, where, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int idEvent = cursor.getInt(0);
            total += getTotalMoneyExchangeInEvent(idEvent) / getTotalMemberJoinEvent(idEvent);
            cursor.moveToNext();
        }
        cursor.close();
        return total;
    }

    //get list member not join event
    public ArrayList<DataMember> getMemberNotJoin(int idEvent) {
        SQLiteDatabase database = getWritableDatabase();
        ArrayList<DataMember> list = new ArrayList<>();
        String[] colum = {COLUM_2_T3};
        Cursor cursor = database.query(TABLE_3, colum, COLUM_1_T3 + " = " + idEvent, null, null, null, null);
        String where = COLUM_1_T1 + " NOT IN ( ";
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            where += cursor.getInt(0) + ",";
            cursor.moveToNext();
        }
        cursor.close();
        where = where.substring(0, where.length() - 1);
        where += ") ";
        Cursor cursor1 = database.query(TABLE_1, null, where, null, null, null, COLUM_2_T1 + " ASC ");
        cursor1.moveToFirst();
        while (!cursor1.isAfterLast()) {
            int idMember = cursor1.getInt(0);
            String nameMember = cursor1.getString(1);
            String phoneMember = cursor1.getString(2);
            String addressMember = cursor1.getString(3);
            String imageMember = cursor1.getString(4);
            long dateMember = cursor1.getLong(5);
            list.add(new DataMember(idMember, nameMember, phoneMember, addressMember, imageMember, dateMember));
            cursor1.moveToNext();
        }
        cursor1.close();
        return list;
    }

    //get 1 member in table
    public DataMember getAMemberInTableMember(int idMember) {
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.query(TABLE_1, null, COLUM_1_T1 + "= " + idMember, null, null, null, null);
        cursor.moveToFirst();
        String nameMember = cursor.getString(1);
        String phoneMember = cursor.getString(2);
        String addressMember = cursor.getString(3);
        String imageMember = cursor.getString(4);
        long dateMember = cursor.getLong(5);
        DataMember dataMember = new DataMember(idMember, nameMember, phoneMember, addressMember,imageMember,dateMember);
        return dataMember;
    }


    //get list want find by name to arraylist from table member

    public ArrayList<DataMember> findDataTableMember(String nameMemberFind) {
        ArrayList<DataMember> list = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        String where = COLUM_2_T1 + " LIKE '%" + nameMemberFind + "%'";
        Cursor cursor = database.query(TABLE_1, null, where, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int idMember = cursor.getInt(0);
            String nameMember = cursor.getString(1);
            String phoneMember = cursor.getString(2);
            String addressMember = cursor.getString(3);
            String imageMember = cursor.getString(4);
            long dateMember = cursor.getLong(5);
            list.add(new DataMember(idMember, nameMember, phoneMember, addressMember, imageMember, dateMember));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    //get list want find by name to arraylist from table events
    public ArrayList<DataEvents> findDataTableEvent(String nameEventFind) {
        ArrayList<DataEvents> list = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        String where = COLUM_2_T2 + " LIKE '%" + nameEventFind + "%'";
        Cursor cursor = database.query(TABLE_2, null, where, null
                , null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int idEvent = cursor.getInt(0);
            String nameEvent = cursor.getString(1);
            String addressEvent = cursor.getString(2);
            int imageEvent = cursor.getInt(3);
            long dateEvent = cursor.getLong(4);
            list.add(new DataEvents(idEvent, nameEvent, addressEvent, imageEvent,dateEvent));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    // get total exchange for event
    public int getTotalMoneyExchangeInEvent(int idEvent) {
        SQLiteDatabase database = getWritableDatabase();
        String[] colum = {"sum(" + COLUM_5_T4 + ")"};
        Cursor cursor = database.query(TABLE_4, colum, COLUM_2_T4 + " = " + idEvent, null, null, null, null);
        cursor.moveToFirst();
        int total = cursor.getInt(0);
        cursor.close();
        return total;
    }

    //get 5 new member in group
    public ArrayList<DataMember> get5NewMember() {
        SQLiteDatabase database = getWritableDatabase();
        ArrayList<DataMember> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_1 + " ORDER BY " + COLUM_1_T1 + " DESC LIMIT 5", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int idMember = cursor.getInt(0);
            String nameMember = cursor.getString(1);
            String phoneMember = cursor.getString(2);
            String addressMember = cursor.getString(3);
            String imageMember = cursor.getString(4);
            long dateMember = cursor.getLong(5);
            list.add(new DataMember(idMember, nameMember, phoneMember, addressMember, imageMember, dateMember));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    //get 5 new Event in group
    public ArrayList<DataEvents> get5NewEvent() {
        SQLiteDatabase database = getWritableDatabase();
        ArrayList<DataEvents> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_2 + " ORDER BY " + COLUM_1_T2 + " DESC LIMIT 5", null);
        cursor.moveToFirst();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int idEvent = cursor.getInt(0);
            String nameEvent = cursor.getString(1);
            String addressEvent = cursor.getString(2);
            int imageEvent = cursor.getInt(3);
            long dateEvent = cursor.getLong(4);
            list.add(new DataEvents(idEvent, nameEvent, addressEvent, imageEvent, dateEvent));
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }

    //get total member in group
    public int getTotalMember() {
        SQLiteDatabase database = getWritableDatabase();
        String[] colum = {"COUNT( " + COLUM_2_T1 + " )"};
        Cursor cursor = database.query(TABLE_1, colum, null, null, null, null, null);
        cursor.moveToFirst();
        int total = cursor.getInt(0);
        return total;
    }

    //get total event in group
    public int getTotalEvent() {
        SQLiteDatabase database = getWritableDatabase();
        String[] colum = {"COUNT( " + COLUM_2_T2 + " )"};
        Cursor cursor = database.query(TABLE_2, colum, null, null, null, null, null);
        cursor.moveToFirst();
        int total = cursor.getInt(0);
        return total;
    }


    // get total member joined event
    public int getTotalMemberJoinEvent(int idEvent) {
        SQLiteDatabase database = getWritableDatabase();
        String table = TABLE_1 + " INNER JOIN " + TABLE_3 + " ON " + TABLE_1 + "." + COLUM_1_T1 + " = " + TABLE_3 + "." + COLUM_2_T3;
        String[] colum = {"COUNT( " + COLUM_2_T1 + " )"};
        String where = TABLE_3 + "." + COLUM_1_T3 + " = " + idEvent;
        Cursor cursor = database.query(table, colum, where, null, null, null, null);
        cursor.moveToFirst();
        int total = cursor.getInt(0);
        return total;
    }

    //get total event member joined
    public int getTotalEventMemberJoined(int idMember) {
        SQLiteDatabase database = getWritableDatabase();
        String[] colum = {" COUNT(" + COLUM_1_T3 + ") "};
        Cursor cursor = database.query(TABLE_3, colum, COLUM_2_T3 + " = " + idMember, null, null, null, null);
        cursor.moveToFirst();
        int total = cursor.getInt(0);
        return total;
    }

    // trung binh 1 nguoi phai dong gop
    public int averageTotal(int idEvent) {
        return getTotalMoneyExchangeInEvent(idEvent) / getTotalMemberJoinEvent(idEvent);
    }

    //get a Event in table Events by idEvent
    public DataEvents getAEventInTableEvents(int idEvent) {
        DataEvents dataEvents;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.query(TABLE_2, null, COLUM_1_T2 + " = " + idEvent, null, null, null, null);
        cursor.moveToFirst();
        String nameEvent = cursor.getString(1);
        String addressEvent = cursor.getString(2);
        int imageEvent = cursor.getInt(3);
        long dateEvent = cursor.getLong(4);
        dataEvents = new DataEvents(idEvent, nameEvent, addressEvent, imageEvent, dateEvent);
        return dataEvents;
    }

}
