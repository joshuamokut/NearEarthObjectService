package com.fedor.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Objects;

@SpringBootApplication
public class FedorApplication extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static GridLayout grid;
    private String apiKey = "LHrrkXRblzF3gB9yrePyKJwhx6MqgxGua6HndcVW";
    private String url = "https://api.nasa.gov/neo/rest/v1/feed";

    public FedorApplication() throws HeadlessException {

        super();
        this.setTitle("Asteroids - Near Earth Object Service");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WindowEventHandler windowEventHandler = new WindowEventHandler();
        windowEventHandler.setApp(this);
        this.addWindowListener(windowEventHandler);


        //create a grid with 2 columns and 3 rows
        grid = new GridLayout(9, 2, 2, 2);

        //set layout to be a grid
        this.setLayout(grid);

        JLabel startDateLabel = new JLabel("Start Date");
        JTextField startDateField = new JTextField();
        JButton resetButton = new JButton("RESET");
        JButton computeButton = new JButton("COMPUTE");
        JLabel day1 = new JLabel();
        JLabel day2 = new JLabel();
        JLabel day3 = new JLabel();
        JLabel day4 = new JLabel();
        JLabel day5 = new JLabel();
        JLabel day6 = new JLabel();
        JLabel day7 = new JLabel();

        JLabel day1Count = new JLabel();
        JLabel day2Count = new JLabel();
        JLabel day3Count = new JLabel();
        JLabel day4Count = new JLabel();
        JLabel day5Count = new JLabel();
        JLabel day6Count = new JLabel();
        JLabel day7Count = new JLabel();


        //add components to the frame
        this.getContentPane().add(startDateLabel);
        this.getContentPane().add(startDateField);
        this.getContentPane().add(resetButton);
        this.getContentPane().add(computeButton);
        this.getContentPane().add(day1);
        this.getContentPane().add(day1Count);
        this.getContentPane().add(day2);
        this.getContentPane().add(day2Count);
        this.getContentPane().add(day3);
        this.getContentPane().add(day3Count);
        this.getContentPane().add(day4);
        this.getContentPane().add(day4Count);
        this.getContentPane().add(day5);
        this.getContentPane().add(day5Count);
        this.getContentPane().add(day6);
        this.getContentPane().add(day6Count);
        this.getContentPane().add(day7);
        this.getContentPane().add(day7Count);


        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //TODO validate input;

                String inputDate = startDateField.getText();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
                LocalDate startDate;
                try {
                    startDate = LocalDate.parse(inputDate, dateTimeFormatter);
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                    day1Count.setText("Date format is wrong!");
                    return;
                }

                RestTemplate restTemplate = new RestTemplate();

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                        .queryParam("start_date", inputDate)
                        .queryParam("api_key", apiKey);

                HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());

                ResponseEntity<Asteroids> report = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Asteroids.class);

                if (report.getStatusCodeValue() == 200) {
                    Map<String, Integer> dailyReport = Objects.requireNonNull(report.getBody()).getAsteroidsPerDay();
                    day1.setText(startDate.toString());
                    day1Count.setText(String.valueOf(dailyReport.getOrDefault(startDate.toString(), 0)) + " Near Earth Object(s)");
                    startDate = startDate.plusDays(1);

                    day2.setText(startDate.toString());
                    day2Count.setText(String.valueOf(dailyReport.getOrDefault(startDate.toString(), 0)) + " Near Earth Object(s)");
                    startDate = startDate.plusDays(1);

                    day3.setText(startDate.toString());
                    day3Count.setText(String.valueOf(dailyReport.getOrDefault(startDate.toString(), 0)) + " Near Earth Object(s)");
                    startDate = startDate.plusDays(1);

                    day4.setText(startDate.toString());
                    day4Count.setText(String.valueOf(dailyReport.getOrDefault(startDate.toString(), 0)) + " Near Earth Object(s)");
                    startDate = startDate.plusDays(1);

                    day5.setText(startDate.toString());
                    day5Count.setText(String.valueOf(dailyReport.getOrDefault(startDate.toString(), 0)) + " Near Earth Object(s)");
                    startDate = startDate.plusDays(1);

                    day6.setText(startDate.toString());
                    day6Count.setText(String.valueOf(dailyReport.getOrDefault(startDate.toString(), 0)) + " Near Earth Object(s)");
                    startDate = startDate.plusDays(1);

                    day7.setText(startDate.toString());
                    day7Count.setText(String.valueOf(dailyReport.getOrDefault(startDate.toString(), 0)) + " Near Earth Object(s)");
                } else {
                    day1Count.setText("an error occurred");
                }
            }
        });


        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                day1.setText("");
                day1Count.setText("");

                day2.setText("");
                day2Count.setText("");

                day3.setText("");
                day3Count.setText("");

                day4.setText("");
                day4Count.setText("");

                day5.setText("");
                day5Count.setText("");

                day6.setText("");
                day6Count.setText("");

                day7.setText("");
                day7Count.setText("");

                startDateField.setText("");
            }

        });

        setBounds(0, 0, 600, 600);


    }

    /**
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(FedorApplication.class, args);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                //Initializes and processing splashScreen: the screen that appear when the program is still loading
                Splashing.splashInit();
                Splashing.Pause(1000);

                Splashing.splashText("Creating Application...");
                Splashing.splashProgress(33);
                Splashing.Pause(1000);

                Splashing.splashText("Loading Packages...");
                Splashing.splashProgress(66);
                Splashing.Pause(1000);

                Splashing.splashText("Almost Done...");
                Splashing.splashProgress(99);
                Splashing.Pause(1000);


                FedorApplication app = new FedorApplication();
                app.setVisible(true);
            }
        });
    }

}
