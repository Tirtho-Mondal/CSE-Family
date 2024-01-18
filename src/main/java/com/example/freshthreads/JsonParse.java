package com.example.freshthreads;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.control.ListView;

public class JsonParse {
    @FXML
    private static Label welcomeText;
    private  static ListView<String>listView;

    @FXML
    protected void onHelloButtonClick() {
        fetchData();
    }

    public static void fetchData(){
        //welcomeText.setText("Welcome to JavaFX Application!");
        //String response = "\"Welcome to JavaFX Application!\"";
        //jsonParse(response);

        try {
            URL url = new URL("https://api.myjson.online/v1/records/7d363520-c0a6-428f-b836-7a469a74922a");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuilder response = new StringBuilder();
            String line;
            System.out.println("ekahne elo");
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            String json = response.toString();
            System.out.println("hi");
            jsonParse(json);

        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println("error khaise");
        }


    }
    public static void jsonParse(String response) {
        // Parse the JSON response using Gson
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        // Get the 'data' object
        JsonObject data = jsonObject.getAsJsonObject("data");

        // Get the 'cse_jobs' array
        JsonArray jobs = data.getAsJsonArray("cse_jobs");

        // Iterate through each job
        for (int i = 0; i < jobs.size(); i++) {
            JsonObject job = jobs.get(i).getAsJsonObject();
            String company = job.get("company").getAsString();
            String location = job.get("location").getAsString();
            String jobTitle = job.get("job_title").getAsString();
            String jobDescription = job.get("job_description").getAsString();

            System.out.println("Company: " + company);
            System.out.println("Location: " + location);
            System.out.println("Job Title: " + jobTitle);
            System.out.println("Job Description: " + jobDescription);

            // Get the 'requirements' array
            JsonArray requirements = job.getAsJsonArray("requirements");

            // Iterate through each requirement
            for (int j = 0; j < requirements.size(); j++) {
                JsonObject requirement = requirements.get(j).getAsJsonObject();
                String degree = requirement.get("degree").getAsString();

                System.out.println("Degree: " + degree);

                // Get the 'skills' array
                JsonArray skills = requirement.getAsJsonArray("skills");

                System.out.print("Skills: ");
                for (int k = 0; k < skills.size(); k++) {
                    System.out.print(skills.get(k).getAsString() + " ");
                }
                System.out.println();

                // Get the 'additional_requirements' array
                JsonArray additionalRequirements = requirement.getAsJsonArray("additional_requirements");

                System.out.print("Additional Requirements: ");
                for (int k = 0; k < additionalRequirements.size(); k++) {
                    System.out.print(additionalRequirements.get(k).getAsString() + " ");
                }
                System.out.println();
            }

            System.out.println(); // Add a newline for better readability between jobs
        }
    }

//    public static void jsonParse(String response){
//
//        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
//        JsonObject data = jsonObject.getAsJsonObject("data");
//        JsonArray jobs = data.getAsJsonArray("cse_jobs");
//        System.out.println("hehe");
//        for(int i=0; i<jobs.size(); i++){
//            JsonObject jsonElement = jobs.get(i).getAsJsonObject();
//            String company = jsonElement.get("company").getAsString();
//            String location = jsonElement.get("location").getAsString();
//            System.out.println(company + ":"+ location);
//            //Company aar location extract korsi. bakita kore nish.
//
//
//
//            //String existingText = welcomeText.getText();
//            //welcomeText.setText(existingText + "Name: " + name + ", Age: "+ age + "\n\n");
//
//        }
//
//    }

    public static void main(String[] args) {
        fetchData();
    }

}