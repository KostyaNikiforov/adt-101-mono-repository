package applicant_form;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class ApplicantFormApp extends Application {
    private final FormValidator validator = new FormValidator();
    private final FileStorage storage = new FileStorage("applications");

    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final TextField emailField = new TextField();
    private final TextField phoneField = new TextField();
    private final TextField dateOfBirthField = new TextField();
    private final TextField cityField = new TextField();
    private final TextField countryField = new TextField();
    private final TextField previousSchoolField = new TextField();
    private final TextField gpaField = new TextField();

    private final ComboBox<String> programBox = new ComboBox<>();
    private final ComboBox<String> savedApplicantsBox = new ComboBox<>();

    private final ToggleGroup genderGroup = new ToggleGroup();
    private final RadioButton maleRadio = new RadioButton("Male");
    private final RadioButton femaleRadio = new RadioButton("Female");
    private final RadioButton otherRadio = new RadioButton("Other");

    private final TextArea motivationArea = new TextArea();
    private final TextArea extracurricularArea = new TextArea();
    private final TextArea reportArea = new TextArea();

    private final CheckBox agreeBox = new CheckBox("I agree to the AUK application terms");

    private final Label firstNameError = createErrorLabel();
    private final Label lastNameError = createErrorLabel();
    private final Label emailError = createErrorLabel();
    private final Label phoneError = createErrorLabel();
    private final Label dateOfBirthError = createErrorLabel();
    private final Label cityError = createErrorLabel();
    private final Label countryError = createErrorLabel();
    private final Label genderError = createErrorLabel();
    private final Label programError = createErrorLabel();
    private final Label previousSchoolError = createErrorLabel();
    private final Label gpaError = createErrorLabel();
    private final Label motivationError = createErrorLabel();
    private final Label extracurricularError = createErrorLabel();
    private final Label agreeError = createErrorLabel();
    private final Label statusLabel = new Label();

    @Override
    public void start(Stage stage) {
        Label title = new Label("AUK Bachelor Program Application");
        title.setFont(new Font(20));

        programBox.getItems().addAll(
                "Software Development Technologies",
                "Business Administration",
                "Economics",
                "Psychology",
                "International Relations"
        );
        programBox.setPromptText("Select program");

        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        otherRadio.setToggleGroup(genderGroup);

        firstNameField.setPromptText("Petro");
        lastNameField.setPromptText("Petrenko");
        emailField.setPromptText("petro@email.com");
        phoneField.setPromptText("+380XXXXXXXXX");
        dateOfBirthField.setPromptText("DD.MM.YYYY");
        cityField.setPromptText("Kyiv");
        countryField.setPromptText("Ukraine");
        previousSchoolField.setPromptText("School name");
        gpaField.setPromptText("0-12");
        motivationArea.setPromptText("Why do you want to study at AUK?");
        extracurricularArea.setPromptText("Clubs, olympiads, volunteering...");
        motivationArea.setPrefRowCount(4);
        extracurricularArea.setPrefRowCount(3);
        reportArea.setPrefRowCount(12);
        reportArea.setEditable(false);
        reportArea.setWrapText(true);
        motivationArea.setWrapText(true);
        extracurricularArea.setWrapText(true);

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(6);
        form.setPadding(new Insets(10));

        int row = 0;
        addField(form, row++, "First name:", firstNameField, firstNameError);
        addField(form, row++, "Last name:", lastNameField, lastNameError);
        addField(form, row++, "Email:", emailField, emailError);
        addField(form, row++, "Phone:", phoneField, phoneError);
        addField(form, row++, "Date of birth:", dateOfBirthField, dateOfBirthError);
        addField(form, row++, "City:", cityField, cityError);
        addField(form, row++, "Country:", countryField, countryError);

        HBox genderBox = new HBox(10, maleRadio, femaleRadio, otherRadio);
        form.add(new Label("Gender:"), 0, row);
        form.add(genderBox, 1, row);
        form.add(genderError, 2, row);
        row++;

        form.add(new Label("Program:"), 0, row);
        form.add(programBox, 1, row);
        form.add(programError, 2, row);
        row++;

        addField(form, row++, "Previous school:", previousSchoolField, previousSchoolError);
        addField(form, row++, "GPA:", gpaField, gpaError);

        form.add(new Label("Motivation:"), 0, row);
        form.add(motivationArea, 1, row);
        form.add(motivationError, 2, row);
        row++;

        form.add(new Label("Activities:"), 0, row);
        form.add(extracurricularArea, 1, row);
        form.add(extracurricularError, 2, row);
        row++;

        form.add(agreeBox, 1, row);
        form.add(agreeError, 2, row);

        Button createReportButton = new Button("Create Report");
        createReportButton.setOnAction(event -> createReport());

        Button saveButton = new Button("Save to File");
        saveButton.setOnAction(event -> saveForm());

        Button loadButton = new Button("Load Selected");
        loadButton.setOnAction(event -> loadForm());

        Button clearButton = new Button("Clear Form");
        clearButton.setOnAction(event -> clearForm());

        Button refreshButton = new Button("Refresh List");
        refreshButton.setOnAction(event -> refreshSavedList());

        savedApplicantsBox.setPromptText("Saved applicants");
        savedApplicantsBox.setPrefWidth(220);
        refreshSavedList();

        HBox buttons = new HBox(10, createReportButton, saveButton, clearButton);
        buttons.setAlignment(Pos.CENTER_LEFT);

        HBox loadRow = new HBox(10, new Label("Load applicant:"), savedApplicantsBox, loadButton, refreshButton);
        loadRow.setAlignment(Pos.CENTER_LEFT);

        Label reportTitle = new Label("Application Report");
        reportTitle.setFont(new Font(16));

        VBox root = new VBox(12,
                title,
                form,
                buttons,
                loadRow,
                statusLabel,
                reportTitle,
                reportArea
        );
        root.setPadding(new Insets(15));

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        stage.setTitle("AUK Applicant Questionnaire");
        stage.setScene(new Scene(scrollPane, 780, 700));
        stage.show();
    }

    private void addField(GridPane form, int row, String label, TextField field, Label error) {
        form.add(new Label(label), 0, row);
        form.add(field, 1, row);
        form.add(error, 2, row);
        field.setPrefWidth(280);
    }

    private Label createErrorLabel() {
        Label label = new Label();
        label.setTextFill(Color.RED);
        return label;
    }

    private ApplicantData collectData() {
        ApplicantData data = new ApplicantData();
        data.setFirstName(firstNameField.getText());
        data.setLastName(lastNameField.getText());
        data.setEmail(emailField.getText());
        data.setPhone(phoneField.getText());
        data.setDateOfBirth(dateOfBirthField.getText());
        data.setCity(cityField.getText());
        data.setCountry(countryField.getText());
        data.setPreviousSchool(previousSchoolField.getText());
        data.setGpa(gpaField.getText());
        data.setProgram(programBox.getValue());
        data.setMotivation(motivationArea.getText());
        data.setExtracurricular(extracurricularArea.getText());
        data.setAgreeTerms(agreeBox.isSelected());

        if (genderGroup.getSelectedToggle() != null) {
            RadioButton selected = (RadioButton) genderGroup.getSelectedToggle();
            data.setGender(selected.getText());
        } else {
            data.setGender("");
        }
        return data;
    }

    private void clearErrors() {
        firstNameError.setText("");
        lastNameError.setText("");
        emailError.setText("");
        phoneError.setText("");
        dateOfBirthError.setText("");
        cityError.setText("");
        countryError.setText("");
        genderError.setText("");
        programError.setText("");
        previousSchoolError.setText("");
        gpaError.setText("");
        motivationError.setText("");
        extracurricularError.setText("");
        agreeError.setText("");
    }

    private void showErrors(Map<String, String> errors) {
        clearErrors();
        if (errors.containsKey("firstName")) {
            firstNameError.setText(errors.get("firstName"));
        }
        if (errors.containsKey("lastName")) {
            lastNameError.setText(errors.get("lastName"));
        }
        if (errors.containsKey("email")) {
            emailError.setText(errors.get("email"));
        }
        if (errors.containsKey("phone")) {
            phoneError.setText(errors.get("phone"));
        }
        if (errors.containsKey("dateOfBirth")) {
            dateOfBirthError.setText(errors.get("dateOfBirth"));
        }
        if (errors.containsKey("city")) {
            cityError.setText(errors.get("city"));
        }
        if (errors.containsKey("country")) {
            countryError.setText(errors.get("country"));
        }
        if (errors.containsKey("gender")) {
            genderError.setText(errors.get("gender"));
        }
        if (errors.containsKey("program")) {
            programError.setText(errors.get("program"));
        }
        if (errors.containsKey("previousSchool")) {
            previousSchoolError.setText(errors.get("previousSchool"));
        }
        if (errors.containsKey("gpa")) {
            gpaError.setText(errors.get("gpa"));
        }
        if (errors.containsKey("motivation")) {
            motivationError.setText(errors.get("motivation"));
        }
        if (errors.containsKey("extracurricular")) {
            extracurricularError.setText(errors.get("extracurricular"));
        }
        if (errors.containsKey("agreeTerms")) {
            agreeError.setText(errors.get("agreeTerms"));
        }
    }

    private boolean validateForm() {
        ApplicantData data = collectData();
        Map<String, String> errors = validator.validate(data);
        showErrors(errors);
        return errors.isEmpty();
    }

    private void createReport() {
        if (!validateForm()) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Please fix the errors before creating the report.");
            return;
        }

        ApplicantData data = collectData();
        reportArea.setText(data.createReport());
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Report created successfully.");
    }

    private void saveForm() {
        if (!validateForm()) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Please fix the errors before saving.");
            return;
        }

        ApplicantData data = collectData();
        try {
            storage.save(data);
            reportArea.setText(data.createReport());
            refreshSavedList();
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Saved as " + data.getPersonKey().replace(" ", "_") + "_form.txt and related files.");
        } catch (IOException e) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Error while saving: " + e.getMessage());
        }
    }

    private void loadForm() {
        String selected = savedApplicantsBox.getValue();
        if (selected == null || selected.isEmpty()) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Select an applicant from the list.");
            return;
        }

        try {
            ApplicantData data = storage.load(selected);
            fillForm(data);
            reportArea.setText(data.createReport());
            clearErrors();
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Loaded application for " + selected + ".");
        } catch (IOException e) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Error while loading: " + e.getMessage());
        }
    }

    private void fillForm(ApplicantData data) {
        firstNameField.setText(data.getFirstName());
        lastNameField.setText(data.getLastName());
        emailField.setText(data.getEmail());
        phoneField.setText(data.getPhone());
        dateOfBirthField.setText(data.getDateOfBirth());
        cityField.setText(data.getCity());
        countryField.setText(data.getCountry());
        previousSchoolField.setText(data.getPreviousSchool());
        gpaField.setText(data.getGpa());
        programBox.setValue(data.getProgram());
        motivationArea.setText(data.getMotivation());
        extracurricularArea.setText(data.getExtracurricular());
        agreeBox.setSelected(data.isAgreeTerms());

        if ("Male".equals(data.getGender())) {
            maleRadio.setSelected(true);
        } else if ("Female".equals(data.getGender())) {
            femaleRadio.setSelected(true);
        } else if ("Other".equals(data.getGender())) {
            otherRadio.setSelected(true);
        } else {
            genderGroup.selectToggle(null);
        }
    }

    private void clearForm() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();
        dateOfBirthField.clear();
        cityField.clear();
        countryField.clear();
        previousSchoolField.clear();
        gpaField.clear();
        programBox.setValue(null);
        motivationArea.clear();
        extracurricularArea.clear();
        reportArea.clear();
        agreeBox.setSelected(false);
        genderGroup.selectToggle(null);
        clearErrors();
        statusLabel.setText("");
    }

    private void refreshSavedList() {
        savedApplicantsBox.getItems().clear();
        savedApplicantsBox.getItems().addAll(storage.listSavedApplicants());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
