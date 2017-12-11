package src.com.yz.servlet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheackCode
 */
@WebServlet("/CheackCode")
public class CheackCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheackCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.准备一个画板
		BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_BGR);
		// 2.获取画笔对象
		Graphics g = image.getGraphics();
		Random r = new Random();
		// 3.设置画笔的颜色
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		// 4.绘制矩形
		g.fillRect(0, 0, 100, 30);
		// 5.调用自定义方法，产生字母数字的组合字符串
		String str = getNumberAndString(4);
		// 保存产生的验证码到session
		HttpSession session = request.getSession();
		session.setAttribute("code", str);

		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		g.setFont(new Font(null, Font.ITALIC, 24));
		// 6.绘制产生的字符串
		g.drawString(str, 5, 25);
		// 7.干扰线
		for (int i = 0; i < 10; i++) {
			g.setColor(new Color(255, 255, 255));
			Graphics2D gg = (Graphics2D) g;
			gg.setStroke(new BasicStroke(3.0f));
			int x = r.nextInt(100);
			int y = r.nextInt(30);
			gg.fillOval(x, y, 1, 1);

		}

		// 8.设置输出内容
		response.setContentType("image/jpeg");
		// 字节输出流
		OutputStream out = response.getOutputStream();
		//
		ImageIO.write(image, "jpeg", out);
		// 关闭
		out.close();
	}

	// 自定方法实现产生字母数字的组合

	private String getNumberAndString(int i) {
		String s = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
		String str = "";
		Random r = new Random();
		for (int j = 0; j < i; j++) {
			str += s.charAt(r.nextInt(s.length()));
		}
		return str;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
