package finalExam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAuth {
    public static boolean login(String email, String password) {
        String hash = hashPassword(password); // Hash the input password
        String sql = "SELECT * FROM users WHERE Email = ? AND Pswd = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, hash);
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next(); // Returns true if a matching user is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    /*public static boolean signup(String firstName, String lastName, String email, String password, int role) {
        if (emailExists(email)) {
            return false; // Email already exists
        }

        String hashedPassword = hashPassword(password); // Hash the input password
        String sql = "INSERT INTO users (FName, LName, Email, Pswd, Role) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, hashedPassword);
            pstmt.setInt(5, role);
            pstmt.executeUpdate();
            
            return true; // Signup successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        
    }*/
    public static boolean signup(String firstName, String lastName, String email, String password, int role) {
        if (emailExists(email)) {
            return false; // Email already exists
        }

        String hashedPassword = hashPassword(password); // Hash the input password
        Connection conn = null;
        Connection conn2 = null;

        try {
            conn = DBConnection.getConnection();

            // Insert into the users table
            String userSql = "INSERT INTO users (FName, LName, Email, Pswd, Role) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(userSql)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, email);
                pstmt.setString(4, hashedPassword);
                pstmt.setInt(5, role);
                pstmt.executeUpdate();
            }
            
            conn2 = DB2Connection.getConnection();

            // If role is 1 or 2, insert into the faculty table
            if (role == 1 || role == 2) {
                int facultyId = getNextFacultyId(); // Get the next unique faculty ID
                String facultySql = "INSERT INTO faculty (id, fName, lName, password, email) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn2.prepareStatement(facultySql)) {
                    pstmt.setInt(1, facultyId); // Use the unique ID
                    pstmt.setString(2, firstName);
                    pstmt.setString(3, lastName);
                    pstmt.setString(4, hashedPassword);
                    pstmt.setString(5, email);
                    pstmt.executeUpdate();
                }
            }

            conn.commit(); // Commit the transaction
            return true; // Signup successful
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback on error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }
    
    private static int getNextFacultyId() {
        String sql = "SELECT MAX(id) AS maxId FROM faculty";
        int maxId = 0;

        try (Connection conn = DB2Connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                maxId = rs.getInt("maxId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxId + 1; // Return the next available ID
    }

    private static boolean emailExists(String email) {
        String sql = "SELECT * FROM users WHERE Email = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next(); // Email already exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
