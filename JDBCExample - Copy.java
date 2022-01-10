package com.example.jdbcexample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;

public class JDBCExample {
	
	public static void main (String... args) {
		// 1. Carico il driver JAR
		// 2. Registro il driver con Java
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // sempre uguale
		} catch (ClassNotFoundException e) {
			// non ho aggiunto il jar			
			e.printStackTrace();
		}
		try {
			// 3. mi connetto al DB che sta qui ----------------------vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
			Connection mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:/sakila", "", ""); // analogo del workbench per java
			// 4. apro un canale di comunicazione
			Statement statement = mysqlConnection.createStatement(); //analogo della pagina vuota dove scrivete SQL in workbench
			// 5. mando la query
			ResultSet tabellaRisultati = statement.executeQuery("SELECT * FROM actor"); // qualsiasi query
			String myFile="...\\src\\main\\java\\com\\example\\jdbcexample\\resultset.txt";
			try {
	            File file = new File(myFile);
	    		file.createNewFile();
	            FileWriter fw=new FileWriter(file); 
	            BufferedWriter bw=new BufferedWriter(fw);  	            
	            while(tabellaRisultati.next()) {
				    // ... get column values from this record
	                bw.write(tabellaRisultati.getString("actor_id")+" "
				    +tabellaRisultati.getString("first_name")+" "+tabellaRisultati.getString("last_name")+"\n");
	                bw.flush();
				}
	        }catch(IOException e) {
	        	System.out.println("\nFile non trovato: \n"+e.getStackTrace());
	        }		
		} catch (SQLException e) {
			// errore di MySQL
			e.printStackTrace();
		}
	}

}
