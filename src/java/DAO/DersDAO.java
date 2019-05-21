/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Ders;
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
public class DersDAO {

    private Ders ders;
    private ArrayList dersList;
    private UserDAO userDao;
    private FileDAO fileDao;
    private BransDAO bransDao;

    public UserDAO getUserDao() {
        if(userDao==null)
            userDao = new UserDAO();
        return userDao;
    }

    public FileDAO getFileDao() {
        if(fileDao==null)
            fileDao = new FileDAO();
        return fileDao;
    }

    public BransDAO getBransDao() {
        if(bransDao==null)
            bransDao = new BransDAO();
        return bransDao;
    }
    
    

    public Ders get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from ders where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.ders = new Ders(rs.getInt("id"), rs.getString("adi"), rs.getInt("ucret"),getBransDao().get(rs.getInt("brans_id")),getFileDao().get(rs.getInt("image_id")),getUserDao().get(rs.getInt("user_id")));
            } else {
                this.ders = null;
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.ders;
    }

    public ArrayList<Ders> list() {
        this.dersList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ders");
            while (rs.next()) {
                this.dersList.add(new Ders(
                        rs.getInt("id"), rs.getString("adi"), rs.getInt("ucret"),getBransDao().get(rs.getInt("brans_id")),getFileDao().get(rs.getInt("image_id")),getUserDao().get(rs.getInt("user_id"))
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.dersList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from ders");
            rs.next();
            count = rs.getInt("a_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<Ders> list(int page, int pageSize) {
        this.dersList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ders order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.dersList.add(new Ders(
                        rs.getInt("id"), rs.getString("adi"), rs.getInt("ucret"),getBransDao().get(rs.getInt("brans_id")),getFileDao().get(rs.getInt("image_id")),getUserDao().get(rs.getInt("user_id"))
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.dersList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from ders where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Ders a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from ders where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Ders a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update ders set adi=?,ucret=?,brans_id=?,image_id=?,user_id=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getUcret());
            st.setInt(3, a.getBrans().getId());
            st.setInt(4, a.getImage().getId());
            st.setInt(5, a.getUser().getId());
            st.setInt(6, a.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Ders a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into ders (adi,ucret,brans_id,image_id,user_id) values (?,?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getUcret());
            st.setInt(3, a.getBrans().getId());
            st.setInt(4, a.getImage().getId());
            st.setInt(5, a.getUser().getId());
            st.executeUpdate();
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    return (generatedKeys.getInt(1));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
