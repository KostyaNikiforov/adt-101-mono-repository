package tripplanner.model;

public class TripDistanceKey implements Comparable<TripDistanceKey> {
    private final Trip trip;

    public TripDistanceKey(Trip trip) {
        this.trip = trip;
    }

    public Trip getTrip() {
        return trip;
    }

    @Override
    public int compareTo(TripDistanceKey other) {
        return Double.compare(this.trip.getDistanceKm(), other.trip.getDistanceKm());
    }

    @Override
    public String toString() {
        return trip.getTitle() + " (" + trip.getDistanceKm() + " km)";
    }
}
