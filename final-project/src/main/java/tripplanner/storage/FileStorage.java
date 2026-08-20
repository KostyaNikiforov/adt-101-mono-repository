package tripplanner.storage;

import tripplanner.model.Checkpoint;
import tripplanner.model.Rider;
import tripplanner.model.Trip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private static final String FILE_VERSION = "MTP1";
    private static final String RIDERS_SECTION = "[RIDERS]";
    private static final String TRIPS_SECTION = "[TRIPS]";
    private static final String TRIP_RIDERS_SECTION = "[TRIP_RIDERS]";
    private static final String CHECKPOINTS_SECTION = "[CHECKPOINTS]";
    private static final String DELIMITER = "|";

    private final File file;

    public FileStorage(String path) {
        this.file = new File(path);
    }

    public void save(List<Rider> riders, List<Trip> trips) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(FILE_VERSION);
            writer.newLine();

            writer.write(RIDERS_SECTION);
            writer.newLine();
            for (Rider rider : riders) {
                writer.write(escape(rider.getId()) + DELIMITER
                        + escape(rider.getName()) + DELIMITER
                        + escape(rider.getPhone()) + DELIMITER
                        + escape(rider.getBikeModel()) + DELIMITER
                        + rider.getExperienceYears());
                writer.newLine();
            }

            writer.write(TRIPS_SECTION);
            writer.newLine();
            for (Trip trip : trips) {
                writer.write(escape(trip.getId()) + DELIMITER
                        + escape(trip.getTitle()) + DELIMITER
                        + escape(trip.getStartDate()) + DELIMITER
                        + escape(trip.getEndDate()) + DELIMITER
                        + trip.getDistanceKm() + DELIMITER
                        + escape(trip.getDescription()));
                writer.newLine();
            }

            writer.write(TRIP_RIDERS_SECTION);
            writer.newLine();
            for (Trip trip : trips) {
                for (String riderId : trip.getRiderIds()) {
                    writer.write(escape(trip.getId()) + DELIMITER + escape(riderId));
                    writer.newLine();
                }
            }

            writer.write(CHECKPOINTS_SECTION);
            writer.newLine();
            for (Trip trip : trips) {
                for (Checkpoint checkpoint : trip.getCheckpoints()) {
                    writer.write(escape(checkpoint.getId()) + DELIMITER
                            + escape(checkpoint.getTripId()) + DELIMITER
                            + escape(checkpoint.getName()) + DELIMITER
                            + escape(checkpoint.getLocation()) + DELIMITER
                            + checkpoint.getOrderIndex() + DELIMITER
                            + escape(checkpoint.getNotes()));
                    writer.newLine();
                }
            }
        }
    }

    public LoadedData load() throws IOException {
        ArrayList<Rider> riders = new ArrayList<>();
        ArrayList<Trip> trips = new ArrayList<>();

        if (!file.exists()) {
            return new LoadedData(riders, trips);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line == null || !line.equals("MTP1")) {
                throw new IOException("Unknown file format");
            }

            String section = "";
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                if (line.startsWith("[") && line.endsWith("]")) {
                    section = line;
                    continue;
                }

                String[] parts = split(line);
                switch (section) {
                    case RIDERS_SECTION -> {
                        if (parts.length >= 5) {
                            riders.add(new Rider(
                                    unescape(parts[0]),
                                    unescape(parts[1]),
                                    unescape(parts[2]),
                                    unescape(parts[3]),
                                    Integer.parseInt(parts[4])
                            ));
                        }
                    }
                    case TRIPS_SECTION -> {
                        if (parts.length >= 6) {
                            trips.add(new Trip(
                                    unescape(parts[0]),
                                    unescape(parts[1]),
                                    unescape(parts[2]),
                                    unescape(parts[3]),
                                    Double.parseDouble(parts[4]),
                                    unescape(parts[5])
                            ));
                        }
                    }
                    case TRIP_RIDERS_SECTION -> {
                        if (parts.length >= 2) {
                            Trip trip = findTrip(trips, unescape(parts[0]));
                            if (trip != null) {
                                trip.addRiderId(unescape(parts[1]));
                            }
                        }
                    }
                    case CHECKPOINTS_SECTION -> {
                        if (parts.length >= 6) {
                            Trip trip = findTrip(trips, unescape(parts[1]));
                            if (trip != null) {
                                Checkpoint checkpoint = new Checkpoint(
                                        unescape(parts[0]),
                                        unescape(parts[1]),
                                        unescape(parts[2]),
                                        unescape(parts[3]),
                                        Integer.parseInt(parts[4]),
                                        unescape(parts[5])
                                );
                                trip.addCheckpoint(checkpoint);
                            }
                        }
                    }
                    default -> {
                    }
                }
            }
        }

        return new LoadedData(riders, trips);
    }

    private Trip findTrip(ArrayList<Trip> trips, String id) {
        for (Trip trip : trips) {
            if (trip.getId().equals(id)) {
                return trip;
            }
        }
        return null;
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\").replace(DELIMITER, "\\|").replace("\n", "\\n");
    }

    private String unescape(String value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == '\\' && i + 1 < value.length()) {
                char next = value.charAt(++i);
                if (next == 'n') {
                    sb.append('\n');
                } else {
                    sb.append(next);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private String[] split(String line) {
        ArrayList<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean escaped = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (escaped) {
                current.append('\\').append(c);
                escaped = false;
            } else if (c == '\\') {
                escaped = true;
            } else if (c == '|') {
                parts.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        if (escaped) {
            current.append('\\');
        }
        parts.add(current.toString());
        return parts.toArray(new String[0]);
    }

    public static class LoadedData {
        private final ArrayList<Rider> riders;
        private final ArrayList<Trip> trips;

        public LoadedData(ArrayList<Rider> riders, ArrayList<Trip> trips) {
            this.riders = riders;
            this.trips = trips;
        }

        public ArrayList<Rider> getRiders() {
            return riders;
        }

        public ArrayList<Trip> getTrips() {
            return trips;
        }
    }
}
