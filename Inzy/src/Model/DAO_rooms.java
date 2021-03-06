/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import DAO.*;
import DB_objects.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author PROBOOK
 */
public class DAO_rooms {
       Connection Maconnexion ; 
       DaoBD dao ;
    public DAO_rooms() {
        dao = new DaoBD(); 
        dao.setPilote("com.mysql.jdbc.Driver");
        dao.setUrl("jdbc:mysql://localhost:3306/java_inzy");
        dao.setLogin("root");
        dao.setPassword("");
        dao.Connect();
        Maconnexion = dao.getConnexion();  
    }
  public ObservableList<DB_rooms> getrooms(String param1,String param2,String param3)  throws SQLException
  {
  System.out.println(param1);
  System.out.println(param2);
  System.out.println(param3);
  ObservableList<DB_rooms> dt = FXCollections.observableArrayList(); 
  ResultSet rs = null;
  Statement statement;
          statement=Maconnexion.createStatement();
       String qr = "select distinct r.id,floor,type,beds,state,price ";
              qr += "from rooms r where not exists(select * from details d where d.room_id = r.id and (('"+param1;
              qr+="'>= start_in and '"+param2+"' <= end_in) or ('"+param1+"' <= start_in and '";
              qr+= param2+"' between start_in and end_in) or ('"+param1;
              qr+="' between start_in and end_in and '"+param2+"' >= end_in) ";
              qr+="or ('"+param1+"' <= start_in and '"+param2+"' >= end_in)))";
	      qr += param3;
  System.out.println(qr);
  rs = statement.executeQuery(qr);
  while(rs.next()){
            DB_rooms r = new DB_rooms(rs.getString("r.id"),rs.getString("floor"),rs.getString("type"),rs.getString("beds"),rs.getString("price"));
            dt.add(r); 
         } 
  return dt;
  }
  
  public ObservableList<DB_rooms> getroomsnav(String param1,String param2,String param3)  throws SQLException
  {
  System.out.println(param1);
  System.out.println(param2);
  System.out.println(param3);
  ObservableList<DB_rooms> dt = FXCollections.observableArrayList(); 
  ResultSet rs = null;
  Statement statement;
          statement=Maconnexion.createStatement();
         String qr = "select distinct r.id,floor,type,beds,state,price ";
              qr += "from rooms r where exists(select * from details d where d.room_id = r.id and (('"+param1;
              qr+="'>= start_in and '"+param2+"' <= end_in) or ('"+param1+"' <= start_in and '";
              qr+= param2+"' between start_in and end_in) or ('"+param1;
              qr+="' between start_in and end_in and '"+param2+"' >= end_in) ";
              qr+="or ('"+param1+"' <= start_in and '"+param2+"' >= end_in)))";
	      qr += param3;
  System.out.println(qr);
  rs = statement.executeQuery(qr);
  while(rs.next()){
            DB_rooms r = new DB_rooms(rs.getString("id"),rs.getString("floor"),rs.getString("type"),rs.getString("beds"),rs.getString("price"));
            dt.add(r); 
         } 
  return dt;
  }
  
    public ObservableList<DB_rooms> getall(String param1,String param2,String param3)  throws SQLException
  {
  System.out.println(param1);
  System.out.println(param2);
  System.out.println(param3);
  ObservableList<DB_rooms> dt = FXCollections.observableArrayList(); 
  ResultSet rs = null;
  Statement statement;
          statement=Maconnexion.createStatement();
  String qr = "select distinct r.id,floor,type,beds,state,price ";
       qr += " from rooms r where 1 ";
       qr += param3;
  System.out.println(qr);
  rs = statement.executeQuery(qr);
  while(rs.next()){
            DB_rooms r = new DB_rooms(rs.getString("id"),rs.getString("floor"),rs.getString("type"),rs.getString("beds"),rs.getString("price"));
            dt.add(r); 
         } 
  return dt;
  }
     public ObservableList<DB_rooms> All() throws SQLException {
               ObservableList<DB_rooms> dt = FXCollections.observableArrayList();
               ResultSet rs = null;
               Statement statement;
               statement=Maconnexion.createStatement();
           rs = statement.executeQuery("select * from rooms ;");
             
               while(rs.next()){
                 
                   DB_rooms r = new DB_rooms(rs.getString("id"),rs.getString("floor"),rs.getString("type"),rs.getString("beds"),rs.getString("price"));
                   dt.add(r);
               }
               return dt;
           
         
     }
      
          
}
  
