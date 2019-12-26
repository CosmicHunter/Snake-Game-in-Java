import javax.print.attribute.standard.DialogOwner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MainPanel extends JPanel implements KeyListener, ActionListener {
      public ImageIcon titleimg;

      private ImageIcon up_mouth;
      private ImageIcon down_mouth;
      private ImageIcon right_mouth;
      private ImageIcon left_mouth;
      private ImageIcon snakeimg;
      private ImageIcon enemyImg;
      private boolean left,right,up,down;

      private Timer timer;
      private int delay = 100;
      private int default_len_snake = 3;
      private int moves_executed = 0;
      private int game_score = 0;

      private int []  snakelen_x;
      private int []  snakelen_y;
      private int [] food_x_pos;
      private int [] food_y_pos;

      private int fx,fy;

      private Random rand;
      private boolean isgameover = false;


      public MainPanel(){
           snakelen_x = new int[750];
           snakelen_y = new int[750];
           food_x_pos = new int[34];
           food_y_pos = new int[34];
           left = right = up = down = false;
           this.addKeyListener(this);
           this.setFocusable(true);
           rand = new Random();
           this.setFocusTraversalKeysEnabled(false);
           timer = new Timer(delay,this);
           timer.start();
           fx = rand.nextInt(34);
           fy = rand.nextInt(23);
           fillFood();
           game_score = 0;


      }
      public void fillFood(){
          food_x_pos[0] = 25;
           for(int i=1;i<34;i++){
              food_x_pos[i] = food_x_pos[i-1] + 25;

           }
           food_y_pos[0] = 75;
           for(int i=1;i<23;i++){
               food_y_pos[i] = food_y_pos[i-1]+25;
           }

      }

      public void paint(Graphics g){
           if(moves_executed==0){
               snakelen_x[0] = 100;
               snakelen_x[1] = 75;
               snakelen_x[2] = 50;

               snakelen_y[0] = 100;
               snakelen_y[1] = 100;
               snakelen_y[2] = 100 ;
           }

          // Title image border
           g.setColor(Color.WHITE);
           g.drawRect(24,10,851,55);


           // Title Image
          titleimg = new ImageIcon("assets/snaketitle.jpg");
          titleimg.paintIcon(this,g,25,11);

          // Draw Border for GamePlay.
          g.setColor(Color.WHITE);
          g.drawRect(24,74,851,577);

          // Fill Area for background
          g.setColor(Color.BLACK);
          g.fillRect(25,75,850,575);
           // show scores
          g.setColor(Color.WHITE);
          g.setFont(new Font("arial",Font.PLAIN,14));
          g.drawString("Score "+game_score,780,30);


          g.setColor(Color.WHITE);
          g.setFont(new Font("arial",Font.PLAIN,14));
          g.drawString("Length "+default_len_snake,780,50);

       // Arrays head stores the postion of the head of the snake
           right_mouth = new ImageIcon("assets/rightmouth.png");
          right_mouth.paintIcon(this,g,snakelen_x[0],snakelen_y[0]);

          for(int i=0;i<default_len_snake;i++){
              if(i==0 && right){
                  right_mouth = new ImageIcon("assets/rightmouth.png");
                  right_mouth.paintIcon(this,g,snakelen_x[i],snakelen_y[i]);
              }
              if(i==0 && left){
                  left_mouth = new ImageIcon("assets/leftmouth.png");
                  left_mouth.paintIcon(this,g,snakelen_x[i],snakelen_y[i]);
              }
              if(i==0 && down){
                  down_mouth = new ImageIcon("assets/downmouth.png");
                 down_mouth.paintIcon(this,g,snakelen_x[i],snakelen_y[i]);
              }
              if(i==0 && up){
               up_mouth = new ImageIcon("assets/upmouth.png");
                  up_mouth.paintIcon(this,g,snakelen_x[i],snakelen_y[i]);
              }
              if(i!=0){
                  snakeimg = new ImageIcon("assets/snakeimage.png");
                  snakeimg.paintIcon(this,g,snakelen_x[i],snakelen_y[i]);
              }
          }


          enemyImg = new ImageIcon("assets/enemy.png");
          if(food_x_pos[fx]==snakelen_x[0] && food_y_pos[fy]==snakelen_y[0]){
              default_len_snake++;
              game_score++;
              fx = rand.nextInt(34);
              fy = rand.nextInt(23);
          }
          enemyImg.paintIcon(this,g,food_x_pos[fx],food_y_pos[fy]);

          for(int i =1;i<default_len_snake;i++){
              if(snakelen_x[i]==snakelen_x[0] && snakelen_y[i]==snakelen_y[0]){
                  right  = false;
                  up = false;
                  down = false;
                  left = false;
                  isgameover = true;
                  g.setColor(Color.PINK);
                  g.setFont(new Font("arial",Font.BOLD,50));
                  g.drawString("GAME OVER",300,300);
                  g.setFont(new Font("arial",Font.BOLD,20));
                  g.drawString("Press Space to restart",350,340);
              }
          }



          g.dispose();
      }

    @Override
    public void actionPerformed(ActionEvent e) {
          timer.start();
          if(!isgameover) {
              if (right) {
                  for (int i = default_len_snake - 1; i >= 0; i--) {
                      snakelen_y[i + 1] = snakelen_y[i];
                  }
                  for (int i = default_len_snake; i >= 0; i--) {
                      if (i == 0) {
                          snakelen_x[0] += 25;
                      } else {
                          snakelen_x[i] = snakelen_x[i - 1];
                      }
                      if (snakelen_x[i] > 850) {
                          snakelen_x[i] = 25;
                      }
                  }
                  repaint();
              }
              if (left) {
                  for (int i = default_len_snake - 1; i >= 0; i--) {
                      snakelen_y[i + 1] = snakelen_y[i];
                  }
                  for (int i = default_len_snake; i >= 0; i--) {
                      if (i == 0) {
                          snakelen_x[0] -= 25;
                      } else {
                          snakelen_x[i] = snakelen_x[i - 1];
                      }
                      if (snakelen_x[i] < 25) {
                          snakelen_x[i] = 850;
                      }
                  }
                  repaint();
              }
              if (up) {
                  for (int i = default_len_snake - 1; i >= 0; i--) {
                      snakelen_x[i + 1] = snakelen_x[i];
                  }
                  for (int i = default_len_snake; i >= 0; i--) {
                      if (i == 0) {
                          snakelen_y[0] = snakelen_y[0] - 25;
                      } else {
                          snakelen_y[i] = snakelen_y[i - 1];
                      }
                      if (snakelen_y[i] < 75) {
                          snakelen_y[i] = 625;
                      }
                  }
                  repaint();
              }

              if (down) {
                  for (int i = default_len_snake - 1; i >= 0; i--) {
                      snakelen_x[i + 1] = snakelen_x[i];
                  }
                  for (int i = default_len_snake; i >= 0; i--) {
                      if (i == 0) {
                          snakelen_y[0] = snakelen_y[0] + 25;
                      } else {
                          snakelen_y[i] = snakelen_y[i - 1];
                      }
                      if (snakelen_y[i] > 625) {
                          snakelen_y[i] = 75;
                      }
                  }
                  repaint();
              }
          }
      }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            moves_executed = 0;
            game_score = 0;
            default_len_snake = 3;
            isgameover = false;
            repaint();
        }

          if(!isgameover) {
              if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                  moves_executed++;
                  if (!left) {
                      right = true;
                  } else {
                      left = true;
                      right = false;
                  }
                  up = false;
                  down = false;
              }
              if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                  moves_executed++;
                  if (!right) {
                      left = true;
                  } else {
                      left = false;
                      right = true;
                  }
                  up = false;
                  down = false;
              }
              if (e.getKeyCode() == KeyEvent.VK_UP) {
                  moves_executed++;
                  if (!down) {
                      up = true;
                  } else {
                      up = false;
                      down = true;
                  }
                  left = false;
                  right = false;
              }
              if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                  moves_executed++;
                  if (!up) {
                      down = true;
                  } else {
                      down = false;
                      up = true;
                  }
                  left = false;
                  right = false;
              }
          }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
