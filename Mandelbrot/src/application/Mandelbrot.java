package application;
import java.util.Arrays;

public class Mandelbrot {
	/**
	 * Compute Mandelbrot set
	 */
	public static int[][] compute(double size, int pixel, int div_threshold) {
		int[][] result = new int[pixel][];
		for(int i = 0; i < pixel; i++) {
			result[i] = new int[pixel];
			Arrays.fill(result[i], 0);
		}
		
		for(int i = 0; pixel > i; i++) {
			double x = i * size / pixel - size / 2;
			for(int j = 0; pixel > j; j++) {
				double y = j * size / pixel - size / 2;
				double a = 0;
				double b = 0;
				for(int k = 1; div_threshold >= k; k++) {
					double _a = a * a - b * b + x;
					double _b = 2 * a * b + y;
					a = _a;
					b = _b;
					if(a * a + b * b > 4) {
						result[j][i] = k;
						break;
					}
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("Hello");
		int[][] result = compute(4.0, 100, 50);
		
		// Display results.
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				System.out.print(String.format("%02d ", result[i][j]));
			}
			System.out.println("");
		}
	}
}
