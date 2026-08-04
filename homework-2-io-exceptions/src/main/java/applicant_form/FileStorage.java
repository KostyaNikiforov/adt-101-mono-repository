package applicant_form;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private final File folder;

    public FileStorage(String folderPath) {
        folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public void save(ApplicantData data) throws IOException {
        String key = data.getPersonKey().replace(" ", "_");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(folder, key + "_form.txt")))) {
            writer.write("firstName=" + data.getFirstName());
            writer.newLine();
            writer.write("lastName=" + data.getLastName());
            writer.newLine();
            writer.write("email=" + data.getEmail());
            writer.newLine();
            writer.write("phone=" + data.getPhone());
            writer.newLine();
            writer.write("dateOfBirth=" + data.getDateOfBirth());
            writer.newLine();
            writer.write("city=" + data.getCity());
            writer.newLine();
            writer.write("country=" + data.getCountry());
            writer.newLine();
            writer.write("gender=" + data.getGender());
            writer.newLine();
            writer.write("program=" + data.getProgram());
            writer.newLine();
            writer.write("previousSchool=" + data.getPreviousSchool());
            writer.newLine();
            writer.write("gpa=" + data.getGpa());
            writer.newLine();
            writer.write("agreeTerms=" + data.isAgreeTerms());
            writer.newLine();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(folder, key + "_motivation.txt")))) {
            writer.write(data.getMotivation());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(folder, key + "_extracurricular.txt")))) {
            writer.write(data.getExtracurricular());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(folder, key + "_report.txt")))) {
            writer.write(data.createReport());
        }
    }

    public ApplicantData load(String personKey) throws IOException {
        String key = personKey.replace(" ", "_");
        File formFile = new File(folder, key + "_form.txt");
        if (!formFile.exists()) {
            throw new IOException("Form file not found for " + personKey);
        }

        ApplicantData data = new ApplicantData();

        try (BufferedReader reader = new BufferedReader(new FileReader(formFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length != 2) {
                    continue;
                }
                String field = parts[0];
                String value = parts[1];

                if (field.equals("firstName")) {
                    data.setFirstName(value);
                } else if (field.equals("lastName")) {
                    data.setLastName(value);
                } else if (field.equals("email")) {
                    data.setEmail(value);
                } else if (field.equals("phone")) {
                    data.setPhone(value);
                } else if (field.equals("dateOfBirth")) {
                    data.setDateOfBirth(value);
                } else if (field.equals("city")) {
                    data.setCity(value);
                } else if (field.equals("country")) {
                    data.setCountry(value);
                } else if (field.equals("gender")) {
                    data.setGender(value);
                } else if (field.equals("program")) {
                    data.setProgram(value);
                } else if (field.equals("previousSchool")) {
                    data.setPreviousSchool(value);
                } else if (field.equals("gpa")) {
                    data.setGpa(value);
                } else if (field.equals("agreeTerms")) {
                    data.setAgreeTerms(Boolean.parseBoolean(value));
                }
            }
        }

        data.setMotivation(readTextFile(new File(folder, key + "_motivation.txt")));
        data.setExtracurricular(readTextFile(new File(folder, key + "_extracurricular.txt")));

        return data;
    }

    public List<String> listSavedApplicants() {
        List<String> names = new ArrayList<>();
        File[] files = folder.listFiles();
        if (files == null) {
            return names;
        }

        for (File file : files) {
            String name = file.getName();
            if (name.endsWith("_form.txt")) {
                String key = name.substring(0, name.length() - "_form.txt".length());
                names.add(key.replace("_", " "));
            }
        }
        return names;
    }

    private String readTextFile(File file) throws IOException {
        if (!file.exists()) {
            return "";
        }

        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (text.length() > 0) {
                    text.append("\n");
                }
                text.append(line);
            }
        }
        return text.toString();
    }
}
