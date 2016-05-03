package source;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
                 * To change this license header, choose License Headers in Project Properties.
                 * To change this template file, choose Tools | Templates
                 * and open the template in the editor.
 */
/**
 *
 * @author Zarir Hamza // DRAW IF AGATES > 17
 */
public class draw extends JPanel {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    static int numofAndGates = 1;
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight() - 60;
    static int numofBus = 1; //change
    static int numofDff = 0;
    static int width2 = 0;
    static String eqn;
    static int height2 = 0;
    static int numofBus2 = 0;
    static ArrayList<String> terms = new ArrayList<>();
    static ArrayList<Integer> size = new ArrayList<>(1);
    static ArrayList<Boolean> one = new ArrayList<>();
    static boolean repaint = true;
    static int point = 0;

    draw(int numofDff, String x, int numofBus2) {
        this.numofDff = numofDff;
        eqn = x;
        this.numofBus2 = numofBus2;
        //draw d = new draw(numofDff, eqn);
        JScrollPane sg = new JScrollPane(this);
        this.setPreferredSize(this.getPreferredSize());
        // sg.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ///sg.setSize(new Dimension(800,800));
        JFrame j = new JFrame();
        j.add(sg);
        // j.setBackground(Color.white);
        j.setResizable(true);
        j.setPreferredSize(new Dimension(400, 400));
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);

