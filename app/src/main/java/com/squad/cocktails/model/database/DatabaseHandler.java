package com.squad.cocktails.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.squad.cocktails.model.Cocktail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryanc on 2/27/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "favoritesManager";
    private static final String TABLE_FAVORITES = "favorites";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_THUMB = "thumbnail";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_THUMB + " TEXT" + ")";
        db.execSQL(CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    public void addFavorite(Cocktail favorite) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, favorite.getCocktailId());
        values.put(KEY_NAME, favorite.getName());
        values.put(KEY_THUMB, favorite.getThumbnail());

        db.insert(TABLE_FAVORITES, null, values);
        db.close();
    }

    public Cocktail getFavorite(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FAVORITES,
                new String[] {KEY_ID, KEY_NAME, KEY_THUMB},
                KEY_ID + "=?",
                new String[] {id},
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor.getCount() == 0)
            return null;

        Cocktail favorite = new Cocktail();
        favorite.setCocktailId(cursor.getString(0));
        favorite.setName(cursor.getString(1));
        favorite.setThumbnail(cursor.getString(2));

        return favorite;
    }

    public List<Cocktail> getAllFavorites() {
        List<Cocktail> favoriteList = new ArrayList<Cocktail>();
        String selectQuery = "SELECT * FROM " + TABLE_FAVORITES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Cocktail favorite = new Cocktail();
                favorite.setCocktailId(cursor.getString(0));
                favorite.setName(cursor.getString(1));
                favorite.setThumbnail(cursor.getString(2));
                favoriteList.add(favorite);
            } while (cursor.moveToNext());
        }

        return favoriteList;
    }

    public int getFavoritesCount() {
        String countQuery = "SELECT * FROM " + TABLE_FAVORITES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateFavorite(Cocktail favorite) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, favorite.getName());
        values.put(KEY_THUMB, favorite.getThumbnail());

        return db.update(TABLE_FAVORITES,
                values,
                KEY_ID + " = ?",
                new String[] {favorite.getCocktailId()});
    }

    public void deleteFavorite(Cocktail favorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITES,
                KEY_ID + " = ?",
                new String[] {favorite.getCocktailId()});
        db.close();
    }

}
