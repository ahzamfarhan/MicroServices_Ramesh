package net.javaguides.employeeservice.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorDetails {

    private String message;
    private String statusCode;

    private LocalDateTime localDateTime;
}
