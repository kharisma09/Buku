package uas;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private BukuControllers service = new BukuControllers();
    private ObservableList<Buku> data = FXCollections.observableArrayList();
    private TableView<Buku> table = new TableView<>();
    private TextField tfJudul = new TextField();
    private TextField tfPenulis = new TextField();
    private TextField tfPenerbit = new TextField();
    private TextField tfTahun = new TextField();
    private ComboBox<String> cbStatus = new ComboBox<>();
    private Buku selectedBuku = null;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("📚 CRUD Buku - JavaFX");

        // Title
        Label labelTitle = new Label("📚 Data Buku");
        labelTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        labelTitle.setAlignment(Pos.CENTER);

        // Table Columns
        TableColumn<Buku, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setMinWidth(50);

        TableColumn<Buku, String> colJudul = new TableColumn<>("Judul");
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        colJudul.setMinWidth(150);

        TableColumn<Buku, String> colPenulis = new TableColumn<>("Penulis");
        colPenulis.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        colPenulis.setMinWidth(150);

        TableColumn<Buku, String> colPenerbit = new TableColumn<>("Penerbit");
        colPenerbit.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        colPenerbit.setMinWidth(150);

        TableColumn<Buku, String> colTahun = new TableColumn<>("Tahun");
        colTahun.setCellValueFactory(cellData -> {
            int tahun = cellData.getValue().getTahunTerbit();
            return new SimpleStringProperty(String.valueOf(tahun));
        });
        colTahun.setMinWidth(100);

        TableColumn<Buku, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setMinWidth(120);

        table.getColumns().addAll(colId, colJudul, colPenulis, colPenerbit, colTahun, colStatus);
        table.setItems(data);

        // Form inputs
        tfJudul.setPromptText("Judul");
        tfPenulis.setPromptText("Penulis");
        tfPenerbit.setPromptText("Penerbit");
        tfTahun.setPromptText("Tahun Terbit");

        cbStatus.getItems().addAll("Tersedia", "Dipinjam", "Hilang");
        cbStatus.setPromptText("Status");
        cbStatus.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 4;");

        // Selection listener
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            selectedBuku = newVal;
            if (newVal != null) {
                tfJudul.setText(newVal.getJudul());
                tfPenulis.setText(newVal.getPenulis());
                tfPenerbit.setText(newVal.getPenerbit());
                tfTahun.setText(String.valueOf(newVal.getTahunTerbit()));
                cbStatus.setValue(newVal.getStatus());
            }
        });

        // Buttons
        Button btnTambah = new Button("Tambah");
        Button btnUpdate = new Button("Update");
        Button btnHapus = new Button("Hapus");

        btnTambah.setOnAction(e -> tambahBuku());
        btnUpdate.setOnAction(e -> updateBuku());
        btnHapus.setOnAction(e -> hapusBuku());

        // Layouts
        HBox row1 = new HBox(10, tfJudul, tfPenulis);
        HBox row2 = new HBox(10, tfPenerbit, tfTahun, cbStatus);
        HBox row3 = new HBox(10, btnTambah, btnUpdate, btnHapus);

        row1.setAlignment(Pos.CENTER);
        row2.setAlignment(Pos.CENTER);
        row3.setAlignment(Pos.CENTER);

        VBox formBox = new VBox(10, row1, row2, row3);
        formBox.setPadding(new Insets(10));
        formBox.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #ddd; -fx-border-radius: 5; -fx-padding: 10;");
        formBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(15, labelTitle, formBox, table);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();

        refreshTable();
    }

    private void tambahBuku() {
        try {
            String judul = tfJudul.getText().trim();
            String penulis = tfPenulis.getText().trim();
            String penerbit = tfPenerbit.getText().trim();
            String tahunStr = tfTahun.getText().trim();
            String status = cbStatus.getValue();

            if (judul.isEmpty() || penulis.isEmpty() || penerbit.isEmpty() || tahunStr.isEmpty() || status == null) {
                showError("Semua field wajib diisi dan status harus dipilih.");
                return;
            }

            int tahun = Integer.parseInt(tahunStr);

            service.tambahBuku(judul, penulis, penerbit, tahun, status);
            refreshTable();
            clearForm();
        } catch (NumberFormatException ex) {
            showError("Tahun harus berupa angka.");
        }
    }

    private void updateBuku() {
        if (selectedBuku != null) {
            try {
                String judulBaru = tfJudul.getText().trim();
                String penulisBaru = tfPenulis.getText().trim();
                String penerbitBaru = tfPenerbit.getText().trim();
                String tahunStr = tfTahun.getText().trim();
                String statusBaru = cbStatus.getValue();

                if (judulBaru.isEmpty() || penulisBaru.isEmpty() || penerbitBaru.isEmpty() || tahunStr.isEmpty() || statusBaru == null) {
                    showError("Semua field wajib diisi dan status harus dipilih.");
                    return;
                }

                int tahunBaru = Integer.parseInt(tahunStr);

                service.updateBuku(selectedBuku.getId(), judulBaru, penulisBaru, penerbitBaru, tahunBaru, statusBaru);
                refreshTable();
                clearForm();
            } catch (NumberFormatException ex) {
                showError("Tahun harus berupa angka.");
            }
        }
    }

    private void hapusBuku() {
        if (selectedBuku != null) {
            service.hapusBuku(selectedBuku.getId());
            refreshTable();
            clearForm();
        }
    }

    private void refreshTable() {
        data.setAll(service.getAll());
    }

    private void clearForm() {
        tfJudul.clear();
        tfPenulis.clear();
        tfPenerbit.clear();
        tfTahun.clear();
        cbStatus.setValue(null);
        table.getSelectionModel().clearSelection();
        selectedBuku = null;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
