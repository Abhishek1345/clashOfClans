import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Game extends MouseAdapter{
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