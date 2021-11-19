package com.nescude.spoofy.handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class ConnectionConfig{

    private Connection con;

    @PostConstruct
    public void prepareDB(){
		String url = "jdbc:mysql://localhost:3306/";
        String dbName = "spoofy_db";
        url += dbName;

        String usuario = "root";
        String pass = "";

        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.con = DriverManager.getConnection(url, usuario, pass);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error, no se pudo conectar a la DB.");
		}
	} 

    public Connection getConnection() {
        if (con == null)
            this.prepareDB();
        return con;
    }

    public void closeConnection(){
        try {
            this.con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.con=null;
    }
}
