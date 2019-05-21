/*
 * To change this license header, choose License Heabrans in Project Properties.
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
    private KategoriDAO kategoriDao;

    public KategoriDAO getKategoriDao() {
        if (kategoriDao==null)
            kategoriDao = new KategoriDAO();
        return kategoriDao;
    }

    
    
    public Brans get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from brans where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.brans = new Brans(rs.getInt("id"), rs.getString("adi"),getKategoriDao().get(rs.getInt("kategori_id")));
            } else {
                this.brans = null;
            }
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
                        rs.getInt("id"), rs.getString("adi"),getKategoriDao().get(rs.getInt("kategori_id"))
                ));
                System.out.println("-----------------");
            }
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.bransList;
    }

    public ArrayList<Brans> list(int page, int pageSize) {
        this.bransList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from brans order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.bransList.add(new Brans(
                        rs.getInt("id"), rs.getString("adi"),getKategoriDao().get(rs.getInt("kategori_id"))
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

    public void delete(Brans a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from brans where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from brans");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Brans a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update brans set adi=?,kategori_id=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getKategori().getId());
            st.setInt(3, a.getId());
            
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Brans a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into brans (adi,kategori_id) values (?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getKategori().getId());
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
