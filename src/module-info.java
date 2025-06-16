
module sae201 {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	
	opens sae201 to javafx.graphics, javafx.fxml, javafx.base;	
}