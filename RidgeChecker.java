package fingerprint;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RidgeChecker {
	static long startTime = System.nanoTime();
	public static boolean findFeature(BufferedImage ran,int x, int y){
		Color col=new Color(ran.getRGB(x, y));
		int blue=col.getBlue();
		//if blue=0, black, if blue=255, white
		if (blue!=0) {
			return false;
		}else 
			{int cn=0;
			for(int i=x-1; i<x+2;i++) {
				for (int j=y-1;j<y+2;j++) {
					if(i<0||i>ran.getWidth()||j<0||j>ran.getHeight()) {
						return true;
					}
					Color neigh=new Color(ran.getRGB(i, j));
					int b=neigh.getBlue();
					if (b==0) {
						cn++;
						}
					}
				}if (cn==2) {
				return true;
			}else {return false;}
			
		}
	}
	

	public static void main(String[] args) throws IOException {
		BufferedImage input= ImageIO.read(new File ("2.png"));
		BufferedImage out = new BufferedImage(input.getWidth(),input.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int a=0; a<input.getWidth();a++) {
			for (int b=0; b<input.getHeight();b++) {
				if(findFeature(input,a,b)) {
					out.setRGB(a,b,0xff0000);
				}else {
					out.setRGB(a, b, input.getRGB(a,b));
				}
			}
			
		}ImageIO.write(out, "png", new File("marked_features_2.png"));
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		
	}

}
