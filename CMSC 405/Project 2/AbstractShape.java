/*
This class contains the parameters for the 3D abstract shape
 */


import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

public class AbstractShape {
    public AbstractShape(GL2 gl2){
        gl2.glBegin(GL.GL_LINE_LOOP);
        gl2.glColor3f(1f, 1f, 1f);
        gl2.glVertex3f(0f, 2f, 0f );

        gl2.glColor3f(1f, 0f, 0f);
        gl2.glVertex3f(-1f, 0f, 1f);

        gl2.glColor3f(0f, 1f, 0f);
        gl2.glVertex3f(1f, 0f, 1f);

        gl2.glColor3f(0f, 0f, 1f);
        gl2.glVertex3f(0f, 0f, -1.4f);

        gl2.glColor3f(1f, 1f, 1f);
        gl2.glVertex3f(0f, 2f, 0f);

        gl2.glColor3f(1f, 0f, 0f);
        gl2.glVertex3f(-1f, 0f, 1f);

        gl2.glEnd();
        gl2.glFlush();
    }
}
