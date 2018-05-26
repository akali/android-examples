package com.tasbaque.recyclerview;

import java.util.ArrayList;

class Konst {
    public static ArrayList<Film> getFilms() {
        ArrayList<Film> films = new ArrayList<>();
        Film godFather = new Film("Godfather", "1972", "https://cdn.empireonline.com/jpg/70/0/0/640/480/aspectfit/0/0/0/0/0/0/c/features/59395a49f68e659c7aa3a1a8/Godfather%20Part%201.jpg");
        Film sw = new Film("Star Wars: Episode V — The Empire Strikes Back", "1980", "https://cdn.empireonline.com/jpg/70/0/0/640/480/aspectfit/0/0/0/0/0/0/c/features/59395a49f68e659c7aa3a1a8/Star%20Wars%20The%20Empire%20Strikes%20Back.jpg");
        Film batman = new Film("The Dark Knight", "2008", "https://cdn.empireonline.com/jpg/70/0/0/640/480/aspectfit/0/0/0/0/0/0/c/features/59395a49f68e659c7aa3a1a8/The%20Dark%20Knight.jpg");
        Film shawshank = new Film("The Shawshank Redemption", "1994", "https://cdn.empireonline.com/jpg/70/0/0/640/480/aspectfit/0/0/0/0/0/0/c/features/59395a49f68e659c7aa3a1a8/Shawshank%20Redemption.jpg");
        Film pulpFiction = new Film("Pulp Fiction", "1994", "https://cdn.empireonline.com/jpg/70/0/0/640/480/aspectfit/0/0/0/0/0/0/c/features/59395a49f68e659c7aa3a1a8/Pulp%20Ficition.jpg");

        films.add(godFather);
        films.add(sw);
        films.add(batman);
        films.add(shawshank);
        films.add(pulpFiction);
        films.add(godFather);
        films.add(sw);
        films.add(batman);
        films.add(shawshank);
        films.add(pulpFiction);
        films.add(godFather);
        films.add(sw);
        films.add(batman);
        films.add(shawshank);
        films.add(pulpFiction);
        films.add(godFather);
        films.add(sw);
        films.add(batman);
        films.add(shawshank);
        films.add(pulpFiction);
        return films;
    }
}
