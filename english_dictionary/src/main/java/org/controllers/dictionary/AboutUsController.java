package org.controllers.dictionary;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;

public class AboutUsController implements Initializable {
    @FXML
    private WebView webView;

    @FXML 
    private ImageView loadingImage;

    private static final String webURL = "https://github.com/quyk67uet/English_dictionaryApp#readme";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // loadingImage.setVisible(false);
        webView.getEngine().load(webURL);
        webView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("/assets/webView.css")).toString());
        
    }
    
}
