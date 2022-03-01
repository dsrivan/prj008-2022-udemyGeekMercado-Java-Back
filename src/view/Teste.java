package view;

import model.Product;

public class Teste {

	public static void main(String[] args) {

		Product notebook = new Product("Notebook Dell", 6000.00);
		Product monitor = new Product("Monitor LG", 1200.00);
		
		System.out.println(notebook);
		System.out.println();
		System.out.println(monitor);

	}

}
