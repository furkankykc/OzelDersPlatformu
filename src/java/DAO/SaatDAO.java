/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Saat;
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
public class SaatDAO {

    private Saat saat;
    private ArrayList saatList;

    public Saat get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from saat where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.saat = new Saat(rs.getInt("id"), rs.getString("saat"));
            } else {
                this.saat = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.saat;
    }

    public ArrayList<Saat> list() {
        this.saatList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from saat");
            while (rs.next()) {
                this.saatList.add(new Saat(
                        rs.getInt("id"), rs.getString("saat")
                ));
                System.out.println("-----------------");

            }
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.saatList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from saat");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<Saat> list(int page, int pageSize) {
        this.saatList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from saat order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.saatList.add(new Saat(
                        rs.getInt("id"), rs.getString("saat")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.saatList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from saat where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Saat a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from saat where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Saat a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update saat set saat=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getSaat());
            st.setInt(2, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Saat a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into saat (saat) values (?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getSaat());
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
