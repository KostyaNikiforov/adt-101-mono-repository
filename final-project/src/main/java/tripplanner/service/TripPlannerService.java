package tripplanner.service;

import tripplanner.algo.BinarySearch;
import tripplanner.algo.MergeSort;
import tripplanner.model.Checkpoint;
import tripplanner.model.Rider;
import tripplanner.model.Trip;
import tripplanner.model.TripDistanceKey;
import tripplanner.model.TripTitleKey;
import tripplanner.storage.FileStorage;
import tripplanner.structures.Stack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TripPlannerService {
    private final List<Rider> riders = new ArrayList<>();
    private final List<Trip> trips = new ArrayList<>();
    private final Stack<Trip> deletedTrips = new Stack<>();
    private final FileStorage storage;

    private TripTitleKey[] tripKeysSortedByTitle;

    public TripPlannerService(String dataFilePath) {
        this.storage = new FileStorage(dataFilePath);
    }

    public void load() throws IOException {
        FileStorage.LoadedData data = storage.load();
        riders.clear();
        trips.clear();
        riders.addAll(data.getRiders());
        trips.addAll(data.getTrips());
        refreshIndexByTitle();
    }

    public void save() throws IOException {
        storage.save(riders, trips);
    }

    public List<Rider> getRiders() {
        return riders;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public Stack<Trip> getDeletedTrips() {
        return deletedTrips;
    }

    public Rider addRider(String name, String phone, String bikeModel, int experienceYears) {
        Rider rider = new Rider(UUID.randomUUID().toString(), name, phone, bikeModel, experienceYears);
        riders.add(rider);
        return rider;
    }

    public void updateRider(Rider rider, String name, String phone, String bikeModel, int experienceYears) {
        rider.setName(name);
        rider.setPhone(phone);
        rider.setBikeModel(bikeModel);
        rider.setExperienceYears(experienceYears);
    }

    public void deleteRider(Rider rider) {
        riders.remove(rider);
        for (Trip trip : trips) {
            trip.removeRiderId(rider.getId());
        }
    }

    public Rider findRiderById(String id) {
        for (Rider rider : riders) {
            if (rider.getId().equals(id)) {
                return rider;
            }
        }
        return null;
    }

    public Trip addTrip(String title, String startDate, String endDate, double distanceKm, String description) {
        Trip trip = new Trip(UUID.randomUUID().toString(), title, startDate, endDate, distanceKm, description);
        trips.add(trip);
        refreshIndexByTitle();
        return trip;
    }

    public void updateTrip(Trip trip, String title, String startDate, String endDate, double distanceKm, String description) {
        trip.setTitle(title);
        trip.setStartDate(startDate);
        trip.setEndDate(endDate);
        trip.setDistanceKm(distanceKm);
        trip.setDescription(description);
        refreshIndexByTitle();
    }

    public void deleteTrip(Trip trip) {
        trips.remove(trip);
        deletedTrips.push(trip);
        refreshIndexByTitle();
    }

    public boolean undoDeleteTrip() {
        if (deletedTrips.isEmpty()) {
            return false;
        }
        trips.add(deletedTrips.pop());
        return true;
    }

    public void assignRider(Trip trip, Rider rider) {
        trip.addRiderId(rider.getId());
    }

    public void unassignRider(Trip trip, Rider rider) {
        trip.removeRiderId(rider.getId());
    }

    public ArrayList<Rider> getRidersForTrip(Trip trip) {
        ArrayList<Rider> result = new ArrayList<>();
        for (String riderId : trip.getRiderIds()) {
            Rider rider = findRiderById(riderId);
            if (rider != null) {
                result.add(rider);
            }
        }
        return result;
    }

    public Checkpoint addCheckpoint(Trip trip, String name, String location, String notes) {
        int order = trip.getCheckpoints().getSize() + 1;
        Checkpoint checkpoint = new Checkpoint(UUID.randomUUID().toString(), trip.getId(), name, location, order, notes);
        trip.addCheckpoint(checkpoint);
        return checkpoint;
    }

    public void updateCheckpoint(Checkpoint checkpoint, String name, String location, String notes) {
        checkpoint.setName(name);
        checkpoint.setLocation(location);
        checkpoint.setNotes(notes);
    }

    public void deleteCheckpoint(Trip trip, Checkpoint checkpoint) {
        trip.getCheckpoints().remove(checkpoint);
        int index = 1;
        for (Checkpoint item : trip.getCheckpoints()) {
            item.setOrderIndex(index++);
        }
    }

    public ArrayList<Trip> sortTripsByDistance() {
        TripDistanceKey[] keys = new TripDistanceKey[trips.size()];
        for (int i = 0; i < trips.size(); i++) {
            keys[i] = new TripDistanceKey(trips.get(i));
        }
        MergeSort.sort(keys);
        ArrayList<Trip> result = new ArrayList<>();
        for (TripDistanceKey key : keys) {
            result.add(key.getTrip());
        }
        return result;
    }

    public Trip searchTripByTitle(String title) {
        int index = BinarySearch.search(tripKeysSortedByTitle, new TripTitleKey(new Trip(title)));
        if (index < 0) {
            return null;
        }

        return tripKeysSortedByTitle[index].getTrip();
    }

    public void seedSampleDataIfEmpty() {
        if (!riders.isEmpty() || !trips.isEmpty()) {
            return;
        }

        Rider anna = addRider("Anna Koval", "+380501112233", "Honda CB500X", 5);
        Rider bohdan = addRider("Bohdan Melnyk", "+380671234567", "Yamaha MT-07", 3);
        Rider olena = addRider("Olena Shevchenko", "+380931112222", "BMW F850GS", 8);

        Trip carpathians = addTrip("Carpathian Loop", "2026-06-10", "2026-06-14", 820,
                "Mountain roads and scenic passes");
        assignRider(carpathians, anna);
        assignRider(carpathians, bohdan);
        addCheckpoint(carpathians, "Start - Lviv", "Lviv", "Meet at fuel station");
        addCheckpoint(carpathians, "Skole", "Skole", "Lunch stop");
        addCheckpoint(carpathians, "Slavske", "Slavske", "Overnight");
        addCheckpoint(carpathians, "Hoverla viewpoint", "Vorokhta", "Photos");

        Trip blackSea = addTrip("Black Sea Ride", "2026-07-01", "2026-07-05", 1100,
                "Coastal trip to Odesa");
        assignRider(blackSea, olena);
        assignRider(blackSea, anna);
        addCheckpoint(blackSea, "Kyiv start", "Kyiv", "Morning departure");
        addCheckpoint(blackSea, "Uman", "Uman", "Fuel and coffee");
        addCheckpoint(blackSea, "Odesa", "Odesa", "Seaside hotel");
    }

    private void refreshIndexByTitle() {
        tripKeysSortedByTitle = new TripTitleKey[trips.size()];
        for (int i = 0; i < trips.size(); i++) {
            tripKeysSortedByTitle[i] = new TripTitleKey(trips.get(i));
        }
        MergeSort.sort(tripKeysSortedByTitle);
    }
}
