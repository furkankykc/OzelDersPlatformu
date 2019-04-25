/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Duyuru;
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
public class DuyuruDAO {

    private Duyuru duyuru;
    private ArrayList duyuruList;

    public Duyuru get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from duyuru where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                this.duyuru = new Duyuru(rs.getInt("id"), rs.getString("baslik"), rs.getString("icerik"));
            else
                this.duyuru = null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.duyuru;
    }

    public ArrayList<Duyuru> list() {
        this.duyuruList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from duyuru");
            while (rs.next()) {
                this.duyuruList.add(new Duyuru(
                        rs.getInt("id"), rs.getString("baslik"), rs.getString("icerik")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.duyuruList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from duyuru");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<Duyuru> list(int page, int pageSize) {
        this.duyuruList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from duyuru order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.duyuruList.add(new Duyuru(
                        rs.getInt("id"), rs.getString("baslik"), rs.getString("icerik")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.duyuruList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from duyuru where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void delete(Duyuru a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from duyuru where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void update(Duyuru a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update duyuru set baslik=?, icerik=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getBaslik());
            st.setString(2, a.getIcerik());
            st.setInt(3, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Duyuru a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into duyuru (baslik,icerik) values (?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getBaslik());
            st.setString(2, a.getIcerik());
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
