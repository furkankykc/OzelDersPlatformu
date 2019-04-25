/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Grup;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author furkankykc
 */
public class GrupDAO {

    private Grup grup;
    private ArrayList grupList;
    private YetkiDAO yetkiDao = new YetkiDAO();

    public Grup get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from grup where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.grup = new Grup(rs.getInt("id"), rs.getString("adi"),yetkiDao.get(rs.getInt("yetki_id")));
            } else {
                this.grup = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.grup;
    }

    public ArrayList<Grup> list() {
        this.grupList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from grup");
            while (rs.next()) {
                this.grupList.add(new Grup(
                        rs.getInt("id"), rs.getString("adi"),yetkiDao.get(rs.getInt("yetki_id"))
                ));
                System.out.println("-----------------");

            }
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.grupList;
    }

    public ArrayList<Grup> list(int page, int pageSize) {
        this.grupList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from grup order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.grupList.add(new Grup(
                        rs.getInt("id"), rs.getString("adi"),yetkiDao.get(rs.getInt("yetki_id"))
                ));
                System.out.println("-----------------");

            }
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.grupList;
    }

    public List<Grup> geUserGrup(int k) {
        this.grupList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user_grup where user_id=" + k);
            while (rs.next()) {
                this.grupList.add(this.get(rs.getInt("grup_id")));
                System.out.println("-----------------");
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.grupList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from grup where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  public void delete(Grup a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from grup where id=?";
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
            ResultSet rs = st.executeQuery("select count(id) as a_count from grup");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
    public void update(Grup a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update grup set adi=? , yetki_id=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getYetki().getId());
            st.setInt(3, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Grup a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into grup (adi,yetki_id) values (?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getYetki().getId());
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
