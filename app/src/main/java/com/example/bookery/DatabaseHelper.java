package com.example.bookery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bookery.Admin.Admin;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name - library_management.db
    private static final String DATABASE_NAME = "library_management1.db";

    // Table Name - Admin,BOOKS,I_Books,Reader,Publisher,Section
    private static final String TABLE_BOOKS = "BOOKS";
    private static final String TABLE_Reader = "Reader";
    private static final String TABLE_Section = "Section";
    private static final String TABLE_I_Books = "I_Books";
    private static final String TABLE_Publisher = "Publisher";
    private static final String TABLE_Admin = "Admin";
    //Columns Names of Admin table
    private static final String ID = "username";
    private static final String TAG = "SQLiteAppLog";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "DatabaseHelper: database created");



    }
    public void addUser(Admin user){
        Log.d(TAG, "addUser() - " + user.toString());
        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("Admin_ID", user.getAdmin_ID()); // get title
        values.put("PASSWORD", user.getPassword());
        values.put("Admin_Name",user.getAdmin_Name());// get author

        //  insert to table
        db.insert(TABLE_Admin, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        //  close - release the reference of writable DB
        db.close();
    }





    @Override
    public void onCreate(SQLiteDatabase db) {


        Log.d(TAG, "onCreate: inside oncreate");
        String CREATE_ADMIN_TABLE = "CREATE TABLE Admin ( " +
                "Admin_ID INTEGER PRIMARY KEY not null , " +
                "Password TEXT not null, " +
                "Admin_Name TEXT not null)" ;
        String CREATE_PUBLISHER_TABLE = "CREATE TABLE Publisher ( " +
                "P_ID INTEGER PRIMARY KEY AUTOINCREMENT not null, " +
                "P_Name TEXT not null )" ;
        String CREATE_SECTION_TABLE = "CREATE TABLE Section ( " +
                "S_ID INTEGER PRIMARY KEY not null, " +
                "S_Name TEXT not null)" ;
        String CREATE_BOOKS_TABLE = "CREATE TABLE BOOKS ( " +
                "B_ID INTEGER PRIMARY KEY not null, " +
                "ISBN INTEGER UNIQUE not null, " +
                "Author_Name TEXT not null, " +
                "B_Name TEXT not null, "+
                "P_ID INTEGER not null,"+
                "S_ID INTEGER not null,"+
                "Admin_ID INTEGER not null,"+
                "FOREIGN KEY (S_ID) REFERENCES Section (S_ID)," +
                "FOREIGN KEY (P_ID) REFERENCES Publisher (P_ID)," +
                "FOREIGN KEY (Admin_ID) REFERENCES Admin (Admin_ID) )" ;

        String CREATE_READER_TABLE = "CREATE TABLE Reader ( " +
                "R_ID INTEGER PRIMARY KEY not null, " +
                "L_Name TEXT not null, "+
                "M_Name Text, "+
                "F_Name TEXT not null," +
                "Admin_ID INTEGER not null,  " +
                "FOREIGN KEY (Admin_ID) REFERENCES Admin (Admin_ID) )";
        String CREATE_I_BOOKS = "CREATE TABLE I_BOOKS ( " +
                "R_ID INTEGER not null, " +
                "B_ID INTEGER not null UNIQUE,"+
                "Issue_Date TEXT not null,"+
                "RETURN_DATE TEXT not null," +
                "FOREIGN KEY (B_ID) REFERENCES BOOKS (B_ID)," +
                "FOREIGN KEY (R_ID) REFERENCES Reader (R_ID) )";
        db.execSQL(CREATE_ADMIN_TABLE);
        db.execSQL(CREATE_PUBLISHER_TABLE);
        db.execSQL(CREATE_SECTION_TABLE);
        db.execSQL(CREATE_BOOKS_TABLE);
        db.execSQL(CREATE_READER_TABLE);
        db.execSQL(CREATE_I_BOOKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS Admin");
        db.execSQL("DROP TABLE IF EXISTS Publisher");
        db.execSQL("DROP TABLE IF EXISTS Section");
        db.execSQL("DROP TABLE IF EXISTS Reader");
        db.execSQL("DROP TABLE IF EXISTS BOOKS");
        db.execSQL("DROP TABLE IF EXISTS I_BOOKS");
        // create fresh books and users table
        this.onCreate(db);
    }
    public ArrayList<Admin> getAllUsers() {
        ArrayList<Admin> users = new ArrayList<Admin>();

        // build the query
        String query = "SELECT  * FROM " + TABLE_Admin;

        //  get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // go over each row, build book and add it to list
        Admin user = null;
        if (cursor.moveToFirst()) {
            do {
                user= new Admin();
                user.setAdmin_Name(cursor.getString(2));
                //System.out.println("TESTid: " + Integer.parseInt(cursor.getString(0)));
                user.setAdmin_ID(cursor.getString(0));
                user.setPassword(cursor.getString(1));

                // Add book to books
                users.add(user);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllUsers() - " + users.toString());

        // return books
        return users;
    }
    public ArrayList<Book> getAllBookList() {

        ArrayList<Book> bookList;
        String sql = "SELECT B_ID,B_Name,P_Name,Author_Name  FROM BOOKS JOIN Publisher on BOOKS.P_ID = Publisher.P_ID";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        bookList = new ArrayList<Book>();
        if (cursor.moveToFirst()){
            do{
                Book book = new Book();
                book.setTitle(cursor.getString(cursor.getColumnIndex("B_Name")));
                book.setBookId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("B_ID"))));
                book.setAuthor_Name(cursor.getString(cursor.getColumnIndex("Author_Name")));
                book.setP_Name(cursor.getString(cursor.getColumnIndex("P_Name")));
                bookList.add(book);
                // do what ever you want here
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookList;

    }
    public ArrayList<Book> getbookbyid(int bid) {

        ArrayList<Book> bookList;
        String sql = "SELECT B_ID,B_Name,P_Name,Author_Name  FROM BOOKS JOIN Publisher on BOOKS.P_ID = Publisher.P_ID WHERE B_ID = " + bid;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        bookList = new ArrayList<Book>();
        if (cursor.moveToFirst()){
            do{
                Book book = new Book();
                book.setTitle(cursor.getString(cursor.getColumnIndex("B_Name")));
                book.setBookId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("B_ID"))));
                book.setAuthor_Name(cursor.getString(cursor.getColumnIndex("Author_Name")));
                book.setP_Name(cursor.getString(cursor.getColumnIndex("P_Name")));
                bookList.add(book);
                // do what ever you want here
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookList;

    }
    public ArrayList<Book> getbookbyISBN(long isbn) {

        ArrayList<Book> bookList;
        String sql = "SELECT B_ID,B_Name,P_Name,Author_Name  FROM BOOKS JOIN Publisher on BOOKS.P_ID = Publisher.P_ID WHERE ISBN = " + isbn;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        bookList = new ArrayList<Book>();
        if (cursor.moveToFirst()){
            do{
                Book book = new Book();
                book.setTitle(cursor.getString(cursor.getColumnIndex("B_Name")));
                book.setBookId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("B_ID"))));
                book.setAuthor_Name(cursor.getString(cursor.getColumnIndex("Author_Name")));
                book.setP_Name(cursor.getString(cursor.getColumnIndex("P_Name")));
                bookList.add(book);
                // do what ever you want here
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookList;

    }
    public ArrayList<Book> getbookbyb_name(String B_Name) {

        ArrayList<Book> bookList;
        String sql = "SELECT B_ID,B_Name,P_Name,Author_Name  FROM BOOKS JOIN Publisher on BOOKS.P_ID = Publisher.P_ID WHERE B_Name = " +"'"+ B_Name+"'";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        bookList = new ArrayList<Book>();
        if (cursor.moveToFirst()){
            do{
                Book book = new Book();
                book.setTitle(cursor.getString(cursor.getColumnIndex("B_Name")));
                book.setBookId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("B_ID"))));
                book.setAuthor_Name(cursor.getString(cursor.getColumnIndex("Author_Name")));
                book.setP_Name(cursor.getString(cursor.getColumnIndex("P_Name")));
                bookList.add(book);
                // do what ever you want here
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookList;

    }
    public ArrayList<Book> getIssuedBooks() {

        ArrayList<Book> bookList;
        String sql = "SELECT I_BOOKS.B_ID AS B_ID,BOOKS.B_Name AS B_Name,Publisher.P_Name AS P_Name, BOOKS.Author_Name AS Author_Name  FROM I_BOOKS INNER JOIN BOOKS on I_BOOKS.B_ID = BOOKS.B_ID INNER JOIN Publisher ON BOOKS.P_ID = Publisher.P_ID" ;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        bookList = new ArrayList<Book>();
        if (cursor.moveToFirst()){
            do{
                Book book = new Book();
                book.setTitle(cursor.getString(cursor.getColumnIndex("B_Name")));
                book.setBookId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("B_ID"))));
                book.setAuthor_Name(cursor.getString(cursor.getColumnIndex("Author_Name")));
                book.setP_Name(cursor.getString(cursor.getColumnIndex("P_Name")));
                bookList.add(book);
                // do what ever you want here
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookList;

    }
    public ArrayList<Book> getReaderIssuedBooks(int RID) {

        ArrayList<Book> bookList;
        String sql = "SELECT I_BOOKS.B_ID AS B_ID,BOOKS.B_Name AS B_Name,Publisher.P_Name AS P_Name, BOOKS.Author_Name AS Author_Name  FROM I_BOOKS INNER JOIN BOOKS on I_BOOKS.B_ID = BOOKS.B_ID INNER JOIN Publisher ON BOOKS.P_ID = Publisher.P_ID WHERE I_BOOKS.R_ID = " + RID ;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        bookList = new ArrayList<Book>();
        if (cursor.moveToFirst()){
            do{
                Book book = new Book();
                book.setTitle(cursor.getString(cursor.getColumnIndex("B_Name")));
                book.setBookId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("B_ID"))));
                book.setAuthor_Name(cursor.getString(cursor.getColumnIndex("Author_Name")));
                book.setP_Name(cursor.getString(cursor.getColumnIndex("P_Name")));
                bookList.add(book);
                // do what ever you want here
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookList;

    }
    public ArrayList<Book> getavailablebooks() {

        ArrayList<Book> bookList;
        String sql = "SELECT B_ID,B_Name,Publisher.P_Name AS P_Name, Author_Name FROM BOOKS INNER JOIN Publisher ON P_ID = Publisher.P_ID LEFT JOIN Publisher on B_ID = Publisher.B_ID WHERE Publisher.B_ID = IS NULL " ;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        bookList = new ArrayList<Book>();
        if (cursor.moveToFirst()){
            do{
                Book book = new Book();
                book.setTitle(cursor.getString(cursor.getColumnIndex("B_Name")));
                book.setBookId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("B_ID"))));
                book.setAuthor_Name(cursor.getString(cursor.getColumnIndex("Author_Name")));
                book.setP_Name(cursor.getString(cursor.getColumnIndex("P_Name")));
                bookList.add(book);
                // do what ever you want here
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookList;

    }
    public String getPassword(String id){
        String password = "";
        String selectQuery = "SELECT password FROM Admin WHERE Admin_ID = "+"'" +id+"'" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            password = cursor.getString(0);
        }else {
            return "Wrong User ID";
        }
        cursor.close();
        db.close();
        // return user
        return password;
    }
    public int checkReaderLogin(int id) {

        int reader_id = 0;
        String selectQuery = "SELECT R_ID FROM reader WHERE R_ID = "+id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            reader_id = cursor.getInt(0);
            Log.e("ReaderID",reader_id+"");
        }else {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return reader_id;
    }
    public int Icheckbook(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT B_ID FROM I_BOOKS WHERE B_ID = "+id;


        int B_ID = 0;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            B_ID = cursor.getInt(0);

        }else {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return B_ID;
    }
    public int checkbook(long isbn) {

        int b_id = 0;
        String selectQuery = "SELECT B_ID FROM BOOKS WHERE ISBN = "+isbn;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            b_id = cursor.getInt(0);

        }else {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return b_id;
    }
    public int checkbook(int bid) {

        int b_id = 0;
        String selectQuery = "SELECT B_ID FROM BOOKS WHERE B_ID = "+bid;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            b_id = cursor.getInt(0);

        }else {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return b_id;
    }
    public int checkbook(String b_name) {

        int b_id = 0;
        String selectQuery = "SELECT B_ID FROM BOOKS WHERE B_Name ="+"'" +b_name+"'";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            b_id = cursor.getInt(0);

        }else {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return b_id;
    }
    public Long AddReader(int R_ID,String id,String F_name, String M_Name, String L_Name) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("R_ID", R_ID);
        values.put("Admin_ID", id);
        values.put("L_Name", L_Name);
        values.put("M_Name", M_Name);
        values.put("F_name", F_name);


        long insid = db.insert("reader", null, values);
        db.close(); // Closing database connection

        return insid;
    }
    public long addI_BOOK(int R_ID, int id, String issue, String returni) {

        long insid = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("R_ID", R_ID);
        values.put("B_ID", id);
        values.put("Issue_Date", issue);
        values.put("RETURN_DATE", returni);



        insid = db.insert("I_BOOKS", null, values);
        db.close(); // Closing database connection

        return insid;
    }
    public ArrayList<String> getAllB_ID() {
        ArrayList<String> isbnList = new ArrayList<String>();

        String sql = "SELECT B_ID FROM book";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do{
                isbnList.add(cursor.getString(cursor.getColumnIndex("isbn")));
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return isbnList;
    }

    public ArrayList<String> getAllPublisher() {
        ArrayList<String> publisherList = new ArrayList<String>();

        String sql = "SELECT P_Name FROM Publisher";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do{
                publisherList .add(cursor.getString(cursor.getColumnIndex("P_Name")));
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return publisherList;
    }
    public ArrayList<String> getAllBook_name() {
        ArrayList<String> BooknameList = new ArrayList<String>();

        String sql = "SELECT B_Name FROM BOOKS";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do{
                BooknameList .add(cursor.getString(cursor.getColumnIndex("B_Name")));
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return BooknameList;
    }
    public ArrayList<Long> getAllISBN() {
        ArrayList<Long> ISBNlist = new ArrayList<Long>();

        String sql = "SELECT ISBN FROM BOOKS";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do{
                ISBNlist .add(cursor.getLong(cursor.getColumnIndex("ISBN")));
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return ISBNlist;
    }
    public ArrayList<Integer> getAllbid() {
        ArrayList<Integer> bidlist = new ArrayList<Integer>();

        String sql = "SELECT B_ID FROM BOOKS";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do{
                bidlist .add(cursor.getInt(cursor.getColumnIndex("B_ID")));
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return bidlist;
    }
    public ArrayList<Integer> getAllrid() {
        ArrayList<Integer> ridlist = new ArrayList<Integer>();

        String sql = "SELECT R_ID FROM Reader";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do{
                ridlist .add(cursor.getInt(cursor.getColumnIndex("R_ID")));
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return ridlist;
    }
    public ArrayList<Integer> getAllPublisherI() {
        ArrayList<Integer> publisherListI = new ArrayList<Integer>();

        String sql = "SELECT P_ID FROM Publisher";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do{
                publisherListI .add(cursor.getInt(cursor.getColumnIndex("P_ID")));
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return publisherListI;
    }
    public ArrayList<String> getAllSection() {
        ArrayList<String> sectionlist = new ArrayList<String>();

        String sql = "SELECT S_Name FROM Section";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do{
                sectionlist.add(cursor.getString(cursor.getColumnIndex("S_Name")));
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return sectionlist;
    }

    public int getPublisherID(String selectedPublisher) {
        int publisher_id ;
        String selectQuery = "SELECT P_ID FROM publisher WHERE P_Name = "+"'" +selectedPublisher+"'" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            publisher_id = cursor.getInt(0);
        }else {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return publisher_id;
    }

    public int getlastbookid() {
        int bookid  = 0;
        String selectQuery = "SELECT B_ID from BOOKS order by B_ID DESC limit 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            bookid = cursor.getInt(0); //The 0 is the column index, we only have 1 column, so the index is 0
        }
        cursor.close();
        db.close();

        return bookid + 1;
    }
    public int getBookID(long isbn)
    {
        int book_id =0 ;
        String selectQuery = "SELECT B_ID FROM BOOKS WHERE ISBN = "+"'" +isbn+"'" ;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            book_id = cursor.getInt(0);
        }else
        {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return book_id;
    }
    public String getBookName(int book_id)
    {
        String b_name = " " ;
        String selectQuery = "SELECT B_Name FROM BOOKS WHERE B_ID = "+"'" +book_id+"'" ;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            b_name = cursor.getString(0);
        }else
        {
            return null;
        }
        cursor.close();
        db.close();
        // return user
        return b_name;
    }
    public String getIssueDate(int book_id)
    {
        String b_name = " " ;
        String selectQuery = "SELECT Issue_Date FROM I_BOOKS WHERE B_ID = "+"'" +book_id+"'" ;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            b_name = cursor.getString(0);
        }else
        {
            return null;
        }
        cursor.close();
        db.close();
        // return user
        return b_name;
    }
    public String getReturnDate(int book_id)
    {
        String b_name = " " ;
        String selectQuery = "SELECT Return_Date FROM I_BOOKS WHERE B_ID = "+"'" +book_id+"'" ;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            b_name = cursor.getString(0);
        }else
        {
            return null;
        }
        cursor.close();
        db.close();
        // return user
        return b_name;
    }
    public String getbookauthor(int book_id)
    {
        String b_author = " " ;
        String selectQuery = "SELECT Author_Name FROM BOOKS WHERE B_ID = "+"'" +book_id+"'" ;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            b_author = cursor.getString(0);
        }else
        {
            return null;
        }
        cursor.close();
        db.close();
        // return user
        return b_author;
    }
    public int getpublisherid(int book_id)
    {
        int p_id ;
        String selectQuery = "SELECT P_ID FROM BOOKS WHERE B_ID = "+"'" +book_id+"'" ;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            p_id = cursor.getInt(0);
        }else
        {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return p_id;
    }
    public int getSectionID(int book_id)
    {
        int s_id ;
        String selectQuery = "SELECT S_ID FROM BOOKS WHERE B_ID = "+"'" +book_id+"'" ;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            s_id = cursor.getInt(0);
        }else
        {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return s_id;
    }
    public Long addBookInfo(int Admin_ID,  int P_ID, int S_ID,String B_Name,String B_author,long ISBN) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Admin_ID", Admin_ID);
        values.put("P_ID", P_ID);
        values.put("S_ID", S_ID);
        values.put("Author_Name", B_author);
        values.put("B_Name",B_Name);
        values.put("ISBN",ISBN);
        long insid = db.insert("BOOKS", null, values);
        db.close(); // Closing database connection

        return insid;
    }

    public int getSectionID(String selectedSection) {
        int branch_id;
        String selectQuery = "SELECT S_ID FROM Section WHERE S_Name = "+"'" +selectedSection+"'" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            branch_id = cursor.getInt(0);
        }else {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return branch_id;
    }

    public Long addpublisher(String P_Name) {
        long insid = 0;
        if(P_Name == "")
        {
            return insid;
        }
        else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("P_Name", P_Name);
            insid = db.insert("Publisher", null, values);
            db.close(); // Closing database connection

            return insid;
        }
    }

    public Long addsection(String P_Name) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("S_Name", P_Name);
        long insid = db.insert("Section", null, values);
        db.close(); // Closing database connection

        return insid;
    }



    public int checkBook(long ISBN) {

        String selectQuery = "SELECT B_Name FROM BOOKS WHERE ISBN = "+"'" +ISBN+"'" ;
        String B_name = "pjv";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            B_name = cursor.getString(0);
        }else
        {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        if(B_name!="pjv")
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }




    public ArrayList<Integer> getAllID() {
        ArrayList<Integer> idList = new ArrayList<Integer>();

        String sql = "SELECT B_ID FROM BOOKS";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do{
                idList.add(cursor.getInt(cursor.getColumnIndex("B_ID")));
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return idList;
    }
    public boolean deleteTitle(int name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("I_BOOKS", "B_ID" + "=" + name, null) > 0;
    }

}

