
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marce
 */
public class main extends javax.swing.JFrame  implements KeyListener {

    /**
     * Creates new form main
     */
   int x = 0;
   int y = 0;
   int dim = 20;
   int length = 2;
   int Score = 2;
   int s = 0;
   int t = 0;
   
  
   boolean leftDirection = false;
   boolean upDirection = false;
   boolean downDirection = false;
   boolean rightDirection = false;
   
   model food;
   
   Timer stopky = null;
   Random rand = new Random();
   
   ArrayList<JLabel> row;
   ArrayList<ArrayList<JLabel>> labels = new ArrayList<>(); 
   ArrayList<model> snake;
   
   JLabel score;
   
   Border frame;
    public main() throws InterruptedException {
        
        snake = new ArrayList<>();
        food = new model(0,0);
        initComponents();
        this.addKeyListener(this);  
        
        setScoreLabel();
        
        createBoard();
        
         food.x = rand.nextInt(dim);
         food.y = rand.nextInt(dim);
         labels.get(food.x).get(food.y).setBackground(Color.green);
         
         
        
         stopky = new Timer((100), new ActionListener(){

            
            public void actionPerformed(ActionEvent evt){
                
               wallCross();

               if(leftDirection == true){
            
                  leftDirection();
               }
                
                if(rightDirection == true){
                    
                    rightDirection();
               }
                
                if(upDirection == true){

                    upDirection();
               }
                
                if(downDirection == true){

                    downDirection();
               }
            }
            
            

        });
        stopky.start();
    }
    
    //functions
   
    void end() throws IOException{
    
        this.dispose();
        new menu(Score).setVisible(true);
    }
     
    void wallCross(){
    
                  if(t<1 && upDirection == true){
                    
                        t=dim;
                    }
                  
                  if(t>dim-2 && downDirection == true){
                    
                        t=-1;
                    }
                  
                  if(s<1 && leftDirection == true){
                    
                        s=dim;
                    }
                  
                  if(s>dim-2 && rightDirection == true){
                    
                        s=-1;
                    }
    } 
    
    void leftDirection(){
    
                s--;  
                
                if(labels.get(s).get(t).getBackground() == Color.red ){
                
                        gameOver();
                    }
                
                labels.get(s).get(t).setBackground(Color.red);
                model had = new model(s,t);               
                snake.add(had);
                
                if(s==food.x && t == food.y){
                
                    generateNewFood();
                    
                    labels.get(food.x).get(food.y).setBackground(Color.green);
                    length++;
                    Score++;
                    score.setText(Integer.toString(Score));
                } 
                
                if(snake.size()>length){
                
                    labels.get(snake.get(0).x).get(snake.get(0).y).setBackground(Color.white);
                    snake.remove(0);
                }
    }
    
    void rightDirection(){
        
                    s++;
                    
                    if(labels.get(s).get(t).getBackground() == Color.red ){
                
                    
                        gameOver();
                        
                    }
                labels.get(s).get(t).setBackground(Color.red);
                
                model had = new model(s,t);               
                snake.add(had);
                
                 if(s==food.x && t == food.y){
                    generateNewFood();
                    labels.get(food.x).get(food.y).setBackground(Color.green);
                    length++;
                    Score++;
                    score.setText(Integer.toString(Score));
                } 
                 
                 System.out.println(length);
                if(snake.size()>length){
                
                    labels.get(snake.get(0).x).get(snake.get(0).y).setBackground(Color.white);
                    snake.remove(0);
                }
    
    }
    
    void upDirection(){
    
                t--;
                    
                if(labels.get(s).get(t).getBackground() == Color.red ){
                
                        gameOver();
                        
                    } 
                
                labels.get(s).get(t).setBackground(Color.red);
                model had = new model(s,t);               
                snake.add(had);
                
               if(s==food.x && t == food.y){
                
                    generateNewFood();
                    
                    labels.get(food.x).get(food.y).setBackground(Color.green);
                    length++;
                    Score++;
                    score.setText(Integer.toString(Score));
                } 
                
                if(snake.size()>length){
                
                    labels.get(snake.get(0).x).get(snake.get(0).y).setBackground(Color.white);
                    snake.remove(0);
                }
    }
    
    
    void downDirection(){
    
                t++;
                    
                if(labels.get(s).get(t).getBackground() == Color.red ){  
                    gameOver();
                }
                    
                labels.get(s).get(t).setBackground(Color.red);
               
                model had = new model(s,t);               
                snake.add(had);
                
                if(s==food.x && t == food.y){
                
                    generateNewFood();
                    labels.get(food.x).get(food.y).setBackground(Color.green);
                    length++;
                    Score++;
                    score.setText(Integer.toString(Score));
                    
                } 
                
                if(snake.size()>length){
                
                    labels.get(snake.get(0).x).get(snake.get(0).y).setBackground(Color.white);
                    snake.remove(0);
                }
    }
    void gameOver(){
    
                System.out.println("game over");
                stopky.stop();
                //JOptionPane.showMessageDialog(null, "Game over, Your score is:" + Score);
                stopky.stop();
                int input = JOptionPane.showOptionDialog(null, "Game over, your score is: " + Score, "Game over", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                if(input == JOptionPane.OK_OPTION){
                   
                    try {
                               
                        end();
                    } catch (IOException ex) {
                                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                            }
   
                    }
    }
    
    void generateNewFood(){
    
        while(labels.get(food.x).get(food.y).getBackground() == Color.red ){
                    food.x = rand.nextInt(dim);
                    food.y = rand.nextInt(dim);
                    }
    }
    
    void createBoard(){
    
                 for(x=0;x<dim;x++){
            row = new ArrayList<>();
            for(y=0;y<dim;y++){
                
                
                JLabel label = new JLabel();
                label.setBounds(x*20+10, y*20+10, 20, 20);
                label.setBorder(frame);
                
                this.add(label);
                
                row.add(label);
        
    
            
        }
            
          labels.add(row);
            
        }
    }
    
    void setScoreLabel(){
    
        score = new JLabel();
        score.setBounds(dim*20+60, 50, 40, 40);
        this.add(score);
        this.setSize((dim*35)+50, (dim*35)+50);
        score.setText(Integer.toString(Score));
        frame = BorderFactory.createRaisedBevelBorder();
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              
                try {
                    new main().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
                rightDirection = false;
                
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
                upDirection = false;
            }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
