/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Grup;
import Entity.User;
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
public class UserDAO {

    private User user;
    private ArrayList userList;
    private GrupDAO gDao = new GrupDAO();

    public User get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from user where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.user = new User(rs.getInt("id"), rs.getInt("bakiye"), rs.getString("telefon"), rs.getString("isim"), rs.getString("sehir"), rs.getString("email"), rs.getString("egitim_duzeyi"), rs.getString("okul_durumu"), rs.getDate("uyelik_tarihi"), rs.getString("meslek"), rs.getString("diger"), rs.getString("password"), gDao.geUserGrup(rs.getInt("id")));
            } else {
                this.user = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.user;
    }

    public User get(String email) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from user where email=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.user = new User(rs.getInt("id"), rs.getInt("bakiye"), rs.getString("telefon"), rs.getString("isim"), rs.getString("sehir"), rs.getString("email"), rs.getString("egitim_duzeyi"), rs.getString("okul_durumu"), rs.getDate("uyelik_tarihi"), rs.getString("meslek"), rs.getString("diger"), rs.getString("password"), gDao.geUserGrup(rs.getInt("id")));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.user;
    }

    public ArrayList<User> list() {
        this.userList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user");
            while (rs.next()) {
                this.userList.add(new User(
                        rs.getInt("id"), rs.getInt("bakiye"), rs.getString("telefon"), rs.getString("isim"), rs.getString("sehir"), rs.getString("email"), rs.getString("egitim_duzeyi"), rs.getString("okul_durumu"), rs.getDate("uyelik_tarihi"), rs.getString("meslek"), rs.getString("diger"), rs.getString("password"), gDao.geUserGrup(rs.getInt("id"))
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.userList;
    }

    public ArrayList<User> list(int page, int pageSize) {
        this.userList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.userList.add(new User(
                        rs.getInt("id"), rs.getInt("bakiye"), rs.getString("telefon"), rs.getString("isim"), rs.getString("sehir"), rs.getString("email"), rs.getString("egitim_duzeyi"), rs.getString("okul_durumu"), rs.getDate("uyelik_tarihi"), rs.getString("meslek"), rs.getString("diger"), rs.getString("password"), gDao.geUserGrup(rs.getInt("id"))
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.userList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from user");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from user where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(User a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from user where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(User a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update user set isim=?, sehir=?, email=?, telefon=?, egitim_duzeyi=?, okul_durumu=?, uyelik_tarihi=?, meslek=?, diger=?, bakiye=?, password=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getIsim());
            st.setString(2, a.getSehir());
            st.setString(3, a.getEmail());
            st.setString(4, a.getTelefon());
            st.setString(5, a.getEgitimDuzeyi());
            st.setString(6, a.getOkulDurumu());
            st.setDate(7, a.getUyelikTarihi());
            st.setString(8, a.getMeslek());
            st.setString(9, a.getDiger());
            st.setInt(10, a.getBakiye());
            st.setString(11, a.getPassword());
            st.setInt(12, a.getId());
            st.executeUpdate();
            for (Grup g : a.getGrup()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into user_grup(user_id,grup_id) values(+" + a.getId() + ",'" + g.getId() + "')");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(User a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into user (isim, sehir, email, telefon, egitim_duzeyi, okul_durumu, uyelik_tarihi, meslek, diger, bakiye, password) values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getIsim());
            st.setString(2, a.getSehir());
            st.setString(3, a.getEmail());
            st.setString(4, a.getTelefon());
            st.setString(5, a.getEgitimDuzeyi());
            st.setString(6, a.getOkulDurumu());
            st.setDate(7, a.getUyelikTarihi());
            st.setString(8, a.getMeslek());
            st.setString(9, a.getDiger());
            st.setInt(10, a.getBakiye());
            st.setString(11, a.getPassword());
            st.executeUpdate();
            int kid = 0;
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                kid = rs.getInt(1);

            }
            for (Grup g : a.getGrup()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into user_grup(user_id,grup_id) values(+" + kid + ",'" + g.getId() + "')");

            }
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
