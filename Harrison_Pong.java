import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;
public class Harrison_Pong{

    public static void main(String[] args){
        JFrame frame = new JFrame("Pong");
        PongPanel panel = new PongPanel();

        frame.setSize(1000,800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

    }
    public static class PongPanel extends JPanel implements ActionListener, KeyListener{
        boolean wPressed = false;
        boolean sPressed = false;
        boolean upPressed = false;
        boolean downPressed = false;
        boolean onePressed = false;
        boolean twoPressed = false;
        boolean start = true;
        boolean vsCPU = false;
        int LpaddleY = 400;
        int RpaddleY = 400;
        int ballX = 500;
        int ballY = 450;
        int ballXspeed = -5;
        int ballYspeed = 7;
        int leftScore = 0;
        int rightScore = 0;
        int LpaddleX = 50;
        int LpaddleW = 25;
        int LpaddleH = 150;
        int RpaddleX = 925;
        int RpaddleW = 25;
        int RpaddleH = 150;
        int ballW = 25;
        int ballH = 25;
        Timer timer = new Timer(1000/60, this);
        public PongPanel(){ // constructor
            setBackground(new Color(0,0,0));

            timer.start();
            setFocusable(true);
            addKeyListener(this);

        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(start == true){

                g.setColor(new Color(255,255,255));
                g.setFont(new Font("Coutier New", Font.BOLD, 70));
                g.drawString("WELCOME TO PONG!", 150, 300);
                g.drawString("Press 1 to vs CPU", 150, 400);
                g.drawString("Press 2 to vs player", 150, 500);
            }
            else if(leftScore < 11 && rightScore < 11){
                g.setColor(new Color(255,255,255));
                g.fillRect(LpaddleX,LpaddleY,LpaddleW,LpaddleH); // Left Paddle
                g.fillRect(RpaddleX,RpaddleY,RpaddleW,RpaddleH); // Right Paddle
                g.fillRect(ballX,ballY,ballW,ballH); // Ball

                g.setFont(new Font("Coutier New", Font.BOLD, 50));
                g.drawString("" + leftScore, 250, 50); // Left Score
                g.drawString("" + rightScore, 725, 50); // Right Score
            }
            else if(leftScore >= 11){
                timer.stop();
                g.setColor(new Color(255,255,255));
                g.setFont(new Font("Coutier New", Font.BOLD, 70));
                g.drawString("LEFT PLAYER WINS!", 150, 400);
            }
            else if(rightScore >= 11){
                timer.stop();
                g.setColor(new Color(255,255,255));
                g.setFont(new Font("Coutier New", Font.BOLD, 70));
                g.drawString("RIGHT PLAYER WINS!", 150, 400);
            }

        }

        public void actionPerformed(ActionEvent e){
            if(start == true){
                if(onePressed == true || twoPressed == true){
                    start = false;
                }
            }
            if(onePressed == true){
                RpaddleY = ballY - RpaddleH/2 + ballH/2;
                if(RpaddleY > ballY - RpaddleH/2 + ballH/2 && ballX > 250){
                    RpaddleY -= 12;
                }
                else if(RpaddleY < ballY - RpaddleH/2 + ballH/2 && ballX > 250){
                    RpaddleY += 12;
                }
            }
            if( wPressed == true )  {
                LpaddleY = LpaddleY -10; 
            }
            else if( sPressed == true )  {
                LpaddleY = LpaddleY +10; 
            }

            if( upPressed == true )  {
                RpaddleY = RpaddleY -10; 
            }
            else if( downPressed == true )  {
                RpaddleY = RpaddleY +10; 
            }

            if(LpaddleY <= 0){
                LpaddleY = 0;
            }
            if(LpaddleY >= 630){
                LpaddleY = 630;
            }
            if(RpaddleY <= 0){
                RpaddleY = 0;
            }
            if(RpaddleY >= 630){
                RpaddleY = 630;
            }

            if(ballY <= 0){
                ballYspeed = ballYspeed/-1;
            }
            else if(ballY >= 750){
                ballYspeed = ballYspeed*-1;
            }

            if(ballX <= 0){
                ballX = 500;
                ballY = 450;
                ballXspeed = 2;
                ballYspeed = 5;
                leftScore++;
                try{
                    Thread.sleep(500);
                }
                catch(Exception ex){
                }
                playSound("score.wav");
            }
            if(ballX >= 1000){
                ballX = 500;
                ballY = 450;
                ballXspeed = -2;
                ballYspeed = 5;
                rightScore++;
                playSound("score.wav");
            }

            boolean touchingLeftPaddle = intersects(LpaddleX, LpaddleY, LpaddleW, LpaddleH);
            if(touchingLeftPaddle == true){
                ballXspeed = ballXspeed*-1;
                if(ballXspeed > 0){
                    ballXspeed++;
                } 
                else {
                    ballXspeed--;
                }
                if(ballYspeed > 0){
                    ballXspeed++;
                }
                else{
                    ballXspeed--;
                }
                if(ballX < LpaddleX + LpaddleW) {
                    ballX = LpaddleX + LpaddleW;
                }
                playSound("collision.wav");
            }

            boolean touchingRightPaddle = intersects(RpaddleX, RpaddleY, RpaddleW, RpaddleH);
            if(touchingRightPaddle == true){
                ballXspeed = ballXspeed*-1;
                if(ballXspeed > 0){
                    ballXspeed++;
                } 
                else {
                    ballXspeed--;
                }
                if(ballYspeed > 0){
                    ballXspeed++;
                }
                else{
                    ballXspeed--;
                }
                if(ballX + ballW > RpaddleX) {
                    ballX = RpaddleX - ballW;
                }
                playSound("collision.wav");
            }

            ballX = ballX + ballXspeed;
            ballY = ballY + ballYspeed;
            repaint();
        }

        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_W) {
                wPressed = true; 
            }
            if(e.getKeyCode() == KeyEvent.VK_S) {
                sPressed = true; 
            }
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = true; 
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = true; 
            }
            if(e.getKeyCode() == KeyEvent.VK_1) {
                onePressed = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_2) {
                twoPressed = true;
            }
        }

        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_W) {
                wPressed = false; 
            }
            if(e.getKeyCode() == KeyEvent.VK_S) {
                sPressed = false; 
            }
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = false; 
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = false; 
            }
            if(e.getKeyCode() == KeyEvent.VK_1) {
                onePressed = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_2) {
                twoPressed = true;
            }
        }

        public void keyTyped(KeyEvent e){

        }

        public boolean intersects(int px1, int py1, int pw, int ph){
            int bx1 = ballX;
            int by1 = ballY;
            int bx2 = ballX+25;
            int by2 = ballY+25;
            int px2 = px1 + pw;
            int py2 = py1 + ph;

            if(bx2>px1 && bx1<px2 && by2>py1 && by1<py2){
                return true;
            }
            else {
                return false;
            }

        }

        public static void playSound(String s){
            try{
                File file = new File(s);
                AudioInputStream audio = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
            }
            catch (Exception e){
                System.out.println("Can't find file named: " + s);
            }
        }
    }
}
