package applicant_form;

public class ApplicantData {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dateOfBirth;
    private String city;
    private String country;
    private String gender;
    private String program;
    private String previousSchool;
    private String gpa;
    private String motivation;
    private String extracurricular;
    private boolean agreeTerms;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getPreviousSchool() {
        return previousSchool;
    }

    public void setPreviousSchool(String previousSchool) {
        this.previousSchool = previousSchool;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getExtracurricular() {
        return extracurricular;
    }

    public void setExtracurricular(String extracurricular) {
        this.extracurricular = extracurricular;
    }

    public boolean isAgreeTerms() {
        return agreeTerms;
    }

    public void setAgreeTerms(boolean agreeTerms) {
        this.agreeTerms = agreeTerms;
    }

    public String getPersonKey() {
        return lastName.trim() + "_" + firstName.trim();
    }

    public String createReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== AUK Bachelor Application Report ===\n\n");
        report.append("Personal Information\n");
        report.append("--------------------\n");
        report.append("Full name: ").append(firstName).append(" ").append(lastName).append("\n");
        report.append("Gender: ").append(gender).append("\n");
        report.append("Date of birth: ").append(dateOfBirth).append("\n");
        report.append("Email: ").append(email).append("\n");
        report.append("Phone: ").append(phone).append("\n");
        report.append("City: ").append(city).append("\n");
        report.append("Country: ").append(country).append("\n\n");
        report.append("Academic Information\n");
        report.append("--------------------\n");
        report.append("Chosen program: ").append(program).append("\n");
        report.append("Previous school: ").append(previousSchool).append("\n");
        report.append("GPA: ").append(gpa).append("\n\n");
        report.append("Motivation Letter\n");
        report.append("-----------------\n");
        report.append(motivation).append("\n\n");
        report.append("Extracurricular Activities\n");
        report.append("--------------------------\n");
        report.append(extracurricular).append("\n\n");
        report.append("Terms accepted: ").append(agreeTerms ? "Yes" : "No").append("\n");
        return report.toString();
    }
}
