package W9_ex5;

import W9_lecture.reader.FileReader;

import java.util.List;

public class FilmReader {

    public List<String> getFilms() {

        FileReader reader = new FileReader();
        List<String> films = reader.asList("W9_lecture/source/films.csv");

        return films;
    }

}
