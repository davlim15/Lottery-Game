/* LOTTO PROJECT RADIOBUTTON FILE
   David Lim 4/4/2016             */
import javax.swing.*;
import java.awt.*;
public class Lotto extends JPanel
{
   
   private JPanel [] panel = new JPanel [5];
   private JRadioButton [] a = new JRadioButton [4];
   private JRadioButton [] b = new JRadioButton [4];
   private JRadioButton [] c = new JRadioButton [4];
   private JRadioButton [] d = new JRadioButton [4];
   
   public void centerPanel()
   {
      //-------------
      //CENTER BORDER
      //-------------
      panel[0] = new JPanel();
      panel[0].setLayout(new GridLayout(1,4,20,0));
      
         panel[1] = new JPanel();
         panel[1].setLayout(new GridLayout(2,2));
         ButtonGroup first = new ButtonGroup();
         for (int i = 0; i<a.length; i++)
         {
            a[i] = new JRadioButton(Integer.toString(i+1));
            panel[1].add(a[i]);
            first.add(a[i]);
         }
         a[0].setSelected(true);
         
         panel[2] = new JPanel();
         panel[2].setLayout(new GridLayout(2,2));
         ButtonGroup second = new ButtonGroup();
         for (int i = 0; i<a.length; i++)
         {
            b[i] = new JRadioButton(Integer.toString(i+1));
            panel[2].add(b[i]);
            second.add(b[i]);
         }
         b[0].setSelected(true);
         
         panel[3] = new JPanel();
         panel[3].setLayout(new GridLayout(2,2));
         ButtonGroup third = new ButtonGroup();
         for (int i = 0; i<a.length; i++)
         {
            c[i] = new JRadioButton(Integer.toString(i+1));
            panel[3].add(c[i]);
            third.add(c[i]);
         }
         c[0].setSelected(true);
         
         panel[4] = new JPanel();
         panel[4].setLayout(new GridLayout(2,2));
         ButtonGroup fourth = new ButtonGroup();
         for (int i = 0; i<a.length; i++)
         {
            d[i] = new JRadioButton(Integer.toString(i+1));
            panel[4].add(d[i]);
            fourth.add(d[i]);
         }
         d[0].setSelected(true);
         
      for (int i = 0; i<a.length; i++)
         panel[0].add(panel[i+1]);
      add(panel[0]);
   }
   
   //methods for status of all radio butons
   //call when checking selection and deselection of radio buttons
   public JRadioButton getA0() { return a[0]; }
   public JRadioButton getA1() { return a[1]; }
   public JRadioButton getA2() { return a[2]; }
   public JRadioButton getA3() { return a[3]; }
   public JRadioButton getB0() { return b[0]; }
   public JRadioButton getB1() { return b[1]; }
   public JRadioButton getB2() { return b[2]; }
   public JRadioButton getB3() { return b[3]; }
   public JRadioButton getC0() { return c[0]; }
   public JRadioButton getC1() { return c[1]; }
   public JRadioButton getC2() { return c[2]; }
   public JRadioButton getC3() { return c[3]; }
   public JRadioButton getD0() { return d[0]; }
   public JRadioButton getD1() { return d[1]; }
   public JRadioButton getD2() { return d[2]; }
   public JRadioButton getD3() { return d[3]; }
   
   public JPanel getButtons()
   {
      return panel[0];
   }
}