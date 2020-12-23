package ch.heigvd.gamify.domain.aggregate;

public interface LeaderboardEntry {
    String getCategory();
    String getUser();
    int getRank();
    int getTotal();
}