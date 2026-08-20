package tripplanner.model;

public class Checkpoint {
    private String id;
    private String tripId;
    private String name;
    private String location;
    private int orderIndex;
    private String notes;

    public Checkpoint(String id, String tripId, String name, String location, int orderIndex, String notes) {
        this.id = id;
        this.tripId = tripId;
        this.name = name;
        this.location = location;
        this.orderIndex = orderIndex;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return orderIndex + ". " + name + " (" + location + ")";
    }
}
