package lewandowski.electronic_gradebook.model;

import lewandowski.electronic_gradebook.model.enums.Country;
import lewandowski.electronic_gradebook.model.enums.RoleName;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class Address {

    private String street;

    private int buildingNumber;

    private int apartmentNumber;

    private String city;

    private String zipCode;

    @Enumerated(EnumType.STRING)
    private Country country;
}