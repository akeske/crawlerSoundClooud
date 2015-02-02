package org.jsoup.Main;

import java.sql.*;

/**
 * Created by akeske
 */
public class DBConnection {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    Connection conn = null;
    Statement stmt = null;

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert(UserInfo u){
        PreparedStatement preparedStmt = null;
        Boolean exist = false;

        String queryCheckDuplicate = "SELECT `id` FROM `tzoura`.`users` WHERE `id`=?";
        try {
            preparedStmt = conn.prepareStatement(queryCheckDuplicate);
            preparedStmt.setInt(1, u.getId());
            ResultSet rs = preparedStmt.executeQuery();
            if(rs.next()){
                Main.duplicateUsersCounter++;
                exist = true;
            }
        } catch (SQLException e) {
        }

        if(exist==false) {
            String query = "INSERT INTO `tzoura`.`users` (`id`, `full_name`, `description`, `country`, `city`, `track_count`," +
                    " `public_favorites_count`, `followers_count`, `followings_count`, `likes_count`, `comments_count`, `username`) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            try {
                preparedStmt = conn.prepareStatement(query);

                preparedStmt.setInt(1, u.getId());
                preparedStmt.setString(2, u.getFull_name());
                preparedStmt.setString(3, u.getDescription());
                preparedStmt.setString(4, u.getCountry());
                preparedStmt.setString(5, u.getCity());
                preparedStmt.setInt(6, u.getTrack_count());
                preparedStmt.setInt(7, u.getPublic_favorites_count());
                preparedStmt.setInt(8, u.getFollowers_count());
                preparedStmt.setInt(9, u.getFollowings_count());
                preparedStmt.setInt(10, u.getLikes_count());
                preparedStmt.setInt(11, u.getComments_count());
                preparedStmt.setString(12, u.getUsername());

                preparedStmt.execute();

                Main.newUsersCounter++;
            } catch (SQLException e) {
            }
        }
    }

    public void close(){
        try {
            if (stmt != null)
                stmt.close();
        }catch (SQLException se) {
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
        }
    }

}
