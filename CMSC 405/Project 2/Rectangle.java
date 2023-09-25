/*
This class contains the parameters for the 3D rectangle
 */


import com.jogamp.opengl.GL2;

public class Rectangle {
    public  Rectangle(GL2 gl2) {
        gl2.glBegin(GL2.GL_QUADS);
        gl2.glColor3f(0f, 0f, 0f);
        gl2.glVertex3f(0.5f, 1f, -0.5f);
        gl2.glVertex3f(-0.5f, 1f, -0.5f);
        gl2.glVertex3f(-0.5f, 1f, 0.5f);
        gl2.glVertex3f(0.5f, 1f, 0.5f);

        gl2.glColor3f(1f, 1f, 1f);
        gl2.glVertex3f(0.5f, -1f, 0.5f);
        gl2.glVertex3f(-0.5f, -1f, 0.5f);
        gl2.glVertex3f(-0.5f, -1f, -0.5f);
        gl2.glVertex3f(0.5f, -1f, -0.5f);

        gl2.glColor3f(0f, 0f, 0f);
        gl2.glVertex3f(0.5f, 1f, 0.5f);
        gl2.glVertex3f(-0.5f, 1f, 0.5f);
        gl2.glVertex3f(-0.5f, -1f, 0.5f);
        gl2.glVertex3f(0.5f, -1f, 0.5f);

        gl2.glColor3f(1f, 1f, 1f);
        gl2.glVertex3f(0.5f, -1f, -0.5f);
        gl2.glVertex3f(-0.5f, -1f, -0.5f);
        gl2.glVertex3f(-0.5f, 1f, -0.5f);
        gl2.glVertex3f(0.5f, 1f, -0.5f);

        gl2.glColor3f(0f, 0f, 0f);
        gl2.glVertex3f(-0.5f, 1f, 0.5f);
        gl2.glVertex3f(-0.5f, 1f, -0.5f);
        gl2.glVertex3f(-0.5f, -1f, -0.5f);
        gl2.glVertex3f(-0.5f, -1f, 0.5f);

        gl2.glColor3f(1f, 1f, 1f);
        gl2.glVertex3f(0.5f, 1f, -0.5f);
        gl2.glVertex3f(0.5f, 1f, 0.5f);
        gl2.glVertex3f(0.5f, -1f, 0.5f);
        gl2.glVertex3f(0.5f, -1f, -0.5f);

        gl2.glEnd();
        gl2.glFlush();
    }
}
