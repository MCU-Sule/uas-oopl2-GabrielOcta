package com.gabriel.uas_1972037_gabrieloctamahardika;

import com.gabriel.dao.MemberDao;
import com.gabriel.dao.PointDao;
import com.gabriel.dao.TransactionDao;
import com.gabriel.entity.FeMember;
import com.gabriel.entity.FePoint;
import com.gabriel.entity.FeTransaction;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
public class MainController implements Initializable {

    public TextField txtCitizenId;
    public TextField txtName;
    public TextArea txtAddress;
    public TextField txtPhone;
    public TextField txtEmail;
    public TextField txtUsername;
    public DatePicker txtBirth;
    public Button btnSave;
    public Button btnReset;
    public Button btnUpdate;
    public TableView<FeMember> tableUser;
    public TableColumn<FeMember, String> columnCitizenId;
    public TableColumn<FeMember, String> columnName;
    public TableColumn<FeMember, String> columnPhone;
    public TableColumn<FeMember, String> columnBirthDate;
    public TextField txtNominal;
    public DatePicker txtTransaction;
    public Button btnTransaction;
    public TableView<FeTransaction> tableTransaction;
    public TableColumn<FeTransaction, String> columnTransaction;
    public TableColumn<FeTransaction, String> columnNominal;
    public TableView<FePoint> tablePoint;
    public TableColumn<FePoint, String> columnID;
    public TableColumn<FePoint, String> columnPoint;
    public Label totalPoint;
    public Label totalTransaction;
    public Label totalMember;
    public ComboBox comboLanguage;
    private LoginController loginController;

    private ObservableList<FeMember> members;
    private ObservableList<FePoint>points;
    private ObservableList<FeTransaction>transactions;
    private FeMember selectedUser;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void saveMemberAction(ActionEvent actionEvent) {
        if(txtCitizenId.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() || txtAddress.getText().trim().isEmpty()
        || txtUsername.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPhone.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tolong isi semua field");
            alert.showAndWait();
        }
        else {
            FeMember member = new FeMember();
            member.setCitizenId(txtCitizenId.getText());
            member.setName(txtName.getText());
            member.setAddress(txtAddress.getText());
            member.setEmail(txtEmail.getText());
            member.setUsername(txtUsername.getText());
            member.setPhone(txtPhone.getText());
            member.setBirthdate(Date.valueOf(txtBirth.getValue()));
            MemberDao memberDao = new MemberDao();
            memberDao.addData(member);
            members.clear();
            members.addAll(memberDao.showData());
            tableUser.refresh();
        }
    }

    public void resetAction(ActionEvent actionEvent) {
        txtCitizenId.clear();
        txtCitizenId.setDisable(false);
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtUsername.clear();
        txtPhone.clear();
        txtBirth.setValue(null);
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        tableTransaction.setItems(null);
        tablePoint.setItems(null);
    }

    public void updateAction(ActionEvent actionEvent) {
        if(txtCitizenId.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() || txtAddress.getText().trim().isEmpty()
                || txtUsername.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPhone.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tolong isi semua field");
            alert.showAndWait();
        }
        else {
            selectedUser = tableUser.getSelectionModel().getSelectedItem();
            System.out.println(tableUser);

            selectedUser.setCitizenId(txtCitizenId.getText());
            selectedUser.setName(txtName.getText());
            selectedUser.setAddress(txtAddress.getText());
            selectedUser.setPhone(txtPhone.getText());
            selectedUser.setEmail(txtEmail.getText());
            selectedUser.setUsername(txtUsername.getText());
            selectedUser.setBirthdate(Date.valueOf(txtBirth.getValue()));

            MemberDao memberDao = new MemberDao();
            int result = memberDao.updateData(selectedUser);
            if (result != 0) {
                System.out.println("Update Berhasil");
            }
            members.clear();
            members.addAll(memberDao.showData());
            txtCitizenId.setDisable(false);
            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
        }
    }

    public void saveTransAction(ActionEvent actionEvent) {
        if(txtNominal.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tolong isi semua field");
            alert.showAndWait();
        }
        else {
            FeTransaction transaction = new FeTransaction();
            transaction.setNominal(Long.parseLong(txtNominal.getText()));
            transaction.setTransDate(Date.valueOf(txtTransaction.getValue()));
            TransactionDao transactionDao = new TransactionDao();
            transactionDao.addData(transaction);
            transactions.clear();
            transactions.addAll(transactionDao.showData());
            tableTransaction.refresh();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnUpdate.setDisable(true);
        MemberDao memberDao = new MemberDao();
        members = (ObservableList<FeMember>) memberDao.showData();
        tableUser.setItems(members);
        int index = members.size();
        ObservableList<String> list = FXCollections.observableArrayList("English", "Indonesia");
        comboLanguage.setItems(list);
        columnCitizenId.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getCitizenId()));
        columnName.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getName()));
        columnPhone.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getPhone()));
        columnBirthDate.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getBirthdate())));
        totalMember.setText(String.valueOf(index));
        totalPoint.setText(String.valueOf(0));
        totalTransaction.setText(String.valueOf(0));
    }

    public void tableClick(MouseEvent mouseEvent) {
        selectedUser = tableUser.getSelectionModel().getSelectedItem();
        if (selectedUser != null){
            txtCitizenId.setText(String.valueOf(selectedUser.getCitizenId()));
            txtName.setText(selectedUser.getName());
            txtAddress.setText(selectedUser.getAddress());
            txtPhone.setText(selectedUser.getPhone());
            txtEmail.setText(selectedUser.getEmail());
            txtUsername.setText(selectedUser.getUsername());
            txtBirth.setValue(LocalDate.parse(String.valueOf(selectedUser.getBirthdate())));
            btnUpdate.setDisable(false);
            btnSave.setDisable(true);
            btnReset.setDisable(false);
            txtCitizenId.setDisable(true);
            FeTransaction transaction = new FeTransaction();
            FePoint point = new FePoint();
            TransactionDao transactionDao = new TransactionDao();
            PointDao pointDao = new PointDao();
            transactions = (ObservableList<FeTransaction>) transactionDao.showData();
            points = (ObservableList<FePoint>) pointDao.showData();
            tableTransaction.setItems(transactions);
            tablePoint.setItems(points);
            columnTransaction.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getTransDate())));
            columnNominal.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getNominal())));
            columnID.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
            columnPoint.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getValue())));
        }
    }

    public void languageAction(ActionEvent actionEvent) {
        if(comboLanguage.getSelectionModel().getSelectedItem().equals("English")){
            Locale l = new Locale("EN");
            LoadView(l);

        }else if(comboLanguage.getSelectionModel().getSelectedItem().equals("Indonesia")){
            Locale l = new Locale("IN");
            LoadView(l);

        }
    }
    public void LoadView(Locale locale){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("main-view.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundle", locale));
        try {
            Parent root = fxmlLoader.load();
            Stage s = (Stage) comboLanguage.getScene().getWindow();
            s.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
