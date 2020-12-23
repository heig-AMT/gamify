package ch.heigvd.gamify.domain.aggregate;

public interface RankingEntry {
    String getCategory();
    String getUser();
    int getRank();
    int getTotal();
}