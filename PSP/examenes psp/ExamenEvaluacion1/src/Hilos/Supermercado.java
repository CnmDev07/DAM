package Hilos;

public class Supermercado {

	private int maximo;
	private int nLatas;
	private Cliente cliente;
	private boolean peticionHecha = false;
	private boolean clientePagando = false;
	private boolean terminaDePagar = false;
	private boolean stockRepuesto = true;

	public Supermercado(int total) {
		this.maximo = total; // número máximo de latas
		this.nLatas = total; // número de latas disponibles
		cliente = null; // cliente atendido por el cajero
	}

	public int getMaximo() {
		return maximo;
	}

	/**
	 * Este método lo utiliza un Cliente para coger num latas del lineal
	 * (estantería) num >=1 && num <= maximo.
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void comprarLatas(Cliente c, int num) throws InterruptedException {

		while (cliente != null) { //si algun cliente esta cogiendo esta siendo atendido...
			wait(); //cliente espera
		}

		cliente = c; //cliente empieza a ser atendido

		System.out.println("Cliente " + c + " quiere " + num + " latas. Hay " + nLatas);
		if (num > nLatas) { //si el qliente quiere mas latas de las disponibles
			System.out.println("Faltan latas");
			stockRepuesto = false; //hace falta stock
			System.out.println("Cliente " + c + " espera");
			notifyAll(); //despierta el resto de hilos
			while (!stockRepuesto) { //hasta que el stock no se reponga se duerme el cliente
				wait();
			}
		}
		nLatas-=num; //restamos el numero de latas que el cliente ha cogido al stock
		System.out.println("Cliente " + c + " coge " + num + " latas de la estantería. Quedan: " + nLatas + " latas");
		if (nLatas == 0) { //si el stock acaba vacio se piden mas al reponedor
			stockRepuesto = false;
		}
		cliente = null; // el cliente deja de ser atendido de momento
		notifyAll(); //despertamos el resto de hilos

	}

	/**
	 * Este método lo utiliza el Reponedor para esperar el aviso de un Cliente por
	 * falta de latas.
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void esperarPeticion() throws InterruptedException {
		while (stockRepuesto) { //mientras haya stock suficiente espera
			wait();
		}

		System.out.println("El reponedor ha sido despertado");
		peticionHecha = true; //la peticion de stock esta hecha
		notifyAll(); //despertamos el resto de hilos
	}

	/**
	 * Este método lo utiliza el Reponedor para reponer las latas en el lineal
	 * (estantería), asegurándose que haya el maximo.
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void nuevoSuministro() throws InterruptedException {
		while (!peticionHecha) { //si no hay una peticion de stock espera
			wait();
		}

		System.out.println("El reponedor suministra nuevas latas");
		nLatas = maximo; //reponemos stock
		stockRepuesto = true; 
		peticionHecha = false; //la peticion se completa
		notifyAll(); //despertamos el resto de hilos
	}

	/**
	 * Este método lo utiliza un Cliente para pagar en la caja.
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void pagar(Cliente c) throws InterruptedException {
		while (cliente != null) { //mientras algun cliente sea atendido espera
			wait();
		}

		cliente = c; //atendemos a un cliente

		System.out.println("Cliente " + c + " empieza a pagar");
		clientePagando = true; //el cliente comienza a pagar
		notifyAll(); //despertamos a los hilos

		while (!terminaDePagar) { //si no ha sido cobrado el cliente espera
			wait();
		}

		System.out.println("Cliente " + c + " ha terminado de pagar y se va");
		clientePagando = false; //el cliente ya ha pagado
		terminaDePagar = false; //termina la accion de pago
		notifyAll(); //despertamos al resto de hilos

	}

	/**
	 * Este método lo utiliza el Cajero para cobrar a un Cliente.
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void cobrar() throws InterruptedException {
		while (!clientePagando) { //mientras no haya un cliente pagando despertamos al resto de hilos y espera
			System.out.println("El cajero está esperando a que haya productos en la linea de caja");
			notifyAll();
			wait();
		}

		System.out.println("Cajero cobra al cliente " + cliente);
		terminaDePagar = true; //el cliente ya ha sido cobrado
		notifyAll(); //despertamos al resto de hilos

		while (clientePagando) { //mientras el cliente no cambie su estado a terminar su pago espera
			wait();
		}

		cliente = null; //el cliente deja de ser atendido

		System.out.println("Cajero está disponible para otro cliente");
		notifyAll(); //despertamos el resto de hilos

	}
}