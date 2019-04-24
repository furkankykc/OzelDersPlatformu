/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Brans;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author furkankykc
 */
public class BransDAO {

    private Brans brans;
    private ArrayList bransList;

    public Brans get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from brans where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                this.brans = new Brans(rs.getInt("id"), rs.getString("adi"));
            else
                this.brans = null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.brans;
    }

    public ArrayList<Brans> list() {
        this.bransList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from brans");
            while (rs.next()) {
                this.bransList.add(new Brans(
                        rs.getInt("id"), rs.getString("adi")
                ));
                System.out.println("-----------------");
            }
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.bransList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from brans where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Brans a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update brans set adi=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Brans a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into brans (adi) values (?)";
        try {
            PreparedStatement st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.executeUpdate();
            
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    return (generatedKeys.getInt(1));
                }

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
