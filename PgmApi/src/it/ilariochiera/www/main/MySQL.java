package it.ilariochiera.www.main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.entity.Player;

public class MySQL {
	
	public static Connection connection;
	  
	  public static String host;
	  
	  public static String port;
	  
	  public static String db;
	  
	  public static String user;
	  
	  public static String pass;
	  
	  
	  public static synchronized void closeConnection() {
	    try {
	      if (connection != null || !connection.isClosed())
	        connection.close(); 
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } 
	  }
	  
	  public static synchronized void openConnection() {
	    try {
	      connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?autoReconnect=true", user, pass);
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	  }
	  
	  public static synchronized boolean containsPlayer(Player player) {
	    try {
	      PreparedStatement sql = connection.prepareStatement("SELECT * FROM `Users` WHERE player=?;");
	      sql.setString(1, player.getName());
	      ResultSet rs = sql.executeQuery();
	      boolean containsP = rs.next();
	      sql.close();
	      rs.close();
	      return containsP;
	    } catch (Exception e) {
	      e.printStackTrace();
	      return false;
	    } 
	  }
	  
	  public static synchronized void setString(Player player, String campo, String valore) {
	    if (connection != null)
	      try {
	        PreparedStatement update = connection.prepareStatement("UPDATE `Users` SET " + campo + "=? WHERE Player=?;");
	        update.setString(1, valore);
	        update.setString(2, player.getName());
	        update.executeUpdate();
	        update.close();
	      } catch (SQLException e1) {
	        e1.printStackTrace();
	      }  
	  }
	  
	  public static synchronized void setStringS(String val2, String campo, String valore) {
		    if (connection != null)
		      try {
		        PreparedStatement update = connection.prepareStatement("UPDATE `Users` SET " + campo + "=? WHERE Player=?;");
		        update.setString(1, valore);
		        update.setString(2, val2);
		        update.executeUpdate();
		        update.close();
		      } catch (SQLException e1) {
		        e1.printStackTrace();
		      }  
		  }
	  
	  public static synchronized void setStringInt(Player player, String campo, int valore) {
		    if (connection != null)
		      try {
		        PreparedStatement update = connection.prepareStatement("UPDATE `Users` SET " + campo + "=? WHERE Player=? and " + campo + " >= 0;");
		        update.setLong(1, valore);
		        update.setString(2, player.getName());
		        update.executeUpdate();
		        update.close();
		      } catch (SQLException e1) {
		        e1.printStackTrace();
		      }  
		  }
	  
	  public static synchronized void setStringStringInt(String player, String campo, int valore) {
		    if (connection != null)
		      try {
		        PreparedStatement update = connection.prepareStatement("UPDATE `Users` SET " + campo + "=? WHERE Player=? and " + campo + " >= 0;");
		        update.setLong(1, valore);
		        update.setString(2, player);
		        update.executeUpdate();
		        update.close();
		      } catch (SQLException e1) {
		        e1.printStackTrace();
		      }  
		  }
	  
	  public static synchronized String getString(Player player, String campo) {
	    String stringa = null;
	    if (connection != null)
	      try {
	        PreparedStatement sql = connection.prepareStatement("SELECT " + campo + " FROM `Users` WHERE player=?;");
	        sql.setString(1, player.getName());
	        ResultSet result = sql.executeQuery();
	        result.next();
	        stringa = result.getString(campo);
	        sql.close();
	        result.close();
	      } catch (SQLException e1) {
	        e1.printStackTrace();
	      }  
	    return stringa;
	  }
	  
	  public static synchronized String getStringString(String player, String campo) {
		    String stringa = null;
		    if (connection != null)
		      try {
		        PreparedStatement sql = connection.prepareStatement("SELECT " + campo + " FROM `Users` WHERE player=?;");
		        sql.setString(1, player);
		        ResultSet result = sql.executeQuery();
		        result.next();
		        stringa = result.getString(campo);
		        sql.close();
		        result.close();
		      } catch (SQLException e1) {
		        e1.printStackTrace();
		      }  
		    return stringa;
		  }

}
