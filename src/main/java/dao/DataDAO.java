package dao;

import db.MyConnection;
import model.Data;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDAO {

    public static List<Data> getAllFiles(String email) {
        List<Data> files = new ArrayList<>();

        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps =
                     connection.prepareStatement("SELECT * FROM data WHERE email = ?")) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("filename");
                String path = rs.getString("path");

                files.add(new Data(id, name, path));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return files;
    }
    public static int hideFile(Data file) throws SQLException, IOException {
        Connection connection=MyConnection.getConnection();
        PreparedStatement ps= connection.prepareStatement("insert into data (name,path,email,bin_data) values(?,?,?,?)");
        ps.setString(1,file.getFileName());
        ps.setString(2,file.getPath());
        ps.setString(3,file.getEmail());
        File f=new File(file.getPath());
        FileReader fr=new FileReader(f);
        ps.setCharacterStream(4,fr,f.length);
        int ans=ps.executeUpdate();
        fr.close();
        f.delete();
        return ans;

    }
    public static void unide(int id) throws SQLException,IOException{
        Connection connection=MyConnection.getConnection();
        PreparedStatement ps= connection.prepareStatement("select path,bin_data from data where id=?");
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        String path=rs.getString("path");
        Clob c=rs.getClob("bin_data");

        Reader r=c.getCharacterStream();
        FileWriter fw=new FileWriter(path);
        int i;
        while((i=r.read())!=-1){
            fw.write((char) i);
        }
        fw.close();
        ps= connection.prepareStatement("delete from data where id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
        System.out.println("Successfully unhidden");
    }


}
