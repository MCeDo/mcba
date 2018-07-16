package cn.maiba.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServImageVal extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Random random = new Random();
	
	public ServImageVal() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int codeLength = 4;		//验证码长度
		int mixTimes = 30;		//模糊程度参数
		Color bgColor = getRandColor(200, 250, 200);	//背景颜色
		Color bfColor = new Color(0, 0, 0); 	//字体颜色
		boolean ifRandomColor = true;
		//单个字符颜色随机
		boolean ifMixColor = false;
		//模糊线是否颜色随机
		
		//设置页面不缓存
		int width = 13 * codeLength + 6, height = 20;
		BufferedImage image = new BufferedImage(width, height, 
				BufferedImage.TYPE_INT_RGB);
		//获取图形上下文
		Graphics g = image.getGraphics();
		//设置背景颜色
		g.setColor(bgColor);
		g.fillRect(0, 0, width, height);
		//设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		//画边框
		g.setColor(new Color(33, 66, 99));
		g.drawRect(0, 0, width-1, height-1);
		//随机产生干扰线，使得图像中的认证码不易被其他程序探测到
		g.setColor(getRandColor(160, 200, 160));
		for(int i=0; i<mixTimes*codeLength/10; i++) {
			if(ifMixColor) {
				g.setColor(getRandColor(160, 200, 160));
			}
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, xl, yl);
		}
		//取随机生成的认证码
		StringBuffer sRand = new StringBuffer("");
		for(int i=0; i<codeLength; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand.append(rand);
			//将认证码显示到图像中
			if(ifRandomColor) {
				g.setColor(getRandColor(10, 110, 0));
			}else {
				g.setColor(bfColor);
			}
			//调用函数出来的颜色相同，可能是种子太接近，所以只能直接生成
			g.drawString(rand, 13*i+6, 16);
		}
		request.getSession().setAttribute("rand", sRand.toString());
		//图片生效
		g.dispose();
		//输出图像到页面
		ImageIO.write(image, "PNG", response.getOutputStream());
	}
	
	private Color getRandColor(int fc, int bc, int interval) {
		// TODO Auto-generated method stub
		if(fc>255) {
			fc = 255;
		}
		if(bc>255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc-interval);
		int g = fc + random.nextInt(bc-interval);
		int b = fc + random.nextInt(bc-interval);
		return new Color(r, g, b);
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
