package zealjiang.lrc;

import java.io.Serializable;

/**
 * һ��������ʾÿһ���ʵ��� ����װ�˸�ʵ������Լ� ����ʵ���ʼʱ�� �ͽ���ʱ�䣬����һЩʵ�õķ���
 * 
 * @author Admin
 */
public class Sentence {//implements Serializable {

	public static String singerName = ""; // ���浱ǰ�������ֵ����֣��Լ���ӵ�

	private static final long serialVersionUID = 20071125L;
	private long fromTime;// ������ʼʱ��,ʱ�����Ժ���Ϊ��λ
	private long toTime;// ��һ��Ľ���ʱ��
	private String content;// ��һ�������
	final static long DISAPPEAR_TIME = 1000L;// ��ʴ���ʾ�굽��ʧ��ʱ��

	// ��3�������Ĺ��캯��
	public Sentence(String content, long fromTime, long toTime) {
		this.content = content;
		this.fromTime = fromTime;
		this.toTime = toTime;
	}

	// ��2�������Ĺ��캯��
	public Sentence(String content, long fromTime) {
		this(content, fromTime, 0);
	}

	// ��1�������Ĺ��캯��
	public Sentence(String content) {
		this(content, 0, 0);
	}

	// ������ʼʱ��
	public long getFromTime() {
		return fromTime;
	}

	// ������ʼʱ��
	public void setFromTime(long fromTime) {
		this.fromTime = fromTime;
	}

	// ���ؽ���ʱ��
	public long getToTime() {
		return toTime;
	}

	// �����ý���ʱ��
	public void setToTime(long toTime) {
		this.toTime = toTime;
	}

	/**
	 * ���ĳ��ʱ���Ƿ������ĳ���м�
	 * 
	 * @param time
	 *            ʱ��
	 * @return �Ƿ������
	 */
	public boolean isInTime(long time) {
		return time >= fromTime && time <= toTime;
	}

	/**
	 * �õ���һ�������
	 * 
	 * @return ����
	 */
	public String getContent() {
		return content;
	}

	/**
	 * �õ�������ӵ�ʱ�䳤��,����Ϊ��λ
	 * 
	 * @return ����
	 */
	public long getDuring() {
		return toTime - fromTime;
	}

	public String toString() {
		return "{" + fromTime + "(" + content + ")" + toTime + "}";
	}
}
