package zealjiang.basetype;

import org.junit.Test;

/**
 * 为什么全局变量无须初始化，局部变量必须初始化？
 * @author JZG
 *
 */
public class DefaultValue {

	private int global;
	
	@Test
	public void main(String[] args) {
		int part = 0;
		System.out.println("global: "+global+" part: "+part);
	}
}
