/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Yorum;
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
public class YorumDAO {

    private Yorum yorum;
    private ArrayList yorumList;

    private final UserDAO userDao = new UserDAO();

    public Yorum get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from yorum where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.yorum = new Yorum(rs.getInt("id"), userDao.get(rs.getInt("user_id")), rs.getString("yorum"));
            } else {
                this.yorum = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.yorum;
    }

    public ArrayList<Yorum> list() {
        this.yorumList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from yorum");
            while (rs.next()) {
                this.yorumList.add(new Yorum(
                        rs.getInt("id"), userDao.get(rs.getInt("user_id")), rs.getString("yorum")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.yorumList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from yorum");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<Yorum> list(int page, int pageSize) {
        this.yorumList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from yorum order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.yorumList.add(new Yorum(
                        rs.getInt("id"), userDao.get(rs.getInt("user_id")), rs.getString("yorum")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.yorumList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from yorum where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public void delete(Yorum a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from yorum where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Yorum a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update yorum set user_id=?, yorum=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getUser().getId());
            st.setString(2, a.getYorum());
            st.setInt(3, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Yorum a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into yorum (user_id, yorum) values (?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, a.getUser().getId());
            st.setString(2, a.getYorum());
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
