package com.toxicfrog.gui.helper;

import com.toxicfrog.cache.ImageCache;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GUIHelper {

	public static Button createChooseButton(String imagePath, String title, double size) {
		double spacing = 10;
		double buttonSize = size - (spacing * 2);
		
	    Button button = new Button();
	    
	    ImageView imageView = new ImageView(ImageCache.getImage(imagePath, null));
	    imageView.setFitWidth(buttonSize);
	    imageView.setFitHeight(buttonSize);
	    
	    Label titleLabel = new Label(title);
	    titleLabel.getStyleClass().add("gui-label-small");
	    
	    VBox buttonContent = new VBox();
	    buttonContent.setAlignment(Pos.CENTER);
	    buttonContent.setPadding(new Insets(spacing));
	    buttonContent.setSpacing(spacing);
	    
	    buttonContent.getChildren().addAll(imageView, titleLabel);
	    
	    button.setPrefSize(buttonSize, buttonSize);
	    button.setAlignment(Pos.CENTER);
        button.setGraphic(buttonContent);
        button.getStyleClass().clear();
        button.getStyleClass().add("image-button-box");
	    
	    return button;
	}
}
