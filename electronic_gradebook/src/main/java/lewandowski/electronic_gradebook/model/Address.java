package lewandowski.electronic_gradebook.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class Address {

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String zipCode;
}