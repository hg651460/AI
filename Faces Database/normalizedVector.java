import java.text.DecimalFormat;

public class normalizedVector {

	double F1,F2,F3,F4,F5;
	
	public normalizedVector(double f12,double f22,double f32,double f42,double f52){
		this.F1=f12;
		this.F2=f22;
		this.F3=f32;
		this.F4=f42;
		this.F5=f52;
	}
	

    DecimalFormat df = new DecimalFormat("0.00"); 
	public String toString() {
	
		String s= String.valueOf(df.format(F1))+","+
				String.valueOf(df.format(F2))+","+
				String.valueOf(df.format(F3))+","+
				String.valueOf(df.format(F4))+","+
				String.valueOf(df.format(F5));

		return s;
	}

	public double getF1() {
		// TODO Auto-generated method stub
		return F1;
	}
	
	public double getF2() {
		// TODO Auto-generated method stub
		return F2;
	}
	public double getF3() {
		// TODO Auto-generated method stub
		return F3;
	}
	public double getF4() {
		// TODO Auto-generated method stub
		return F4;
	}
	public double getF5() {
		// TODO Auto-generated method stub
		return F5;
	}
}
