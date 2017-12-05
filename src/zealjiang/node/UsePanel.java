package zealjiang.node;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UsePanel extends Applet implements ActionListener {
	Label lblName, lblNumber, lblSex, lblJob, lblText;
	TextField tfName, tfNumber;
	Checkbox chMale, chFemale;
	CheckboxGroup c;
	TextArea taText;
	Choice chJob;
	Button btnOk, btnDisplay;
	Panel p1, p2, p3, p4, p5, p6, p7, p8, p9;
	String strName, strNumber, strSex, strJob, strText;

	public void init() {
		// 初始化界面对象
		lblName = new Label("姓名：");
		lblNumber = new Label("身份证号：");
		lblSex = new Label("性别");
		lblJob = new Label("职业");
		lblText = new Label("个性化宣言：");
		tfName = new TextField(23);
		tfNumber = new TextField(20);
		taText = new TextArea(10, 20);
		c = new CheckboxGroup();
		chMale = new Checkbox("男", c, true);
		chFemale = new Checkbox("女", c, false);
		chJob = new Choice();
		chJob.add("计算机业");
		chJob.add("医生");
		chJob.add("教师");
		chJob.add("军队");
		btnOk = new Button("确定");
		btnDisplay = new Button("显示");
		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();
		p5 = new Panel();
		p6 = new Panel();
		p7 = new Panel(new BorderLayout());
		p8 = new Panel();
		p9 = new Panel(new BorderLayout());
		// 设置界面
		p1.add(lblName);
		p1.add(tfName);
		p2.add(lblNumber);
		p2.add(tfNumber);
		p3.add(lblSex);
		p3.add(chMale);
		p3.add(chFemale);
		p4.add(lblJob);
		p4.add(chJob);
		p5.add(p3);
		p5.add(p4);
		p6.setLayout(new BorderLayout());
		p6.add(p1, BorderLayout.NORTH);
		p6.add(p2, BorderLayout.CENTER);
		p6.add(p5, BorderLayout.SOUTH);
		p7.add(lblText, BorderLayout.NORTH);
		p7.add(taText, BorderLayout.CENTER);
		p8.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		p8.add(btnOk);
		p8.add(btnDisplay);
		p9.add(p6, BorderLayout.NORTH);
		p9.add(p7, BorderLayout.CENTER);
		p9.add(p8, BorderLayout.SOUTH);
		add(p9);
		// 添加监听事件
		btnOk.addActionListener(this);
		btnDisplay.addActionListener(this);
		btnDisplay.setEnabled(true);
		strName = new String();
		strNumber = new String();
		strSex = new String();
		strJob = new String();
		strText = new String();
	}

	public void actionPerformed(ActionEvent evt) {
		String arg = evt.getActionCommand();
		// 收集用户信息并写入XML文件
		if (arg.equals("确定")) {
			strName = tfName.getText().trim();
			strNumber = tfNumber.getText().trim();
			if (chMale.getState())
				strSex = "男";
			else
				strSex = "女";
			strJob = chJob.getSelectedItem();
			strText = taText.getText().trim();
			try {
				// 创建新的文档对象
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.newDocument();
				// 创建元素
				Element root = doc.createElement("UserData");
				Element eName = doc.createElement("Name");
				Element eNumber = doc.createElement("Number");
				Element eSex = doc.createElement("Sex");
				Element eJob = doc.createElement("Job");
				Element eText = doc.createElement("Text");
				// 添加节点
				root.appendChild(eName);
				root.appendChild(eNumber);
				root.appendChild(eSex);
				root.appendChild(eJob);
				root.appendChild(eText);
				// 添加值
				eName.appendChild(doc.createTextNode("\n " + strName + " \n"));
				eNumber.appendChild(doc.createTextNode("\n " + strNumber
						+ " \n"));
				eSex.appendChild(doc.createTextNode("\n " + strSex + " \n"));
				eJob.appendChild(doc.createTextNode("\n " + strJob + " \n"));
				eText.appendChild(doc.createTextNode("\n " + strText + " \n"));
				// 创建文件对象
				File f = new File("d:/user.xml");
				FileOutputStream fOut = new FileOutputStream(f);
				// 初始化xml文件
				fOut.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
						.getBytes());
				// 写入文件
				System.out.println(root.toString());
				fOut.write(root.toString().getBytes());
				fOut.flush();
				fOut.close();
				btnDisplay.setEnabled(true);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		// 读取XML文件并输出到文本区域
		else if (arg.equals("显示")) {
			try {
				// 得到XML文档对象
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				System.out.println("strName:"+strName);
				Document doc = db.parse("d:/user.xml");
				
				Element root = doc.getDocumentElement();
				// 获取叶子节点值
				strName = root.getElementsByTagName("Name").item(0)
						.getFirstChild().getNodeValue().trim();
				
				strNumber = root.getElementsByTagName("Number").item(0)
						.getFirstChild().getNodeValue().trim();
				strSex = root.getElementsByTagName("Sex").item(0)
						.getFirstChild().getNodeValue().trim();
				strJob = root.getElementsByTagName("Job").item(0)
						.getFirstChild().getNodeValue().trim();
				strText = root.getElementsByTagName("Text").item(0)
						.getFirstChild().getNodeValue().trim();
				// 输出到文本区域
				taText.setText("");
				taText.append("姓名：" + strName + "\n身份证号：" + strNumber + "\n性别："
						+ strSex + "\n职业：" + strJob + "\n个性化宣言：\n" + strText);
			} catch (Exception e) {
				System.out.println("--");
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String args[]) {
		Frame f = new Frame("收集用户界面");
		// 关闭窗口退出程序
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
		// 定义类实例
		UsePanel p = new UsePanel();
		p.init();
		f.add("Center", p);
		f.setSize(600, 450);
		f.show();
	}
};
