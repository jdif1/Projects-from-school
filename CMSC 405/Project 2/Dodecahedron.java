/*
This class contains the parameters for the 3D dodecahedron
 */

import com.jogamp.opengl.GL2;

public class Dodecahedron {
    public Dodecahedron(GL2 gl2){
        double[][] vertices = new double[][]{
                { 2, -2, 2 }, { 2, -2, -2 }, { 2, 1, -2 }, { 2, 1, 2 }, { 1.5, 5.0, 0 }, { -1.5, 5.0, 0 },
                { -2, -2, 2 }, { -2, 1, 2 }, { -2, 1, -2 }, { -2, -2, -2 }, { 1.5, -5.0, 0 }, { -1.5, -5.0, 0 },
        };
        int[][] faces = new int[][] {
                { 0, 1, 2, 3 }, { 3, 2, 4 }, { 7, 3, 4, 5 }, { 2, 8, 5, 4 }, { 5, 8, 7 }, { 0, 3, 7, 6 },
                { 2, 1, 9, 8 }, { 6, 7, 8, 9 }, { 0, 1, 10 }, { 6, 9, 11 }, { 6, 0, 10, 11 }, { 9, 1, 10, 11 },
        };
        double[][] colors = new double[][] {
                {0,0,0}, {0,0,1}, {0,1,1}, {1,1,1}, {1,1,0}, {1,0,0},
                {0,0,0}, {0,0,1}, {0,1,1}, {1,1,1}, {1,1,0}, {1,0,0},
        };
        gl2.glPushMatrix();

        for (int i = 0; i < faces.length; i++)
        {
            gl2.glColor3dv(colors[i], 0 );

            gl2.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < faces[i].length; j++)
            {
                gl2.glVertex3dv( vertices[faces[i][j]], 0 );
            }
            gl2.glEnd();
        }
        for (int i = 0; i < faces.length; i++)
        {
            gl2.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < faces[i].length; j++)
            {
                gl2.glVertex3dv( vertices[faces[i][j]], 0 );
            }
            gl2.glEnd();
        }
        gl2.glPopMatrix();
        gl2.glFlush();
    }
}

