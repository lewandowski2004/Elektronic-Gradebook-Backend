package lewandowski.electronic_gradebook.model.enums;

import lombok.Getter;

@Getter
public enum Voivodeship {

    WOJEWÓDZTWO_DOLNOŚLĄSKIE ("Województwo Dolnośląskie"),
    WOJEWÓDZTWO_KUJAWSKO_POMORSKIE ("Województwo Kujawsko Pomorskie"),
    WOJEWÓDZTWO_LUBELSKIE ("Województwo Lubelskie"),
    WOJEWÓDZTWO_LUBUSKIE(" Województwo Lubuskie"),
    WOJEWÓDZTWO_ŁÓDZKIE ("Województwo Łódzkie"),
    WOJEWÓDZTWO_MAŁOPOLSKIE ("Województwo Małopolskie"),
    WOJEWÓDZTWO_MAZOWIECKIE ("Województwo Mazowieckie"),
    WOJEWÓDZTWO_OPOLSKIE ("Województwo Opolskie"),
    WOJEWÓDZTWO_PODKARPACKIE ("Województwo Podkarpackie"),
    WOJEWÓDZTWO_PODLASKIE ("Województwo Podlaskie"),
    WOJEWÓDZTWO_POMORSKIE ("Województwo Pomorskie"),
    WOJEWÓDZTWO_ŚLĄSKIE ("Województwo Śląskie"),
    WOJEWÓDZTWO_ŚWIĘTOKRZYSKIE ("Województwo Świętokrzyskie"),
    WOJEWÓDZTWO_WARMIŃSKO_MAZURSKIE ("Województwo Warmińsko Mazurskie"),
    WOJEWÓDZTWO_WIELKOPOLSKIE ("Województwo Wielkopolskie"),
    WOJEWÓDZTWO_ZACHODNIOPOMORSKIE ("Województwo Zachodniopomorskie");

    private String name;

    Voivodeship(String name) {
        this.name = name;
    }
}
