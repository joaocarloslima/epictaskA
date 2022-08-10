package br.com.fiap.epictaskapi.dto;

public record TaskDto (
     String title,
     String description,
     int score,
     int status

) {}
