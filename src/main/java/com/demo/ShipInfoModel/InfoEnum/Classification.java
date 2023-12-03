package com.demo.ShipInfoModel.InfoEnum;

import lombok.Getter;

@Getter
public enum Classification {
    BB("bb", new String[]{"Battleship", "Battlecruiser", "Aviation Battleship", "Monitor"}),
    CV("cv", new String[]{"Aircraft Carrier", "Light Aircraft Carrier"}),
    CA("ca", new String[]{"Heavy Cruiser", "Large Cruiser"}),
    CL("cl", new String[]{"Light Cruiser"}),
    DD("dd", new String[]{"Destroyer", "Guided-missile Destroyer"}),
    SS("ss", new String[]{"Submarine", "Submarine Carrier"}),
    Other("other", new String[]{"Munition Ship", "Repair Ship", "Sailing Frigate"});

    private final String classAbbreviation;
    private final String[] types;

    Classification(String classAbbreviation, String[] types) {
        this.classAbbreviation = classAbbreviation;
        this.types = types;
    }

}
