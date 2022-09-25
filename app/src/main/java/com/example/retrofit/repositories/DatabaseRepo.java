package com.example.retrofit.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.retrofit.model.Property;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepo {
    private DatabaseHandler databaseHandler = null;

    public DatabaseRepo(Context context) {
        databaseHandler = new DatabaseHandler(context);
    }


    public void addPropertyList(List<Property> propertyList) {
        for (Property p : propertyList
        ) {
            addProperty(p);
        }
    }

    // code to add the new property
    public void addProperty(Property property) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseEnums.KEY_ID, property.getId());
        values.put(DataBaseEnums.KEY_TYPE, property.getType());
        values.put(DataBaseEnums.KEY_PRICE, property.getPrice());
        values.put(DataBaseEnums.KEY_IMAGE_SRC, property.getImgSrc());
        values.put(DataBaseEnums.KEY_IS_SELECTED, property.getIsSelected());

        db.insert(DataBaseEnums.TABLE_PROPERTY, null, values);
        // db.close(); // Closing database connection
    }

    // code to get the single contact
    public Property getProperty(int id) {
        SQLiteDatabase db = databaseHandler.getReadableDatabase();

        Cursor cursor = db.query(DataBaseEnums.TABLE_PROPERTY, new String[]{DataBaseEnums.KEY_ID,
                        DataBaseEnums.KEY_TYPE, DataBaseEnums.KEY_PRICE, DataBaseEnums.KEY_IMAGE_SRC
                        , DataBaseEnums.KEY_IS_SELECTED}, DataBaseEnums.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Property contact = new Property(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), (long) cursor.getInt(3), cursor.getInt(4) != 0);
        return contact;
    }

    // code to get all contacts in a list view
    public List<Property> getAllContacts() {
        List<Property> propertyList = new ArrayList<Property>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataBaseEnums.TABLE_PROPERTY;

        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Property property = new Property();
                property.setId(cursor.getString(0));
                property.setType(cursor.getString(1));
                property.setImgSrc(cursor.getString(2));
                property.setPrice((long)cursor.getInt(3));
                property.setIsSelected(cursor.getInt(4) != 0);
                // Adding property to list
                propertyList.add(property);
            } while (cursor.moveToNext());
        }

        return propertyList;
    }

    // code to update the single contact
    public int updateContact(Property contact) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseEnums.KEY_TYPE, contact.getId());
        values.put(DataBaseEnums.KEY_PRICE, contact.getType());

        // updating row
        return db.update(DataBaseEnums.TABLE_PROPERTY, values, DataBaseEnums.KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }

    // code to update the single contact
    public int updateIsSelected(String  id , Boolean isCheck) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        Boolean check = false;
        if(isCheck) check = true;
        values.put(DataBaseEnums.KEY_IS_SELECTED, check);


        // updating row
        return db.update(DataBaseEnums.TABLE_PROPERTY, values, DataBaseEnums.KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }


    // Deleting single contact
    public void deleteContact(Property contact) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete(DataBaseEnums.TABLE_PROPERTY, DataBaseEnums.KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + DataBaseEnums.TABLE_PROPERTY;
        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        // return count
        return cursor.getCount();
    }

}
