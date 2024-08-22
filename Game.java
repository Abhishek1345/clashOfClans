import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Game extends MouseAdapter{
    JFrame f;
    public  void setUp(){
        try{
        
        f=new JFrame();
        Canon c=new Canon(100,150);
        Barbarian b=new Barbarian(-60,-60);
        b.setBounds(0,0,500,500);
        f.add(b);
        c.setBounds(0,0,500,500);
        
        f.add(c);
        
        f.setSize(500,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addMouseListener(this);
        
        f.setVisible(true);
        
        
        }
        catch(Exception e){System.out.println(e);}
    }
    public static void main(String args[]){
        new Game().setUp();
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        Barbarian b=new Barbarian(e.getX(),e.getY());
        b.setBounds(0,0,500,500);
        f.add(b);
       
        for(int i=0;i<f.getContentPane().getComponents().length;i++){
            Component c=f.getContentPane().getComponents()[i];
            
            if(c instanceof Canon){
                Canon can=(Canon)c;
                b.targetX=can.x;
                b.targetY=can.y;
                b.target=can;
                Thread t=new Thread(){
                    @Override
                    public void run(){
                b.attack();
                f.remove(can);
            }
        };
        t.start();
                break;
        
     
    }
    
}
}

}
class Canon extends JComponent implements ActionListener{
    int x,y;
    
   boolean underAttack=false;
    int life=20;
    Timer t=new Timer(5,this);
    @Override
    public void paintComponent(Graphics g){
      
        super.paintComponent(g);
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("./canon.png"); 
        
        
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

class Barbarian extends JComponent implements ActionListener{
    int x,y,speedX=0,speedY=0;
    Timer t=new Timer(5,this);
    int targetX=100,targetY=150;
    Canon target;
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("./barbarian.png");  
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
                
            }
            else if(y>targetY){
                speedY=-1;
                
            }
             if(x<targetX){
               
                speedX=1;
            }
            else if(x>targetX){
                speedX=-1;
                
            }
        }
        target.underAttack=true;
    
    }
            
}

