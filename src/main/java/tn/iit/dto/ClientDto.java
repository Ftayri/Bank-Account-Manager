package tn.iit.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClientDto {
    private String cin;
    private String fullName;
    private String address;
    private int numberOfAccounts;
    private float totalBalance;
}
