package ch.heigvd.gamify.domain.aggregate;

public interface LeaderboardEntry {
    String getCategory();
    String getUserId();
    int getRank();
    int getTotal();
}