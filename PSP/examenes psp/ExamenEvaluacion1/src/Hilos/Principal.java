package Hilos;

public class Principal {

	public static void main(String[] args) {

		final int MAX_LATAS_CLIENTE = 10;
		final int CLIENTES = 10;

		Supermercado s = new Supermercado(MAX_LATAS_CLIENTE);
		
		Cajero cajero = new Cajero(s);
		Reponedor reponedor = new Reponedor(s);
		Cliente[] cliente = new Cliente[CLIENTES];

		int id  = 1;
		for (int i=0; i<cliente.length;i++) 
			cliente[i] = new Cliente(s,id++);

		cajero.start();
		reponedor.start();

		for (int i=0; i<cliente.length;i++) 
			cliente[i].start();
		
		//Aquí debes implementar la condición de sincronización CS Principal

		for (Cliente c : cliente) {  //recorro el array de clientes
			try {
				c.join(); // espero a que cada cliente termine sus compras
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		cajero.interrupt(); //interrumpo el cajero
		reponedor.interrupt(); // interrumpo el reponedor

	}

}
