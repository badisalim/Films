package W9_ex5;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilmSummarizer {

    private static FilmReader reader = new FilmReader();
    private static List<String> films = reader.getFilms();

    public static void main(String[] args) {

        List<Film> films = topThreeFilmsSortedByRating();
        System.out.println("Top 3 with highest rating sorted by rating: \n" + films);

        List<Film> films1 = topThreeFilmsAndLongerThreeHours();
        System.out.println("Top 3 with highest rating longer than 3 hours, sorted by rating: \n" + films1);

        List<Film> films2 = topFourMoreExpensive();
        System.out.println("Top 4 most expensive, sorted by budget: \n" + films2);

        List<Film> films3 = topFourMoreExpensiveAndShorterThanOne();
        System.out.println("Top 4 most expensive shorter than 90 minutes, sorted by budget: \n" + films3);

        List<Film> films4 = topFourRatingAndBudgetBetween();
        System.out.println("Top 4 most rated over 7 and budget between 50.000 and 500.000, sorted by budget: \n"+films4);
    }

    private static List<Film>topFourRatingAndBudgetBetween(){
        return filterMethod().stream()
//                .sorted(Comparator.comparing(Film::getScore).reversed())
                .filter(e->e.getScore()>7)
                .filter(e->e.getBudget()>50_000&&(e.getBudget()<500_000))
                .sorted(Comparator.comparing(Film::getBudget).reversed())
                .limit(4)
                .collect(Collectors.toList());

    }
    private static List<Film> topFourMoreExpensiveAndShorterThanOne() {
        return filterMethod().stream()
                .filter(e -> e.getRunTime() < 90)
                .sorted(Comparator.comparing(Film::getBudget).reversed())
                .limit(4)
                .collect(Collectors.toList());
    }

    private static List<Film> topFourMoreExpensive() {
        return filterMethod().stream()
                .sorted(Comparator.comparing(Film::getBudget).reversed())
                .limit(4)
                .collect(Collectors.toList());
    }

    private static List<Film> topThreeFilmsAndLongerThreeHours() {
        return filterMethod().stream()
                .filter(e -> e.getRunTime() > 180)
                .sorted(Comparator.comparing(Film::getScore).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    private static List<Film> topThreeFilmsSortedByRating() {
        return filterMethod().stream()
                .sorted(Comparator.comparing(Film::getScore).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    private static List<Film> filterMethod() {
        return films.stream()
                .skip(1)
                .map(e -> e.split(";"))
                .map(e -> new Film(e[8]
                        , Double.valueOf(e[9])
                        , Integer.valueOf(e[10])
                        , Integer.valueOf(e[6])
                        , Long.valueOf(e[0])
                        , Long.valueOf(e[5])))
                .collect(Collectors.toList());
    }

}

