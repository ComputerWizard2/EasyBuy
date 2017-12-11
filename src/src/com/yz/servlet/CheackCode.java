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
		// 1.׼��һ������
		BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_BGR);
		// 2.��ȡ���ʶ���
		Graphics g = image.getGraphics();
		Random r = new Random();
		// 3.���û��ʵ���ɫ
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		// 4.���ƾ���
		g.fillRect(0, 0, 100, 30);
		// 5.�����Զ��巽����������ĸ���ֵ�����ַ���
		String str = getNumberAndString(4);
		// �����������֤�뵽session
		HttpSession session = request.getSession();
		session.setAttribute("code", str);

		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		g.setFont(new Font(null, Font.ITALIC, 24));
		// 6.���Ʋ������ַ���
		g.drawString(str, 5, 25);
		// 7.������
		for (int i = 0; i < 10; i++) {
			g.setColor(new Color(255, 255, 255));
			Graphics2D gg = (Graphics2D) g;
			gg.setStroke(new BasicStroke(3.0f));
			int x = r.nextInt(100);
			int y = r.nextInt(30);
			gg.fillOval(x, y, 1, 1);

		}

		// 8.�����������
		response.setContentType("image/jpeg");
		// �ֽ������
		OutputStream out = response.getOutputStream();
		//
		ImageIO.write(image, "jpeg", out);
		// �ر�
		out.close();
	}

	// �Զ�����ʵ�ֲ�����ĸ���ֵ����

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
