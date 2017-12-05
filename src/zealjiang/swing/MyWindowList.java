package zealjiang.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyWindowList extends JFrame implements ListSelectionListener{

	JList list1,list2;
	String news[] = {"人民日报","新民晚报","浙江日报","文汇报"};
	String sports[] = {"足球","排球","乒乓球","篮球"};
	
	JTextArea textArea;
	
	public MyWindowList(String s) {
		// TODO Auto-generated constructor stub
		super(s);
		Container container = getContentPane();
		container.setBackground(Color.BLUE);
		container.setLayout(new GridLayout(2, 2));
		container.setSize(200,500);
		list1 = new JList(news);
		//list1.setVisibleRowCount(3);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list1.addListSelectionListener(this);
		
		list2 = new JList(sports);
		list2.setVisibleRowCount(2);
		list2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list2.addListSelectionListener(this);
		
		container.add(list1);
		container.add(list2);
		textArea = new JTextArea(10,20);
		container.add(textArea);
		this.setVisible(true);
		this.pack();
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==list1){
			textArea.setText(null);
			Object listValue = ((JList)e.getSource()).getSelectedValue();
			String seleName = listValue.toString();
			for(int i=0;i<news.length;i++){
				if(news[i].equals(seleName)){
					textArea.append(seleName+":被选中\n");
				}
			}
		}else if(e.getSource()==list2){
			textArea.setText(null);
			int tempList[] = list2.getSelectedIndices();
			for(int i=0;i<tempList.length;i++){
				textArea.append(sports[tempList[i]]+":被选中\n");
			}
		}
	}
       
	public static void main(String[] args) {
		MyWindowList myWindowList = new MyWindowList("列表示例");
	}
}
