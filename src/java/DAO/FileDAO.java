/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.File;
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
public class FileDAO {

    private File file;
    private ArrayList fileList;

    public File get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from file where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                this.file = new File(rs.getInt("id"), rs.getString("adi"), rs.getString("path"), rs.getString("type"));
            else
                this.file = null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.file;
    }

    public ArrayList<File> list() {
        this.fileList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from file");
            while (rs.next()) {
                this.fileList.add(new File(
                        rs.getInt("id"), rs.getString("adi"), rs.getString("path"), rs.getString("type")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.fileList;
    }

    public ArrayList<File> list(int page, int pageSize) {
        this.fileList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from file order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.fileList.add(new File(
                        rs.getInt("id"), rs.getString("adi"), rs.getString("path"), rs.getString("type")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.fileList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from file where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(File a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update file set adi=?, type=?, path=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setString(2, a.getType());
            st.setString(3, a.getPath());
            st.setInt(4, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(File a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into file (adi,type,path) values (?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.setString(2, a.getType());
            st.setString(3, a.getPath());
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
