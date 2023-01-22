package com.example.projectdatabase12;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;


import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

//import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.BaseDirection;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HelloApplication extends Application {
    Scene memberPage,Main , CoachPage , nutritionistPage , WorkOutPlanPage , FoodPlanPage
            ,paymentPage ,bioPage , ItemsPage ,SalesPage;
    ObservableList<Member> memberList = FXCollections.observableArrayList();
    ObservableList<Coach> CoachList = FXCollections.observableArrayList();
    ObservableList<Nutritionist> nutritionistList = FXCollections.observableArrayList();
    ObservableList<WorkOutPlan> WorkOutPlanList = FXCollections.observableArrayList();
    ObservableList<FoodPlan> FoodPlanList = FXCollections.observableArrayList();
    ObservableList<Payment> paymentList = FXCollections.observableArrayList();
    ObservableList<Bio> bioList = FXCollections.observableArrayList();
    ObservableList<Items> ItemsData = FXCollections.observableArrayList();
    ObservableList<Items> SalesData = FXCollections.observableArrayList();

    DataBaseConnection db = new DataBaseConnection();
    File f = null;
    String path = null;

    File f1 = null;
    String path1 = null;

    static Cell getHeaderTextCell(String textValue) {

        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    static Cell getHeaderTextCellValue(String textValue) {

        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getBillingandShippingCell(String textValue) {

        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getCell10fLeft(String textValue, Boolean isBold) {
        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT);
        return isBold ? myCell.setBold() : myCell;

    }

    private void readMemberData() throws SQLException, ClassNotFoundException {

        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM Members";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                memberList.add(new Member(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4),
                        rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9)));

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public  void ReadPayment() throws IOException, SQLException, ClassNotFoundException {
        Connection con = db.getConnection().connectDB();
        try {
            String sql = "SELECT * FROM payment";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                paymentList.add(new Payment(rs.getInt(1),rs.getDouble(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private void readCoachData() throws SQLException, ClassNotFoundException {

        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM Coaches";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                CoachList.add(new Coach(rs.getString(1), rs.getString(2), rs.getString(3)));

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void readNutritionistData() {
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM Nutritionists";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                nutritionistList.add(new Nutritionist(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readWorkOutPlanData() {
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM WorkOutPlan";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                String nm = rs.getString(6);


                ImageView ii = new ImageView(nm);

                Image img = ii.getImage();


                WorkOutPlanList.add(new WorkOutPlan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),new ImageView(img)));

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void readFoodPlanData() {
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM FoodPlan";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                String nm = rs.getString(6);


                ImageView ii = new ImageView(nm);

                Image img = ii.getImage();


                FoodPlanList.add(new FoodPlan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),new ImageView(img)));

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void readBioData() {
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM Bio";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                bioList.add(new Bio(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4) , rs.getString(5)) );

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void readItems() throws IOException, SQLException, ClassNotFoundException {
        Connection con = db.getConnection().connectDB();
        try {
            String sql = "SELECT * FROM Items";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ItemsData.add(new Items(rs.getString(1),rs.getString(2),
                        rs.getDouble(3),rs.getInt(4),rs.getDouble(5),
                        rs.getDouble(6),rs.getDouble(7)));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            readFoodPlanData();
            readMemberData();
            readCoachData();
            readNutritionistData();
            readFoodPlanData();
            readWorkOutPlanData();
            ReadPayment();
            readBioData();
            readItems();
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image memberBack = new Image("DB.jpg");
        ImageView memberBackImage = new ImageView(memberBack);
        memberBackImage.setFitHeight(700);
        memberBackImage.setFitWidth(900);

        TableView<Member> tableMember = new TableView<>();
        tableMember.setEditable(true);

        TableColumn<Member, String> Id = new TableColumn<>("ID");
        Id.setPrefWidth(60);
        Id.setResizable(false);
        Id.setCellValueFactory(new PropertyValueFactory<Member, String>("GID"));

        TableColumn<Member, String> Name = new TableColumn<>("Name");
        Name.setPrefWidth(95);
        Name.setResizable(false);
        Name.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
        Name.setCellFactory(TextFieldTableCell.forTableColumn());
        Name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, String>>() {

            @Override
            public void handle(CellEditEvent<Member, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setName(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getName() ;


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set Name ='"+name+"'  WHERE gID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });



        TableColumn<Member, Integer> age = new TableColumn<>("Age");
        age.setPrefWidth(60);
        age.setResizable(false);
        age.setCellValueFactory(new PropertyValueFactory<Member, Integer>("age"));
        age.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        age.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, Integer>>() {

            @Override
            public void handle(CellEditEvent<Member, Integer> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setAge(arg0.getNewValue());
                String id = 	arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                int Age =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getAge();


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set age ='"+Age+"'  WHERE gID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }


            }

        });





        TableColumn<Member, Double> wht = new TableColumn<>("Weight");
        wht.setPrefWidth(60);
        wht.setResizable(false);
        wht.setCellValueFactory(new PropertyValueFactory<Member, Double>("weight"));
        wht.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        wht.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, Double>>() {

            @Override
            public void handle(CellEditEvent<Member, Double> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setWeight(arg0.getNewValue());
                String id = 	arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                double Weight =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWeight();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set weight ='"+Weight+"'  WHERE gID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }


            }

        });



        TableColumn<Member, Double> het = new TableColumn<>("Height");
        het.setPrefWidth(60);
        het.setResizable(false);
        het.setCellValueFactory(new PropertyValueFactory<Member, Double>("height"));
        het.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        het.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, Double>>() {

            @Override
            public void handle(CellEditEvent<Member, Double> arg0) {
                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setHeight(arg0.getNewValue());
                String id = 	arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                double Height =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getHeight();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set weight ='"+Height+"'  WHERE gID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }




            }

        });


        TableColumn<Member, String> address = new TableColumn<>("Address");
        address.setPrefWidth(120);
        address.setResizable(false);
        address.setCellValueFactory(new PropertyValueFactory<Member, String>("address"));
        address.setCellFactory(TextFieldTableCell.forTableColumn());
        address.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, String>>() {

            @Override
            public void handle(CellEditEvent<Member, String> arg0) {



                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setAddress(arg0.getNewValue());
                String id = 	arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getAddress() ;


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set addrees ='"+name+"'  WHERE gID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

        });



        TableColumn<Member, String> phone = new TableColumn<>("Phone Number");
        phone.setPrefWidth(110);
        phone.setResizable(false);
        phone.setCellValueFactory(new PropertyValueFactory<Member, String>("phoneNum"));
        phone.setCellFactory(TextFieldTableCell.forTableColumn());
        phone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, String>>() {

            @Override
            public void handle(CellEditEvent<Member, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPhoneNum(arg0.getNewValue());
                String id = 	arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPhoneNum() ;


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set phoneNumber ='"+name+"'  WHERE gID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

        });



        TableColumn<Member, Boolean> locker = new TableColumn<>("Locker");
        locker.setPrefWidth(60);
        locker.setResizable(false);
        locker.setCellValueFactory(new PropertyValueFactory<Member, Boolean>("lockerOption"));

        ObservableList<Boolean> options2 = FXCollections.observableArrayList(
                true,false
        );
        locker.setCellFactory(ChoiceBoxTableCell.forTableColumn(options2));
        locker.setOnEditCommit(event -> {
            Member workOutPlan = event.getRowValue();
            workOutPlan.setLockerOption(event.getNewValue());
            boolean name = workOutPlan.isLockerOption();
            String id = workOutPlan.getGID();
            byte n = 0;

            try {
                if(name == true) {
                    n=1;
                }

                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE Members set lockerOp ='"+n+"'  WHERE gID='"+id+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }





        });





        TableColumn<Member, String> date = new TableColumn<>("End Date");
        date.setPrefWidth(100);
        date.setResizable(false);
        date.setCellValueFactory(new PropertyValueFactory<Member, String>("membershipEndDate"));
        date.setCellFactory(TextFieldTableCell.forTableColumn());

        date.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, String>>() {

            @Override
            public void handle(CellEditEvent<Member, String> arg0) {


                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setMembershipEndDate(arg0.getNewValue());
                String id = 	arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getMembershipEndDate() ;


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set EndDate ='"+name+"'  WHERE gID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

        });


        tableMember.getColumns().addAll(Id, Name, age, wht, het, address, phone, locker, date);
        tableMember.setPrefSize(726, 436);
        tableMember.setLayoutX(87);
        tableMember.setLayoutY(110);

        tableMember.setItems(memberList);

        Label MemberLabel = new Label("Member");
        MemberLabel.setLayoutX(367);
        MemberLabel.setLayoutY(26);
        MemberLabel.setFont(Font.font("Robot", 36));
        MemberLabel.setTextFill(Color.web("#ffffff"));
        MemberLabel.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        Button addMember = new Button("Add" , new ImageView("add.png"));
        addMember.setLayoutX(229);
        addMember.setLayoutY(596);
        addMember.setPrefWidth(70);
        addMember.setPrefHeight(57);
        addMember.setTextFill(Color.BLACK);
        addMember.setContentDisplay(ContentDisplay.TOP);
        addMember.setOnAction(e -> {

            Stage addMemberStage = new Stage();
            addMemberStage.setResizable(false);
            addMemberStage.setAlwaysOnTop(true);
            addMemberStage.setTitle("Add Member Window");

            Label gIDLabel = new Label("Gym ID: ");
            TextField gIDInput = new TextField();

            Label nameLabel = new Label("Name: ");
            TextField nameInput = new TextField();

            Label ageLabel = new Label("Age: ");
            TextField ageInput = new TextField();

            Label weightLabel = new Label("Weight: ");
            TextField weightInput = new TextField();

            Label heightLabel = new Label("Height: ");
            TextField heightInput = new TextField();

            Label addressLabel = new Label("Address: ");
            TextField addressInput = new TextField();

            Label phoneNumLabel = new Label("Phone Number: ");
            TextField phoneNumInput = new TextField();

            Label lockerLabel = new Label("Locker Option: ");
            ChoiceBox<Boolean> lockerInput = new ChoiceBox<Boolean>(FXCollections.observableArrayList(true, false));

            Label membershipEndDateLabel = new Label("Membership end date (DD-MM-YY): ");
            DatePicker membershipEndDateInput = new DatePicker();

            VBox labels = new VBox(10);
            labels.getChildren().addAll(gIDLabel, nameLabel, ageLabel, weightLabel, heightLabel, addressLabel, phoneNumLabel, lockerLabel, membershipEndDateLabel);

            VBox inputs = new VBox(1);
            inputs.getChildren().addAll(gIDInput, nameInput, ageInput, weightInput, heightInput, addressInput, phoneNumInput, lockerInput, membershipEndDateInput);

            HBox boxes = new HBox();
            boxes.getChildren().addAll(labels, inputs);

            Button addMemberButton = new Button("Add");
            addMemberButton.setOnAction(e1 -> {

                try {

                    Connection con = db.getConnection().connectDB();

                    String sql = "Insert Into Members (GID,Name,age,weight,heigth,addrees,phoneNumber,lockerOp,EndDate) values("+
                            "'"+gIDInput.getText()+"'" + ", "+
                            "'"+nameInput.getText()+"'"+ ", "+
                            ageInput.getText()+ ", "+
                            weightInput.getText()+ ", "+
                            heightInput.getText()+ ", "+
                            "'"+addressInput.getText()+"'"+", "+
                            "'"+phoneNumInput.getText()+"'"+", "+
                            lockerInput.getValue()+ ", "+
                            "'"+membershipEndDateInput.getValue().toString()+"'" +")";

                    Statement stmt = con.createStatement();
                    stmt.execute(sql);
                    con.close();

                    memberList.add(new Member(gIDInput.getText(), nameInput.getText(), Integer.parseInt(ageInput.getText()), Double.parseDouble(weightInput.getText()), Double.parseDouble(heightInput.getText()),
                            addressInput.getText(), phoneNumInput.getText(), lockerInput.getValue(), membershipEndDateInput.getValue().toString()));

                } catch (SQLException e2) {
                    //displayErrorMassage("Primary key already exists!");
                    Alert alert = new Alert(Alert.AlertType.NONE, "Primary key already exists!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                } catch (ClassNotFoundException e2) {

                    Alert alert = new Alert(Alert.AlertType.NONE, "Error sending data to database!!!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                }

            });

            VBox everything = new VBox(8);
            everything.setAlignment(Pos.CENTER);
            everything.getChildren().addAll(boxes, addMemberButton);

            Scene scene = new Scene(everything);

            addMemberStage.setScene(scene);

            addMemberStage.show();
        });

        Button deleteMember = new Button("Delete", new ImageView("can.png"));
        deleteMember.setLayoutX(586);
        deleteMember.setLayoutY(596);
        deleteMember.setPrefWidth(70);
        deleteMember.setPrefHeight(57);

        deleteMember.setTextFill(Color.BLACK);
        //deleteMember.setFont(javafx.scene.text.Font.font("Oranienbaum", 18));
        deleteMember.setContentDisplay(ContentDisplay.TOP);
        deleteMember.setOnAction(e->{
            Member selectedItem = tableMember.getSelectionModel().getSelectedItem();
            String id = selectedItem.getGID();

            tableMember.getItems().remove(selectedItem);


            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from Members WHERE gID='"+id+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }



        });

        Pane memberPane = new Pane();
        memberPane.getChildren().addAll(memberBackImage, tableMember, MemberLabel, addMember, deleteMember);
        memberPage = new Scene(memberPane, 900, 700);
        memberPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


//=============================================MainPage======================================================

        StackPane st8 = new StackPane();
        Image mh8 = new Image("DB.jpg");
        ImageView mah8 = new ImageView(mh8);
        mah8.setFitHeight(1050);
        mah8.setFitWidth(1920);

        MenuBar bar1 = new MenuBar();
        bar1.setPrefHeight(25);
        bar1.prefWidth(25);
        Menu mn2 = new Menu("Close");
        MenuItem item2 = new MenuItem("Close");
        item2.setOnAction(e -> {
            primaryStage.close();
        });
        mn2.getItems().add(item2);
        bar1.getMenus().add(mn2);

        GridPane Pane = new GridPane();

        Label lable1 = new Label("User Name :");
        lable1.setPrefHeight(113);
        lable1.setPrefWidth(227);
        lable1.setFont(javafx.scene.text.Font.font("Oranienbaum", 20));
        lable1.setTextFill(Color.BLACK);
        lable1.setStyle("-fx-font-size: 20pt; -fx-text-fill: #ffffff;");

        Label lable2 = new Label("Password :");

        lable2.setPrefHeight(113);
        lable2.setPrefWidth(227);
        lable2.setFont(javafx.scene.text.Font.font("Oranienbaum", 20));
        lable2.setTextFill(Color.BLACK);
        lable2.setStyle("-fx-font-size: 20pt; -fx-text-fill: #ffffff;");

        TextField Text1 = new TextField();
        Text1.setPrefHeight(25);
        Text1.setPrefWidth(195);

        PasswordField pass = new PasswordField();
        pass.setPrefHeight(25);
        pass.setPrefWidth(195);

        Button But = new Button("Login", new ImageView("login.png"));
        But.setPrefHeight(61);
        But.setPrefWidth(106);
        But.setTextFill(Color.BLACK);

        But.setId("button");

        But.setFont(javafx.scene.text.Font.font("Oranienbaum", 18));
        But.setContentDisplay(ContentDisplay.TOP);
        Pane.setMargin(But, new Insets(0, 0, 0, 300));
        But.setOnAction(e -> {

            if (Text1.getText().equals("admin") && pass.getText().equals("admin")) {
                primaryStage.setScene(memberPage);
            }
            //primaryStage.setScene(SaleItemsPage);

        });

        Pane.add(lable1, 0, 2);
        Pane.add(lable2, 0, 3);

        Pane.add(Text1, 1, 2);
        Pane.add(pass, 1, 3);
        Pane.add(But, 0, 4);

        pass.setPromptText("passwords");
        Text1.setPromptText("User Name");

        Pane.setAlignment(Pos.CENTER);
        st8.getChildren().addAll(mah8, Pane);

        Main = new Scene(st8, 900, 700);
        Main.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        //=========================================================================================

        Image mh = new Image("DB.jpg");
        ImageView mah = new ImageView(mh);
        mah.setFitHeight(1050);
        mah.setFitWidth(1920);


        Pane CoachPane = new Pane();

        TableView<Coach> tableCoach = new TableView<>();
        tableCoach.setEditable(true);


        TableColumn<Coach, String> IdCoach = new TableColumn<>("ID");
        IdCoach.setPrefWidth(60);
        IdCoach.setResizable(false);
        IdCoach.setCellValueFactory(new PropertyValueFactory<Coach, String>("CID"));




        TableColumn<Coach, String> NameCoach = new TableColumn<>("Name");
        NameCoach.setPrefWidth(110);
        NameCoach.setResizable(false);
        NameCoach.setCellValueFactory(new PropertyValueFactory<Coach, String>("coachName"));
        NameCoach.setCellFactory(TextFieldTableCell.forTableColumn());
        NameCoach.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Coach, String>>() {

            @Override
            public void handle(CellEditEvent<Coach, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setCoachName(arg0.getNewValue());
                String id = 	arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getCoachName();


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Coaches set coachName ='"+name+"'  WHERE CID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

        });


        TableColumn<Coach, String> CoachPhone = new TableColumn<>("Phone Number");
        CoachPhone.setPrefWidth(95);
        CoachPhone.setResizable(false);
        CoachPhone.setCellValueFactory(new PropertyValueFactory<Coach, String>("PhoneNumber"));
        CoachPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        CoachPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Coach, String>>() {

            @Override
            public void handle(CellEditEvent<Coach, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPhoneNumber(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPhoneNumber() ;


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Coaches set phoneNumber ='"+name+"'  WHERE CID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });






        tableCoach.getColumns().addAll(IdCoach,NameCoach,CoachPhone);
        tableCoach.setPrefSize(726, 436);
        tableCoach.setLayoutX(87);
        tableCoach.setLayoutY(110);

        tableCoach.setItems(CoachList);

        Button showBio = new Button("Show Bio" /*, new ImageView("add.png")*/);
        showBio.setLayoutX(396);
        showBio.setLayoutY(596);
        showBio.setPrefWidth(70);
        showBio.setPrefHeight(57);
        showBio.setTextFill(Color.BLACK);
        showBio.setContentDisplay(ContentDisplay.TOP);
        showBio.setOnAction(e -> {
            try {
                Stage stage = new Stage();

                Connection con = db.getConnection().connectDB();

                String sql1 = "SELECT C.coachName, B.WorkingDays, B.WorkingTimes, B.TrainingType FROM Bio B, Coaches C WHERE B.EID = C.CID AND C.EID = '" + tableCoach.getSelectionModel().getSelectedItem().getCID() +"'";
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt1.executeQuery(sql1);

                String sql2 = "SELECT count(M.GID) FROM Coaches C, Members M WHERE C.GID = M.coachID GROUP BY (C.CID) HAVING C.CID='" + tableCoach.getSelectionModel().getSelectedItem().getCID() +"'"; // coachID in SQL
                Statement stmt2 = con.createStatement();
                ResultSet numberOfStudents = stmt2.executeQuery(sql2);

                Label coachNameLabel = new Label("Name: " + rs.getString(0));
                Label numberofStudetnsLabel = new Label("Number of students: " + numberOfStudents.getString(0));
                Label workingDays = new Label("Working Days: " + rs.getString(1));
                Label workingTimesLabel = new Label("Working times: " + rs.getString(2));
                Label trainingTypeLabel = new Label("Training type: " + rs.getString(3));

                VBox labels = new VBox(5);
                labels.getChildren().addAll(coachNameLabel, numberofStudetnsLabel, workingDays, workingTimesLabel, trainingTypeLabel);

                Scene scene = new Scene(labels);

                stage.setScene(scene);
                stage.show();

                con.close();

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });





        Button addCoach = new Button("Add" , new ImageView("add.png"));
        addCoach.setLayoutX(229);
        addCoach.setLayoutY(596);
        addCoach.setPrefWidth(70);
        addCoach.setPrefHeight(57);
        addCoach.setTextFill(Color.BLACK);
        addCoach.setContentDisplay(ContentDisplay.TOP);
        addCoach.setOnAction(e -> {

            Stage addCoachStage = new Stage();
            addCoachStage.setResizable(false);
            addCoachStage.setAlwaysOnTop(true);
            addCoachStage.setTitle("Add Coach");

            Label gIDLabel = new Label("Coach ID: ");
            TextField gIDInput = new TextField();

            Label nameLabel = new Label("Name: ");
            TextField nameInput = new TextField();

            Label phoneNumLabel = new Label("Phone Number: ");
            TextField phoneNumInput = new TextField();

            VBox labels = new VBox(10);
            labels.getChildren().addAll(gIDLabel, nameLabel,phoneNumLabel);

            VBox inputs = new VBox(1);
            inputs.getChildren().addAll(gIDInput, nameInput,  phoneNumInput);

            HBox boxes = new HBox();
            boxes.getChildren().addAll(labels, inputs);

            Button addCoachButton = new Button("Add");
            addCoachButton.setOnAction(e1 -> {

                try {

                    Connection con = db.getConnection().connectDB();

                    String sql1 = "INSERT INTO Employees (EID) VALUES('"+gIDInput.getText()+"')";

                    Statement stmt1 = con.createStatement();
                    stmt1.execute(sql1);

                    String sql2 = "INSERT INTO Coaches (CID,coachName,phoneNumber) VALUES ('"+gIDInput.getText()+"','"+nameInput.getText()+"','"+phoneNumInput.getText()+"')";

                    Statement stmt2 = con.createStatement();
                    stmt2.execute(sql2);
                    con.close();

                    CoachList.add(new Coach(gIDInput.getText(), nameInput.getText() ,phoneNumInput.getText()));

                } catch (SQLException e2) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Primary key already exists!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                } catch (ClassNotFoundException e2) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Error sending data to database!!!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                }

            });

            VBox everything = new VBox(8);
            everything.setAlignment(Pos.CENTER);
            everything.getChildren().addAll(boxes, addCoachButton);

            Scene scene = new Scene(everything);

            addCoachStage.setScene(scene);

            addCoachStage.show();



        });

        Button deleteCoach = new Button("Delete", new ImageView("can.png"));
        deleteCoach.setLayoutX(586);
        deleteCoach.setLayoutY(596);
        deleteCoach.setPrefWidth(70);
        deleteCoach.setPrefHeight(57);

        deleteCoach.setTextFill(Color.BLACK);
        //deleteMember.setFont(javafx.scene.text.Font.font("Oranienbaum", 18));
        deleteCoach.setContentDisplay(ContentDisplay.TOP);
        deleteCoach.setOnAction(e->{
            Coach selectedItem = tableCoach.getSelectionModel().getSelectedItem();
            String id = selectedItem.getCID();

            tableCoach.getItems().remove(selectedItem);


            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from Coaches WHERE CID='"+id+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }



        });

        Label CoachLabel = new Label("Coach");
        CoachLabel.setLayoutX(367);
        CoachLabel.setLayoutY(26);
        CoachLabel.setFont(Font.font("Robot", 36));
        CoachLabel.setTextFill(Color.web("#ffffff"));
        CoachLabel.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");



        CoachPane.getChildren().addAll(mah,tableCoach,CoachLabel,addCoach,showBio ,deleteCoach);

        CoachPage = new Scene(CoachPane, 900, 700);
        CoachPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


//============================================================
        Image NutrImg = new Image("DB.jpg");
        ImageView NutrView = new ImageView(NutrImg);
        NutrView.setFitHeight(700);
        NutrView.setFitWidth(900);

        TableView<Nutritionist> tableNutritionist = new TableView<>();
        tableNutritionist.setEditable(true);


        TableColumn<Nutritionist, String> IdNutritionist = new TableColumn<>("ID");
        IdNutritionist.setPrefWidth(60);
        IdNutritionist.setResizable(false);
        IdNutritionist.setCellValueFactory(new PropertyValueFactory<Nutritionist, String>("NID"));

        TableColumn<Nutritionist, String> NameNutritionist = new TableColumn<>("Name");
        NameNutritionist.setPrefWidth(95);
        NameNutritionist.setResizable(false);
        NameNutritionist.setCellValueFactory(new PropertyValueFactory<Nutritionist, String>("nutritionistName"));
        NameNutritionist.setCellFactory(TextFieldTableCell.forTableColumn());
        NameNutritionist.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Nutritionist, String>>() {

            @Override
            public void handle(CellEditEvent<Nutritionist, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setNutritionistName(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getNID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getNutritionistName() ;

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Nutritionists set nutritionistName ='"+name+"' WHERE NID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });

        TableColumn<Nutritionist, String> addressNutritionist = new TableColumn<>("Address");
        addressNutritionist.setPrefWidth(120);
        addressNutritionist.setResizable(false);
        addressNutritionist.setCellValueFactory(new PropertyValueFactory<Nutritionist, String>("officeAddress"));
        addressNutritionist.setCellFactory(TextFieldTableCell.forTableColumn());
        addressNutritionist.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Nutritionist, String>>() {

            @Override
            public void handle(CellEditEvent<Nutritionist, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setOfficeAddress(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getNID();
                String address =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getOfficeAddress() ;


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Nutritionists set officeAddress ='" + address + "' WHERE NID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

        });

        TableColumn<Nutritionist, String> phoneNutritionist = new TableColumn<>("Phone Number");
        phoneNutritionist.setPrefWidth(110);
        phoneNutritionist.setResizable(false);
        phoneNutritionist.setCellValueFactory(new PropertyValueFactory<Nutritionist, String>("phoneNumber"));
        phoneNutritionist.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNutritionist.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Nutritionist, String>>() {

            @Override
            public void handle(CellEditEvent<Nutritionist, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPhoneNumber(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getNID();
                String phoneNum =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPhoneNumber() ;


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Nutritionists set phoneNumber ='"+phoneNum+"' WHERE NID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

        });



        tableNutritionist.getColumns().addAll(IdNutritionist, NameNutritionist, addressNutritionist, phoneNutritionist);
        tableNutritionist.setPrefSize(726, 436);
        tableNutritionist.setLayoutX(87);
        tableNutritionist.setLayoutY(110);


        tableNutritionist.setItems(nutritionistList);


        Label NutritionistLabel = new Label("Nutritionist");
        NutritionistLabel.setLayoutX(367);
        NutritionistLabel.setLayoutY(26);
        NutritionistLabel.setFont(Font.font("Robot", 36));
        NutritionistLabel.setTextFill(Color.web("#ffffff"));
        NutritionistLabel.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");


        Button showBioNu = new Button("Show Bio" /*, new ImageView("add.png")*/);
        showBioNu.setLayoutX(398);
        showBioNu.setLayoutY(596);
        showBioNu.setPrefWidth(70);
        showBioNu.setPrefHeight(57);
        showBioNu.setTextFill(Color.BLACK);
        showBioNu.setContentDisplay(ContentDisplay.TOP);
        showBioNu.setOnAction(e->{
            try {
                Stage stage = new Stage();

                Connection con = db.getConnection().connectDB();

                String sql1 = "SELECT N.nutritionistName, B.WorkingDays, B.WorkingTimes, B.TrainingType FROM Bio B, Nutritionists N WHERE B.BID = N.NID AND N.NID = '" + tableNutritionist.getSelectionModel().getSelectedItem().getNID() + "'";
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt1.executeQuery(sql1);

                String sql2 = "SELECT count(M.GID) FROM Nutritionists N, Members M WHERE N.TID = M.nutritionistID GROUP BY (N.NID) HAVING N.NID='" + tableNutritionist.getSelectionModel().getSelectedItem().getNID() +"'"; // nutritionistsID in SQL
                Statement stmt2 = con.createStatement();
                ResultSet numberOfStudents = stmt2.executeQuery(sql2);

                Label nutritionistNameLabel = new Label("Name: " + rs.getString(0));
                Label numberofStudetnsLabel = new Label("Number of students: " + numberOfStudents.getString(0));
                Label workingDays = new Label("Working Days: " + rs.getString(1));
                Label workingTimesLabel = new Label("Working times: " + rs.getString(2));
                Label trainingTypeLabel = new Label("Training type: " + rs.getString(3));

                VBox labels = new VBox(5);
                labels.getChildren().addAll(nutritionistNameLabel, numberofStudetnsLabel, workingDays, workingTimesLabel, trainingTypeLabel);

                Scene scene = new Scene(labels);

                stage.setScene(scene);
                stage.show();

                con.close();

            } catch (Exception e1) {
                e1.printStackTrace();
            }



        });

        Button addNutritionist = new Button("Add" , new ImageView("add.png"));
        addNutritionist.setLayoutX(229);
        addNutritionist.setLayoutY(596);
        addNutritionist.setPrefWidth(70);
        addNutritionist.setPrefHeight(57);
        addNutritionist.setTextFill(Color.BLACK);
        //deleteMember.setFont(javafx.scene.text.Font.font("Oranienbaum", 18));
        addNutritionist.setContentDisplay(ContentDisplay.TOP);
        addNutritionist.setOnAction(e -> {

            Stage addNutritionistStage = new Stage();
            addNutritionistStage.setResizable(false);
            addNutritionistStage.setAlwaysOnTop(true);
            addNutritionistStage.setTitle("Add Nutritionist Window");

            Label gIDLabel = new Label("Gym ID: ");
            TextField gIDInput = new TextField();

            Label nameLabel = new Label("Name: ");
            TextField nameInput = new TextField();

            Label addressLabel = new Label("Office Address: ");
            TextField addressInput = new TextField();

            Label phoneNumLabel = new Label("Phone Number: ");
            TextField phoneNumInput = new TextField();

            VBox labels = new VBox(10);
            labels.getChildren().addAll(gIDLabel, nameLabel, addressLabel, phoneNumLabel);

            VBox inputs = new VBox(1);
            inputs.getChildren().addAll(gIDInput, nameInput, addressInput, phoneNumInput);

            HBox boxes = new HBox();
            boxes.getChildren().addAll(labels, inputs);

            Button addMemberButton = new Button("Add");
            addMemberButton.setOnAction(e1 -> {

                try {

                    Connection con = db.getConnection().connectDB();

                    String sql1 = "INSERT INTO Employees (EID) VALUES('" + gIDInput.getText() + "')";
                    Statement stmt1 = con.createStatement();
                    stmt1.execute(sql1);

                    String sql2 = "INSERT INTO Nutritionists (NID, nutritionistName, phoneNumber, officeAddress) VALUES("+
                            "'"+gIDInput.getText()+"'" + ", "+
                            "'"+nameInput.getText()+"'"+ ", "+
                            "'"+phoneNumInput.getText()+"'"+", "+
                            "'"+addressInput.getText()+"')";
                    Statement stmt2 = con.createStatement();
                    stmt2.execute(sql2);
                    con.close();

                    nutritionistList.add(new Nutritionist(gIDInput.getText(), nameInput.getText(), phoneNumInput.getText(), addressInput.getText()));

                } catch (SQLException e2) {
                    displayErrorMassage("Primary key already exists!");
                } catch (ClassNotFoundException e2) {
                    System.out.println("Error sending data to database!!!");
                }

            });

            VBox everything = new VBox(8);
            everything.setAlignment(Pos.CENTER);
            everything.getChildren().addAll(boxes, addMemberButton);

            Scene scene = new Scene(everything);

            addNutritionistStage.setScene(scene);

            addNutritionistStage.show();
        });

        Button deleteNutritionist = new Button("Delete", new ImageView("can.png"));
        deleteNutritionist.setLayoutX(586);
        deleteNutritionist.setLayoutY(596);
        deleteNutritionist.setPrefWidth(70);
        deleteNutritionist.setPrefHeight(57);
        deleteNutritionist.setTextFill(Color.BLACK);
        //deleteNutritionist.setFont(javafx.scene.text.Font.font("Oranienbaum", 18));
        deleteNutritionist.setContentDisplay(ContentDisplay.TOP);
        deleteNutritionist.setOnAction(e->{
            Nutritionist selectedItem = tableNutritionist.getSelectionModel().getSelectedItem();
            String id = selectedItem.getNID();

            tableNutritionist.getItems().remove(selectedItem);


            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from Nutritionists WHERE NID='"+id+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }



        });
        Pane nutritionistPane = new Pane();
        nutritionistPane.getChildren().addAll(NutrView, tableNutritionist, NutritionistLabel, addNutritionist, deleteNutritionist);
        nutritionistPage = new Scene(nutritionistPane, 900, 700);
        nutritionistPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//==================================WorkOutPlan====================================================================
        Image WorkImg = new Image("DB.jpg");
        ImageView WorkView = new ImageView(WorkImg);
        WorkView.setFitHeight(700);
        WorkView.setFitWidth(900);


        TableView<WorkOutPlan> tableWorkOut = new TableView<>();
        tableWorkOut.setEditable(true);

        TableColumn<WorkOutPlan, String> IdWork = new TableColumn<>("ID");
        IdWork.setPrefWidth(60);
        IdWork.setResizable(false);
        IdWork.setCellValueFactory(new PropertyValueFactory<WorkOutPlan, String>("WID"));

        TableColumn<WorkOutPlan, String> PlantypeWork = new TableColumn<>("Plan Type");
        PlantypeWork.setPrefWidth(95);
        PlantypeWork.setResizable(false);
        PlantypeWork.setCellValueFactory(new PropertyValueFactory<WorkOutPlan, String>("Plantype"));
        ObservableList<String> options = FXCollections.observableArrayList(
                "Cardio" , "Bodybuilding"
        );
        PlantypeWork.setCellFactory(ChoiceBoxTableCell.forTableColumn(options));
        PlantypeWork.setOnEditCommit(event -> {
            WorkOutPlan workOutPlan = event.getRowValue();
            workOutPlan.setPlantype(event.getNewValue());
            String name = workOutPlan.getPlantype();
            String id = workOutPlan.getWID();

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE WorkOutPlan set Plantype ='"+name+"' WHERE WID='"+id+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }



        });




        TableColumn<WorkOutPlan, String> DmWork = new TableColumn<>("DM Percentage ");
        DmWork.setPrefWidth(95);
        DmWork.setResizable(false);
        DmWork.setCellValueFactory(new PropertyValueFactory<WorkOutPlan, String>("DmPercentage"));
        DmWork.setCellFactory(TextFieldTableCell.forTableColumn());
        DmWork.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<WorkOutPlan, String>>() {

            @Override
            public void handle(CellEditEvent<WorkOutPlan, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDmPercentage(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDmPercentage() ;

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE WorkOutPlan set DmPercentage ='"+name+"' WHERE WID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });



        TableColumn<WorkOutPlan, String> DfWork = new TableColumn<>("DF Percentage ");
        DfWork.setPrefWidth(95);
        DfWork.setResizable(false);
        DfWork.setCellValueFactory(new PropertyValueFactory<WorkOutPlan, String>("dfPercentage"));
        DfWork.setCellFactory(TextFieldTableCell.forTableColumn());
        DfWork.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<WorkOutPlan, String>>() {

            @Override
            public void handle(CellEditEvent<WorkOutPlan, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDfPercentage(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDfPercentage() ;

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE WorkOutPlan set dfPercentage ='"+name+"' WHERE WID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });


        TableColumn<WorkOutPlan, String> deadLineWork = new TableColumn<>("Dead Line ");
        deadLineWork.setPrefWidth(120);
        deadLineWork.setResizable(false);
        deadLineWork.setCellValueFactory(new PropertyValueFactory<WorkOutPlan, String>("Deadline"));
        deadLineWork.setCellFactory(TextFieldTableCell.forTableColumn());
        deadLineWork.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<WorkOutPlan, String>>() {

            @Override
            public void handle(CellEditEvent<WorkOutPlan, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDeadline(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDeadline();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE WorkOutPlan set Deadline ='"+name+"' WHERE WID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });




        TableColumn<WorkOutPlan, ImageView> exercisesWork = new TableColumn<>("Exercises");
        exercisesWork.setPrefWidth(280);
        exercisesWork.setResizable(false);
        exercisesWork.setCellValueFactory(new PropertyValueFactory<WorkOutPlan, ImageView>("exercises"));
        exercisesWork.setCellFactory(column -> {
            return new TableCell<WorkOutPlan, ImageView>() {
                private Button button;

                @Override
                protected void updateItem(ImageView item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        // Create the Button if it doesn't exist yet
                        if (button == null) {
                            button = new Button();
                            button.setOnAction(event -> {

                                WorkOutPlan foodPlan = getTableView().getItems().get(getIndex());

                                try {

                                    FileChooser fileChooser = new FileChooser();
                                    f = fileChooser.showOpenDialog(getScene().getWindow());
                                    path = f.getAbsolutePath();
                                    path = path.replace("\\", "\\\\");
                                    String id = foodPlan.getWID();

                                    Connection con = db.getConnection().connectDB();
                                    String sql = "Update WorkOutPlan set  exercises='"+path+"' where WID='"+id+"'";

                                    Statement stmt = con.createStatement();
                                    stmt.executeUpdate(sql);
//		        					Alert alert = new Alert(Alert.AlertType.NONE, "The Prodect has been Update", ButtonType.OK);
//		        					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
//		        					}


                                    con.close();


                                }catch (Exception e) {
                                    // TODO: handle exception
                                }

                                if (f != null) {

                                    foodPlan.setExercises(new ImageView(f.toURI().toString()));

                                    getTableView().getItems().set(getIndex(), foodPlan);
                                }
                            });
                        }
                        // Set the Button text and graphic to the current value of the "meals" property
                        button.setText(item.getId());
                        button.setGraphic(item);

                        setGraphic(button);
                    }
                }
            };
        });






        tableWorkOut.getColumns().addAll(IdWork, PlantypeWork, DmWork, DfWork , deadLineWork ,exercisesWork );
        tableWorkOut.setPrefSize(726, 436);
        tableWorkOut.setLayoutX(87);
        tableWorkOut.setLayoutY(110);

        tableWorkOut.setItems(WorkOutPlanList);
        Label WorkOutLabel = new Label("WorkOut Plan");
        WorkOutLabel.setLayoutX(367);
        WorkOutLabel.setLayoutY(26);
        WorkOutLabel.setFont(Font.font("Robot", 36));
        WorkOutLabel.setTextFill(Color.web("#ffffff"));
        WorkOutLabel.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        Button addWorkOut= new Button("Add");
        addWorkOut.setLayoutX(229);
        addWorkOut.setLayoutY(596);
        addWorkOut.setPrefWidth(70);
        addWorkOut.setPrefHeight(57);
        addWorkOut.setOnAction(e -> {

            Stage addMemberStage = new Stage();
            addMemberStage.setResizable(false);
            addMemberStage.setAlwaysOnTop(true);
            addMemberStage.setTitle("Add WorkOut Plan ");

            Label gIDLabel = new Label("ID: ");
            TextField gIDInput = new TextField();

            Label nameLabel = new Label("Plantype : ");

            ChoiceBox<String> nameInput = new ChoiceBox<String>(FXCollections.observableArrayList("Cardio" , "Bodybuilding"));

            Label ageLabel = new Label("DmPercentage : ");
            TextField ageInput = new TextField();

            Label weightLabel = new Label("dfPercentage : ");
            TextField weightInput = new TextField();

            Label heightLabel = new Label("Deadline : ");

            DatePicker heightInput = new DatePicker();

            Label addressLabel = new Label("exercises : ");
            Button  addressInput = new Button();
            addressInput.setPrefHeight(50);
            addressInput.setPrefWidth(50);
            addressInput.setTextFill(Color.BLACK);
            addressInput.setFont(javafx.scene.text.Font.font("Oranienbaum", 18));
            addressInput.setContentDisplay(ContentDisplay.TOP);
            addressInput.setOnAction(em -> {
                FileChooser filechooser = new FileChooser();
                f = filechooser.showOpenDialog(addMemberStage);

                path = f.getAbsolutePath();
                path = path.replace("\\", "\\\\");

            });


            VBox labels = new VBox(10);
            labels.getChildren().addAll(gIDLabel, nameLabel, ageLabel, weightLabel, heightLabel, addressLabel);

            VBox inputs = new VBox(1);
            inputs.getChildren().addAll(gIDInput, nameInput, ageInput, weightInput, heightInput, addressInput);

            HBox boxes = new HBox();
            boxes.getChildren().addAll(labels, inputs);



            Button addMemberButton = new Button("Add Work");
            addMemberButton.setOnAction(e1 -> {



                try {
                    File f = new File(path);
                    ImageView ii = new ImageView(path);

                    Image img = ii.getImage();

                    Connection con = db.getConnection().connectDB();

                    String sql = "Insert Into WorkOutPlan(WID,Plantype,DmPercentage,dfPercentage,Deadline,exercises) values('"+gIDInput.getText()+"' ,"
                            + "'"+nameInput.getValue().toString()+"','"+"%"+ageInput.getText()+"' ,'"+"%"+weightInput.getText()+"','"+heightInput.getValue()+"','"+path+"')";

                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    WorkOutPlanList.add(new WorkOutPlan(gIDInput.getText(), nameInput.getValue(), "%"+ageInput.getText(),"%"+weightInput.getText(), heightInput.getValue().toString(),
                            new ImageView(img)));
                    con.close();
                    path = null;

                } catch (SQLException e2) {


                    Alert alert = new Alert(Alert.AlertType.NONE, "Primary key already exists!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                } catch (ClassNotFoundException e2) {

                    Alert alert = new Alert(Alert.AlertType.NONE, "Error sending data to database!!!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                }

            });

            VBox everything = new VBox(8);
            everything.setAlignment(Pos.CENTER);
            everything.getChildren().addAll(boxes, addMemberButton);

            Scene scene = new Scene(everything);

            addMemberStage.setScene(scene);

            addMemberStage.show();
        });


        Button deleteWorkOut = new Button("Delete");
        deleteWorkOut.setLayoutX(586);
        deleteWorkOut.setLayoutY(596);
        deleteWorkOut.setPrefWidth(70);
        deleteWorkOut.setPrefHeight(57);
        deleteWorkOut.setOnAction(e->{
            WorkOutPlan selectedItem = tableWorkOut.getSelectionModel().getSelectedItem();
            String id = selectedItem.getWID();

            tableWorkOut.getItems().remove(selectedItem);


            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from WorkOutPlan WHERE WID='"+id+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }



        });
        Pane WorkOutPane = new Pane();
        WorkOutPane.getChildren().addAll(WorkView , tableWorkOut , WorkOutLabel, addWorkOut , deleteWorkOut);
        WorkOutPlanPage = new Scene(WorkOutPane, 900, 700);
        WorkOutPlanPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//==========================================	FoodPlan =====================================
        Image foodImg = new Image("DB.jpg");
        ImageView foodView = new ImageView(foodImg);
        foodView.setFitHeight(700);
        foodView.setFitWidth(900);

        TableView<FoodPlan> tableFood = new TableView<>();
        tableFood.setEditable(true);


        TableColumn<FoodPlan, String> IdFood = new TableColumn<>("ID");
        IdFood.setPrefWidth(60);
        IdFood.setResizable(false);
        IdFood.setCellValueFactory(new PropertyValueFactory<FoodPlan, String>("PID"));

        TableColumn<FoodPlan, String> PlantypeFood = new TableColumn<>("Plan Type");
        PlantypeFood.setPrefWidth(95);
        PlantypeFood.setResizable(false);
        PlantypeFood.setCellValueFactory(new PropertyValueFactory<FoodPlan, String>("Plantype"));
        ObservableList<String> options1 = FXCollections.observableArrayList(
                "Cardio" , "Bodybuilding"
        );
        PlantypeFood.setCellFactory(ChoiceBoxTableCell.forTableColumn(options1));
        PlantypeFood.setOnEditCommit(event -> {
            FoodPlan workOutPlan = event.getRowValue();
            workOutPlan.setPlantype(event.getNewValue());
            String name = workOutPlan.getPlantype();
            String id = workOutPlan.getPID();

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE FoodPlan set Plantype ='"+name+"' WHERE PID='"+id+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }





        });





        TableColumn<FoodPlan, String> DmFood = new TableColumn<>("DM Percentage ");
        DmFood.setPrefWidth(95);
        DmFood.setResizable(false);
        DmFood.setCellValueFactory(new PropertyValueFactory<FoodPlan, String>("DmPercentage"));
        DmFood.setCellFactory(TextFieldTableCell.forTableColumn());
        DmFood.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FoodPlan, String>>() {

            @Override
            public void handle(CellEditEvent<FoodPlan, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDmPercentage(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDmPercentage() ;

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE FoodPlan set DmPercentage ='"+name+"' WHERE PID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });



        TableColumn<FoodPlan, String> DfFood = new TableColumn<>("DF Percentage ");
        DfFood.setPrefWidth(95);
        DfFood.setResizable(false);
        DfFood.setCellValueFactory(new PropertyValueFactory<FoodPlan, String>("dfPercentage"));
        DfFood.setCellFactory(TextFieldTableCell.forTableColumn());
        DfFood.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FoodPlan, String>>() {

            @Override
            public void handle(CellEditEvent<FoodPlan, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDfPercentage(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDfPercentage() ;

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE FoodPlan set dfPercentage ='"+name+"' WHERE PID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });



        TableColumn<FoodPlan, String> deadLineFood= new TableColumn<>("Dead Line ");
        deadLineFood.setPrefWidth(95);
        deadLineFood.setResizable(false);
        deadLineFood.setCellValueFactory(new PropertyValueFactory<FoodPlan, String>("Deadline"));
        deadLineFood.setCellFactory(TextFieldTableCell.forTableColumn());
        deadLineFood.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FoodPlan, String>>() {

            @Override
            public void handle(CellEditEvent<FoodPlan, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDeadline(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDeadline();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE FoodPlan set Deadline ='"+name+"' WHERE PID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });


        TableColumn<FoodPlan, ImageView> exercisesFood = new TableColumn<>("Meals");
        exercisesFood.setPrefWidth(300);
        exercisesFood.setResizable(false);
        exercisesFood.setCellValueFactory(new PropertyValueFactory<FoodPlan, ImageView>("meals"));
        exercisesFood.setCellFactory(column -> {
            return new TableCell<FoodPlan, ImageView>() {
                private Button button;

                @Override
                protected void updateItem(ImageView item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        // Create the Button if it doesn't exist yet
                        if (button == null) {
                            button = new Button();
                            button.setOnAction(event -> {

                                FoodPlan foodPlan = getTableView().getItems().get(getIndex());

                                try {

                                    FileChooser fileChooser = new FileChooser();
                                    f1 = fileChooser.showOpenDialog(getScene().getWindow());
                                    path1 = f1.getAbsolutePath();
                                    path1 = path1.replace("\\", "\\\\");
                                    String id = foodPlan.getPID();

                                    Connection con = db.getConnection().connectDB();
                                    String sql = "Update FoodPlan set  meals='"+path1+"' where PID='"+id+"'";

                                    Statement stmt = con.createStatement();
                                    stmt.executeUpdate(sql);
//		        					Alert alert = new Alert(Alert.AlertType.NONE, "The Prodect has been Update", ButtonType.OK);
//		        					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
//		        					}


                                    con.close();


                                }catch (Exception e) {
                                    // TODO: handle exception
                                }

                                if (f1 != null) {

                                    foodPlan.setMeals(new ImageView(f1.toURI().toString()));

                                    getTableView().getItems().set(getIndex(), foodPlan);
                                }
                            });
                        }
                        // Set the Button text and graphic to the current value of the "meals" property
                        button.setText(item.getId());
                        button.setGraphic(item);

                        setGraphic(button);
                    }
                }
            };
        });

        // Set the callback that will be executed when the user commits an edit
        exercisesFood.setOnEditCommit(event -> {
            // Update the table cell with the new value
            event.getTableView().getItems().set(event.getTablePosition().getRow(), event.getRowValue());
        });



        tableFood.getColumns().addAll(IdFood, PlantypeFood, DmFood, DfFood , deadLineFood ,exercisesFood);
        tableFood.setPrefSize(726, 436);
        tableFood.setLayoutX(87);
        tableFood.setLayoutY(110);
        tableFood.setItems(FoodPlanList);

        Label FoodLabel = new Label("Food Plan");
        FoodLabel.setLayoutX(367);
        FoodLabel.setLayoutY(26);
        FoodLabel.setFont(Font.font("Robot", 36));
        FoodLabel.setTextFill(Color.web("#ffffff"));
        FoodLabel.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");




        Button addFood= new Button("Add");
        addFood.setLayoutX(229);
        addFood.setLayoutY(596);
        addFood.setPrefWidth(70);
        addFood.setPrefHeight(57);
        addFood.setOnAction(e -> {

            Stage addMemberStage = new Stage();
            addMemberStage.setResizable(false);
            addMemberStage.setAlwaysOnTop(true);
            addMemberStage.setTitle("Add WorkOut Plan ");

            Label gIDLabel = new Label("ID: ");
            TextField gIDInput = new TextField();

            Label nameLabel = new Label("Plantype : ");

            ChoiceBox<String> nameInput = new ChoiceBox<String>(FXCollections.observableArrayList("Cardio" , "Bodybuilding"));

            Label ageLabel = new Label("DmPercentage : ");
            TextField ageInput = new TextField();

            Label weightLabel = new Label("dfPercentage : ");
            TextField weightInput = new TextField();

            Label heightLabel = new Label("Deadline : ");

            DatePicker heightInput = new DatePicker();

            Label addressLabel = new Label("meals : ");
            Button  addressInput = new Button();
            addressInput.setPrefHeight(50);
            addressInput.setPrefWidth(50);
            addressInput.setTextFill(Color.BLACK);
            addressInput.setFont(Font.font("Oranienbaum", 18));
            addressInput.setContentDisplay(ContentDisplay.TOP);
            addressInput.setOnAction(em -> {
                FileChooser filechooser = new FileChooser();
                f1 = filechooser.showOpenDialog(addMemberStage);

                path1 = f1.getAbsolutePath();
                path1 = path1.replace("\\", "\\\\");

            });


            VBox labels = new VBox(10);
            labels.getChildren().addAll(gIDLabel, nameLabel, ageLabel, weightLabel, heightLabel, addressLabel);

            VBox inputs = new VBox(1);
            inputs.getChildren().addAll(gIDInput, nameInput, ageInput, weightInput, heightInput, addressInput);

            HBox boxes = new HBox();
            boxes.getChildren().addAll(labels, inputs);



            Button addMemberButton = new Button("Add Food Plan");
            addMemberButton.setOnAction(e1 -> {



                try {
                    File f1 = new File(path1);
                    ImageView ii = new ImageView(path1);

                    Image img = ii.getImage();

                    Connection con = db.getConnection().connectDB();

                    String sql = "Insert Into FoodPlan(PID,Plantype,DmPercentage,dfPercentage,Deadline,meals) values('"+gIDInput.getText()+"' ,"
                            + "'"+nameInput.getValue().toString()+"','"+"%"+ageInput.getText()+"' ,'"+"%"+weightInput.getText()+"','"+heightInput.getValue()+"','"+path1+"')";

                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    FoodPlanList.add(new FoodPlan(gIDInput.getText(), nameInput.getValue(), "%"+ageInput.getText(),"%"+weightInput.getText(), heightInput.getValue().toString(),
                            new ImageView(img)));
                    con.close();
                    path1 = null;

                } catch (SQLException e2) {
                    e2.printStackTrace();

                    Alert alert = new Alert(Alert.AlertType.NONE, "Primary key already exists!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                } catch (ClassNotFoundException e2) {

                    Alert alert = new Alert(Alert.AlertType.NONE, "Error sending data to database!!!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                }

            });

            VBox everything = new VBox(8);
            everything.setAlignment(Pos.CENTER);
            everything.getChildren().addAll(boxes, addMemberButton);

            Scene scene = new Scene(everything);

            addMemberStage.setScene(scene);

            addMemberStage.show();
        });



        Button deleteFood = new Button("Delete");
        deleteFood.setLayoutX(586);
        deleteFood.setLayoutY(596);
        deleteFood.setPrefWidth(70);
        deleteFood.setPrefHeight(57);
        deleteFood.setOnAction(e->{
            FoodPlan selectedItem = tableFood.getSelectionModel().getSelectedItem();
            String id = selectedItem.getPID();

            tableFood.getItems().remove(selectedItem);


            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from FoodPlan WHERE PID='"+id+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }



        });



        Pane FoddPane = new Pane();

        FoddPane.getChildren().addAll(foodView , tableFood ,FoodLabel, addFood , deleteFood);

        FoodPlanPage = new Scene(FoddPane, 900, 700);
        FoodPlanPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//===================================Payment==============================================================================

        Image payBack = new Image("DB.jpg");
        ImageView payBackImage = new ImageView(payBack);
        payBackImage.setFitHeight(700);
        payBackImage.setFitWidth(900);
        TextField searchPayment = new TextField();
        searchPayment.setLayoutX(86);
        searchPayment.setLayoutY(68);
        searchPayment.setPrefWidth(125);
        searchPayment.setPrefHeight(30);

        TableView<Payment> tablePayment = new TableView<>();
        tablePayment.setEditable(true);

        TableColumn<Payment, String> Idpy = new TableColumn<>("ID");
        Idpy.setPrefWidth(60);
        Idpy.setResizable(false);
        Idpy.setCellValueFactory(new PropertyValueFactory<Payment, String>("PTID"));

        TableColumn<Payment, Double> amount = new TableColumn<>("Amount");
        amount.setPrefWidth(140);
        amount.setResizable(false);
        amount.setCellValueFactory(new PropertyValueFactory<Payment, Double>("amount"));
        amount.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        amount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Payment, Double>>() {

            @Override
            public void handle(CellEditEvent<Payment, Double> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setAmount(arg0.getNewValue());
                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPTID();
                Double amount =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getAmount() ;


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Payment set Amount ='"+amount+"' WHERE PTID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (SQLException e2) {
                    displayErrorMassage("Make sure to enter a valid amount!");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });




        TableColumn<Payment, String> datePurchased = new TableColumn<>("Date Purchased");
        datePurchased.setPrefWidth(110);
        datePurchased.setResizable(false);
        datePurchased.setCellValueFactory(new PropertyValueFactory<Payment, String>("date"));

        tablePayment.getColumns().addAll(Idpy, amount, datePurchased);
        tablePayment.setPrefSize(726, 436);
        tablePayment.setLayoutX(87);
        tablePayment.setLayoutY(110);
        FilteredList<Payment> FliterPayment = new FilteredList<>(paymentList, b -> true);
        searchPayment.textProperty().addListener((observable, oldValue, newValue) -> {
            FliterPayment.setPredicate(e -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(e.getPTID()).indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if (String.valueOf(e.getAmount()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (e.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }  else
                    return false;

            });

        });

        SortedList<Payment> sortPayment = new SortedList<>(FliterPayment);
        sortPayment.comparatorProperty().bind(tablePayment.comparatorProperty());



        tablePayment.setItems(sortPayment);

        Label bioLabel1 = new Label("Payments");
        bioLabel1.setLayoutX(367);
        bioLabel1.setLayoutY(26);
        bioLabel1.setFont(Font.font("Robot", 36));
        bioLabel1.setTextFill(Color.web("#ffffff"));
        bioLabel1.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        Button addPayment = new Button("Add");
        addPayment.setLayoutX(229);
        addPayment.setLayoutY(596);
        addPayment.setPrefWidth(70);
        addPayment.setPrefHeight(57);
        addPayment.setOnAction(e -> {

            Stage addPaymentStage = new Stage();
            addPaymentStage.setResizable(false);
            addPaymentStage.setAlwaysOnTop(true);
            addPaymentStage.setTitle("Add Payment Window");

            Label PIDLabel = new Label("Payment ID: ");
            TextField PIDInput = new TextField();

            Label amountLabel = new Label("amount: ");
            TextField amountInput = new TextField();


            Label purchaseDateLabel = new Label("Purchase Date (DD-MM-YY): ");
            DatePicker purchaseDateInput = new DatePicker();

            VBox labels = new VBox(10);
            labels.getChildren().addAll(PIDLabel, amountLabel, purchaseDateLabel);

            VBox inputs = new VBox(1);
            inputs.getChildren().addAll(PIDInput, amountInput, purchaseDateInput);

            HBox boxes = new HBox();
            boxes.getChildren().addAll(labels, inputs);

            Button addPaymentButton = new Button("Add");
            addPaymentButton.setOnAction(e1 -> {

                try {

                    Connection con = db.getConnection().connectDB();

                    String sql = "INSERT INTO payment(amount,dateSale) VALUES ('"+
                            amountInput.getText()+"', '"+purchaseDateInput.getValue().toString()+"')";


                    Statement stmt = con.createStatement();
                    stmt.execute(sql);
                    con.close();

                    paymentList.add(new Payment(Double.parseDouble(amountInput.getText()), purchaseDateInput.getValue().toString()));


                } catch (SQLException e2) {

                    Alert alert = new Alert(Alert.AlertType.NONE, "Primary key already exists!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                    e2.printStackTrace();
                } catch (ClassNotFoundException e2) {
                    System.out.println("Error sending data to database!!!");
                } catch (InputMismatchException e2) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Please enter a valid input!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                }



            });
            VBox everything = new VBox(8);
            everything.setAlignment(Pos.CENTER);
            everything.getChildren().addAll(boxes, addPaymentButton);

            Scene scene = new Scene(everything);

            addPaymentStage.setScene(scene);

            addPaymentStage.show();
        });

        Button deletePayment = new Button("Delete");
        deletePayment.setLayoutX(586);
        deletePayment.setLayoutY(596);
        deletePayment.setPrefWidth(70);
        deletePayment.setPrefHeight(57);
        deletePayment.setOnAction(e->{
            Payment selectedItem = tablePayment.getSelectionModel().getSelectedItem();
            int id = selectedItem.getPTID();

            tablePayment.getItems().remove(selectedItem);


            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from Payment WHERE PTID='"+id+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }



        });

        Pane paymentPane = new Pane();
        paymentPane.getChildren().addAll(payBackImage, tablePayment, searchPayment,bioLabel1 , deletePayment);
        paymentPage = new Scene(paymentPane, 900, 700);
        paymentPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


//===================================================Bio page ================
        Image bioBack = new Image("DB.jpg");
        ImageView bioBackImage = new ImageView(bioBack);
        bioBackImage.setFitHeight(700);
        bioBackImage.setFitWidth(900);
        TableView<Bio> tableBio = new TableView<>();
        tableBio.setEditable(true);

        TableColumn<Bio, String> Idbio = new TableColumn<>("BID");
        Idbio.setPrefWidth(60);
        Idbio.setResizable(false);
        Idbio.setCellValueFactory(new PropertyValueFactory<Bio, String>("BID"));

        TableColumn<Bio, String> workingDays = new TableColumn<>("Working Days");
        workingDays.setPrefWidth(140);
        workingDays.setResizable(false);
        workingDays.setCellValueFactory(new PropertyValueFactory<Bio, String>("workingDays"));
        workingDays.setCellFactory(TextFieldTableCell.forTableColumn());
        workingDays.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Bio, String>>() {

            @Override
            public void handle(CellEditEvent<Bio, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setWorkingDays(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getBID();
                String workingDays =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWorkingDays() ;


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Bio set WorkingDays ='"+workingDays+"'  WHERE BID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });

        TableColumn<Bio, String> workingTimes = new TableColumn<>("Working Times");
        workingTimes.setPrefWidth(110);
        workingTimes.setResizable(false);
        workingTimes.setCellValueFactory(new PropertyValueFactory<Bio, String>("workingTimes"));
        workingTimes.setCellFactory(TextFieldTableCell.forTableColumn());
        workingTimes.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Bio, String>>() {

            @Override
            public void handle(CellEditEvent<Bio, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setWorkingTimes(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getBID();
                String workingTimes =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWorkingTimes();


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Bio set WorkingTimes ='"+workingTimes+"'  WHERE BID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }


            }

        });

        TableColumn<Bio, String> trainingType = new TableColumn<>("Training Type");
        trainingType.setPrefWidth(110);
        trainingType.setResizable(false);
        trainingType.setCellValueFactory(new PropertyValueFactory<Bio, String>("trainingType"));
        ObservableList<String> options4 = FXCollections.observableArrayList(
                "Cardio", "Muscle Growth"
        );
        trainingType.setCellFactory(ChoiceBoxTableCell.forTableColumn(options4));
        trainingType.setOnEditCommit(event -> {
            Bio workOutPlan = event.getRowValue();
            workOutPlan.setTrainingType(event.getNewValue());
            System.out.println("MMMMMMMMMMMMMMMM:"+event.getNewValue());
            String name = workOutPlan.getTrainingType();
            String id = workOutPlan.getBID();

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE Bio set TrainingType ='"+name+"' WHERE BID='"+id+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.printStackTrace();
            }

        });




        tableBio.getColumns().addAll(Idbio, workingDays, workingTimes, trainingType);
        tableBio.setPrefSize(726, 436);
        tableBio.setLayoutX(87);
        tableBio.setLayoutY(110);

        tableBio.setItems(bioList);

        Label biolable = new Label("Bio");
        biolable.setLayoutX(367);
        biolable.setLayoutY(26);
        biolable.setFont(Font.font("Robot", 36));
        biolable.setTextFill(Color.web("#ffffff"));
        biolable.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        Button addBio = new Button("Add");
        addBio.setLayoutX(229);
        addBio.setLayoutY(596);
        addBio.setPrefWidth(70);
        addBio.setPrefHeight(57);
        addBio.setOnAction(e -> {

            Stage addBioStage = new Stage();
            addBioStage.setResizable(false);
            addBioStage.setAlwaysOnTop(true);
            addBioStage.setTitle("Add Member Window");

            Label BIDLabel = new Label("Bio ID: ");
            TextField BIDInput = new TextField();

            Label workingDaysLabel = new Label("Working Days: ");
            TextField workingDaysInput = new TextField();

            Label workingTimesLabel = new Label("Working Times: ");
            TextField workingTimesInput = new TextField();

            Label trainingTypeLabel = new Label("Training Type: ");
            ChoiceBox<String> trainingTypeInput = new ChoiceBox<String>(FXCollections.observableArrayList("Cardio", "Muscle Growth"));

            Label numOfStudentsLabel = new Label("Coach Name : ");
            TextField numOfStudentsInput = new TextField();

            VBox labels = new VBox(10);
            labels.getChildren().addAll(BIDLabel,numOfStudentsLabel ,workingDaysLabel, workingTimesLabel, trainingTypeLabel);

            VBox inputs = new VBox(1);
            inputs.getChildren().addAll(BIDInput, numOfStudentsInput, workingDaysInput, workingTimesInput, trainingTypeInput);

            HBox boxes = new HBox();
            boxes.getChildren().addAll(labels, inputs);

            Button addMemberButton = new Button("Add");
            addMemberButton.setOnAction(e1 -> {

                try {

                    Connection con = db.getConnection().connectDB();

                    String sql = "Insert Into Bio (BID,WorkingDays,WorkingTimes,TrainingType,NumberOfStudents) values("+
                            "'"+BIDInput.getText()+"'" + ", "+
                            "'"+workingDaysInput.getText()+"'"+ ", "+
                            "'"+workingTimesInput.getText()+ "'" + ", "+
                            "'"+trainingTypeInput.getValue().toString()+ "'" + ", "+
                            numOfStudentsInput.getText()+ ")";
                    Statement stmt = con.createStatement();
                    stmt.execute(sql);
                    con.close();

                    bioList.add(new Bio(BIDInput.getText(),numOfStudentsInput.getText() ,workingDaysInput.getText(), workingTimesInput.getText(), trainingTypeInput.getValue().toString() ));


                } catch (SQLException e2) {
                    displayErrorMassage("Primary key already exists!");
                } catch (ClassNotFoundException e2) {
                    System.out.println("Error sending data to database!!!");
                }

            });

            VBox everything = new VBox(8);
            everything.setAlignment(Pos.CENTER);
            everything.getChildren().addAll(boxes, addMemberButton);

            Scene scene = new Scene(everything);

            addBioStage.setScene(scene);

            addBioStage.show();
        });

        Button deleteBio = new Button("Delete");
        deleteBio.setLayoutX(586);
        deleteBio.setLayoutY(596);
        deleteBio.setPrefWidth(70);
        deleteBio.setPrefHeight(57);
        deleteBio.setOnAction(e->{
            Bio selectedItem = tableBio.getSelectionModel().getSelectedItem();
            String id = selectedItem.getBID();

            tableBio.getItems().remove(selectedItem);


            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from Bio WHERE BID='"+id+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }



        });





        Pane bioPane = new Pane();
        bioPane.getChildren().addAll(memberBackImage, tableBio, biolable, addBio, deleteBio);
        bioPage = new Scene(bioPane, 900, 700);
        bioPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//===================== items page ===========================================
        Image itemsback = new Image("DB.jpg");
        ImageView ItemBackImage = new ImageView(itemsback);
        ItemBackImage.setFitHeight(700);
        ItemBackImage.setFitWidth(900);
        TextField searchItems= new TextField();
        searchItems.setLayoutX(86);
        searchItems.setLayoutY(68);
        searchItems.setPrefWidth(125);
        searchItems.setPrefHeight(30);




        TableView<Items> ItemsTable = new TableView<>();
        ItemsTable.setEditable(true);
        TableColumn<Items, String> IID = new TableColumn<>("ID");
        IID.setPrefWidth(125);
        IID.setResizable(false);
        IID.setCellValueFactory(new PropertyValueFactory<Items, String>("IID"));

        TableColumn<Items, String> name = new TableColumn<>("Name");
        name.setPrefWidth(125);
        name.setResizable(false);
        name.setCellValueFactory(new PropertyValueFactory<Items, String>("Name"));

        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Items, String>>() {

            @Override
            public void handle(CellEditEvent<Items, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setName(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getIID();
                String name =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getName() ;


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Items set name ='"+name+"'  WHERE IID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });




        TableColumn<Items, Double> Price = new TableColumn<>("Price");
        Price.setPrefWidth(100);
        Price.setResizable(false);
        Price.setCellValueFactory(new PropertyValueFactory<Items, Double>("price"));
        Price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        Price.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Items, Double>>() {

            @Override
            public void handle(CellEditEvent<Items, Double> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPrice(arg0.getNewValue());
                String id = 	arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getIID();
                double Weight =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPrice();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Items set price ='"+Weight+"'  WHERE IID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();
                    ItemsTable.refresh();

                } catch (Exception e2) {
                    e2.getMessage();
                }


            }

        });




        TableColumn<Items, Double> Profit = new TableColumn<>("Profit");
        Profit.setPrefWidth(100);
        Profit.setResizable(false);
        Profit.setCellValueFactory(new PropertyValueFactory<Items, Double>("proft"));



        TableColumn<Items, Double> Purchase = new TableColumn<>("Purchase");
        Purchase.setPrefWidth(100);
        Purchase.setResizable(false);
        Purchase.setCellValueFactory(new PropertyValueFactory<Items, Double>("Purchase"));
        Purchase.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        Purchase.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Items, Double>>() {

            @Override
            public void handle(CellEditEvent<Items, Double> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPurchase(arg0.getNewValue());
                String id = 	arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getIID();
                double Weight =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPurchase();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Items set purchase ='"+Weight+"'  WHERE IID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }


            }

        });




        ItemsTable.getColumns().addAll(IID, name, Price, Profit, Purchase);
        ItemsTable.setPrefSize(726, 436);
        ItemsTable.setLayoutX(87);
        ItemsTable.setLayoutY(110);
        FilteredList<Items> FliterProdect = new FilteredList<>(ItemsData, b -> true);
        searchItems.textProperty().addListener((observable, oldValue, newValue) -> {
            FliterProdect.setPredicate(e -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (e.getIID().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (e.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(e.getPrice()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(e.getPurchase()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                }  else
                    return false;

            });

        });
        SortedList<Items> sortdata = new SortedList<>(FliterProdect);
        sortdata.comparatorProperty().bind(ItemsTable.comparatorProperty());


        ItemsTable.setItems(ItemsData);

        Label ItemsLabel1 = new Label("Items");
        ItemsLabel1.setLayoutX(367);
        ItemsLabel1.setLayoutY(26);
        ItemsLabel1.setFont(Font.font("Robot", 36));
        ItemsLabel1.setTextFill(Color.web("#ffffff"));
        ItemsLabel1.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        Button addItems = new Button("Add");
        addItems.setLayoutX(229);
        addItems.setLayoutY(596);
        addItems.setPrefWidth(70);
        addItems.setPrefHeight(57);
        addItems.setOnAction(e->{

            Stage addPaymentStage = new Stage();
            addPaymentStage.setResizable(false);
            addPaymentStage.setAlwaysOnTop(true);
            addPaymentStage.setTitle("Add Items Window");

            Label PIDLabel = new Label("Items ID : ");
            TextField PIDInput = new TextField();

            Label amountLabel = new Label("Name : ");
            TextField NameInput = new TextField();

            Label PriceLabel = new Label("Price : ");
            TextField PriceInput = new TextField();

            Label purchaseDateLabel = new Label("Purchase : ");
            TextField PurchaseInput = new TextField();

            VBox labels = new VBox(10);
            labels.getChildren().addAll(PIDLabel, amountLabel, PriceLabel,purchaseDateLabel);

            VBox inputs = new VBox(1);
            inputs.getChildren().addAll(PIDInput, NameInput,PriceInput, PurchaseInput);

            HBox boxes = new HBox();
            boxes.getChildren().addAll(labels, inputs);

            Button addPaymentButton = new Button("Add");
            addPaymentButton.setOnAction(e1 -> {

                try {

                    Connection con = db.getConnection().connectDB();

                    String sql = "INSERT INTO Items(IID,name,price,purchase) VALUES ('"+PIDInput.getText()+"' ,'"+
                            NameInput.getText()+"', '"+PriceInput.getText()+"' , '"+PurchaseInput.getText()+"')";


                    Statement stmt = con.createStatement();
                    stmt.execute(sql);
                    con.close();

                    ItemsData.add(new Items(PIDInput.getText(),
                            NameInput.getText(),Double.parseDouble(PriceInput.getText()) , Double.parseDouble(PurchaseInput.getText())));


                } catch (SQLException e2) {

                    Alert alert = new Alert(Alert.AlertType.NONE, "Primary key already exists!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                    e2.printStackTrace();
                } catch (ClassNotFoundException e2) {
                    System.out.println("Error sending data to database!!!");
                } catch (InputMismatchException e2) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Please enter a valid input!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                }



            });
            VBox everything = new VBox(8);
            everything.setAlignment(Pos.CENTER);
            everything.getChildren().addAll(boxes, addPaymentButton);

            Scene scene = new Scene(everything);

            addPaymentStage.setScene(scene);

            addPaymentStage.show();




        });


        Button deleteItems = new Button("Delete");
        deleteItems.setLayoutX(586);
        deleteItems.setLayoutY(596);
        deleteItems.setPrefWidth(70);
        deleteItems.setPrefHeight(57);
        deleteItems.setOnAction(e->{
            Items selectedItem = ItemsTable.getSelectionModel().getSelectedItem();
            String id = selectedItem.getIID();

            ItemsTable.getItems().remove(selectedItem);


            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from Items WHERE IID='"+id+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }



        });

        Pane ItemstPane = new Pane();
        ItemstPane.getChildren().addAll(ItemBackImage,ItemsTable,searchItems ,ItemsLabel1, addItems, deleteItems);
        ItemsPage = new Scene(ItemstPane, 900, 700);
        ItemsPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//=========================================================== sALES PAGE ======================
        Image SaleBack = new Image("DB.jpg");
        ImageView SaleBackImage = new ImageView(SaleBack);
        SaleBackImage.setFitHeight(700);
        SaleBackImage.setFitWidth(900);
        TextField ItemsID= new TextField();
        ItemsID.setLayoutX(86);
        ItemsID.setLayoutY(68);
        ItemsID.setPrefWidth(125);
        ItemsID.setPrefHeight(30);


        TextField totalamount = new TextField();
        totalamount.setLayoutX(704);
        totalamount.setLayoutY(594);
        totalamount.setPrefWidth(125);
        totalamount.setPrefHeight(30);

        Button Sales = new Button("Sale");
        Sales.setLayoutX(273);
        Sales.setLayoutY(594);
        Sales.setPrefWidth(70);
        Sales.setPrefHeight(57);





        TableView<Items> SaleTable  = new TableView<>();
        SaleTable .setEditable(true);

        TableColumn<Items, String> ItemName = new TableColumn<>("Name");
        ItemName.setPrefWidth(125);
        ItemName.setResizable(false);
        ItemName.setCellValueFactory(new PropertyValueFactory<Items, String>("Name"));

        TableColumn<Items, Double> ItemPrice = new TableColumn<>("Price");
        ItemPrice.setPrefWidth(125);
        ItemPrice.setResizable(false);
        ItemPrice.setCellValueFactory(new PropertyValueFactory<Items, Double>("price"));

        ItemPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        ItemPrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Items, Double>>() {

            @Override
            public void handle(CellEditEvent<Items, Double> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPrice(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getIID();
                Double amount =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPrice() ;

            }

        });



        TableColumn<Items, Integer> ItemQuantity = new TableColumn<>("Quantity");
        ItemQuantity.setPrefWidth(125);
        ItemQuantity.setResizable(false);
        ItemQuantity.setCellValueFactory(new PropertyValueFactory<Items, Integer>("quantity"));
        ItemQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ItemQuantity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Items, Integer>>() {

            @Override
            public void handle(CellEditEvent<Items, Integer> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setQuantity(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getIID();
                int amount =arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getQuantity() ;

            }

        });


        TableColumn<Items,Double> totalItems = new TableColumn<>("Total");
        totalItems.setPrefWidth(125);
        totalItems.setResizable(false);
        totalItems.setCellValueFactory(new PropertyValueFactory<Items,Double>("total"));


        SaleTable.getColumns().addAll(ItemName,ItemPrice,ItemQuantity,totalItems);

        SaleTable.setPrefSize(726, 436);
        SaleTable.setLayoutX(87);
        SaleTable.setLayoutY(110);

        SaleTable.setItems(SalesData);


        Label SaleLabel1 = new Label("Sale Page");
        SaleLabel1.setLayoutX(367);
        SaleLabel1.setLayoutY(26);
        SaleLabel1.setFont(Font.font("Robot", 36));
        SaleLabel1.setTextFill(Color.web("#ffffff"));
        SaleLabel1.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");
        ItemsID.setOnAction(e->{
            try {
                Connection con = db.getConnection().connectDB();
                String sql = "SELECT * FROM items WHERE IID = '" + ItemsID.getText() + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                double price = 0;
                double total = 0;
                int quantity = 0;
                String iid = "";
                String names = "";
                double sumAmout = 0;
                double proft = 0;
                double purchase = 0;

                int fg = 0;
                int flag = 0;
                while (rs.next()) {
                    if(fg== 0) {
                        names = rs.getString("name");
                        price = rs.getDouble("price");
                        quantity = rs.getInt("quantity");
                        iid = rs.getString("IID");
                        proft = rs.getDouble("proft");
                        purchase = rs.getDouble("purchase");

                        for (int i = 0; i < SalesData.size(); i++) {
                            if (SalesData.get(i).getIID().equals(ItemsID.getText())) {
                                int n = 0;
                                n = SalesData.get(i).getQuantity();
                                n++;
                                SalesData.get(i).setQuantity(n);

                                SaleTable.refresh();
                                fg = 1;
                                flag = 1;
                                break;


                            }
                        }
                        if (flag == 0) {
                            SalesData.add(new Items(iid, names, price, ++quantity, proft , purchase,total));
                            SaleTable.refresh();
                        }

                        for (Items o : SalesData) {

                            sumAmout = o.getTotal() + sumAmout;
                        }

                        totalamount.setText(NumberFormat.getCurrencyInstance().format(sumAmout));

                    }
                }
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            ItemsID.setText("");



        });

        Sales.setOnAction(e->{

            Calendar calendar = Calendar.getInstance();
            Date dateae = calendar.getTime();
            String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(dateae.getTime());
            LocalDate currentdate = LocalDate.now();
            double sum =0 ;

            for (Items o : SalesData) {

                sum = o.getTotal() + sum;
            }

            paymentList.add(new Payment(sum,currentdate.toString()));
            try {
                Connection con = db.getConnection().connectDB();
                String sql = "insert into Payment(amount,dateSale) values ('"
                        +sum + "','" + currentdate.toString() + "' )";
                Statement stmt = con.createStatement();

                stmt.executeUpdate(sql);
                con.close();
            } catch (Exception r) {
                System.out.println(r);
            }


            try {



            String path = "C:\\Users\\moham\\Desktop\\bills\\" + currentdate + ".pdf";
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            float x = pdfDocument.getDefaultPageSize().getWidth() / 2;
            float y = pdfDocument.getDefaultPageSize().getHeight() / 2;

            Document document = new Document(pdfDocument);
            float threecol = 190f;
            float twocol = 285f;
            float twocol150 = 435f;
            float twoColumnWidth[] = { twocol150, twocol };
            float threeColumnWidth[] = { threecol, threecol, threecol, threecol };
            float fullWidth[] = { threecol * 3 };

            Paragraph onesp = new Paragraph("\n");
            Paragraph twosp = new Paragraph("\n\n");
            Table nestedtable = new Table(new float[] { twocol / 2, twocol / 2 });
            Table headerTable = new Table(twoColumnWidth);
            Border nb = new SolidBorder(com.itextpdf.kernel.color.Color.WHITE, 1, 0);

            headerTable.addCell(new Cell().add("Bills").setBold().setFontSize(20f).setBorder(nb)
                    .setTextAlignment(TextAlignment.LEFT).setMarginLeft(5));

            nestedtable.addCell(getHeaderTextCell("Day.:"));
            nestedtable.addCell(getHeaderTextCellValue(day));
            nestedtable.addCell(getHeaderTextCell("Bill Date:"));
            nestedtable.addCell(getHeaderTextCellValue("" + currentdate));

            headerTable.addCell(nestedtable.setBorder(nb)).setBorder(nb);
            document.add(headerTable);
            document.add(new Paragraph("\n"));
            Border gb = new SolidBorder(com.itextpdf.kernel.color.Color.GRAY, 2);
            Table tableDivider = new Table(fullWidth);
            document.add(tableDivider.setBorder(gb));
            document.add(onesp);
            Table twoColTable = new Table(twoColumnWidth);
            twoColTable.addCell(getBillingandShippingCell("Billing Information"));
            twoColTable.addCell(getBillingandShippingCell("Shipping Information"));
            document.add(twoColTable.setMarginBottom(12f));

            Table twoColTable2 = new Table(twoColumnWidth);
            twoColTable2.addCell(getCell10fLeft("Company", true));
            twoColTable2.addCell(getCell10fLeft("Name", true));
            twoColTable2.addCell(getCell10fLeft("AbuThaher", false));
            twoColTable2.addCell(getCell10fLeft(String.valueOf(sum), false)); // NAME
            document.add(twoColTable2);

            Table twoColTable3 = new Table(twoColumnWidth);
            twoColTable3.addCell(getCell10fLeft("Name", true));
            twoColTable3.addCell(getCell10fLeft("Address", true));
            twoColTable3.addCell(getCell10fLeft("" + String.valueOf(sum), false));
            twoColTable3.addCell(getCell10fLeft("8570 Gulseth Terra, 3324 Eastwood\nSpringfi, Ma, 01114", false));
            document.add(twoColTable3);
            float oneColoumnwidth[] = { twocol150 };
            Table oneColTable1 = new Table(oneColoumnwidth);
            oneColTable1.addCell(getCell10fLeft("Address", true));
            oneColTable1.addCell(getCell10fLeft("8570 Gulseth Terra, 3324 Eastwood\nSpringfi, Ma, 01114", false));
            // oneColTable1.addCell(getCell10fLeft("Email",true));
            // oneColTable1.addCell(getCell10fLeft("stern@example.com",false));
            document.add(oneColTable1.setMarginBottom(10f));

            Table tableDivider2 = new Table(fullWidth);
            Border dgb = new DashedBorder(com.itextpdf.kernel.color.Color.GRAY, 0.5f);
            document.add(tableDivider2.setBorder(dgb));

            Paragraph producPara = new Paragraph("Products");

            document.add(producPara.setBold());
            Table threeColTable1 = new Table(threeColumnWidth);
            threeColTable1.setBackgroundColor(com.itextpdf.kernel.color.Color.BLACK, 0.7f);

            threeColTable1.addCell(new Cell().add("Description").setBold()
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE).setBorder(nb));
            threeColTable1.addCell(
                    new Cell().add("Quantity").setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                            .setTextAlignment(TextAlignment.CENTER).setBorder(nb));
            threeColTable1
                    .addCell(new Cell().add("Price").setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                            .setTextAlignment(TextAlignment.RIGHT).setBorder(nb))
                    .setMarginRight(15f);
            threeColTable1
                    .addCell(new Cell().add("Proft").setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                            .setTextAlignment(TextAlignment.RIGHT).setBorder(nb))
                    .setMarginRight(20f);
            document.add(threeColTable1);

            Table threeColTable2 = new Table(threeColumnWidth);
            String FONT = "C:\\Users\\moham\\Desktop\\AbuThaherjava\\Project\\src\\imgs\\ARIALUNI.TTF";

            PdfFont F = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);


            LanguageProcessor al = new ArabicLigaturizer();

            float totalSum = 0;
            float totalProf = 0;
            for (int i = 0; i < SalesData.size(); i++) {

                double total = SalesData.get(i).getQuantity() * SalesData.get(i).getPrice();
                System.out.println(SalesData.get(i).getQuantity() + " " + SalesData.get(i).getPurchase());
                double totalpr = SalesData.get(i).getQuantity() * SalesData.get(i).getProft();
                totalSum += total;
                totalProf += totalpr;

                Paragraph text = new Paragraph(al.process(SalesData.get(i).getName())).setFont(F)
                        .setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.LEFT);

                threeColTable2.addCell(new Cell().add(text).setBorder(nb)).setMarginLeft(10f);
                threeColTable2.addCell(new Cell().add(String.valueOf(SalesData.get(i).getQuantity()))
                        .setTextAlignment(TextAlignment.CENTER).setBorder(nb));
                threeColTable2.addCell(
                                new Cell().add(String.valueOf(total)).setTextAlignment(TextAlignment.RIGHT).setBorder(nb))
                        .setMarginRight(15f);
                threeColTable2.addCell(new Cell().add(String.valueOf(SalesData.get(i).getProft()))
                        .setTextAlignment(TextAlignment.RIGHT).setBorder(nb)).setMarginRight(20f);
            }
            document.add(threeColTable2.setMarginBottom(20f));
            float onetwo[] = { threecol + 125f, threecol * 2 };
            Table threeColTable4 = new Table(onetwo);
            threeColTable4.addCell(new Cell().add("").setBorder(nb));
            threeColTable4.addCell(tableDivider2).setBorder(nb);
            document.add(threeColTable4);
            float threeColumnWidth23[] = { threecol, threecol, threecol, threecol, threecol, threecol };
            Table threeColTable3 = new Table(threeColumnWidth23);
            threeColTable3.addCell(new Cell().add("").setBorder(nb)).setMarginLeft(10f);
            threeColTable3.addCell(new Cell().add("Total").setTextAlignment(TextAlignment.CENTER).setBorder(nb));
            threeColTable3.addCell(
                            new Cell().add(String.valueOf(totalSum)).setTextAlignment(TextAlignment.RIGHT).setBorder(nb))
                    .setMarginRight(15f);
            threeColTable3.addCell(new Cell().add("").setBorder(nb)).setMarginLeft(10f);
            threeColTable3
                    .addCell(new Cell().add("Total Proft").setTextAlignment(TextAlignment.CENTER).setBorder(nb));
            threeColTable3.addCell(
                            new Cell().add(String.valueOf(totalProf)).setTextAlignment(TextAlignment.RIGHT).setBorder(nb))
                    .setMarginRight(12f);

            document.add(threeColTable3);
            document.add(tableDivider2);
            document.add(new Paragraph("\n"));
            document.add(tableDivider.setBorder(new SolidBorder(1)).setMarginBottom(15f));
            document.close();

            } catch (Exception e2) {
                e2.getMessage();
            }


            SalesData.clear();
            SaleTable.refresh();



        });


        Pane SalePane = new Pane();
        SalePane.getChildren().addAll(SaleBackImage,SaleTable,ItemsID,SaleLabel1,totalamount,Sales);
        SalesPage = new Scene(SalePane, 900, 700);
        SalesPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(nutritionistPage);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void displayErrorMassage(String error) {
        Stage errorWindow = new Stage();
        errorWindow.setTitle("Error");
        errorWindow.setAlwaysOnTop(true);

        Label errorMassage = new Label(error);

        errorWindow.setScene(new Scene(errorMassage));
        errorWindow.show();
    }

}
