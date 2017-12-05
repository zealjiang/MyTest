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
		// ��ʼ���������
		lblName = new Label("������");
		lblNumber = new Label("���֤�ţ�");
		lblSex = new Label("�Ա�");
		lblJob = new Label("ְҵ");
		lblText = new Label("���Ի����ԣ�");
		tfName = new TextField(23);
		tfNumber = new TextField(20);
		taText = new TextArea(10, 20);
		c = new CheckboxGroup();
		chMale = new Checkbox("��", c, true);
		chFemale = new Checkbox("Ů", c, false);
		chJob = new Choice();
		chJob.add("�����ҵ");
		chJob.add("ҽ��");
		chJob.add("��ʦ");
		chJob.add("����");
		btnOk = new Button("ȷ��");
		btnDisplay = new Button("��ʾ");
		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();
		p5 = new Panel();
		p6 = new Panel();
		p7 = new Panel(new BorderLayout());
		p8 = new Panel();
		p9 = new Panel(new BorderLayout());
		// ���ý���
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
		// ��Ӽ����¼�
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
		// �ռ��û���Ϣ��д��XML�ļ�
		if (arg.equals("ȷ��")) {
			strName = tfName.getText().trim();
			strNumber = tfNumber.getText().trim();
			if (chMale.getState())
				strSex = "��";
			else
				strSex = "Ů";
			strJob = chJob.getSelectedItem();
			strText = taText.getText().trim();
			try {
				// �����µ��ĵ�����
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.newDocument();
				// ����Ԫ��
				Element root = doc.createElement("UserData");
				Element eName = doc.createElement("Name");
				Element eNumber = doc.createElement("Number");
				Element eSex = doc.createElement("Sex");
				Element eJob = doc.createElement("Job");
				Element eText = doc.createElement("Text");
				// ��ӽڵ�
				root.appendChild(eName);
				root.appendChild(eNumber);
				root.appendChild(eSex);
				root.appendChild(eJob);
				root.appendChild(eText);
				// ���ֵ
				eName.appendChild(doc.createTextNode("\n " + strName + " \n"));
				eNumber.appendChild(doc.createTextNode("\n " + strNumber
						+ " \n"));
				eSex.appendChild(doc.createTextNode("\n " + strSex + " \n"));
				eJob.appendChild(doc.createTextNode("\n " + strJob + " \n"));
				eText.appendChild(doc.createTextNode("\n " + strText + " \n"));
				// �����ļ�����
				File f = new File("d:/user.xml");
				FileOutputStream fOut = new FileOutputStream(f);
				// ��ʼ��xml�ļ�
				fOut.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
						.getBytes());
				// д���ļ�
				System.out.println(root.toString());
				fOut.write(root.toString().getBytes());
				fOut.flush();
				fOut.close();
				btnDisplay.setEnabled(true);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		// ��ȡXML�ļ���������ı�����
		else if (arg.equals("��ʾ")) {
			try {
				// �õ�XML�ĵ�����
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				System.out.println("strName:"+strName);
				Document doc = db.parse("d:/user.xml");
				
				Element root = doc.getDocumentElement();
				// ��ȡҶ�ӽڵ�ֵ
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
				// ������ı�����
				taText.setText("");
				taText.append("������" + strName + "\n���֤�ţ�" + strNumber + "\n�Ա�"
						+ strSex + "\nְҵ��" + strJob + "\n���Ի����ԣ�\n" + strText);
			} catch (Exception e) {
				System.out.println("--");
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String args[]) {
		Frame f = new Frame("�ռ��û�����");
		// �رմ����˳�����
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
		// ������ʵ��
		UsePanel p = new UsePanel();
		p.init();
		f.add("Center", p);
		f.setSize(600, 450);
		f.show();
	}
};
