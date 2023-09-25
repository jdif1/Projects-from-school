/*
This class contains the code necessary to make a 3D cylinder based on the parameters given when a Cylinder object is
created.
 */


import com.jogamp.opengl.GL2;

public class Cylinder {
    public Cylinder(GL2 gl2, double radius, double height, int slices, int stacks, int rings) {
        for (int j = 0; j < stacks; j++) {
            gl2.glBegin(GL2.GL_QUAD_STRIP);
            for (int i = 0; i <= slices; i++) {
                double longitude = (2 * Math.PI / slices) * i;
                gl2.glNormal3d(Math.cos(longitude), Math.sin(longitude), 0);
                gl2.glColor3f(1.0f, 0.0f, 0.0f);
                gl2.glVertex3d(radius * Math.cos(longitude), radius *
                        Math.sin(longitude), (height / stacks) * (j + 1));
                gl2.glVertex3d(radius * Math.cos(longitude), radius * Math.sin(longitude),
                        (height / stacks) * j);
            }
            gl2.glEnd();
        }
        if (rings > 0) {
            gl2.glNormal3d(0, 0, 1);
            for (int j = 0; j < rings; j++) {
                gl2.glBegin(GL2.GL_QUAD_STRIP);
                for (int i = 0; i <= slices; i++) {
                    double angle = (2 * Math.PI / slices) * i;
                    gl2.glColor3f(0.0f, 0.0f, 0.0f);
                    gl2.glVertex3d(radius * Math.cos(angle) * ((1.0 / rings) * j), radius * Math.sin(angle)
                            * (1.0 / rings) * j, height);
                    gl2.glVertex3d(radius * Math.cos(angle) * ((1.0 / rings) * (j + 1)), radius
                            * Math.sin(angle) * (1.0 / rings) * (j + 1), height);
                }
                gl2.glEnd();
            }

            gl2.glNormal3d(0, 0, -1);
            for (int j = 0; j < rings; j++) {
                double d1 = (1.0 / rings) * j;
                double d2 = (1.0 / rings) * (j + 1);
                gl2.glBegin(GL2.GL_QUAD_STRIP);
                for (int i = 0; i <= slices; i++) {
                    double angle = (2 * Math.PI / slices) * i;
                    double sin = Math.sin(angle);
                    double cos = Math.cos(angle);

                    gl2.glColor3f(0.0f, 0.0f, 0.0f); // Blue
                    gl2.glVertex3d(radius * cos * d2, radius * sin * d2, 0);
                    gl2.glVertex3d(radius * cos * d1, radius * sin * d1, 0);
                }
                gl2.glEnd();
            }
        }
        gl2.glFlush();

    }
}
