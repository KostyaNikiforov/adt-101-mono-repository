package tripplanner.model;

public class Rider extends Person {
    private String bikeModel;
    private int experienceYears;

    public Rider(String id, String name, String phone, String bikeModel, int experienceYears) {
        super(id, name, phone);
        this.bikeModel = bikeModel;
        this.experienceYears = experienceYears;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(String bikeModel) {
        this.bikeModel = bikeModel;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    public String getRole() {
        return "Rider";
    }

    @Override
    public String toString() {
        return getName() + " - " + bikeModel;
    }
}
