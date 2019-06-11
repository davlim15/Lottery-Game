/* LOTTO PROJECT MAIN FILE
   David Lim 4/4/2016      */
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class LottoClient extends JFrame
{
   
   private Container contents;
   private JLabel winnings, amount, jackpot, jamount;
   private JRadioButton [] a = new JRadioButton [4];
   private JRadioButton [] b = new JRadioButton [4];
   private JRadioButton [] c = new JRadioButton [4];
   private JRadioButton [] d = new JRadioButton [4];
   private JButton initiate;
   private JPanel toppanel, mainpanel, bottompanel, lpanel, rpanel;
   private Timer timer;
   private int varwin, varjack, match=0, total;
   private int [] lot = {1,1,1,1};
   private int [] win = {1,2,3,4};
   private Random random = new Random();
   
   
   public LottoClient() throws IOException
   {
      super("Lottery");
      contents = getContentPane();
      contents.setLayout(new BorderLayout());
      
      Scanner file = new Scanner(new File("LottoFile"));
      varwin = file.nextInt();
      varjack = file.nextInt();
      
      //------------
      //NORTH BORDER
      //------------
      toppanel = new JPanel();
      toppanel.setPreferredSize(new Dimension(500,150));
      contents.add(toppanel, BorderLayout.NORTH);
      
      //-------------
      //CENTER BORDER
      //-------------
      Lotto mainpanel = new Lotto(); //components constructed in Lotto()
      mainpanel.centerPanel();
      contents.add(mainpanel.getButtons(), BorderLayout.CENTER);
      a[0] = mainpanel.getA0();
      a[1] = mainpanel.getA1();
      a[2] = mainpanel.getA2();
      a[3] = mainpanel.getA3();
      b[0] = mainpanel.getB0();
      b[1] = mainpanel.getB1();
      b[2] = mainpanel.getB2();
      b[3] = mainpanel.getB3();
      c[0] = mainpanel.getC0();
      c[1] = mainpanel.getC1();
      c[2] = mainpanel.getC2();
      c[3] = mainpanel.getC3();
      d[0] = mainpanel.getD0();
      d[1] = mainpanel.getD1();
      d[2] = mainpanel.getD2();
      d[3] = mainpanel.getD3();
      
      //------------
      //SOUTH BORDER
      //------------
      bottompanel = new JPanel();
      bottompanel.setLayout(new GridLayout(1,2));
      
         lpanel = new JPanel();
         lpanel.setLayout(new GridLayout(2,2));
         winnings = new JLabel("Winnings:",SwingConstants.CENTER);
         amount = new JLabel("$"+Integer.toString(varwin),SwingConstants.CENTER);
         amount.setFont(new Font("Courier", Font.BOLD, 15));
         jackpot = new JLabel("JACKPOT:",SwingConstants.CENTER);
         jamount = new JLabel("$"+Integer.toString(varjack),SwingConstants.CENTER);
         jamount.setFont(new Font("Courier", Font.BOLD, 15));
         lpanel.add(winnings);
         lpanel.add(jackpot);
         lpanel.add(amount);
         lpanel.add(jamount);
         
         rpanel = new JPanel();
         initiate = new JButton("Initiate! ($5)");
         rpanel.add(initiate);
         
      bottompanel.add(lpanel);
      bottompanel.add(rpanel);
      contents.add(bottompanel, BorderLayout.SOUTH);
      
      //Handlers for timer, radio buttons, and initiate button
      ButtonHandler bh = new ButtonHandler();
      for (int i = 0; i<a.length; i++)
      {
         a[i].addItemListener(bh);
         b[i].addItemListener(bh);
         c[i].addItemListener(bh);
         d[i].addItemListener(bh);
      }
      InitiateHandler ih = new InitiateHandler();
      initiate.addActionListener(ih);
      timer = new Timer(100, ih);
      timer.start(); //timer runs at the start of the program for animation
      
      setSize(500,290);
      setVisible(true);
   }
   
   //GRAPHICS IN PAINT METHOD
   public void paint(Graphics g)
   {
      super.paint(g);
      g.drawRect(8,31,483,149); //initx,inity,length,width
      g.setColor(Color.BLACK);
      g.fillOval(30,66,79,79); //-,-,+2,+2
      g.fillOval(150,66,79,79);
      g.fillOval(270,66,79,79);
      g.fillOval(390,66,79,79);
      g.setColor(Color.BLACK);
      g.setFont(new Font("Courier", Font.BOLD, 15));
      g.drawString(Integer.toString(lot[0]),65,175);
      g.drawString(Integer.toString(lot[1]),185,175);
      g.drawString(Integer.toString(lot[2]),305,175);
      g.drawString(Integer.toString(lot[3]),425,175);
      g.setColor(Color.WHITE);
      g.setFont(new Font("Courier", Font.BOLD, 50));
      for (int i = 0; i<a.length; i++)
      {
         if (timer.isRunning())              //Animated numers are light gray
            g.setColor(Color.LIGHT_GRAY);
         else if (lot[i]==win[i])            //Lotto numbers revealed here. Correct numbers are green, incorrect numbers are gray
            g.setColor(Color.GREEN);
         else
            g.setColor(Color.GRAY);
         g.drawString(Integer.toString(win[i]),55+120*(i),120);
      }
   }
   
   //LISTENER 1
   private class ButtonHandler implements ItemListener
   {
      public void itemStateChanged(ItemEvent ie)
      {
         //paints seleted number above radio button for comparison to lotto numbers
         for (int i = 0; i<a.length; i++)
         {
            if (ie.getSource()==a[i])
            {
               lot[0] = 1+i;
               repaint();
            }
            if (ie.getSource()==b[i])
            {
               lot[1] = 1+i;
               repaint();
            }
            if (ie.getSource()==c[i])
            {
               lot[2] = 1+i;
               repaint();
            }
            if (ie.getSource()==d[i])
            {
               lot[3] = 1+i;
               repaint();
            }
         }
      }
   }
   
   //LISTENER 2
   private class InitiateHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         //Animation: Flashes random numbers as if numbers are about to be randomly selected
         for (int i = 0; i<a.length; i++)
         {
            win[i]++;
            if (win[i]>4)
               win[i] = 1;
         }
         repaint();
         
         /* $5 deducted from winnings
            4 lotto numbers from 1-4 selected randomly
            Random numbers compared with selected numbers
            0 matches: Nothing happens. +$5 to jackpot
            1-3 matches: JOptionPane displays winnings. +$5 to jackpot
            4 matches: WINNER! Jackpot added to winnings. Jackpot reset to $500
            If you run out of money: LOSER! Winnings and jackpot reset to initial values of $100 and $1000 respectively
             */
         if(e.getSource()==initiate)
         {
            varwin-=5;
            for (int i = 0; i<a.length; i++)
               win[i] = random.nextInt(4)+1;
            for (int i = 0; i<a.length; i++)
            {
               if (lot[i]==win[i])
                  match++;
            }
            if (match==0)
               varjack+=5;
            if (match<4 && match>=1)
            {
               if (match==1)
               {
                  varwin++;
                  total = 1;
               }
               if (match==2)
               {
                  varwin+=5;
                  total = 5;
               }
               if (match==3)
               {
                  varwin+=10;
                  total = 10;
               }
               varjack+=5;
               timer.stop();
               repaint();
               JOptionPane.showMessageDialog(null, match+" matches! You win $"+total);
               timer.start();
            }
            if (match==4)
            {
               varwin+=varjack;
               varjack = 500;
               timer.stop();
               repaint();
               JOptionPane.showMessageDialog(null, "Jackpot! YOU WIN");
               timer.start();
            }
            for (int i = 0; i<a.length; i++)
               win[i] = 1+i;
            amount.setText("$"+Integer.toString(varwin));
            jamount.setText("$"+Integer.toString(varjack));
            match = 0;
            if (varwin<5)
            {
               timer.stop();
               repaint();
               JOptionPane.showMessageDialog(null, "Not enough money! YOU LOSE");
               timer.start();
               varwin = 100;
               varjack = 1000;
               amount.setText("$"+Integer.toString(varwin));
               jamount.setText("$"+Integer.toString(varjack));
            }
            try
            {
               FileOutputStream fos = new FileOutputStream("LottoFile",false);
   		      PrintWriter pw = new PrintWriter(fos);
               pw.print(varwin+" "+varjack);
               pw.close();
            }
      		catch (FileNotFoundException fnfe)
      		{
      			System.out.println("File LottoFile not found");
      		}
         }
      }
   }
   
   public static void main(String[] args) throws IOException
   {
      LottoClient app = new LottoClient();
      app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}