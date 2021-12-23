package matrix;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.SwingUtilities;

public class MatrixRain extends FullScreenFrame {
	private static final long serialVersionUID = 6341276691527129471L;
	private static final Random random = new Random(1);
	
	private int fontSize;
	private Font font;
	private Drop[] drops;
	
	public MatrixRain() {
		super("Matrix Screen Saver");
		fontSize = Config.INSTANCE.fontSize;
		font = new Font("Monospaced", Font.PLAIN, fontSize);
		drops = new Drop[ScreenArea.TOTAL_WIDTH/fontSize];
		for (int i=0; i<drops.length; i++) 
			drops[i] = new Drop(i, fontSize);
		setVisible(true);
	}

	@Override
	protected void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ScreenArea.TOTAL_WIDTH, ScreenArea.TOTAL_HEIGHT);
		g.setColor(Color.GREEN);
		g.setFont(font);
		for (int i=0; i<drops.length; i++)
			drops[i].draw(g);
	}
	
	public static void start() {
		SwingUtilities.invokeLater(() -> new MatrixRain());
	}
	
	private static final class Drop {
		private static final Color HIGHLIGHT = new Color(
				Config.INSTANCE.highlightRed,
				Config.INSTANCE.highlightGreen,
				Config.INSTANCE.highlightBlue
		);
		private static Map<Integer, Color> COLOR_CACHE = new HashMap<>();
		
		private char[] text;
		private int x, y, velocity, length, alphaIncrement, fontSize;
		
		public Drop(int i, int fontSize) {
			this.fontSize = fontSize;
			x = i*fontSize;
			text = Config.ALPHABET.toCharArray();
			shuffle();
			reset();
		}
		
		private int randBetween(int min, int max) {
			return random.nextInt(max-min+1)+min;
		}
		
		private boolean hasOverflowed() {
			return y > ScreenArea.TOTAL_HEIGHT;
		}
		
		private void shuffle() {
			for (int i=0, x, y; i<text.length; i++) {
				x = random.nextInt(Config.ALPHABET.length());
				y = random.nextInt(Config.ALPHABET.length());
				swap(x, y);
			}
		}
		
		private void swap(int x, int y) {
			char temp = text[x];
			text[x] = text[y];
			text[y] = temp;
		}
		
		private void reset() {
			length = random.nextInt(Config.ALPHABET.length()-1)+1;	// avoid 0;
			alphaIncrement = 255/length;
			if (alphaIncrement == 0)
				alphaIncrement = 1;
			y = -length*fontSize;
			velocity = randBetween(Config.INSTANCE.velocityMin, Config.INSTANCE.velocityMax);
		}
		
		private static Color transparent(int alpha) {
			Color match = COLOR_CACHE.get(alpha);
			if (match != null)
				return match;
			match = new Color(
					Config.INSTANCE.colorRed, 
					Config.INSTANCE.colorGreen, 
					Config.INSTANCE.colorBlue, 
					alpha
			);
			COLOR_CACHE.put(alpha, match);
			return match;
		}
		
		public void draw(Graphics2D g) {
			if (hasOverflowed() && random.nextDouble() > Config.INSTANCE.rainResetChance)
				reset();
			for (int i=0; i<length; i++) {
				if (i == length - 1)
					g.setColor(HIGHLIGHT);
				else
					g.setColor(transparent(alphaIncrement*i));
				g.drawChars(text, i, 1, x, y+i*fontSize);
			}
			y += velocity;
		}
	}
}