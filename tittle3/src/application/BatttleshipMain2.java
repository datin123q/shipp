package application;
import java.io.InputStream;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BatttleshipMain2 {
	public int d,e,f,g,h,l,m,n;
    private boolean running = false;
    private Board enemyBoard;
	protected Board playerBoard;
	
    private int shipsToPlace = 5;

    protected boolean enemyTurn = false;

    protected Random random = new Random();
    public int score ;
    public int count;
    public score newscore= new score();
    public Button history = new Button("History");
	public Button menu = new Button("Menu");
 	public MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/sound/sound.mp3").toString()));
    public Parent createContent(Stage primaryStage) {
    	
    	InputStream input = getClass().getResourceAsStream("/images/back.png");
		 
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        Pane root = new Pane(imageView);
        root.setPrefSize(1000, 600);
  
 //       mediaPlayer.setAutoPlay(true);
//        Pane root = new Pane();
//        root.setPrefSize(1000, 600);
        InputStream input1 = getClass().getResourceAsStream("/images/unmute.png");
		 
        Image image1 = new Image(input1);
        ImageView imageView1 = new ImageView(image1);
        InputStream input2 = getClass().getResourceAsStream("/images/mute.png");
		 
        Image image2 = new Image(input2);
        ImageView imageView2 = new ImageView(image2);
        
        
        
        Button but1 = new Button();
        but1.setShape(new Circle(15));
        but1.setMinSize(30, 30);
        but1.setMaxSize(30, 30);
        but1.setGraphic(imageView2 );
        but1.setLayoutX(0);
        but1.setLayoutY(0);
        root.getChildren().addAll(but1);
        but1.setOnAction(new EventHandler<ActionEvent>() {
        	int k=1;
        	 
            @Override
            public void handle(ActionEvent event) {
            	k++;
            	if(k%2==1) {mediaPlayer.stop(); but1.setGraphic(imageView2 );}
				else {mediaPlayer.play(); but1.setGraphic(imageView1 );}

            }
        });


        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();
            if(cell.ship!=null) {
          	  score = score + cell.ship.point;
          }
             count++;
             int a = cell.x;
             int c = cell.y+65;
               
                   if(count>4) {
                       Button button = new Button("Step"+(count-4)+":  x = "+ m+" "+"  y="+(char)n);
                       button.setPrefSize(130, 10);
                       root.getChildren().add(button);
                       button.setLayoutX(870);
                       button.setLayoutY(100);
                       }
                   
                   if(count>3) {
                       Button button3 = new Button("Step"+(count-3)+":  x = "+ h+" "+"  y="+(char)l);
                       button3.setPrefSize(130, 10);
                       root.getChildren().add(button3);
                       button3.setLayoutX(870);
                       button3.setLayoutY(75);
                      m = h;
                      n =l;
                       }
                   if(count>2) {
                     Button button3 = new Button("Step"+(count-2)+":  x = "+ f+" "+"  y="+(char)g);
                     button3.setPrefSize(130, 10);
                     root.getChildren().add(button3);
                     button3.setLayoutX(870);
                     button3.setLayoutY(50);
                    h = f;
                    l = g;
                     }
                   
                   
                   if(count>1) {
                	   Button button2 = new Button("Step"+(count-1)+":  x = "+ d+" "+"  y="+(char)e);
                   button2.setPrefSize(130, 10);
                   root.getChildren().add(button2);
                   button2.setLayoutX(870);
                   button2.setLayoutY(25);
                   f = d;
                   g = e;
                   
                   }
                	  
                 if(count >= 1) {
                	  Button button1 = new Button("Step"+count+":  x = "+ a+" "+"  y="+(char)c);
                      button1.setPrefSize(130, 10);
                      root.getChildren().add(button1);
                      button1.setLayoutX(870);
                      button1.setLayoutY(0);
                      d = a;
                      e = c;
                  }
            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN");
                newscore.addscore(score, count);
                Media media3 = new Media(getClass().getResource("/sound/win.mp3").toString());
            	MediaPlayer mediaPlayer3 = new MediaPlayer(media3);
                mediaPlayer3.setVolume(1);
               mediaPlayer3.setAutoPlay(true);
                mediaPlayer.stop();
                Image image3= new Image(getClass().getResourceAsStream("/images/youwin.jpg"));
                ImageView imageView3 = new ImageView(image3);
                Pane root2 = new Pane(imageView3);
               // root2.setPrefSize(1000, 600);
                menu.setFont(new Font(25));
    	        menu.setLayoutX(250);
    	        menu.setLayoutY(480);
    	        history.setFont(new Font(25));
    	        history.setLayoutX(680);
    	        history.setLayoutY(480);
            	Integer y = new Integer(score);
    			String z=y.toString();
                Label Yourscore= new Label(z);
                Yourscore.setFont(new Font(55));
                Yourscore.setLayoutX(470);
                Yourscore.setLayoutY(180);

    	       
                root2.getChildren().addAll(Yourscore,menu , history);
                Scene secondScene = new Scene(root2, 1000, 560);
                primaryStage.setScene(secondScene);
                primaryStage.show();
            }

            if (enemyTurn)
                enemyMove(primaryStage);
        });

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
            	cell.headOfShip = true ;
            	if (--shipsToPlace == 0) {
                    startGame();
                }
            }
        });

        HBox vbox = new HBox(100, playerBoard, enemyBoard);
        vbox.setAlignment(Pos.CENTER);

       vbox.setLayoutX(90);
       vbox.setLayoutY(125);
       root.getChildren().add(vbox);

        return root;
    }

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

            if(cell.headOfShip) {
            	if(cell.ship.vertical) {
            		for(int i = y;i< y+cell.ship.type; i++) {
            			Cell celli= playerBoard.getCell(x,i);
            			if(!celli.wasShot) {
                			celli.shoot();
                			score = score - cell.ship.point;
        }
            		}
            	}else {
            		for(int i = x;i< x+cell.ship.type; i++) {
            			Cell celli= playerBoard.getCell(i,y);
            			if(!celli.wasShot) {
                			celli.shoot();
                			score = score - cell.ship.point;
                		}
            	}
            }
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

    private void startGame() {
        // place enemy ships
        int type = 5;

        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        running = true;
    }

    
    public void start(Stage primaryStage)  {
    	 menu.setOnAction(new EventHandler<ActionEvent>() {
        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	Menu menu = new Menu();
	            	menu.start(primaryStage);
	            	}
	            });
    	 history.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				   history his= new history();
				   his.start(primaryStage);
				
			}
    		 
    	 });
        Scene scene = new Scene(createContent(primaryStage));
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
      
    }

}

