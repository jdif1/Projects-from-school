/*
This class contains the parameters for the 3D triangle
 */

import com.jogamp.opengl.GL2;

public class Triangle {
    private static float colorValue1 = 0f;
    private static float colorValue2 = 0f;
    private static float colorValue3 = 0f;

    public Triangle(GL2 gl2) {
        gl2.glBegin(GL2.GL_TRIANGLES);
        gl2.glColor3f(colorValue1, colorValue2, colorValue3);
        gl2.glVertex3f(0f, 1.5f, 0.0f);

        gl2.glColor3f(colorValue1, colorValue2, colorValue3);
        gl2.glVertex3f(-.5f, -.5f, .5f);

        gl2.glColor3f(colorValue1, colorValue2, colorValue3);
        gl2.glVertex3f(.5f, -.5f, .5f);

        gl2.glColor3f(colorValue1, colorValue2, colorValue3);
        gl2.glVertex3f(0f, 1.5f, 0.0f);

        gl2.glColor3f(colorValue1, colorValue2, colorValue3);
        gl2.glVertex3f(.5f, -.5f, .5f);

        gl2.glColor3f(colorValue1, colorValue2, colorValue3);
        gl2.glVertex3f(.5f, -.5f, -.5f);

        gl2.glColor3f(colorValue1, colorValue2, colorValue3);
        gl2.glVertex3f(0f, 1.5f, 0.0f);

        gl2.glColor3f(colorValue1, colorValue2, colorValue3);
        gl2.glVertex3f(.5f, -.5f, -.5f);

        gl2.glColor3f(colorValue1, colorValue2, colorValue3);
        gl2.glVertex3f(-.5f, -.5f, -.5f);

        gl2.glColor3f(colorValue1, colorValue2, colorValue3);
        gl2.glVertex3f(0f, 1.5f, 0.0f);

        gl2.glColor3f(colorValue1, colorValue2, colorValue3);
        gl2.glVertex3f(-.5f, -.5f, -.5f);

        gl2.glColor3f(colorValue1, colorValue2, colorValue3);
        gl2.glVertex3f(-.5f, -.5f, .5f);

        gl2.glEnd();
        gl2.glFlush();
    }

    public float getColorValue1() {
        return colorValue1;
    }

    public float getColorValue2() {
        return colorValue2;
    }

    public float getColorValue3() {
        return colorValue3;
    }

    public void setColorValue1(float colorValue1) {
        Triangle.colorValue1 = colorValue1;
    }

    public void setColorValue2(float colorValue2) {
        Triangle.colorValue2 = colorValue2;
    }

    public void setColorValue3(float colorValue3) {
        Triangle.colorValue3 = colorValue3;
    }
}
