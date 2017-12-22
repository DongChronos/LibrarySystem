package com.Library.action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
/**
 * 验证码类
 */
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Library.globle.Constant;

/**
 * 验证码类
 * 实现生成验证码功能
 */
@WebServlet("/CodeAction")
public class CodeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String codeNumbers = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("执行CodeAction方法");
		//图片类型
		response.setContentType("image/gif");
		response.setHeader("Pragma", "NO-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		

		//图片缓存
		BufferedImage image = new BufferedImage(Constant.IMAGE_WIDTH, Constant.IMAGE_HEIGHT, BufferedImage.TYPE_INT_BGR);
		
		//获得绘制对象
		Graphics2D g = (Graphics2D) image.getGraphics();
		//绘制图片背景颜色
		g.setColor(createRandomColor(200,300));
		//填充
		g.fillRect(0, 0, Constant.IMAGE_WIDTH, Constant.IMAGE_HEIGHT);
		
		//绘制字符
				for(int i = 0;i < 4;i++)
				{
					drawString(g,i);
				}
				
				//绘制干扰线
				drawNoiseLine(g, 5);
				
				//存储验证码
				saveCodeInSession(request);
				
				this.codeNumbers = "";
				//输出
				ServletOutputStream sos = response.getOutputStream();
				ImageIO.write(image, "GIF", sos);
				sos.flush();
				sos.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	/**
	 * 绘制图片背景颜色
	 * @param bcf 最大值
	 * @param bcl 最小值
	 * @return
	 */
	private Color createRandomColor(int bcf,int bcl)
	{
		boolean flag = true;
		int r = 0,g = 0,b = 0;
		while(flag)
		{
			r = bcf + new Random().nextInt(bcl - bcf);
			g = bcf + new Random().nextInt(bcl - bcf);
			b = bcf + new Random().nextInt(bcl - bcf);
			if((r >= 0&&r <= 255) &&(g >=0 && g<= 255) && (b >=0 && b <= 255))
			{
				flag = false;
			}
		}
		return new Color(r, g, b);
	}
	
	/**
	 * 绘制字符
	 * @param g 图层
	 * @param i 第i位字符
	 */
	private void drawString(Graphics2D g,int i)
	{
		int j = new Random().nextInt(Constant.IMAGE_CHAR.length());
		String codeNumber = Constant.IMAGE_CHAR.substring(j, j + 1);
		g.setColor(Constant.color[new Random().nextInt(Constant.color.length)]);
		g.setFont(Constant.codeFont[new Random().nextInt(Constant.codeFont.length)]);
		g.drawString(codeNumber, 15*i + 20,30);
		this.codeNumbers += codeNumber;
	}
	
	/**
	 * 绘制干扰线
	 * @param g 图层
	 * @param NoiseLineNumbers 数量
	 */
	private void drawNoiseLine(Graphics2D g,int NoiseLineNumbers)
	{
		for(int i = 0;i < NoiseLineNumbers;i++)
		{
			int x1 = (int) (1 + (Math.random()*120));
			int y1 = (int) (1 + (Math.random()*40));
			int x2 = (int) (1 + (Math.random()*120));
			int y2 = (int) (1 + (Math.random()*40));
			g.setStroke((Stroke) new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL));
			g.drawLine(x1, y1, x2, y2);
		}
	}
	
	/**
	 * 将验证码保存到session中
	 * @param request
	 */
	private void saveCodeInSession(HttpServletRequest request)
	{
		HttpSession session = request.getSession(true);
		// 将之前的验证码移除，把新的验证码内容保存进session中
		session.setAttribute(Constant.CHECK_NUMBER_NAME, codeNumbers);
	}
}
