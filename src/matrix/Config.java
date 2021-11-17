package matrix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
	public static final String FILE_NAME = "properties.conf";
	public static final Config INSTANCE;
	public static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОавгдеёжзийклмноПРСТУФХЦЧШЩЪЫЬЭЮЯпрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=[]}{|\\'\":;?/>.<,~`";;
	
	public final int fontSize, 
		velocityMin, velocityMax,
		colorRed, colorGreen, colorBlue,
		highlightRed, highlightGreen, highlightBlue;
	public final double rainResetChance;
	
	static {
		Path path = Paths.get(FILE_NAME);
		if (Files.exists(path))
			INSTANCE = from(FILE_NAME);
		else {
			INSTANCE = new Config.Builder().build();
			try {
				INSTANCE.to(FILE_NAME);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Config(int fontSize, int velocityMin, int velocityMax, int colorRed, int colorGreen, int colorBlue,
			int highlightRed, int highlightGreen, int highlightBlue, double rainResetChance) {
		this.fontSize = fontSize;
		this.velocityMin = velocityMin;
		this.velocityMax = velocityMax;
		this.colorRed = colorRed;
		this.colorGreen = colorGreen;
		this.colorBlue = colorBlue;
		this.highlightRed = highlightRed;
		this.highlightGreen = highlightGreen;
		this.highlightBlue = highlightBlue;
		this.rainResetChance = rainResetChance;
	}
	
	public static Config from(String name) {
		File file = new File(name);
		Builder builder = new Builder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			String[] split;
			String line;
			while ((line = reader.readLine()) != null) {
				split = line.split("=");
				switch (split[0]) {
					case "fontSize": builder.setFontSize(Integer.parseInt(split[1])); break;
					case "velocityMin": builder.setVelocityMin(Integer.parseInt(split[1])); break;
					case "velocityMax": builder.setVelocityMax(Integer.parseInt(split[1])); break;
					case "colorRed": builder.setColorRed(Integer.parseInt(split[1])); break; 
					case "colorGreen": builder.setColorGreen(Integer.parseInt(split[1])); break;
					case "colorBlue": builder.setColorBlue(Integer.parseInt(split[1])); break;
					case "highlightRed": builder.setHighlightRed(Integer.parseInt(split[1])); break; 
					case "highlightGreen": builder.setHighlightGreen(Integer.parseInt(split[1])); break;
					case "highlightBlue": builder.setHighlightBlue(Integer.parseInt(split[1])); break;
					case "rainResetChance": builder.setRainResetChance(Double.parseDouble(split[1])); break;
					default: throw new IllegalArgumentException("Invalid property : "+split[0]);
				}
			}	
		} catch (IOException e) {
			return null;
		} 
		return builder.build();
	}
	
	public File to(String name) throws FileNotFoundException {
		File file = new File(name);
		try (PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file))))) {
			writer.append(format("fontSize", fontSize));
			writer.append(format("velocityMin", velocityMin));
			writer.append(format("velocityMax", velocityMax));
			writer.append(format("colorRed", colorRed));
			writer.append(format("colorGreen", colorGreen));
			writer.append(format("colorBlue", colorBlue));
			writer.append(format("highlightRed", highlightRed));
			writer.append(format("highlightGreen", highlightGreen));
			writer.append(format("highlightBlue", highlightBlue));
			writer.append(format("rainResetChance", rainResetChance));
			writer.flush();
		}
		return file;
	}
	
	private static <T> String format(String key, T value) {
		return String.format("%s=%s\n", key, value.toString());
	}
	
	public static class Builder {
		public int fontSize, 
			velocityMin, velocityMax,
			colorRed, colorGreen, colorBlue,
			highlightRed, highlightGreen, highlightBlue;
		public double rainResetChance;
		
		public Builder() {	// defaults
			fontSize = 10;
			velocityMin = 5;
			velocityMax = 15;
			colorRed = 0;
			colorGreen = 255;
			colorBlue = 0;
			highlightRed = 255;
			highlightGreen = 255;
			highlightBlue = 255;
			rainResetChance = 0.9;
		}

		public Builder setFontSize(int fontSize) {
			this.fontSize = fontSize;
			return this;
		}

		public Builder setVelocityMin(int velocityMin) {
			this.velocityMin = velocityMin;
			return this;
		}

		public Builder setVelocityMax(int velocityMax) {
			this.velocityMax = velocityMax;
			return this;
		}

		public Builder setColorRed(int colorRed) {
			this.colorRed = colorRed;
			return this;
		}

		public Builder setColorGreen(int colorGreen) {
			this.colorGreen = colorGreen;
			return this;
		}

		public Builder setColorBlue(int colorBlue) {
			this.colorBlue = colorBlue;
			return this;
		}

		public Builder setHighlightRed(int highlightRed) {
			this.highlightRed = highlightRed;
			return this;
		}

		public Builder setHighlightGreen(int highlightGreen) {
			this.highlightGreen = highlightGreen;
			return this;
		}

		public Builder setHighlightBlue(int highlightBlue) {
			this.highlightBlue = highlightBlue;
			return this;
		}

		public Builder setRainResetChance(double rainResetChance) {
			this.rainResetChance = rainResetChance;
			return this;
		}

		public Config build() {
			return new Config(fontSize, 
					velocityMin, velocityMax, 
					colorRed, colorGreen, colorBlue, 
					highlightRed, highlightGreen, highlightBlue, 
					rainResetChance);
		}
	}
}
