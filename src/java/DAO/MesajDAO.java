/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Mesaj;
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
public class MesajDAO {

    private Mesaj mesaj;
    private ArrayList mesajList;
    private UserDAO userDao;

    public Mesaj get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from mesaj where id=?";
        userDao = new UserDAO();
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.mesaj = new Mesaj(rs.getInt("id"), userDao.get(rs.getInt("gonderici_id")), userDao.get(rs.getInt("alici_id")), rs.getString("title"), rs.getString("icerik"));
            } else {
                this.mesaj = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.mesaj;
    }

    public ArrayList<Mesaj> list() {
        this.mesajList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        userDao = new UserDAO();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from mesaj");
            while (rs.next()) {
                this.mesajList.add(new Mesaj(
                        rs.getInt("id"), userDao.get(rs.getInt("gonderici_id")), userDao.get(rs.getInt("alici_id")), rs.getString("title"), rs.getString("icerik")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.mesajList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from mesaj");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<Mesaj> list(int page, int pageSize) {
        this.mesajList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        userDao = new UserDAO();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from mesaj order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.mesajList.add(new Mesaj(
                        rs.getInt("id"), userDao.get(rs.getInt("gonderici_id")), userDao.get(rs.getInt("alici_id")), rs.getString("title"), rs.getString("icerik")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.mesajList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from mesaj where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Mesaj a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from mesaj where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Mesaj a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update mesaj set gonderici_id=?, alici_id=?, title=?, icerik=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getGonderici().getId());
            st.setInt(2, a.getAlici().getId());
            st.setString(3, a.getTitle());
            st.setString(4, a.getIcerik());
            st.setInt(5, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Mesaj a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "insert into mesaj (gonderici_id, alici_id, title, icerik) values (?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, a.getGonderici().getId());
            st.setInt(2, a.getAlici().getId());
            st.setString(3, a.getTitle());
            st.setString(4, a.getIcerik());
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
