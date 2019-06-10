/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Saloni
 */
public class Database {
    
    String getdate()
    {
        DateFormat dateformat = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String n = dateformat.format(date);
        return n;
    }
    String message = "";
    String sender = "";
    String reciever = "";
    String datentime = "";

public void setdata(String message, String reciever, String sender,String datentime)
{
this.message = message;
this.reciever = reciever;
this.sender = sender;
this.datentime = datentime;
try{
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatlog","root","");
    String sql = "insert into history(Sender, Reciever,datentime,message) values('"+sender+"','"+reciever+"','"+datentime+"','"+message+"')";
    Statement stmt = conn.createStatement();
    stmt.executeUpdate(sql);
    stmt.close();
    conn.close();
}
catch(Exception e)
{
    System.out.println(e.toString());
}
}
public int getID()
{
    int ID = 0;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatlog","root","");
        Statement stat = conn.createStatement();
        String querry = "select * from history order by id desc limit 1";
        ResultSet rs = stat.executeQuery(querry);
        if(rs.next())
        {
            ID = rs.getInt("ID");
        }
        else
        {
            System.out.println("Cannot get ID");
        }
        }
    catch(Exception e)
    {
        System.out.println("BAD REQUEST");
}
    System.out.println("returning ID: "+ID);
    return ID;
}
}



