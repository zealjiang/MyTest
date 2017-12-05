package com.string;

public class CheckExg {

	/**
	 * Java �����������ʽ

1. ����֤����:
1) ����֤�������ʽ(15λ) 
^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$
forJava:  Pattern p = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
------ ------ ------ ˵�� start ------ ------ ------
15λ����֤�����λ�ĺ���: 
1-2λʡ����������ֱϽ�д��룻 
3-4λ�ؼ��С��ˡ������ݴ��룻 
5-6λ�ء��ؼ��С������룻 
7-12λ����������,����670401����1967��4��1��,��18λ�ĵ�һ������ 
13-15λΪ˳��ţ�����15λ��Ϊ������ŮΪ˫����
------ ------ ------ ˵�� end ------ ------ ------
2) ����֤�������ʽ(18λ)^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$
forJava:  Pattern p = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
------ ------ ------ ˵�� start ------ ------ ------
18λ����֤�����λ�ĺ���: 
1-2λʡ����������ֱϽ�д��룻 
3-4λ�ؼ��С��ˡ������ݴ��룻 
5-6λ�ء��ؼ��С������룻 
7-14λ���������գ�����19670401����1967��4��1�գ� 
15-17λΪ˳��ţ�����17λ�������ڶ�λ����Ϊ������ŮΪ˫���� 
18λΪУ���룬0-9��X����Ϊβ�ŵ�У���룬���ɰ�ǰʮ��λ���ִ���ͳһ�Ĺ�ʽ��������ģ�����Ľ����0-10��
���ĳ�˵�β����0��9�����������X�������β����10����ô�͵���X�����棬��Ϊ�����10��β�ţ���ô���˵�����֤�ͱ����19λ��X���������ֵ�10����X������10��
------ ------ ------ ˵�� end ------ ------ ------

2. �绰����:
1) �ƶ��绰:  ^((13[0-9])|(15[^4,\D])|(18[0-9]))\d{8}$
------ ------ ------ ˵�� start ------ ------ ------
�й������ֻ����뿪ͷ���� 133��1349��153��180��181��189
�й���ͨ�ֻ����뿪ͷ���� 130��131��132��145��155��156��185��186
�й��ƶ��ֻ����뿪ͷ���� 1340-1348��135��136��137��138��139��147��150��151��152��157��158��159��182��183��184��187��188��
���䣺14��ͷ�ĺ�����ǰΪ������ר���ŶΣ�����ͨ����145���ƶ�����147�ȵȡ���������14��ͷ�ĺ���Ҳ����ʹ������ͨ����ȫ��ҵ�񣬲������ơ�
------ ------ ------ ˵�� end ------ ------ ------
forJava:  Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); 
2) �̶��绰:  
--------------- ����-���� -------------------
String regex1 = "\\(?(010|021|022|023|024|025|026|027|028|029|852)?\\)?-?\\d{8}";//3λ����,8λ����
String regex2 = "\\(?(0[3-9][0-9]{2})?\\)?-?\\d{7,8}";//4λ����
String regex3 = "(\\(?(010|021|022|023|024|025|026|027|028|029|852)?\\)?-?\\d{8})|(\\(?(0[3-9][0-9]{2})?\\)?-?\\d{7,8})";
--------------- ���Ϸֻ��� (\\-?[0-9]{1,4})? ------- ����-����-�ֻ��� ---------------
String regex1 = "\\(?(010|021|022|023|024|025|026|027|028|029|852|)\\)?-?\\d{8}(\\-?[0-9]{1,4})?";//3λ����
String regex2 = "\\(?(0[3-9][0-9]{2})\\)?-?\\d{7,8}(\\-?[0-9]{1,4})?";//4λ����
String regex3 = "(\\(?(010|021|022|023|024|025|026|027|028|029|852|)\\)?-?\\d{8}(\\-?[0-9]{1,4})?)|(\\(?(0[3-9][0-9]{2})\\)?-?\\d{7,8}(\\-?[0-9]{1,4})?)";

3. IP��ַ: 
(25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)){3}
forJava:  (25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}

4. ����:
\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*
forJava:  p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

5. ����: ------------- ��-��-��: ------------------
��: //���Ϊ��λ,�Ҳ�����0000
([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})
��-�� //δ��������.
((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))
��-��-�� 
Pattern.compile("([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))");//˵��: ���Ϊ��λ,�Ҳ�����0000,֮��������,δ��������.

6. ����: [\u4e00-\u9fa5]
forJava:  p = Pattern.compile("^[\u4e00-\u9fa5]+$");

======================== ˵�� ==================
1. \w ---> �����ַ�[a-zA-Z_0-9],��Java�еı�ʶ��
\w+ ---> һ�����������ַ�[a-zA-Z_0-9]{1,}
2. [-+.]  ---> -��+��. �����ַ�
3. X* ---> ��λ���
X? ---> ��λ�һ��
X+ ---> һ�λ���
4. ��Ҫת��������ַ�: 
\ ---> \\
[ ---> \[
] ---> \]
( ---> \(
) ---> \)
{ ---> \{
} ---> \}
. ---> \.
* ---> \*
? ---> \?
+ ---> \+
^ ---> \^
$ ---> \$
| ---> \|
5. �����������ʽ��ѯ��ַ:
http://www.ostools.net/regex#
	 */
}