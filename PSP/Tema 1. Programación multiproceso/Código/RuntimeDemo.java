package psp.ud1;

import java.io.IOException;
class RuntimeDemo {
	public static void main(String args[]) {
		Runtime rt = Runtime.getRuntime();
		Process proc;
		String cmd = "notepad";
		try {
			proc = rt.exec(cmd);
			proc.waitFor(); //probar ejecutar sin esta línea
		} catch (IOException e) {
			System.out.println(cmd + " es un comando desconocido");	
		} catch (InterruptedException e) {
			System.out.println("Ups");
		}
	}
}


