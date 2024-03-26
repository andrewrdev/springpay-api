package andrewrdev.SpringPay.responses;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponse {
    private String message;
    private String status;
    private int statusCode;
    private LocalDateTime timestamp;

    // --------------------------------------------------------------------------------------------

    public CustomResponse(String message) {
        this.message = message;        
    }

    // --------------------------------------------------------------------------------------------

    public CustomResponse(String message, String status, int statusCode) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }
    
    // --------------------------------------------------------------------------------------------
}