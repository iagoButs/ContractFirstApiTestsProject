package API.models;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUsers {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private UserPersonalIdDocument personalIdDocument;
}
