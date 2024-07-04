package ENTITY;

public class movies_info {
    private String movieID;
    private String movieName;
    private String ReleaseYear;
    private int Duration;
    private String Genre;
    private String movieLanguage;
    private String country;
    private String Synopsis;
    private float score;
    public String getMovieID() {
        return movieID;
    }
    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }
    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public String getReleaseYear() {
        return ReleaseYear;
    }
    public void setReleaseYear(String releaseYear) {
        ReleaseYear = releaseYear;
    }
    public int getDuration() {
        return Duration;
    }
    public void setDuration(int duration) {
        Duration = duration;
    }
    public String getGenre() {
        return Genre;
    }
    public void setGenre(String genre) {
        Genre = genre;
    }
    public String getMovieLanguage() {
        return movieLanguage;
    }
    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getSynopsis() {
        return Synopsis;
    }
    public void setSynopsis(String synopsis) {
        Synopsis = synopsis;
    }
    public float getScore() {
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }
}
