package CoffeeClicker;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
public class Click {
	static int coffeeMade = 0;
	static int autoBrewerCount = 0;
	static int autoBrewerPrice = 20;
	static int ultraBrewerPrice = 200;
	static int ultraBrewerCount = 0;
	static int beanPrice = 15;
	static int coffeeBeans = 100;
	static boolean autoBuyer = false;
	public static void main(String[] args) {
		Timer tiktok = new Timer();
		Timer beanPriceChanger = new Timer();
		tiktok.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(coffeeBeans >= autoBrewerCount + ultraBrewerCount * 5) {
					coffeeMade = coffeeMade + autoBrewerCount;
					coffeeMade = coffeeMade + ultraBrewerCount  * 5;
					coffeeBeans = coffeeBeans - (autoBrewerCount + ultraBrewerCount * 5);
				}
				else if(autoBuyer == true) {
					if(coffeeMade >= 10) {
						coffeeBeans = coffeeBeans + 100;
						coffeeMade = coffeeMade - beanPrice;
					}
				}
			}
		}, 1000, 1000);
		beanPriceChanger.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				beanPrice = ThreadLocalRandom.current().nextInt(10, 30);
			}
		}, 10000, 10000);
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		Label score = new Label(shell, 0);
		Label beans = new Label(shell, 0);
		Label autocount = new Label(shell, 0);
		Label ultracount = new Label(shell, 0);
		autocount.setText("AutoBrewer Count: " + autoBrewerCount);
		ultracount.setText("UltraBrewer Count: "+ ultraBrewerCount);
		beans.setText("Coffee Beans: " + coffeeBeans);
		Button brew = new Button(shell, SWT.PUSH);
		Button buyBeans = new Button(shell, SWT.PUSH);
		Button autobrewer = new Button(shell, SWT.PUSH);
		autobrewer.setText("AutoBrewer ("+ autoBrewerPrice + ")");
		autobrewer.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(coffeeMade >= autoBrewerPrice) {
					autoBrewerCount++;
					coffeeMade = coffeeMade - autoBrewerPrice;
					autoBrewerPrice = autoBrewerPrice + 10;
					autocount.setSize(200, autocount.getSize().y);
					autocount.setText("AutoBrewer Count: " + autoBrewerCount);
					autobrewer.setText("AutoBrewer ("+ autoBrewerPrice + ")");
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		brew.setText("Make some coffee");
		brew.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(coffeeBeans >= 1) {
					coffeeMade++;
					coffeeBeans--;
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		Button ultrabrewer = new Button(shell, SWT.PUSH);
		ultrabrewer.setText("UltraBrewer (" + ultraBrewerPrice + ")");
		ultrabrewer.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(coffeeMade  >= ultraBrewerPrice) {
					ultraBrewerCount++;
					coffeeMade = coffeeMade - ultraBrewerPrice;
					ultraBrewerPrice = ultraBrewerPrice + 50;
					ultrabrewer.setText("UltraBrewer (" + ultraBrewerPrice + ")");
					ultracount.setSize(200, ultracount.getSize().y);
					ultracount.setText("UltraBrewer Count: "+ ultraBrewerCount);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		Button buyerSwitch = new Button(shell, SWT.PUSH);
		buyerSwitch.setText("Turn On AutoBuyer (500)");
		buyerSwitch.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(coffeeMade >= 500 && autoBuyer == false) {
					autoBuyer = true;
					coffeeMade = coffeeMade - 500;
					buyerSwitch.setText("Turn Off AutoBuyer (0)");
				}
				else if(autoBuyer == true) {
					autoBuyer = false;
					buyerSwitch.setText("Turn On AutoBuyer (500)");
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		buyBeans.setText("Buy 100 Coffee Beans (" + beanPrice + ")");
		buyBeans.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(coffeeMade >= 10) {
					coffeeBeans = coffeeBeans + 100;
					coffeeMade = coffeeMade - beanPrice;
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		score.setText("Coffee Made: " + coffeeMade);
		shell.setSize(200,300);
		shell.open();
		autobrewer.setSize(150, autobrewer.getSize().y);
		ultrabrewer.setSize(150, ultrabrewer.getSize().y);
		while(!shell.isDisposed()) {
			score.setSize(200, score.getSize().y);
			score.setText("Coffee Made: " + coffeeMade);
			beans.setSize(200, beans.getSize().y);
			beans.setText("Coffee Beans: " + coffeeBeans);
			if(!display.readAndDispatch()) display.sleep();
		}
		tiktok.cancel();
	}
}