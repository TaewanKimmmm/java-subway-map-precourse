package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private static final String INFO = "[INFO] ";

    private String name;
    private List<Station> stationsOnLine = new ArrayList<>();

    public void registerStation(Station station) {
        stationsOnLine.add(station);
    }

    public Line(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void printStations() {
        for (Station station : stationsOnLine) {
            System.out.println(INFO + station.getName());
        }
        System.out.println();
    }

    public boolean isSameName(String lineName) {
        return this.name.equals(lineName);
    }

    public boolean isStationRegistered(String stationName) {
        for (Station station : stationsOnLine) {
            if (station.getName().equals(stationName)) {
                return true;
            }
        }
        return false;
    }
}
