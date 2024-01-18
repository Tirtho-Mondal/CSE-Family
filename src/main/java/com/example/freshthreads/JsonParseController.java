// JsonParseController.java
package com.example.freshthreads;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParseController {
    @FXML
    private Label welcomeText;

    @FXML
    private ListView<String> jobListView;

    @FXML
    protected void onHelloButtonClick() {
        fetchData();
    }

    private void fetchData() {
        try {
            URL url = new URL("https://api.myjson.online/v1/records/7d363520-c0a6-428f-b836-7a469a74922a");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            String json = response.toString();
            jsonParse(json);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void jsonParse(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject data = jsonObject.getAsJsonObject("data");
        JsonArray jobs = data.getAsJsonArray("cse_jobs");

        jobListView.getItems().clear(); // Clear existing items

        for (int i = 0; i < jobs.size(); i++) {
            JsonObject job = jobs.get(i).getAsJsonObject();
            String company = job.get("company").getAsString();
            String location = job.get("location").getAsString();
            String jobTitle = job.get("job_title").getAsString();
            String jobDescription = job.get("job_description").getAsString();

//            Text companyText = new Text("Company: " + company);
//            companyText.setFont(Font.font("Arial", FontWeight.BOLD));
            Text companyText = new Text("Company: " + company);
            companyText.setStyle("-fx-font-weight: bold;");

            String listItem = "\nLocation: " + location + "\nJob Title: " + jobTitle + "\nJob Description: " + jobDescription;
            jobListView.getItems().addAll(companyText.getText(), listItem);

            JsonArray requirements = job.getAsJsonArray("requirements");

            for (int j = 0; j < requirements.size(); j++) {
                JsonObject requirement = requirements.get(j).getAsJsonObject();
                String degree = requirement.get("degree").getAsString();

                String requirementItem = "\nDegree: " + degree;

                JsonArray skills = requirement.getAsJsonArray("skills");
                requirementItem += "\nSkills: ";
                for (int k = 0; k < skills.size(); k++) {
                    requirementItem += skills.get(k).getAsString() + " ";
                }

                JsonArray additionalRequirements = requirement.getAsJsonArray("additional_requirements");
                requirementItem += "\nAdditional Requirements: ";
                for (int k = 0; k < additionalRequirements.size(); k++) {
                    requirementItem += additionalRequirements.get(k).getAsString() + " ";
                }

                jobListView.getItems().add(requirementItem);
            }

            jobListView.getItems().add("\n"); // Add a newline for better readability between jobs
        }
    }

//    private void jsonParse(String response) {
//        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
//        JsonObject data = jsonObject.getAsJsonObject("data");
//        JsonArray jobs = data.getAsJsonArray("cse_jobs");
//
//        jobListView.getItems().clear(); // Clear existing items
//
//        for (int i = 0; i < jobs.size(); i++) {
//            JsonObject job = jobs.get(i).getAsJsonObject();
//            String company = job.get("company").getAsString();
//            String location = job.get("location").getAsString();
//            String jobTitle = job.get("job_title").getAsString();
//            String jobDescription = job.get("job_description").getAsString();
//
//            String listItem = "Company: " + company + "\nLocation: " + location + "\nJob Title: " + jobTitle + "\nJob Description: " + jobDescription;
//            jobListView.getItems().add(listItem);
//
//            JsonArray requirements = job.getAsJsonArray("requirements");
//
//            for (int j = 0; j < requirements.size(); j++) {
//                JsonObject requirement = requirements.get(j).getAsJsonObject();
//                String degree = requirement.get("degree").getAsString();
//
//                String requirementItem = "\nDegree: " + degree;
//
//                JsonArray skills = requirement.getAsJsonArray("skills");
//                requirementItem += "\nSkills: ";
//                for (int k = 0; k < skills.size(); k++) {
//                    requirementItem += skills.get(k).getAsString() + " ";
//                }
//
//                JsonArray additionalRequirements = requirement.getAsJsonArray("additional_requirements");
//                requirementItem += "\nAdditional Requirements: ";
//                for (int k = 0; k < additionalRequirements.size(); k++) {
//                    requirementItem += additionalRequirements.get(k).getAsString() + " ";
//                }
//
//                listItem += requirementItem;
//            }
//
//            jobListView.getItems().add(listItem + "\n"); // Add a newline for better readability between jobs
//        }
//    }
}