        j.setTitle("Circuit");
        j.pack();
        //    j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLocationByPlatform(true);
        j.setVisible(true);
        j.addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {

                repaint = false;
                numofAndGates = 0;
                numofBus = 1; //change
                // numofDff = 1;
                width2 = 0;
                height2 = 0;
                terms.clear();
                point = 0;
                one.clear();
                // eqn= null;
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                //                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void componentShown(ComponentEvent e) {
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    public Component getFrame(){
        return this.getParent();
    }
    public void readString(String x) {
        int drawStart = 1;
        terms.clear();
        size.clear();
        x = x + "+";
        x.replaceAll("\\s", "");

        for (int a = 0; a < x.length() - 1; a++) {
            if (x.charAt(a) == 'D' && Character.isDigit(x.charAt(a + 1))) {
                size.add(drawStart - 1);
                //   System.out.println("The y is: " + x.charAt(a) + x.charAt(a+1));
                a += 1;
            } /*  else if(x.charAt(a)== '='){
                              String y = x.substring(a+1,
                                      x.indexOf(" ", a+1));
                              terms.add(y);
                      drawStart++;
                          a = a + y.length();
                              }*/ else if (Character.isAlphabetic(x.charAt(a))) {
                String y = x.substring(a,
                        x.indexOf("+", a + 1));
                a = a + y.length();
                if (a + 1 == x.length()) {
                    size.add(drawStart);
                }
                if (y.length() == 1) {
                    //      one.add();
                }
                drawStart++;
                terms.add(y);

            } else if (x.charAt(a) == '!') {
                String y = x.substring(a,
                        x.indexOf("+", a + 1));
                a = a + y.length();
                if (a + 1 == x.length()) {
                    size.add(drawStart);
                }
                drawStart++;
                terms.add(y);
            }
        }
        numofAndGates = drawStart;
        //    size.add(numofAndGates-2);
        for (int b = 0; b < terms.size(); b++) {
            //    System.out.println(terms.get(b));
        }
    }
    //extra or gate for no reason

    @Override
    public void paintComponent(Graphics g) {
        //  if(repaint == false){
        //   System.out.println(numofDff);
        super.paintComponent(g);
        //FIX R GATE
        // = 175/5; // change 7 for now
        //  numofDff = 4;
        drawBus(numofBus2, g);
        //  drawCircuit(g,"D1 =ABC + !A!B!C ");
        //    drawCircuit(g,"D3 =AB + !A!B + A!B + !A!B D4 =AB + !A!B + A!B + !A!B ");
        // System.out.println("zarir ahzma" + numofAndGates);
        drawCircuit(g, eqn);
        //   System.out.println(eqn+ "this is t");
        drawDff(g, numofDff);

        repaint = true;
        // }

    }

    public void drawCircuit(Graphics g, String y) {
        //  terms.clear();
        int point2 = 0;
        String x = y;
        readString(x);

        for (int c = 0; c < size.size(); c++) {

            //    System.out.println(size.get(c) + " " + c + " sadasdsa");
        }
        for (int a = 0; a < terms.size(); a++) {
            drawAndGate(terms.get(a), g, a);
        }
        //  drawOrGate(g,0,size.get(0),0);

        int numofFF = 0;
        for (int b = 1; b < size.size(); b++) {
///
            //    System.out.println(size.get(b) + " " + b + " sadasdsa");
            //           System.out.println("this is the event" + (point2 - size.get(b)) + " " + point2 + " " + size.get(b) + size.size());
            if (point2 - size.get(b) == 0) {
                int sizeofY = (height - 10) / numofDff + 1;
                int sizeofX = width - (numofBus * 40) - 200;
                int midNum = point2;
                g.drawLine(440, 50 + (((numofFF + 1) * 40) + 20), 600 + numofFF * 10, 50 + ((numofFF + 1) * 40) + 20);
                g.drawLine(600 + numofFF * 10, 50 + ((numofFF + 1) * 40) + 20, 600 + numofFF * 10, 85 + (midNum * 40));
                // g.drawLine(550,85 + (midNum*40), 600 + numofFF*10, 85 + (midNum*40)); // move right
                g.drawLine(600 + numofFF * 10, 85 + (midNum * 40), 600 + numofFF * 10, (sizeofY * (numofFF) + 60));//move up
                g.drawLine(600 + numofFF * 10, (sizeofY * (numofFF) + 60), sizeofX, (sizeofY * (numofFF) + 60));
                numofFF++;
            } else if (b + 1 == size.size()) {
                //  if(point2 - size.get(b) != 0){
                drawOrGate(g, point2, size.get(b) - 1, numofFF);
                numofFF++;
                //     }

            } else {

                //    if(point2 - size.get(b) != 0){
                drawOrGate(g, point2, size.get(b) - 1, numofFF);
                numofFF++;
                //   }

                //  System.out.println(size.get(b) + "this is halloween" + numofAndGates);
            }
            point2 = size.get(b);
            for (int xz = 0; xz < size.size(); xz++) {

            }
        }
    }

    public void drawBus(int var, Graphics g) {
        int numofLines = var * 2;
        height2 = (numofAndGates + 1) * 40;
        width2 = 175 / var;
        //  System.out.println(height + "asdasd" + height2);
        for (int a = 1; a <= numofLines; a++) {
            if (a % 2 != 0) {
                g.setColor(Color.BLACK);
                g.drawLine(width2 * a, 20, width2 * a, height2);

                g.setColor(Color.BLACK);
                g.drawString(Character.toString((char) ((a / 2) + 65)), (width2 * a), 10);

            } else {
                g.setColor(Color.BLACK);
                g.fillOval((width2 * a), 15, 7, 7);//first dot

                g.drawLine(width2 * (a - 1), 20, width2 * a, 20);//horizontal line

                g.setColor(Color.BLACK);
                g.drawLine(width2 * a, 20, width2 * a, height2);//blue line

                g.setColor(Color.BLACK);
                g.fillOval((width2 * a), 15, 10, 10);
                g.fillOval((width2 * (a - 1)) - 5, 15, 10, 10);//second dot

                Polygon poly = new Polygon(
                        new int[]{((width2 * (a))), (width2 * (a) + 15), (width2 * (a) - 15)},//make triangle
                        new int[]{60, 30, 30},
                        3);

                Graphics2D g2 = (Graphics2D) g.create();//draw with graphics2d
                g2.setColor(Color.yellow);
                g2.fill(poly);

                g.setColor(Color.yellow); //bubble
                g.fillOval(((width2 * (a)) - 5), 55, 10, 10);

                g.setColor(Color.BLACK);
                g.drawString(Character.toString((char) ((a / 2) + 64)) + "!", (width2 * a), 10);
            }
        }
        numofBus = numofLines;
        this.revalidate();
    }

    public void drawDff(Graphics g, int numofDff) {
        this.numofDff = numofDff;

        int sizeofY = (height - 10) / numofDff + 1;
        int sizeofX = width - (numofBus * 40) - 200; // REPLACE 200 WITH WIDTH OF AND PLUS OR GATE

        for (int x = 1; x <= numofDff; x++) {
            g.setColor(Color.BLACK);
            // System.out.println(sizeofX);
            //   System.out.println(sizeofY);
            g.drawRect(sizeofX, (sizeofY * (x - 1)) + 50, 70, (int) (height / (numofDff + Math.sqrt(numofDff))));
            g.drawString("D", sizeofX + 5, (sizeofY * (x - 1)) + 50 + 20);
            //  g.drawString(">CLK",sizeofX + 5, (sizeofY*(x-1))+ 50 + (int) (height / (numofDff + Math.sqrt(numofDff))) - 10 );

            g.drawString("Q", sizeofX + 55, (sizeofY * (x - 1)) + 50 + 20);
            g.drawString("!Q", sizeofX + 55, (sizeofY * (x - 1)) + 40 + (int) (height / (numofDff + Math.sqrt(numofDff))));
            if (numofAndGates < 17) {
                g.drawLine(sizeofX + 70, (sizeofY * (x - 1)) + 50 + 20, sizeofX + 70 + 20 * x, (sizeofY * (x - 1)) + 50 + 20);//right
                g.drawLine(sizeofX + 70 + 20 * x, (sizeofY * (x - 1)) + 50 + 20, sizeofX + 70 + 20 * x, (sizeofY * (numofDff)) + 10 * x);///down
                if (x == 1) {
                    g.drawLine(sizeofX + 70 + 20 * x, (sizeofY * (numofDff)) + 10 * x, width2 * (x), (sizeofY * (numofDff)) + 10 * x);//left
                    g.drawLine(width2 * (x), (sizeofY * (numofDff)) + 10 * x, width2 * (x), 30);//up
                    //   g.drawString("Q!",sizeofX + 55, (sizeofY*(numofDff-1))+ 50 + (int) (height / (numofDff + Math.sqrt(numofDff))) - 10);

                } else {
                    g.drawLine(sizeofX + 70 + 20 * x, (sizeofY * (numofDff)) + 10 * x, width2 * (x) + width2 * (x - 1), (sizeofY * (numofDff)) + 10 * x);//left
                    g.drawLine(width2 * (x) + width2 * (x - 1), (sizeofY * (numofDff)) + 10 * x, width2 * (x) + width2 * (x - 1), 30);//up
                    //      g.drawString("Q!",sizeofX + 55, (sizeofY*(numofDff-1))+ 50 + (int) (height / (numofDff + Math.sqrt(numofDff))) - 10);

                }

            } else {
                g.drawLine(sizeofX + 70, (sizeofY * (x - 1)) + 50 + 20, sizeofX + 70 + 20 * x, (sizeofY * (x - 1)) + 50 + 20);//right
                g.drawLine(sizeofX + 70 + 20 * x, (sizeofY * (x - 1)) + 50 + 20, sizeofX + 70 + 20 * x, (40 * (numofAndGates)) + 20 * x + 30);///down
                if (x == 1) {
                    g.drawLine(sizeofX + 70 + 20 * x, (40 * (numofAndGates)) + 20 * x + 30, width2 * (x), (40 * (numofAndGates)) + 20 * x + 30);//left
                    g.drawLine(width2 * (x), (40 * (numofAndGates)) + 20 * x + 30, width2 * (x), 30);//up
                    //   g.drawString("Q!",sizeofX + 55, (sizeofY*(numofDff-1))+ 50 + (int) (height / (numofDff + Math.sqrt(numofDff))) - 10);

                } else {
                    g.drawLine(sizeofX + 70 + 20 * x, (40 * (numofAndGates)) + 20 * x + 30, width2 * (x) + width2 * (x - 1), (40 * (numofAndGates)) + 20 * x + 30);//left
                    g.drawLine(width2 * (x) + width2 * (x - 1), (40 * (numofAndGates)) + 20 * x + 30, width2 * (x) + width2 * (x - 1), 30);//up
                    //      g.drawString("Q!",sizeofX + 55, (sizeofY*(numofDff-1))+ 50 + (int) (height / (numofDff + Math.sqrt(numofDff))) - 10);

                }

            }
            //    g.drawString("Q!",sizeofX + 55, (sizeofY*(numofDff-1))+ 50 + (int) (height / (numofDff + Math.sqrt(numofDff))) - 10);

            //   System.out.println("adfjsdhfwopeq;ncjnq" + width2 + "asdasd" + width2 * (numofDff-x));
            this.revalidate();
        }
    }//number

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(width, (numofAndGates + 17) * 40);
    }

