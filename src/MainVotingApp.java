import java.util.Random;

public class MainVotingApp {

    public static void main(String[] args) {
        System.out.println("=== SIMULASI VOTING DIMULAI ===");
        
        int jumlahVoter = 100; 
        Random random = new Random();

        for (int i = 1; i <= jumlahVoter; i++) {
            
            // Tentukan pilihan voter secara acak
            String pilihanKandidat;
            if (random.nextBoolean()) {
                pilihanKandidat = "Kandidat A";
            } else {
                pilihanKandidat = "Kandidat B";
            }

            String userId = "User-" + i;
            
            // Setiap tugas berisi data vote yang spesifik
            Runnable voteTask = new VoteTask(userId, pilihanKandidat);
            
            // Buat Thread baru untuk setiap voter
            Thread voterThread = new Thread(voteTask, "Thread-Voter-" + i);
            
            voterThread.start();
        }

        System.out.println("=== 100 voter telah dikerahkan... Main thread selesai. ===");
    }
}
