
public class vector {

	int F1,F2,F3,F4,F5;
	vector(int f12,int f22,int f32,int f42,int f52){
		this.F1=f12;
		this.F2=f22;
		this.F3=f32;
		this.F4=f42;
		this.F5=f52;
	}
	
	public String toString() {
	
		return String.valueOf(F1)+","+
				String.valueOf(F2)+","+
				String.valueOf(F3)+","+
				String.valueOf(F4)+","+
				String.valueOf(F5);
	}

	public int getF1() {
		// TODO Auto-generated method stub

		return F1;
	}
	
	public int getF2() {
		// TODO Auto-generated method stub
		return F2;
	}
	public int getF3() {
		// TODO Auto-generated method stub
		return F3;
	}
	public int getF4() {
		// TODO Auto-generated method stub
		return F4;
	}
	public int getF5() {
		// TODO Auto-generated method stub
		return F5;
	}
}
