/*
The JOGL Class is where the JPanel that will display the constructed images are created. The class also has a mouse
click event handler that pauses the animation when the user clicks on it. The actual location of the 3D objects and
other things such as their scale are determined here.

This is based off the JoglStarter.java file that was provided as part of the week 4 materials
 */

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLJPanel;

public class JOGL extends JPanel implements
        GLEventListener, KeyListener, MouseListener, MouseMotionListener, ActionListener {

    private final GLJPanel display;
    public Timer animationTimer;
    public int frameNumber = 0;

    public JOGL() {
        GLCapabilities caps = new GLCapabilities(null);
        display = new GLJPanel(caps);
        display.setPreferredSize( new Dimension(800,800) );
        display.addGLEventListener(this);
        setLayout(new BorderLayout());
        add(display,BorderLayout.CENTER);
        display.addMouseListener(this);
        startAnimation();
    }

    public void display(GLAutoDrawable drawable) {

        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl2.glLoadIdentity();

        gl2.glPushMatrix();
        gl2.glLineWidth(2);
        gl2.glTranslated(0.0, 2 * Math.cos(.05 * frameNumber), 0.0);
        gl2.glTranslated(-6.0, 5.0, 0);
        gl2.glRotated(30, 0, 0, 0);
        gl2.glScaled(1, 1, 1);
        gl2.glRotated(-frameNumber * 0.7, 1, 0, 0);
        //too few slices and the cylinder will not appear round
        Cylinder cylinder = new Cylinder(gl2, 1, 2, 25, 1, 1);
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glLineWidth(1);
        gl2.glTranslated(0, 5.0, 0);
        gl2.glRotated(45, 1.1, -1.5, 2);
        gl2.glScaled(1.5, 1.5, 1.5);
        gl2.glRotated(-frameNumber * 0.9, 1, 0, 0);
        Cube cube = new Cube(gl2);
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glTranslated(6.0, 5.0, 0.0);
        gl2.glRotated(45, 1.5, -2, 3);
        gl2.glScaled(.5, .5, .5);
        gl2.glRotated(-frameNumber * 0.5, 0, 1, 0);
        Dodecahedron dodecahedron = new Dodecahedron(gl2);
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glLineWidth(2);
        gl2.glTranslated(2 * Math.cos(.05 * frameNumber), 0.0, 0.0);
        gl2.glTranslated(6, -5, 0);
        gl2.glRotated(-frameNumber * 0.7, 0, 1, 0);
        Rectangle rectangle = new Rectangle(gl2);
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glLineWidth(3);
        gl2.glTranslated(-6.0, -5.0, 0);
        gl2.glRotated(20, 1.5, -2, 3);
        gl2.glScaled(2 * Math.cos(.05 * frameNumber), 2 * Math.cos(.05 * frameNumber),
                2 * Math.cos(.05 * frameNumber));
        gl2.glRotated(-frameNumber * 0.7, 1, 0, 0);
        AbstractShape abstractShape = new AbstractShape(gl2);
        gl2.glPopMatrix();

        gl2.glPushMatrix();
        gl2.glLineWidth(2);
        gl2.glTranslated(0.0, -5.0, 0);
        gl2.glRotated(90, 1.5, 2, -3);
        gl2.glScaled(1.5, 1.5, 1.5);
        gl2.glRotated(frameNumber * 0.7, 0, 0, 1);
        Triangle triangle = new Triangle(gl2);
        //cycling the triangle through various colors
        if (triangle.getColorValue1() < 1) {
            triangle.setColorValue1(triangle.getColorValue1() + .01f);
        } else {
            triangle.setColorValue1(0.0f);
        }
        if (triangle.getColorValue2() < 1) {
            triangle.setColorValue2(triangle.getColorValue2() + .02f);
        } else {
            triangle.setColorValue2(0.0f);
        }
        if (triangle.getColorValue3() < 1) {
            triangle.setColorValue3(triangle.getColorValue3() + .03f);
        } else {
            triangle.setColorValue3(0.0f);
        }
        gl2.glPopMatrix();
    }

    public void init(GLAutoDrawable drawable) {
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glClearColor(0.3F, 0.3F, 0.3F, 1.0F);
        gl2.glEnable(GL2.GL_DEPTH_TEST);
        gl2.glLineWidth(2);
        gl2.glShadeModel(GL2.GL_SMOOTH);
        gl2.glEnable(GL2.GL_COLOR_MATERIAL);

        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();
        gl2.glOrtho(-10, 10, -10, 10, -5, 5);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);

        gl2.glClearDepthf(1.0f);
        gl2.glDepthFunc(GL2.GL_LEQUAL);
        gl2.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        gl2.glEnable(GL2.GL_BLEND);
        gl2.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        gl2.glEnable(GL2.GL_LIGHTING);
        gl2.glEnable(GL2.GL_LIGHT0);
        gl2.glEnable(GL2.GL_NORMALIZE);
        gl2.glEnable(GL2.GL_COLOR_MATERIAL);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //not needed since window is not resizable
    }

    public void dispose(GLAutoDrawable drawable) {
        //not needed
    }

    public void keyPressed(KeyEvent e) {
        //not needed
    }

    public void keyTyped(KeyEvent e) {
        //not needed
    }

    public void keyReleased(KeyEvent e) {
        //not needed
    }

    private boolean animating;

    private void updateFrame() {
        frameNumber++;
    }

    public void startAnimation() {
        if ( ! animating ) {
            if (animationTimer == null) {
                animationTimer = new Timer(30, this);
            }
            animationTimer.start();
            animating = true;
        }
    }

    public void pauseAnimation() {
        if (animating) {
            animationTimer.stop();
            animating = false;
        }
    }

    public void actionPerformed(ActionEvent evt) {
        updateFrame();
        display.repaint();
    }

    public void mousePressed(MouseEvent evt) {
        if (animating){
            pauseAnimation();
        }else{
            startAnimation();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //not needed
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //not needed
    }

    public void mouseMoved(MouseEvent evt) { }
    public void mouseClicked(MouseEvent evt) { }
    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }

}
