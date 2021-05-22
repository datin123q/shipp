package application;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class BattleshipMain extends BatttleshipMain2 {
	@Override
	public void enemyMove(Stage primaryStage) {
	        while (enemyTurn) {
	            int x = random.nextInt(10);
	            int y = random.nextInt(10);

	            Cell cell = playerBoard.getCell(x, y);
	            if (cell.wasShot)
	                continue;

	            enemyTurn = cell.shoot();
	            if(cell.ship!=null) {
	           	   score = score - cell.ship.point;
	             }
	            

	            if (playerBoard.ships == 0) {
	                System.out.println("YOU LOSE");
	                newscore.addlose();
	                mediaPlayer.stop();
	                Media media3 = new Media(getClass().getResource("/sound/lose.mp3").toString());
	            	MediaPlayer mediaPlayer3 = new MediaPlayer(media3);
	                mediaPlayer3.setVolume(1);
	               mediaPlayer3.setAutoPlay(true);
	                
	                Image image3= new Image(getClass().getResourceAsStream("/images/youlose.jpg"));
	                ImageView imageView3 = new ImageView(image3);
	                Pane root2 = new Pane(imageView3);
	                menu.setFont(new Font(25));
	    	        menu.setLayoutX(250);
	    	        menu.setLayoutY(430);
	    	        history.setFont(new Font(25));
	    	        history.setLayoutX(680);
	    	        history.setLayoutY(430);
	                root2.getChildren().addAll(menu , history);
	                Scene secondScene = new Scene(root2, 1000, 560);
	                primaryStage.setScene(secondScene);
	                primaryStage.show();
	                enemyTurn = !cell.shoot();


	            }
	        }
	    }

}
