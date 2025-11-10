import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VoteTask implements Runnable {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/db_latihan_pbo";
    private static final String USER = "root";
    private static final String PASS = ""; 

    private String userId;
    private String pilihan;

    // Constructor
    public VoteTask(String userId, String pilihan) {
        this.userId = userId;
        this.pilihan = pilihan;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        String sql = "INSERT INTO tabel_voting (user_id, pilihan_kandidat) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, this.userId);
            pstmt.setString(2, this.pilihan);

            pstmt.executeUpdate();

            System.out.println(threadName + " (User: " + this.userId + "): Berhasil vote untuk -> " + this.pilihan);

        } catch (SQLException e) {

            System.err.println(threadName + ": GAGAL vote untuk " + this.userId + ". Error: " + e.getMessage());
        }
    }
}