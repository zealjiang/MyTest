package zealjiang.serializable;

import java.io.Serializable;

public class WaveBean implements Serializable{

	private static final long serialVersionUID = -5177438901125052970L;
	private String name;
	private float gain;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getGain() {
		return gain;
	}
	public void setGain(float gain) {
		this.gain = gain;
	}
	
	

}
