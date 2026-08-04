package applicant_form;

import java.util.HashMap;
import java.util.Map;

public class FormValidator {

    public Map<String, String> validate(ApplicantData data) {
        Map<String, String> errors = new HashMap<>();

        if (data.getFirstName() == null || data.getFirstName().trim().isEmpty()) {
            errors.put("firstName", "First name is required");
        } else if (!data.getFirstName().trim().matches("[A-Za-z]+")) {
            errors.put("firstName", "Only letters allowed");
        }

        if (data.getLastName() == null || data.getLastName().trim().isEmpty()) {
            errors.put("lastName", "Last name is required");
        } else if (!data.getLastName().trim().matches("[A-Za-z]+")) {
            errors.put("lastName", "Only letters allowed");
        }

        if (data.getEmail() == null || data.getEmail().trim().isEmpty()) {
            errors.put("email", "Email is required");
        } else if (!data.getEmail().trim().contains("@") || !data.getEmail().trim().contains(".")) {
            errors.put("email", "Enter a valid email");
        }

        if (data.getPhone() == null || data.getPhone().trim().isEmpty()) {
            errors.put("phone", "Phone is required");
        } else if (!data.getPhone().trim().matches("[+0-9\\- ]{9,15}")) {
            errors.put("phone", "Enter a valid phone number");
        }

        if (data.getDateOfBirth() == null || data.getDateOfBirth().trim().isEmpty()) {
            errors.put("dateOfBirth", "Date of birth is required");
        } else if (!data.getDateOfBirth().trim().matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            errors.put("dateOfBirth", "Use format DD.MM.YYYY");
        }

        if (data.getCity() == null || data.getCity().trim().isEmpty()) {
            errors.put("city", "City is required");
        }

        if (data.getCountry() == null || data.getCountry().trim().isEmpty()) {
            errors.put("country", "Country is required");
        }

        if (data.getGender() == null || data.getGender().isEmpty()) {
            errors.put("gender", "Select gender");
        }

        if (data.getProgram() == null || data.getProgram().isEmpty()) {
            errors.put("program", "Select a program");
        }

        if (data.getPreviousSchool() == null || data.getPreviousSchool().trim().isEmpty()) {
            errors.put("previousSchool", "Previous school is required");
        }

        if (data.getGpa() == null || data.getGpa().trim().isEmpty()) {
            errors.put("gpa", "GPA is required");
        } else {
            try {
                double gpa = Double.parseDouble(data.getGpa().trim());
                if (gpa < 0 || gpa > 12) {
                    errors.put("gpa", "GPA must be between 0 and 12");
                }
            } catch (NumberFormatException e) {
                errors.put("gpa", "GPA must be a number");
            }
        }

        if (data.getMotivation() == null || data.getMotivation().trim().isEmpty()) {
            errors.put("motivation", "Motivation letter is required");
        } else if (data.getMotivation().trim().length() < 30) {
            errors.put("motivation", "Write at least 30 characters");
        }

        if (data.getExtracurricular() == null || data.getExtracurricular().trim().isEmpty()) {
            errors.put("extracurricular", "Please describe your activities");
        }

        if (!data.isAgreeTerms()) {
            errors.put("agreeTerms", "You must accept the terms");
        }

        return errors;
    }
}