    public void drawAndGate(String input, Graphics orGfx2, int numFF) {//num of gate
        JPanel jp = new JPanel();
        Graphics2D orGfx = (Graphics2D) orGfx2;
        orGfx.setColor(Color.black);
        if (input.length() > 1) {
            orGfx.drawLine(400, 50 + (numFF * 40), 400, 80 + (numFF * 40));
            orGfx.drawLine(400, 50 + (numFF * 40), 420, 50 + (numFF * 40));
            orGfx.drawLine(400, 80 + (numFF * 40), 420, 80 + (numFF * 40));
            orGfx.drawArc(400, 50 + (numFF * 40), 40, 30, 270, 180);
            orGfx.drawString(input, 405, 75 + (numFF * 40));
        } else {
            orGfx.drawLine(400, 50 + (numFF * 40) + 20, 440, 50 + (numFF * 40) + 20);
            orGfx.drawString(input, 430, 65 + (numFF * 40));
        }
        orGfx.drawLine(400, 50 + (numFF * 40) + 20, 0, 50 + ((numFF * 40) + 20));

        for (int a = 0; a < input.length(); a++) {
            if (input.charAt(a) == '!') {
                //      orGfx.clearRect((width2 * 2*((int)input.charAt(a+1) - 64) - 5),50,10,10);
                orGfx.fillOval((width2 * 2 * ((int) input.charAt(a + 1) - 64) - 5), 50 + (numFF * 40) + 15, 10, 10);
                //    System.out.println((int)input.charAt(a+1) - 64);
                a = a + 1;
            } else if (input.charAt(a) == 'A') {
                orGfx.fillOval((width2 * ((int) input.charAt(a) - 64) - 5), 50 + (numFF * 40) + 15, 10, 10);
            } else {

                //                        orGfx.clearRect((width2 * 2*((int)input.charAt(a+1) - 64) - 5),50,10,10);
                orGfx.fillOval(width2 * ((int) input.charAt(a) - 65) + ((width2 * ((int) input.charAt(a) - 64)) - 5), 50 + (numFF * 40) + 15, 10, 10);
                //     System.out.println("sadassadasdasd" + (width2 * (int)input.charAt(a) - 65) + ((width2 *((int)input.charAt(a) - 64)) - 5));
            }

        }
        this.revalidate();
        this.repaint();

    }//numff = numofff

