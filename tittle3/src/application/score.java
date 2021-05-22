package application;

import java.io.IOException;
import java.io.RandomAccessFile;

   
public class score {
	public String a[]=new String[21];
	public String filePath() {
	String s= getClass().getResource("history.txt").toString();
	s=s.substring(6);

	  return s;
    }
	public String filePath= filePath();
	public void addscore(int score, int count) {
		String b[] = new String[21];
		laymang(b);
		changearray(b,score,count);
		addbxh(b);
		
	}
	public void addlose() {
		String a[] = new String[21];
		laymang(a);
		for(int j=19;j>=0;j--) {
			a[j+1]=a[j];
			
		}
       a[0]= "LOSE                ";
       addbxh(a);
		//printbxh();
	}
	public void reset() {
		String a[]= new String [21];
		resarray(a);
		addbxh(a);
		
	}
	public void resarray(String a[]) {
		
		for(int i=0;i<20;i++) {
			a[i]="                    ";
		}
	}
	public void addbxh(String a[]) {
		for(int i=0;i<20;i++) {
			
			try {
				writeData(filePath, a[i], 22*i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void laymang(String a[]){
		for(int i=0;i<20;i++) {
			String s;
			try {
				s = new String(readCharsFromFile(filePath, 22*i,20));
				  a[i] = s;  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void changearray(String a[],int score,int count) {
		Integer y = new Integer(score);
		String z=y.toString();
		if(z.length()==2) z=" "+z;
		if(z.length()==1) z="  "+z;
		Integer t = new Integer(count);
		String w=t.toString();
		if(z.length()==2) z=" "+z;
		for(int j=19;j>=0;j--) {
					a[j+1]=a[j];
					
				}
		a[0]= "score: "+z+",count="+ w;
				
		}
	

	private void writeData(String filePath, String data, int seek) throws IOException {
		RandomAccessFile file = new RandomAccessFile(filePath, "rw");
		file.seek(seek);
		file.write(data.getBytes());
		file.close();
	}

	private byte[] readCharsFromFile(String filePath, int seek, int chars) throws IOException {
		RandomAccessFile file = new RandomAccessFile(filePath, "r");
		file.seek(seek);
		byte[] bytes = new byte[chars];
		file.read(bytes);
		file.close();
		return bytes;
	}

}