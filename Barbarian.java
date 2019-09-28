import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Barbarian extends JComponent implements ActionListener{
    int x,y,speedX=0,speedY=0;
    Timer t=new Timer(5,this);
    int targetX=100,targetY=150;
    Canon target;
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("https://github.com/Abhishek1345/clashOfClans/blob/master/barbarian.png");  
        g.drawImage(i,x,y,50,50,this);
        this.t.start();
        
    }
    
    public Barbarian(int x,int y){
        this.x=x;
        this.y=y;
    }
    public void actionPerformed(ActionEvent e){
        x+=speedX;
        y+=speedY;
        repaint();
    }
    public void attack(){
        approach();
        speedX=0;
        speedY=0;
        while(target.life!=0){
            try{
            target.life--;
            Thread.sleep(500);
        }catch(Exception e){}
    }
}
    
    public void approach(){
        while(!(new Rectangle(x,y,50,50)).intersects(new Rectangle(targetX,targetY,50,50))){
           
             if(y<targetY){
                speedY=1;
                speedX=0;
            }
            else if(y>targetY){
                speedY=-1;
                speedX=0;
            }
             else if(x<targetX){
                speedY=0;
                speedX=1;
            }
            else if(x>targetX){
                speedX=-1;
                speedY=0;
            }
        }
        target.underAttack=true;
    }
            
}
