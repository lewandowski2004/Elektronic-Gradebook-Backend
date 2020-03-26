package lewandowski.electronic_gradebook.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String zipCode;
}