package mx.edu.utez.server.kernel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

// Crea getters, setters y toString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> {
    private T data;
    private HttpStatus status;
    private boolean error;
    private String message;

    public CustomResponse(HttpStatus status, boolean error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public CustomResponse(T data, HttpStatus status, boolean error) {
        this.data = data;
        this.status = status;
        this.error = error;
    }
}
