package tripplanner.model;

import tripplanner.structures.LinkedList;

import java.util.ArrayList;

public class Trip {
    private String id;
    private String title;
    private String startDate;
    private String endDate;
    private double distanceKm;
    private String description;
    private final ArrayList<String> riderIds = new ArrayList<>();
    private final LinkedList<Checkpoint> checkpoints = new LinkedList<>();

    public Trip(String title) {
        this.title = title;
    }

    public Trip(String id, String title, String startDate, String endDate, double distanceKm, String description) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distanceKm = distanceKm;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getRiderIds() {
        return riderIds;
    }

    public LinkedList<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void addRiderId(String riderId) {
        if (!riderIds.contains(riderId)) {
            riderIds.add(riderId);
        }
    }

    public void removeRiderId(String riderId) {
        riderIds.remove(riderId);
    }

    public void addCheckpoint(Checkpoint checkpoint) {
        checkpoints.add(checkpoint);
    }

    @Override
    public String toString() {
        return title + " (" + startDate + ", " + distanceKm + " km)";
    }
}
