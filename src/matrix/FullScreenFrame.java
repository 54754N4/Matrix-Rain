package matrix;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class FullScreenFrame extends JFrame implements MouseListener, MouseMotionListener, KeyListener {
	private static final long serialVersionUID = 8633577421804337082L;
	public static final long DEFAULT_FRAME_DURATION = 10;
	private Integer ix, iy; 		// initial mouse position
	private long frameDuration;
	
	public FullScreenFrame(String title) {
		super(title);
		frameDuration = DEFAULT_FRAME_DURATION;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setAlwaysOnTop(true);
		setResizable(false);
		setBackground(new Color(0,0,0,0));
		Rectangle bounds = getMaxBounds();
		setBounds(bounds);
		setMaximizedBounds(bounds);
		setSize(getMaxDimension());
		setContentPane(new DrawingPanel());
		addMouseListener(this);
		addKeyListener(this);
		addMouseMotionListener(this);
		validate();
	}
	
	protected abstract void render(Graphics2D g);
	
	public void setFrameDuration(long ms) {
		frameDuration = ms;
	}
	
	public Rectangle getMaxBounds() {
		return new Rectangle(ScreenArea.LEFT, ScreenArea.TOP, ScreenArea.MAX_WIDTH, ScreenArea.MAX_HEIGHT);
	}
	
	public Dimension getMaxDimension() {
		return new Dimension(ScreenArea.TOTAL_WIDTH, ScreenArea.TOTAL_HEIGHT);
	}

	public void delay(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public void mouseClicked(MouseEvent e) {
		System.exit(0);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (ix == null && iy == null) {
			ix = e.getX();
			iy = e.getY();
			return;
		} else if (e.getX() != ix || e.getY() != iy)
			if (Config.INSTANCE.mouseExit == 1)
				System.exit(0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (Config.INSTANCE.keyboardExit == 1)
			System.exit(0);
	}

	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mouseDragged(MouseEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}
	
	private final class DrawingPanel extends JPanel {
		private static final long serialVersionUID = -5719760888399028189L;

		public DrawingPanel() {
			setSize(getMaxDimension());
			setBounds(getMaxBounds());
			setOpaque(false);
			setBackground(new Color(0,0,0,0));
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			render(g2);
			g.dispose();
			delay(frameDuration);
			repaint();
		}
	}
}