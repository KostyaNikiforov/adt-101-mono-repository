package tripplanner.model;

public class TripTitleKey implements Comparable<TripTitleKey> {
    private final Trip trip;

    public TripTitleKey(Trip trip) {
        this.trip = trip;
    }

    public Trip getTrip() {
        return trip;
    }

    @Override
    public int compareTo(TripTitleKey other) {
        return this.trip.getTitle().compareToIgnoreCase(other.trip.getTitle());
    }

    @Override
    public String toString() {
        return trip.getTitle();
    }
}
