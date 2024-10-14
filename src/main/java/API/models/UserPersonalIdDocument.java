package API.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPersonalIdDocument {
    private String documentId;
    private String countryOfIssue;
    private String validUntil;
}
