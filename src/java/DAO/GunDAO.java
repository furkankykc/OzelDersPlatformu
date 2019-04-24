/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Gun;
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
public class GunDAO {

    private Gun gun;
    private ArrayList gunList;

    public Gun get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from gun where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.gun = new Gun(rs.getInt("id"), rs.getString("adi"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.gun;
    }

    public ArrayList<Gun> list() {
        this.gunList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from gun");
            while (rs.next()) {
                this.gunList.add(new Gun(
                        rs.getInt("id"), rs.getString("adi")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.gunList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from gun");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<Gun> list(int page, int pageSize) {
        this.gunList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from gun order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.gunList.add(new Gun(
                        rs.getInt("id"), rs.getString("adi")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.gunList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from gun where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Gun a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update gun set adi=? where id=?";
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

    public void create(Gun a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into gun (adi) values (?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
