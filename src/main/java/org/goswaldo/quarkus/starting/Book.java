package org.goswaldo.quarkus.starting;

public class Book {

    final int id;
    final String title;
    final String author;
    final int yearOfPublication;
    final String genre;

    public Book(int id, String title, String author, int yearOfPublication, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public String getGenre() {
        return genre;
    }
}
