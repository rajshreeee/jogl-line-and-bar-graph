package rrassi2;
import java.awt.Frame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Math;



class thirds implements GLEventListener {
/**
 * Interface to the GLU library.
 */
private GLU glu;

int[] xlist = new int[]{50,100,120 }; 
int[] ylist = new int[]{50,100,120 }; 

public void init(GLAutoDrawable gld) {
    GL2 gl = gld.getGL().getGL2();
    glu = new GLU();

    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    gl.glViewport(-250, -150, 250, 150);
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    
    
}


public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();

    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    /*
     * put your code here
     */
   
    for (int i = 0; i < xlist.length-1; i++){//array index starts from 0 in java
    drawLine(gl, xlist[i], ylist[i], xlist[i+1], ylist[i+1]);}
    
    drawLine(gl, 0,0, 250,0);//drawing x axis
    drawLine(gl, 0,0, 0,200);//drawing y axis
}

public void reshape(GLAutoDrawable drawable, int x, int y, int width,
        int height) {
}

public void displayChanged(GLAutoDrawable drawable,
        boolean modeChanged, boolean deviceChanged) {
}

private void drawLine(GL2 gl, int x1, int y1, int x2, int y2) {
	int dx = Math.abs(x2-x1);
	int dy =Math.abs(y2-y1);
	int p = 2*dy-dx;
	
	int twoDy = 2*dy;
	int twoDyDx = 2*(dy - dx ) ;
	int x, y, xEnd;
	// ' Determine which point to use a s start, which as end * /
	if(x1 > x2){
	x = x2;
	y = y2;
	xEnd = x1 ;
	}
	 else {
	x = x1;
	y= y1;
	xEnd = x2;
	 }
	plot (gl,x, y);
	while (x < xEnd) {
	x++;
	
	if (p < 0 )
p += twoDy;
else {
y++ ;
p+= twoDyDx;
}
plot ( gl,x , y);
	}
    
}
private void plot(GL2 gl, int x, int y) {
	// TODO Auto-generated method stub
	 gl.glBegin(GL2.GL_POINTS);

 	 gl.glVertex2f(x, y);
 	 
 	    gl.glEnd();
}


public void dispose(GLAutoDrawable arg0)
{
	
}
}
public class BLA
{
public static void main(String args[])
{
	//getting the capabilities object of GL2 profile
	final GLProfile profile=GLProfile.get(GLProfile.GL2);
	GLCapabilities capabilities=new GLCapabilities(profile);
	// The canvas
	final GLCanvas glcanvas=new GLCanvas(capabilities);
	thirds b=new thirds();
	glcanvas.addGLEventListener(b);
	glcanvas.setSize(400, 400);
	//creating frame
	final Frame frame=new Frame("Basic frame");
	//adding canvas to frame
	frame.add(glcanvas);
	frame.setSize(640,480);
	frame.setVisible(true);
	  frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}});
	 
	      
	
}}




