package com.nescude.spoofy.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nescude.spoofy.handlers.ConnectionConfig;
import com.nescude.spoofy.interfaces.IRepository;
import com.nescude.spoofy.model.users.Premium;
import com.nescude.spoofy.model.users.Standard;
import com.nescude.spoofy.model.users.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserRepository implements IRepository<User>{

    @Autowired
    ConnectionConfig con;

    @Override
    public boolean save(User u) {
        String query = "insert into user "+
        "values(?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.getConnection().prepareStatement(query);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getFullName());
            ps.setString(3, "2021-11-12");
            ps.setString(4, u.getType().value());
            ps.execute();
            return true;            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

    @Override
    public User find(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    

    public User find(String username) {
        String query = "select * from user where username = ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.getConnection().prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            User user;
            while (rs.next()){
                if (rs.getString("type").equals(User.PLAN_TYPE.PREMIUM))
                    user = new Premium(rs.getString("username"),rs.getString("fullname"),rs.getString("type"));    
                else
                    user = new Standard(rs.getString("username"),rs.getString("fullname"),rs.getString("type"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String username) {
        String query = "delete from user where username = ?";
        PreparedStatement ps;
        try {
            ps = con.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        String query = "select * from user";
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<User> users = new ArrayList<>();
        try {
            ps = con.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()){
                User user;
                if (rs.getString("type").equals(User.PLAN_TYPE.PREMIUM))
                    user = new Premium(rs.getString("username"),rs.getString("fullname"),rs.getString("type"));    
                else
                    user = new Standard(rs.getString("username"),rs.getString("fullname"),rs.getString("type"));
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
