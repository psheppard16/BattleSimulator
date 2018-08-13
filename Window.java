
/**
 * Write a description of class Window here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.util.ArrayList;
public class Window
{
    ArrayList<Player> team1;
    ArrayList<Player> team2;
    String team1Name = "team1";
    String team2Name = "team2";
    int team1Size = 1;
    int team2Size = 1;
    int team1Multi = 1;
    int team2Multi = 1;
    int done = 0;
    BufferedImage vsImage;
    JFrame inputScreen;
    Insets insets;
    JTextField name1Box;
    JTextField power1Box;
    JTextField name2Box;
    JTextField power2Box;
    JTextField size2Box;
    JTextField size1Box;
    JButton fightButton;
    public Window() 
    {
        inputScreen = new JFrame();
        inputScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inputScreen.setLocationByPlatform(true);
        inputScreen.setResizable(false);
        inputScreen.addWindowListener(new WindowListener()
            {
                public void windowClosed(WindowEvent e) 
                {
                    System.exit(0);
                } 

                public void windowActivated(WindowEvent e) 
                {
                }

                public void windowClosing(WindowEvent e) 
                {
                }

                public void windowDeactivated(WindowEvent e) 
                {
                }

                public void windowDeiconified(WindowEvent e) 
                {
                }

                public void windowOpened(WindowEvent e) 
                {
                }

                public void windowIconified(WindowEvent e) 
                {
                }
            });
        insets = inputScreen.getInsets();
        inputScreen.setSize(1000 + insets.left + insets.right,
            500 + insets.top + insets.bottom);
        inputScreen.setVisible(true);
        try
        {
            name1Box = new JTextField("Team1 name here", 15);
            name1Box.setBounds(50, 50, 150, 25);
            inputScreen.add(name1Box);
            Thread.sleep(10);

            fightButton = new JButton("FIGHT!");
            fightButton.setBounds(450, 350, 100, 50);
            fightButton.addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        fightBattle(e);
                    }          
                });
            inputScreen.add(fightButton);
            Thread.sleep(10);

            power1Box = new JTextField("Power of team1", 15);
            power1Box.setBounds(50, 150, 150, 25);
            inputScreen.add(power1Box);
            Thread.sleep(10);

            size1Box = new JTextField("size of team1", 15);
            size1Box.setBounds(50, 250, 150, 25);
            inputScreen.add(size1Box);
            Thread.sleep(10);

            name2Box = new JTextField("Team2 name here", 15);
            name2Box.setBounds(800, 50, 150, 25);
            inputScreen.add(name2Box);
            Thread.sleep(10);

            size2Box = new JTextField("size of team2", 15);
            size2Box.setBounds(800, 250, 150, 25);
            inputScreen.add(size2Box);
            Thread.sleep(10);

            power2Box = new JTextField("power of team2", 15);
            power2Box.setBounds(800, 150, 150, 25);
            inputScreen.add(power2Box);
            Thread.sleep(10);

            vsImage = ImageIO.read(new File("images/vsImage.jpg"));
            JLabel vsIcon = new JLabel(new ImageIcon(vsImage));
            vsIcon.setBounds(350, 100, 300, 300);
            inputScreen.add(vsIcon);
            Thread.sleep(10);
        }
        catch (NullPointerException e)
        {

        }
        catch (NumberFormatException l)
        {

        }
        catch (IOException g)
        {
        }
        catch (InterruptedException j)
        {
        }
        inputScreen.revalidate();
        inputScreen.repaint();
    }

    public void fightBattle(ActionEvent e) 
    {
        team1Name = name1Box.getText();
        Math.abs(team1Size = Integer.parseInt(size1Box.getText()));
        Math.abs(team1Multi = Integer.parseInt(power1Box.getText()));
        team2Name = name2Box.getText();
        Math.abs(team2Size = Integer.parseInt(size2Box.getText()));
        Math.abs(team2Multi = Integer.parseInt(power2Box.getText()));

        team1 = new ArrayList<Player>(team1Size);
        team2 = new ArrayList<Player>(team2Size);
        for (int i = 0; i < team1Size; i++)
        {
            team1.add(i, new Player(team1Multi));
        }

        for (int i = 0; i < team2Size; i++)
        {
            team2.add(i, new Player(team2Multi));
        }

        while (done != 1)
        {
            for (int i = 0; i <  team1.size() * Math.sqrt(team1Multi) * 2; i++)
            {
                team2.get(Utility.random(0, team2.size() - 1)).health -= team1.get(Utility.random(0, team1.size() - 1)).strength;
            }

            for (int i = 0; i < team2.size() * Math.sqrt(team2Multi) * 2; i++)
            {
                team1.get(Utility.random(0, team1.size() - 1)).health -= team2.get(Utility.random(0, team2.size() - 1)).strength;
            }

            for (int i = 0; i < team1.size(); i++)
            {
                if (team1.get(i).health <= 0)
                {
                    team1.remove(i);
                }
                else
                {
                    team1.get(i).maxHealth = team1.get(i).maxHealth - (team1.get(i).maxHealth - team1.get(i).health) / 3; 
                    team1.get(i).health = team1.get(i).maxHealth;
                }
            }

            for (int i = 0; i < team2.size(); i++)
            {
                if (team2.get(i).health <= 0)
                {
                    team2.remove(i);
                }
                else
                {
                    team2.get(i).maxHealth = team2.get(i).maxHealth - (team2.get(i).maxHealth - team2.get(i).health) / 3;
                    team2.get(i).health = team2.get(i).maxHealth;
                }
            }

            JOptionPane.showMessageDialog(null, "there are " + team1.size() + " players left in " + team1Name + "'s army");
            JOptionPane.showMessageDialog(null, "there are " + team2.size() + " players left in " + team2Name + "'s army");
            if (!(team1.size() == 0 || team2.size() == 0))
            {
                done = JOptionPane.showConfirmDialog(null, "would you like do do another battle?", "", JOptionPane.YES_NO_OPTION);
            }
            else
            {
                if (team1.size() == 0)
                {
                    JOptionPane.showConfirmDialog(null, team2Name + " is victorious!", "", JOptionPane.OK_OPTION);
                }
                else if (team2.size() == 0)
                {
                    JOptionPane.showConfirmDialog(null, team1Name + " is victorious!", "", JOptionPane.OK_OPTION);
                }
                done = 1;
            }
        }
        System.exit(0);
    }
}