package al.deandmorina.sportscenter.payload.responseDTO;

import lombok.Data;

@Data
public class JWTAuthResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String tokenType;

    public JWTAuthResponseDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = "Bearer";
    }
}
