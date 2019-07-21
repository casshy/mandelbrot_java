package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.Node;
import javafx.scene.paint.Color;


public class Main extends Application {
	Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			WritableImage img = draw();
			ImageView imgView = new ImageView(img);
			root.getChildren().add((Node)imgView);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public WritableImage draw() {
		int width = (int)scene.getWidth();
		int height = (int)scene.getHeight();
		System.out.println(String.format("%d, %d", width, height));
		
		WritableImage img = new WritableImage(width, height);
		int[][] result = Mandelbrot.compute(4, width, 50);
			
		PixelWriter writer = img.getPixelWriter();
		for(int i = 0; i < img.getWidth(); i++) {
			for(int j = 0; j < img.getHeight(); j++) {
//				System.out.println(String.format("i:%d, j:%d, res:%d", i, j, result[j][i]));
				
				int argb1 = (255 << 24) | (128 << 16) | (128 << 8) | 255;
				int argb2 = (255 << 24) | (255 << 16) | (255 << 8) | 0;
				int black = (255 << 24) | (0 << 16) | (0 << 8) | 0;
				if(result[j][i] > 0) {
					if(result[j][i] % 2 == 0)
						writer.setArgb(i, j, argb1);
					else
						writer.setArgb(i,  j,  argb2);
				}else {
					writer.setArgb(i, j, black);
				}
			}
		}
		return img;
	}
	
	public WritableImage fillImage(Image img) {
		WritableImage wImg = new WritableImage((int)img.getWidth(), (int)img.getHeight());
		System.out.println(String.format("Width: %d, Height: %d", (int)img.getWidth(), (int)img.getHeight()));
		
		PixelWriter writer = wImg.getPixelWriter();
		for(int y = 0; y < wImg.getHeight(); y++) {
			for(int x = 0; x < wImg.getWidth(); x++) {
				int argb = (255 << 24) | (255 << 16) | (0 << 8) | 0;
				writer.setArgb(x, y, argb);
			}
		}
		PixelReader reader = wImg.getPixelReader();
		System.out.println(String.format("%d", reader.getArgb(100, 200)));
		
		return wImg;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