    public void drawOrGate(Graphics g, int begin, int end, int numofFF) {
        //     System.out.println(begin + "sadasd");

        int sizeofY = (height - 10) / numofDff + 1;
        int sizeofX = width - (numofBus * 40) - 200;
        int midNum = (end - begin) / 2 + begin;
        g.drawLine(500, 50 + (midNum * 40), 520, 50 + (midNum * 40));
        g.drawLine(500, 100 + (midNum * 40), 520, 100 + (midNum * 40));
        g.drawArc(480, 50 + (midNum * 40), 30, 50, 270, 180);
        g.drawArc(480, 50 + (midNum * 40), 70, 50, 270, 180);
        for (int a = begin; a <= end; a++) {
            //   System.out.println("This is the thinge i: " + end + " " + begin + " " + (end-begin+1));

            g.drawLine(510, 55 + (midNum * 40) + (40 / (end - begin + 1)) * (a - begin + 1), 450 + (30 / (end - begin + 1)) * (a - begin), 55 + (midNum * 40) + (40 / (end - begin + 1)) * (a - begin + 1));//right
            g.drawLine(450 + (30 / (end - begin + 1)) * (a - begin), 55 + (midNum * 40) + (40 / (end - begin + 1)) * (a - begin + 1), 450 + (30 / (end - begin + 1)) * (a - begin), 70 + ((a) * 40));//up
            // System.out.println(450 + (30/(end-begin))*(a-begin) + " this is it" + end + " " + begin + " " + (30/(end-begin)) + " " + (a-begin) + " " + (end-begin));
            g.drawLine(450 + (30 / (end - begin + 1)) * (a - begin), 70 + ((a) * 40), 440, 70 + ((a) * 40));//right
        }
        g.drawLine(550, 85 + (midNum * 40), 600 + numofFF * 10, 85 + (midNum * 40)); // move right
        g.drawLine(600 + numofFF * 10, 85 + (midNum * 40), 600 + numofFF * 10, (sizeofY * (numofFF) + 60));//move up
        g.drawLine(600 + numofFF * 10, (sizeofY * (numofFF) + 60), sizeofX, (sizeofY * (numofFF) + 60));
    }

    // public static void main(String[] args) {
    // }
}
