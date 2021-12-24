
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class internettracker2 {
    public static String datebefore;
    public static String dateafter;
    public static boolean insertintodb=false;
    public static PreparedStatement psmt;
    public static Connection con;
    public static void main(String[] args) throws InterruptedException{
        for(int i=1;i<1000;i++){
            try{
            URL url=new URL("http://www.google.com");
            URLConnection connection=url.openConnection();
            connection.connect();
            
            if(insertintodb==true){
                    //insert the latest time in database
                    
                    Date date2= new Date();
                    dateafter=String.valueOf(date2);
                    System.out.println("After date taken");
                    //insert();
                    try{
                        Class.forName("org.sqlite.JDBC");
                        String sql="insert into test(datebefore,dateafter) values(?,?)";
                        //Connection con=DriverManager.getConnection("jdbc:sqlite:/root/Documents/databases/tracker.db");
                        con=DriverManager.getConnection("jdbc:sqlite:/root/Documents/databases/tracker.db");
                        //String sql="insert into test(datebefore,dateafter) values(?,?)";
                        //PreparedStatement psmt=con.prepareStatement(sql);
                        psmt=con.prepareStatement(sql);
                        psmt.setString(1,datebefore);
                        psmt.setString(2,dateafter);
                        psmt.execute();
                        System.out.println("inserted into database");
                        
                    }catch(ClassNotFoundException | SQLException ex){
                        ex.printStackTrace();
                    }
                    finally{
                        try{
                           psmt.close();
                           con.close();
                        }catch(Exception ext){
                            ext.printStackTrace();
                        }
                    }
                   
                    
                    insertintodb=false;
                }
                else{
                    System.out.println("Connection");
                    Thread.sleep(1000);
                }
            
            
            }catch(Exception e){
                
                if(insertintodb==false){
                    Date date= new Date();
                    datebefore=String.valueOf(date);
                    System.out.println("before date taken");
                    insertintodb=true;
                }
                else{
                    System.out.println("No Internet Connection");
                    Thread.sleep(1000);
                }
                
            }
        }
    }
}
