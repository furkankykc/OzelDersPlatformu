/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Kategori;
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
public class KategoriDAO {

    private Kategori kategori;
    private ArrayList kategoriList;

    public Kategori get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from kategori where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.kategori = new Kategori(rs.getInt("id"), rs.getString("adi"));
            } else {
                this.kategori = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.kategori;
    }

    public ArrayList<Kategori> list() {
        this.kategoriList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from kategori");
            while (rs.next()) {
                this.kategoriList.add(new Kategori(
                        rs.getInt("id"), rs.getString("adi")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.kategoriList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from kategori");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<Kategori> list(int page, int pageSize) {
        this.kategoriList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from kategori order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.kategoriList.add(new Kategori(
                        rs.getInt("id"), rs.getString("adi")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.kategoriList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from kategori where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Kategori a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update kategori set adi=? where id=?";
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

    public int create(Kategori a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into kategori (adi) values (?)";
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
