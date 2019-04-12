package RainbowWindow;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
public class Main {
	enum RGB{red, green, blue};
	public static void main(String[] args) {
		int color =  0;
		boolean add = true;
		RGB colorToChange = RGB.red;
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setBackground(new Color(display, color, 0, 0));
		shell.setSize(200,200);
		shell.open();
		while(true) {
			for(int i = 0; i < 255; i++) {
				if(add == true) color++;
				else color--;
				if(colorToChange == RGB.red) {
					shell.setBackground(new Color(display, color, 0, 0));
				}
				else if(colorToChange == RGB.green) {
					shell.setBackground(new Color(display, 0, color, 0));
				}
				else if(colorToChange == RGB.blue) {
					shell.setBackground(new Color(display, 0, 0, color));
				}
				shell.open();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(!display.readAndDispatch()) display.sleep();
			}
			if(add == true) add = false;
			else {
				add = true;
				if(colorToChange == RGB.red) colorToChange = RGB.green;
				else if (colorToChange == RGB.green) colorToChange = RGB.blue;
				else if (colorToChange == RGB.blue) colorToChange = RGB.red;
			}
		}
	}
}