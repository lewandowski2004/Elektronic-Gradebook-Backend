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

    private String street;

    private int buildingNumber;

    private int apartmentNumber;

    private String city;

    private String zipCode;
}