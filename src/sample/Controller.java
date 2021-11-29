package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;


public class Controller {
    public static Integer FIOnumber = 0;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final Desktop desktop = Desktop.getDesktop();
    @FXML
    private URL location;

    @FXML
    private TextField FIO;

    @FXML
    private Button add;

    @FXML
    private Button addRow;

    @FXML
    private TextField arrears;

    @FXML
    private Button createDocument;

    @FXML
    private TextField date;

    @FXML
    private TextField number;

    @FXML
    private TextField paid;

    @FXML
    private TableView<Filler> table;
    @FXML
    private TableColumn<Filler, String> FIOcolumn;
    @FXML
    private TableColumn<Filler, Integer> numberColumn;

    @FXML
    private TableColumn<Filler, Double> arrearsColumn;

    @FXML
    private TableColumn<Filler, Double> paidColumn;

    @FXML
    private TableColumn<Filler, String> dateColumn;


    public Controller() {
    }

    @FXML
    void initialize() {

        handleCreateDocument();
        handleAdd();
        handleAddRow();
        FIOcolumn.setCellValueFactory(new PropertyValueFactory<>("FIO"));
        FIOcolumn.setStyle("-fx-alignment: LEFT;");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));

        dateColumn.setComparator((o1, o2) -> {
            try {
                return Long.compare(simpleDateFormat.parse(o1).getTime(), simpleDateFormat.parse(o2).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return -1;
        });
        dateColumn.setStyle("-fx-alignment: CENTER;");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        numberColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        arrearsColumn.setCellValueFactory(new PropertyValueFactory<>("Arrears"));
        arrearsColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        paidColumn.setCellValueFactory(new PropertyValueFactory<>("Paid"));
        paidColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        table.setItems(fillersList);

    }

    private ObservableList<Filler> fillersList = FXCollections.observableArrayList();

    public void handleAdd() {
        add.setOnAction(actionEvent ->
        {
            try {
                fillersList.add(new Filler(FIO.getText(),
                        simpleDateFormat.format(simpleDateFormat.parse(date.getText())),
                        Integer.parseInt(number.getText()),
                        Double.parseDouble(arrears.getText()),
                        Double.parseDouble(paid.getText())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

    }

    public void handleCreateDocument() {
        createDocument.setOnAction(actionEvent -> {
            Node node = (Node) actionEvent.getSource();
            Stage myStage = (Stage) node.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("C:\\Users\\Darya\\Desktop"));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("DOCX", "*.docx"),
                    new FileChooser.ExtensionFilter("DOTX", "*.dotx")
            );
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(myStage);
            File newFile = new File("C:\\User\\Darya\\Desktop\\DS2");
            try {
                Files.copy(file.toPath(), newFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (newFile != null) {
                try {
                    desktop.open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

//        createDocument.setOnAction(actionEvent -> {
//            if (Desktop.isDesktopSupported()) {
//                try {
//                    File myFile = new File("C:\\Users\\Darya\\Desktop\\DSlab1.1.doc");
//                    Desktop.getDesktop().open(myFile);
//                } catch (IOException ex) {
//                    System.out.println("No app for this document format");
//                }
//            }
//        });
    }

    public void handleAddRow() {
        addRow.setOnAction(actionEvent -> fillersList.add(createRandomFiller()));
    }


    public Filler createRandomFiller() {
        String FIO = "Курилович Дарья Олеговна" + FIOnumber;
        FIOnumber++;
        Integer number = (int) (Math.random() * 100);
        Double arrears = Math.random() * 100;
        Double paid = Math.random() * 100;
        return new Filler(FIO, simpleDateFormat.format(new Date(random())), number, arrears, paid);
    }

    public static long random() {
        long leftLimit = 100000000L;
        long rightLimit = 10000000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}

