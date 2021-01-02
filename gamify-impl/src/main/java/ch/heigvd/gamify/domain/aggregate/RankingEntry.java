package ch.heigvd.gamify.domain.aggregate;

public interface RankingEntry {
    String getCategory();
    String getUserId();
    int getRank();
    int getTotal();
}