package Main;

import java.io.*;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

public class Controller implements Control {
    @FXML private BorderPane borderPane;
    @FXML private ChoiceBox choiceBox;
    @FXML private TextField textWidth;
    @FXML private TextField textHeight;
    @FXML private GridPane gridpane;


    private Image image;
    private List<File> files;
    private boolean uploadCheck;

// Warning message shown at gridpane
    private void showMessage(String message) {
        gridpane.getChildren().clear();
        Label label = new Label(message);
        gridpane.add(label, 20, 35);
    }

// upload images
    @FXML public void uploadHandle() {
        String text;
        FileInputStream input;
        ImageView imageView;
        String name;
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif" ,"*.jpeg","*.tiff","*.raw")
        );
        files = chooser.showOpenMultipleDialog(borderPane.getScene().getWindow());

        for (int i = 0; i < files.size(); i++) {
                try {
                    Label textArea = new Label();
                    File file = files.get(i);
                    name = file.getName();
                    input = new FileInputStream(file);
                    image = new Image(input);
                    imageView = new ImageView(image);
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100); //thumbnail (100x100)
                    text = name + "\n" + image.getHeight() + "×" + image.getWidth();
                    textArea.setText(text);
                    if (files.size() > 5) {
                        showMessage("Upload no more than 5 images.");
                        break;
                    }
                    gridpane.add(imageView, i % 5, i / 5);
                    gridpane.add(textArea, i % 5, i / 5 + 1);
                    uploadCheck = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

    }
// Convert new image, generate new name and save path
    @FXML public void newFileName() throws Exception {
        if(uploadCheck) {
            FileInputStream input;
            FileChooser chooser = new FileChooser();
            File file = chooser.showSaveDialog(borderPane.getScene().getWindow());
            for (int i = 0; i < files.size(); i++) {
                chooser.setInitialFileName(file.getName());
                input = new FileInputStream(files.get(i));
                image = new Image(input);
                String sourcePath = files.get(i).getPath();
                String filePath = file.getPath();
                String savePath = filePath + i + "." + choiceBox.getValue() ; // add i to avoid repeating file name

                if (textHeight.getText().equals("") || textWidth.getText().equals("")) {
                    showMessage("Please input desire convert size");
                    break;
                    } else {
                        int width = Integer.parseInt(textWidth.getText());
                        int height = Integer.parseInt(textHeight.getText());
                        convertImages(sourcePath,width,height,savePath);
                    }
            }
            uploadCheck = false;
            textWidth.clear();
            textHeight.clear();

        }else {
            showMessage("Please upload image first");
        }
    }
// Success & error messages
    private void convertImages(String sourcePath, int width, int height, String savePath) throws Exception{
        Convert convert = Convert.getInstance();
        boolean convertFinish = convert.convertImages(sourcePath, width, height, savePath);
        if(convertFinish){
            showMessage("Picture(s) successfully transferred.");
        }else {
            showMessage("Picture(s) failed transfer.");
        }
    }
}
