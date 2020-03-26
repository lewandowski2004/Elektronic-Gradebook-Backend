package lewandowski.electronic_gradebook.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address {

    @NotNull
    @Size(max = 100)
    @Column(name = "address_line_1")
    private String addressLine1;

    @Size(max = 100)
    @Column(name = "address_line_2")
    private String addressLine2;

    @NotNull
    @Size(max = 100)
    @Column(name = "city")
    private String city;

    @NotNull
    @Size(max = 6)
    @Column(name = "zip_code")
    private String zipCode;
}