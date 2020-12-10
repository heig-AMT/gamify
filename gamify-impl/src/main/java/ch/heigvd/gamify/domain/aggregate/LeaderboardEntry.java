package ch.heigvd.gamify.domain.aggregate;

public interface LeaderboardEntry {
    String getUser();
    int getTotal();
}