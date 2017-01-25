/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.project;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
/**
 *
 * @author amnipp
 */
public class ColorGameGUI extends javax.swing.JFrame {

    ColorGame cg;
    Random r = new Random();
    //ArrayList<JButton> colorBtnList;
    HashMap<JButton, String> colorBtnList = new HashMap<>();
    /**
     * Creates new form ColorGameGUI
     */
    public ColorGameGUI() {
        initComponents();
    }
    
    public void setColorGame(ColorGame cg){
        this.cg = cg;
    }
    
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
            //I know this is really bad and should not be done this way but
            //I cant think of much else at this moment -amnipp
            switch(i){
                case 0:
                    colorBtn1.setBackground(color);
                    colorBtn1.setForeground(color);
                    colorBtnList.put(colorBtn1, colorList[i].toLowerCase());
                    break;
                case 1:
                    colorBtn2.setBackground(color);
                    colorBtn2.setForeground(color);
                    colorBtnList.put(colorBtn2, colorList[i].toLowerCase());
                    break;
                case 2:
                    colorBtn3.setBackground(color);
                    colorBtn3.setForeground(color);
                    colorBtnList.put(colorBtn3, colorList[i].toLowerCase());
                    break;
                case 3:
                    colorBtn4.setBackground(color);
                    colorBtn4.setForeground(color);
                    colorBtnList.put(colorBtn4, colorList[i].toLowerCase());
                    break;
                case 4:
                    colorBtn5.setBackground(color);
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
    public JLabel getScoreLabel(){
        return scoreLabel;
    }
    
    public JLabel getColorLabel(){
        return colorLabel;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        colorLabel = new javax.swing.JLabel();
        colorBtn1 = new RoundedButton();
        colorBtn2 = new RoundedButton();
        colorBtn3 = new RoundedButton();
        colorBtn4 = new RoundedButton();
        colorBtn5 = new RoundedButton();
        scoreLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 400));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        colorLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        getContentPane().add(colorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 110, 50));

        colorBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn1ActionPerformed(evt);
            }
        });
        getContentPane().add(colorBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 100, 100));

        colorBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn2ActionPerformed(evt);
            }
        });
        getContentPane().add(colorBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 100, 100));

        colorBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn3ActionPerformed(evt);
            }
        });
        getContentPane().add(colorBtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 100, 100));

        colorBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn4ActionPerformed(evt);
            }
        });
        getContentPane().add(colorBtn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 100, 100));

        colorBtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn5ActionPerformed(evt);
            }
        });
        getContentPane().add(colorBtn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 100, 100));

        scoreLabel.setText("Score");
        getContentPane().add(scoreLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 100, 20));

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
    private javax.swing.JButton colorBtn1;
    public javax.swing.JButton colorBtn2;
    private javax.swing.JButton colorBtn3;
    private javax.swing.JButton colorBtn4;
    private javax.swing.JButton colorBtn5;
    private javax.swing.JLabel colorLabel;
    public javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables

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
        protected void paintBorder(Graphics graphic){
            graphic.setColor(getForeground());
            graphic.drawOval(0, 0, getSize().width-1, getSize().height-1);
        }
        Shape shape;
        @Override
        public boolean contains(int x,int y){
            if(shape == null || !shape.getBounds().equals(getBounds())){
                shape = new Ellipse2D.Float(0,0,getWidth(),getHeight());
            }
            return shape.contains(x,y);
        }
    }

}

