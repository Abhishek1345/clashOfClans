import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Canon extends JComponent implements ActionListener{
    int x,y;
    
   boolean underAttack=false;
    int life=20;
    Timer t=new Timer(5,this);
    @Override
    public void paintComponent(Graphics g){
      
        super.paintComponent(g);
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("https://raw.githubusercontent.com/Abhishek1345/clashOfClans/master/canon.png"); 
        
        
        g.drawImage(i,x,y,50,50,this);
        if(underAttack){
            drawHealthBar(g);
        }
        
    }
        
    
    
    public Canon(int x,int y){
        this.x=x;
        this.y=y;
    }
    public void actionPerformed(ActionEvent e){
        repaint();
    }
    public void drawHealthBar(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(x,y-15,life,10);
    }   
            
}
