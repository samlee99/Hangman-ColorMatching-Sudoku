/***************************************************************
* file: ColorGameGUI.java
* author: Sam Lee, Andrew Nipp, Joshua Ludwig, Steven Mai, Je'Don Carter
* class: CS 245 – Programming Graphical User Interfaces
*
* assignment: Project v1.1
* date last modified: 1/25/2017
*
* purpose: This is the color game GUI file. It deals with displaying
* the colors of the buttons, highlighting, and shapes.
* 
*
****************************************************************/ 
package cs245.project;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import static java.lang.Thread.sleep;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
/**
 *
 * @author amnipp
 */
public class ColorGameGUI extends javax.swing.JFrame {

    ColorGame cg;
    Random r = new Random();
    HashMap<JButton, String> colorBtnList = new HashMap<>();
    /**
     * Creates new form ColorGameGUI
     */
    public ColorGameGUI() {
        initComponents();
        currentTime();
        addBindings();
    }

    public void setColorGame(ColorGame cg){
        this.cg = cg;
    }
    
    //method: addBindings
    //purpose: adds keyBindings to the Clock label
    public void addBindings(){
        Action exit = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                dispose();
                System.exit(0);
            }
        };
        Action credits = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                System.out.print("test1\n");
                MainMenu menu = new MainMenu();
                CreditsScreen credits = new CreditsScreen();
                menu.setVisible(false);
                credits.setVisible(true);
                dispose();
            }
        };
        String actName = "exit";
        Clock.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),
                actName);
        Clock.getActionMap().put(actName, exit);
        actName = "credits";
        Clock.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"),
                actName);
        Clock.getActionMap().put(actName, credits);
    }
    
    //method: initRandomColors()
    //purpose: this method chooses what colors the buttons are 
    public void initRandomColors(){
        String[] colorList = cg.getColorList();
        //Randomize the color list
        for (int i = colorList.length - 1; i > 0; i--)
        {
          int index = r.nextInt(i + 1);
          // Simple swap
          String a = colorList[index];
          colorList[index] = colorList[i];
          colorList[i] = a;
        }
        Field field;
        Color color = null;
        for(int i = 0; i < colorList.length; ++i){
            if(colorList[i].toLowerCase().equals("purple")){
               color = new Color(255, 0, 255); 
            }else{
                try {
                    field = Class.forName("java.awt.Color").getField(colorList[i].toLowerCase());
                    color = (Color)field.get(null);
                } catch (Exception ex) {}
            }
            
            ImageIcon icon;
            String colorLink;
            switch(i){
                case 0:
                    colorLink = "/cs245/project/trooper_images/deathstar_" + 
                            colorList[i] + ".png";
                    icon = new ImageIcon(getClass().getResource(colorLink));
                    colorBtn1.setIcon(icon);
                    colorBtn1.setForeground(color);
                    colorBtnList.put(colorBtn1, colorList[i].toLowerCase());
                    break;
                case 1:
                    colorLink = "/cs245/project/trooper_images/deathstar_" + 
                            colorList[i] + ".png";
                    icon = new ImageIcon(getClass().getResource(colorLink));
                    colorBtn2.setIcon(icon);
                    colorBtn2.setForeground(color);
                    colorBtnList.put(colorBtn2, colorList[i].toLowerCase());
                    break;
                case 2:
                    colorLink = "/cs245/project/trooper_images/deathstar_" + 
                            colorList[i] + ".png";
                    icon = new ImageIcon(getClass().getResource(colorLink));
                    colorBtn3.setIcon(icon);
                    colorBtn3.setForeground(color);
                    colorBtnList.put(colorBtn3, colorList[i].toLowerCase());
                    break;
                case 3:
                    colorLink = "/cs245/project/trooper_images/deathstar_" + 
                            colorList[i] + ".png";
                    icon = new ImageIcon(getClass().getResource(colorLink));
                    colorBtn4.setIcon(icon);
                    colorBtn4.setForeground(color);
                    colorBtnList.put(colorBtn4, colorList[i].toLowerCase());
                    break;
                case 4:
                    colorLink = "/cs245/project/trooper_images/deathstar_" + 
                            colorList[i] + ".png";
                    icon = new ImageIcon(getClass().getResource(colorLink));
                    colorBtn5.setIcon(icon);
                    colorBtn5.setForeground(color);
                    colorBtnList.put(colorBtn5, colorList[i].toLowerCase());
                    break;
                default:
                    System.out.println("default");
                    colorBtn1.setBackground(color);
                    colorBtn1.setForeground(color);
                    colorBtnList.put(colorBtn1, colorList[i].toLowerCase());
            }
        }
    }
    
    public void currentTime() {
        Thread clock = new Thread()
        {
                public void run()
                {
                    for(;;)
                    {
                        GregorianCalendar cal = new GregorianCalendar();
                        
                        int second = cal.get(GregorianCalendar.SECOND);
                        int minute = cal.get(GregorianCalendar.MINUTE);
                        int hour = cal.get(GregorianCalendar.HOUR);
                        Date now = new Date();
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM d, yyyy");
                        Clock.setText(dateFormatter.format(now) + "    " + hour + ": " + minute + ":  " + second);
                        try{
                            sleep(1000);
                        }
                        catch (Exception e)
                        {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                }
        };
        clock.start();
    }
    
    //method: getScoreLabel
    //purpose: gets the score during the color game for display
    public JLabel getScoreLabel(){
        return scoreLabel;
    }
    //method: getScoreLabel
    //purpose: gets the color label during the color game for display
    public JLabel getColorLabel(){
        return colorLabel;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    //method: initComponents
    //purpose: initializes the buttons,sets their coordinates, and lets them poll for mouse clicks
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        colorLabel = new javax.swing.JLabel();
        colorBtn1 = new RoundedButton();
        colorBtn2 = new RoundedButton();
        colorBtn3 = new RoundedButton();
        colorBtn4 = new RoundedButton();
        colorBtn5 = new RoundedButton();
        scoreLabel = new javax.swing.JLabel();
        Clock = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 400));
        getContentPane().setLayout(null);

        colorLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        getContentPane().add(colorLabel);
        colorLabel.setBounds(280, 180, 110, 50);

        colorBtn1.setToolTipText("Click the death star!");
        colorBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn1ActionPerformed(evt);
            }
        });
        getContentPane().add(colorBtn1);
        colorBtn1.setBounds(70, 100, 120, 120);

        colorBtn2.setToolTipText("Click the death star!");
        colorBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn2ActionPerformed(evt);
            }
        });
        getContentPane().add(colorBtn2);
        colorBtn2.setBounds(150, 250, 120, 120);

        colorBtn3.setToolTipText("Click the death star!");
        colorBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn3ActionPerformed(evt);
            }
        });
        getContentPane().add(colorBtn3);
        colorBtn3.setBounds(340, 250, 120, 120);

        colorBtn4.setToolTipText("Click the death star!");
        colorBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn4ActionPerformed(evt);
            }
        });
        getContentPane().add(colorBtn4);
        colorBtn4.setBounds(250, 30, 120, 120);

        colorBtn5.setToolTipText("Click the death star!");
        colorBtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn5ActionPerformed(evt);
            }
        });
        getContentPane().add(colorBtn5);
        colorBtn5.setBounds(420, 100, 120, 120);

        scoreLabel.setText("Score");
        getContentPane().add(scoreLabel);
        scoreLabel.setBounds(470, 50, 100, 20);

        Clock.setForeground(java.awt.Color.red);
        Clock.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Clock.setToolTipText("");
        getContentPane().add(Clock);
        Clock.setBounds(380, 20, 180, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs245/project/trooper_images/white.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 600, 400);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void colorBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorBtn2ActionPerformed
        // TODO add your handling code here:
        /*int color = r.nextInt(5) + 1;
        cg.changeColor(color);
        int colorNum = r.nextInt(5);
        cg.changeName(colorNum);*/
        JButton pressed = (JButton)evt.getSource();
        cg.setGuessedColor(colorBtnList.get(pressed));
        System.out.println(colorBtnList.get(pressed));
    }//GEN-LAST:event_colorBtn2ActionPerformed

    private void colorBtn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorBtn5ActionPerformed
        // TODO add your handling code here:
        JButton pressed = (JButton)evt.getSource();
        cg.setGuessedColor(colorBtnList.get(pressed));
        System.out.println(colorBtnList.get(pressed));
    }//GEN-LAST:event_colorBtn5ActionPerformed

    private void colorBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorBtn4ActionPerformed
        // TODO add your handling code here:
        JButton pressed = (JButton)evt.getSource();
        cg.setGuessedColor(colorBtnList.get(pressed));
        System.out.println(colorBtnList.get(pressed));
    }//GEN-LAST:event_colorBtn4ActionPerformed

    private void colorBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorBtn3ActionPerformed
        // TODO add your handling code here:
        JButton pressed = (JButton)evt.getSource();
        cg.setGuessedColor(colorBtnList.get(pressed));
        System.out.println(colorBtnList.get(pressed));
    }//GEN-LAST:event_colorBtn3ActionPerformed

    private void colorBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorBtn1ActionPerformed
        // TODO add your handling code here:
        JButton pressed = (JButton)evt.getSource();
        cg.setGuessedColor(colorBtnList.get(pressed));
        System.out.println(colorBtnList.get(pressed));
    }//GEN-LAST:event_colorBtn1ActionPerformed

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
            java.util.logging.Logger.getLogger(ColorGameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ColorGameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ColorGameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColorGameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColorGameGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Clock;
    private javax.swing.JButton colorBtn1;
    public javax.swing.JButton colorBtn2;
    private javax.swing.JButton colorBtn3;
    private javax.swing.JButton colorBtn4;
    private javax.swing.JButton colorBtn5;
    private javax.swing.JLabel colorLabel;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables
    //method: RoundedButton
    //purpose: this makes the button larger and makes them round
    private class RoundedButton extends JButton {
        public RoundedButton(){
            super("");
            //make the button large
            Dimension btnSize = getPreferredSize();
            btnSize.width = btnSize.height = Math.max(btnSize.width, btnSize.height);
            setPreferredSize(btnSize);
            setContentAreaFilled(false);
        }
        @Override
    //method: paintComponent
    //purpose: this checks if the mouse if the mouse is over the button and it'll
    //darken the button to highlight it.
        protected void paintComponent(Graphics graphic){
            if(getModel().isArmed()){
                Color color = getBackground();
                color = color.darker();
                graphic.setColor(color);
            }else{
                graphic.setColor(getBackground());
            }
            if(getModel().isRollover()){
                Color color = getBackground();
                color = color.darker();
                graphic.setColor(color);                
            }
            graphic.fillOval(0, 0, getSize().width-1, getSize().height-1);
            super.paintComponent(graphic);
        }
        @Override
     //method: paintBorder
    //purpose: this paints the border of the buttons
        protected void paintBorder(Graphics graphic){
            graphic.setColor(getForeground());
            graphic.drawOval(0, 0, getSize().width-1, getSize().height-1);
        }
        Shape shape;
        @Override
                    //method: contains
    //purpose: this makes checks if the buttons are within the bounds
    //if not, it'll give the correct bounds for mouse processing
        public boolean contains(int x,int y){
            if(shape == null || !shape.getBounds().equals(getBounds())){
                shape = new Ellipse2D.Float(0,0,getWidth(),getHeight());
            }
            return shape.contains(x,y);
        }
    }

}

