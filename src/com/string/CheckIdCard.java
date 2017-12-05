package com.string;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ��֤���֤���� ���֤����, ���Խ������֤����ĸ����ֶΣ��Լ���֤���֤�����Ƿ���Ч; ���֤���빹�ɣ�6λ��ַ����+8λ����+3λ˳����+1λУ����
 * 
 * @ClassName: CheckIdCard
 * @Description: TODO
 * @author suncy
 * @date 2013-1-4 ����11:06:09
 * 
 */
public class CheckIdCard {
	private String cardNumber; // ���������֤����
	private Boolean cacheValidateResult = null; // �������֤�Ƿ���Ч����Ϊ��֤��Ч��ʹ��Ƶ���Ҽ��㸴��
	private Date cacheBirthDate = null; // ����������ڣ���Ϊ��������ʹ��Ƶ���Ҽ��㸴��
	private final static String BIRTH_DATE_FORMAT = "yyyyMMdd"; // ���֤�����еĳ������ڵĸ�ʽ
	private final static Date MINIMAL_BIRTH_DATE = new Date(-2209017600000L); // ���֤����С��������,1900��1��1��
	private final static int NEW_CARD_NUMBER_LENGTH = 18;
	private final static int OLD_CARD_NUMBER_LENGTH = 15;
	private final static char[] VERIFY_CODE = { '1', '0', 'X', '9', '8', '7',
			'6', '5', '4', '3', '2' }; // 18λ���֤�����һλУ����
	private final static int[] VERIFY_CODE_WEIGHT = { 7, 9, 10, 5, 8, 4, 2, 1,
			6, 3, 7, 9, 10, 5, 8, 4, 2 };// 18λ���֤�У��������ֵ�����У����ʱ��Ȩֵ

	public boolean validate() {
		if (null == cacheValidateResult) {
			boolean result = true;
			result = result && (null != cardNumber); // ���֤�Ų���Ϊ��
			result = result && NEW_CARD_NUMBER_LENGTH == cardNumber.length(); // ���֤�ų�����18(��֤)
			// ���֤�ŵ�ǰ17λ�����ǰ���������
			for (int i = 0; result && i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
				char ch = cardNumber.charAt(i);
				result = result && ch >= '0' && ch <= '9';
			}
			// ���֤�ŵĵ�18λУ����ȷ
			result = result
					&& (calculateVerifyCode(cardNumber) == cardNumber
							.charAt(NEW_CARD_NUMBER_LENGTH - 1));
			// �������ڲ������ڵ�ǰʱ�䣬���Ҳ�������1900��
			try {
				Date birthDate = this.getBirthDate();
				result = result && null != birthDate;
				result = result && birthDate.before(new Date());
				result = result && birthDate.after(MINIMAL_BIRTH_DATE);
				/**
				 * ���������е��ꡢ�¡��ձ�����ȷ,�����·ݷ�Χ��[1,12],���ڷ�Χ��[1,31]������ҪУ�����ꡢ���¡�С�µ����ʱ��
				 * �·ݺ����������
				 */
				String birthdayPart = this.getBirthDayPart();
				String realBirthdayPart = this.createBirthDateParser().format(
						birthDate);
				result = result && (birthdayPart.equals(realBirthdayPart));
			} catch (Exception e) {
				result = false;
			}
			cacheValidateResult = Boolean.valueOf(result);// TODO
			// �������֤�����ʡ�������������
		}
		return cacheValidateResult;
	}

	/**
	 * �����15λ���֤���룬���Զ�ת��Ϊ18λ
	 * 
	 * @param cardNumber
	 * @return
	 */
	public CheckIdCard(String cardNumber) {
		if (null != cardNumber) {
			cardNumber = cardNumber.trim();
			if (OLD_CARD_NUMBER_LENGTH == cardNumber.length()) {
				cardNumber = contertToNewCardNumber(cardNumber);
			}
		}
		this.cardNumber = cardNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getAddressCode() {
		this.checkIfValid();
		return this.cardNumber.substring(0, 6);
	}

	public Date getBirthDate() {
		if (null == this.cacheBirthDate) {
			try {
				this.cacheBirthDate = this.createBirthDateParser().parse(
						this.getBirthDayPart());
			} catch (Exception e) {
				throw new RuntimeException("���֤�ĳ���������Ч");
			}
		}
		return new Date(this.cacheBirthDate.getTime());
	}

	public boolean isMale() {
		return 1 == this.getGenderCode();
	}

	public boolean isFemal() {
		return false == this.isMale();
	}

	/**
	 * ��ȡ���֤�ĵ�17λ������Ϊ���ԣ�ż��ΪŮ��
	 * 
	 * @return
	 */
	private int getGenderCode() {
		this.checkIfValid();
		char genderCode = this.cardNumber.charAt(NEW_CARD_NUMBER_LENGTH - 2);
		return (((int) (genderCode - '0')) & 0x1);
	}

	private String getBirthDayPart() {
		return this.cardNumber.substring(6, 14);
	}

	private SimpleDateFormat createBirthDateParser() {
		return new SimpleDateFormat(BIRTH_DATE_FORMAT);
	}

	private void checkIfValid() {
		if (false == this.validate()) {
			throw new RuntimeException("���֤���벻��ȷ��");
		}
	}

	/**
	 * У���루��ʮ��λ������
	 * 
	 * ʮ��λ���ֱ������Ȩ��͹�ʽ S = Sum(Ai * Wi), i = 0...16 ���ȶ�ǰ17λ���ֵ�Ȩ��ͣ�
	 * Ai:��ʾ��iλ���ϵ����֤��������ֵ Wi:��ʾ��iλ���ϵļ�Ȩ���� Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
	 * 2; ����ģ Y = mod(S, 11)< ͨ��ģ�õ���Ӧ��У���� Y: 0 1 2 3 4 5 6 7 8 9 10 У����: 1 0 X 9
	 * 8 7 6 5 4 3 2
	 * 
	 * @param cardNumber
	 * @return
	 */
	private static char calculateVerifyCode(CharSequence cardNumber) {
		int sum = 0;
		for (int i = 0; i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
			char ch = cardNumber.charAt(i);
			sum += ((int) (ch - '0')) * VERIFY_CODE_WEIGHT[i];
		}
		return VERIFY_CODE[sum % 11];
	}

	/**
	 * ��15λ���֤����ת����18λ���֤����<br>
	 * 15λ���֤������18λ���֤���������Ϊ��<br>
	 * 1��15λ���֤�����У�"�������"�ֶ���2λ��ת��ʱ��Ҫ����"19"����ʾ20����<br>
	 * 2��15λ���֤�����һλУ���롣18λ���֤�У�У������ݸ���ǰ17λ����
	 * 
	 * @param cardNumber
	 * @return
	 */
	private static String contertToNewCardNumber(String oldCardNumber) {
		StringBuilder buf = new StringBuilder(NEW_CARD_NUMBER_LENGTH);
		buf.append(oldCardNumber.substring(0, 6));
		buf.append("19");
		buf.append(oldCardNumber.substring(6));
		buf.append(CheckIdCard.calculateVerifyCode(buf));
		return buf.toString();
	}
}