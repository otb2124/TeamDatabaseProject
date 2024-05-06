//package finalExam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    public static Integer getFacultyIdByFName(String fName) {
        Integer facultyId = null; 
        String query = "SELECT UserId FROM users WHERE FName = ?"; 

  
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
        	ps.setString(1, fName); 
            ResultSet rs = ps.executeQuery(); 

            if (rs.next()) { 
                facultyId = rs.getInt("UserId"); 
            }
           } catch (SQLException e) {
               e.printStackTrace();
           }

        return facultyId; 
    }
    
    public static Integer getRoleByEmail(String email) {
        Integer role = null; 
        String query = "SELECT Role FROM users WHERE Email = ?"; 

  
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
        	ps.setString(1, email); 
            ResultSet rs = ps.executeQuery(); 

            if (rs.next()) { 
                role = rs.getInt("Role"); 
            }
           } catch (SQLException e) {
               e.printStackTrace();
           }

        return role; 
    }
    
    public static String getfNameByEmail(String email) {
        String fName = null; 
        String query = "SELECT FName FROM users WHERE Email = ?"; 

  
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
        	ps.setString(1, email); 
            ResultSet rs = ps.executeQuery(); 

            if (rs.next()) { 
                fName = rs.getString("FName"); 
            }
           } catch (SQLException e) {
               e.printStackTrace();
           }

        return fName; 
    }
    
    public static String getlNameByEmail(String email) {
        String lName = null; 
        String query = "SELECT LName FROM users WHERE Email = ?"; 

  
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
        	ps.setString(1, email); 
            ResultSet rs = ps.executeQuery(); 

            if (rs.next()) { 
                lName = rs.getString("LName"); 
            }
           } catch (SQLException e) {
               e.printStackTrace();
           }

        return lName; 
    }



    
    public static boolean insertPaper(int id, String title, String abstractText) {
    String query = "INSERT INTO papers (id, title, abstract) VALUES (?, ?, ?)";
    boolean success = false;

    try (Connection conn = DB2Connection.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, id);
        ps.setString(2, title);
        ps.setString(3, abstractText);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            success = true;
        }



    } catch (Exception e) {

    }

    return success;
}

   public static boolean updatePaper(int id, String title, String abstractText) {
    String query = "UPDATE papers SET title = ?, abstract = ? WHERE id = ?";
    boolean success = false;

    try (Connection conn = DB2Connection.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setString(1, title);
        ps.setString(2, abstractText);
        ps.setInt(3, id);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            success = true;
        }

    } catch (Exception e) {
       
    }

    return success;
}

    
    public static List<PaperInfo> searchByKeyword(String keyword) {
        List<PaperInfo> results = new ArrayList<>(); 

        
        String query = "SELECT f.fName, f.lName, p.title, p.abstract " +
                       "FROM papers p " +
                       "JOIN paper_keywords pk ON p.id = pk.id " +
                       "JOIN authorship a ON p.id = a.paperId " +
                       "JOIN faculty f ON a.facultyId = f.id " +
                       "WHERE pk.keyword = ?";

        
        try (Connection conn = DB2Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, keyword); 
            ResultSet rs = ps.executeQuery(); 

            
            while (rs.next()) {
                String author = rs.getString("fName") + " " + rs.getString("lName");
                String title = rs.getString("title");
                String abstractText = rs.getString("abstract");

                
                results.add(new PaperInfo(author, title, abstractText));
            }

        } catch (SQLException e) {
            e.fillInStackTrace(); 
        }

        return results; 
    }
    
    private static int getId(String Name) {
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
