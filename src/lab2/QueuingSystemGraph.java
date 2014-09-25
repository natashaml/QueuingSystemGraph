package lab2;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
/**
 * @author Natasha Levkovich & Nastya Tsikhonchuk
 * @since 24.09.14
 */
public class QueuingSystemGraph extends Applet{
    Button    btn2,btn3,btn4;
    Choice    chc1;
    TextField idfld, rad;
    int  o, h;

    public void init(){
        setSize(800,675);
        setLayout(null);
        idfld = new TextField();
        rad = new TextField();
        add(rad);
        add(idfld);
        idfld.setText("10");
        rad.setText("3");
        btn2 = new Button("График f(t) плотности пуассоновского закона");
        add(btn2);
        btn3 = new Button("График для формулы вероятности состояния простоя одноканальной СМО с числом мест ожидания m");
        add(btn3);
        btn4 = new Button("e^t");
        add(btn4);
        chc1 = new Choice();
        add(chc1);
        chc1.addItem("Масштабирование графика в отношении 1:1");
        chc1.addItem("Масштабирование графика в отношении 1:2");
        idfld.setBounds(600, 550, 100, 25);
        rad.setBounds(700, 550, 100, 25);
        btn2.setBounds(0,575,800,25);
        btn3.setBounds(0,600,800,25);
        btn4.setBounds(0,625,800,25);
        chc1.setBounds(0,650,800,25);
        setBackground(Color.LIGHT_GRAY);

        final BL frame1 = new BL();
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        btn2.addActionListener(frame1);
        btn3.addActionListener(frame1);
        btn4.addActionListener(frame1);

    }

    public void paint_f(Graphics g,int n){
        int x,y,lambda=1,yold,step=1;
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica",Font.PLAIN, 25));
        g.drawString("График f(t) плотности пуассоновского закона",140,140);
        o = Integer.parseInt(rad.getText());
        h =Integer.parseInt(idfld.getText());
        int xold = 0;
        yold=(int)Math.abs(lambda*Math.expm1(-Math.E*xold*lambda/180)*(getSize().height));
        while (xold<800)
        {x=xold+step;
            y=(int)Math.abs(lambda*Math.expm1(-Math.E*xold/h*lambda/180)*(getSize().height-140));
            xold=x;
            yold=y;
            g.drawOval(xold/n,yold/n,o,o);
            g.fillOval(xold/n,yold/n,o,o);

        }
    }

    public void paint_p(Graphics g,int n){
        int x,y,P0=1,xold=0,yold,p=1;
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica",Font.PLAIN,20));
        g.drawString("График для формулы вероятности состояния простоя" +
                " одноканальной СМО",80,140);
        o = Integer.parseInt(rad.getText());
        h =Integer.parseInt(idfld.getText());
        int step = 1;
        yold=(int)Math.abs(xold);
        while(xold<800){
            x=xold+step;
            p=x;
            for(int i=1;i<2;i++){
                p=p*x;
                P0=P0+p;
            }
            y=540-(int)Math.abs(1000000/P0*h);
            xold=x;
            yold=y;
            g.drawOval(xold/n,yold/n,o,o);
            g.fillOval(xold/n,yold/n,o,o);
            P0=1;
            p=1;

        }
    }

    public void paint_e(Graphics g,int svoboda,
                        int okryglenie,int n,int ris){
        int x,y,P0=0,xold=0,yold,t=0,factor=1;
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica",Font.PLAIN, 50));
        g.drawString("e^t",380,140);
        o = Integer.parseInt(rad.getText());
        h =Integer.parseInt(idfld.getText());
        int step = 1;
        yold=(int)Math.abs(P0/okryglenie);
        while(xold<ris){
            x=xold+step;
            t=x;
            P0=1+t;
            double p = 0.1;
            for(double i=1;i<svoboda;i++){
                t=t*x;
                factor=factor*factor++;
                P0=P0+t/factor;
            }
            y=540-P0/1000000;
            xold=x;
            yold=y;
            g.drawOval(xold/n,yold/n,o,o);
            g.fillOval(xold/n,yold/n,o,o);
            P0=1;
            t=1;

        }
    }

    public void destroy(Graphics g){
        g.setColor(getBackground());
        g.fillRect(0,0,getSize().width,getSize().height);

    }


    public void paint(Graphics g){
    }

    public class BL implements ActionListener{
        int i;
        String k   = "";
        String k1  = k;
        Integer i1 = new Integer(i);
        String s   = new String();

        public void actionPerformed(final ActionEvent e){
            if(((Button)e.getSource()) == btn2){
                destroy(getGraphics());
                if(chc1.getSelectedIndex() == 0){
                    paint_f(getGraphics(),1);
                }
                else{
                    paint_f(getGraphics(),2);
                }
            }
            if(((Button)e.getSource()) == btn3){
                destroy(getGraphics());
                if(chc1.getSelectedIndex() == 0){
                    paint_p(getGraphics(),1);
                }
                else{
                    paint_p(getGraphics(),2);
                }
            }
            if(((Button)e.getSource()) == btn4){
                destroy(getGraphics());
                if(chc1.getSelectedIndex() == 0){
                    paint_e(getGraphics(),2,300000,1,800);
                    paint_e(getGraphics(),3,300000,1,800);
                    paint_e(getGraphics(),4,300000,1,240);
                    paint_e(getGraphics(),5,300000,1,60);
                }
                else{
                    paint_e(getGraphics(),2,300000,2,800);
                    paint_e(getGraphics(),3,300000,2,800);
                    paint_e(getGraphics(),4,300000,2,240);
                    paint_e(getGraphics(),5,300000,2,60);
                }
            }
        }
    }
}