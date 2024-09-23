package br.com.fiap.epictaska.task;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Task {
    @Id
    UUID id;

    @NotNull
    String title;

    @Size(min = 10)
    String description;

    @Min(0) @Max(100)
    int status;

    @Min(1)
    int score;
}
