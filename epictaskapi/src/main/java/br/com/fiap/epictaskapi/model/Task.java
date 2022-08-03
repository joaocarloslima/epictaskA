package br.com.fiap.epictaskapi.model;

public record Task (
     String title,
     String description,
     int score,
     int status

) {}
