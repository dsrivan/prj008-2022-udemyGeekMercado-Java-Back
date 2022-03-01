package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import helper.Utils;
import model.Product;

public class Marketplace {

	private static Scanner keyboard = new Scanner(System.in);
	private static ArrayList<Product> products;
	private static Map<Product, Integer> cart;

	public static void main(String[] args) {

		products = new ArrayList<Product>();
		cart = new HashMap<Product, Integer>();

		Marketplace.menu();

	}

	private static void menu() {
		System.out.println("*******************************");
		System.out.println("\n---------- Bem-vindx ----------");
		System.out.println("------------- Shop ------------");

		System.out.println("\nSelecione uma opção");
		System.out.println("1 - Cadastrar produto");
		System.out.println("2 - Listar produtos");
		System.out.println("3 - Comprar produto");
		System.out.println("4 - Visualizar carrinho");
		System.out.println("5 - Sair do sistema");

		int option = 0;

		try {
			option = Integer.parseInt(Marketplace.keyboard.nextLine());
		} catch(InputMismatchException e) {
			Marketplace.menu();
		} catch(NumberFormatException f) {
			Marketplace.menu();
		}

		switch (option) {
		case 1:
			Marketplace.save();
			break;
		case 2:
			Marketplace.list();
			break;
		case 3:
			Marketplace.buy();
			break;
		case 4:
			Marketplace.cart();
			break;
		case 5:
			System.out.println("Obrigado.");
			Utils.pause(2);
			System.exit(0);
			break;
		default:
			System.out.println("Opção inválida.");
			Utils.pause(2);
			Marketplace.menu();
			break;
		}
	}

	private static void save() {
		System.out.println();
		System.out.println("*******************************");
		System.out.println("----- Cadastro de produto -----");
		System.out.println();

		System.out.println("Informe o nome do produto");
		String name = Marketplace.keyboard.nextLine();

		System.out.println("Informe o preço do produto");
		Double price = Marketplace.keyboard.nextDouble();

		Product product = new Product(name, price);
		Marketplace.products.add(product);

		System.out.println("O produto " + product.getName() + " foi cadastrado.");
		Utils.pause(2);
		Marketplace.menu();
	}

	private static void list() {
		System.out.println();
		System.out.println("*******************************");
		System.out.println("------ Lista de produtos ------");
		System.out.println();

		if (Marketplace.products.size() == 0) {
			System.out.println("Nenhum produto cadastrado");
			System.out.println();
		} else {
			for (Product p : Marketplace.products) {
				System.out.println(p);
				System.out.println();
			}
		}
		Utils.pause(2);
		Marketplace.menu();
	}

	private static void buy() {
		System.out.println();
		System.out.println("*******************************");
		System.out.println("------- Comprar produto -------");
		System.out.println();

		if (Marketplace.products.size() == 0) {
			System.out.println("Nenhum produto cadastrado");
			Utils.pause(2);
			Marketplace.menu();
		} else {
			System.out.println("----- Produtos disponívels-----");
			for (Product p : Marketplace.products) {
				System.out.println(p);
				System.out.println();
			}

			System.out.println();
			System.out.println("Informe o código do produto");
			int id = Integer.parseInt(Marketplace.keyboard.nextLine());
			boolean have = false;

			for (Product p : Marketplace.products) {
				if (p.getId() == id) {
					int quant = 0;

					try {
						// se já existir o produto no carrinho, atualiza a quantidade
						quant = Marketplace.cart.get(p);
						Marketplace.cart.put(p, quant + 1);
					} catch (NullPointerException e) {
						// não existe no carrinho, adiciona 1 quantidade
						Marketplace.cart.put(p, 1);
					}

					System.out.println();
					System.out.println("O produto " + p.getName() + " foi adicionado ao carrinho.");
					have = true;
				}
			}

			if (have) {
				System.out.println("Adicionar mais produtos ao carrinho?");
				System.out.println("1 - SIM, 0 - NAO");
				int option = Integer.parseInt(Marketplace.keyboard.nextLine());

				if (option == 1) {
					Marketplace.buy();
				} else {
					System.out.println();
					System.out.println("Fechando pedido.");
					Utils.pause(2);
					Marketplace.order();
				}
			} else {
				System.out.println();
				System.out.println("Produto não encontrado para o código " + id);
				Utils.pause(2);
				Marketplace.menu();
			}
		}
	}

	private static void cart() {
		System.out.println();
		System.out.println("*******************************");
		System.out.println("---------- Carrinho -----------");
		System.out.println();

		if (Marketplace.cart.size() == 0) {
			System.out.println("Nenhum produto no carrinho");
			System.out.println();
		} else {
			for (Product p : Marketplace.cart.keySet()) {
				System.out.println("Produto: " + p + 
						"\nQuantidade: " + Marketplace.cart.get(p));
				System.out.println();
			}
		}
		Utils.pause(2);
		Marketplace.menu();
	}

	private static void order() {
		Double total = 0.0;
		
		System.out.println("*******************************");
		System.out.println("---------- Carrinho -----------");
		System.out.println();
		
		for (Product p : Marketplace.cart.keySet()) {
			int quant = Marketplace.cart.get(p);
			total += p.getPrice() * quant;
			
			System.out.println(p);
			System.out.println("Quantidade: " + quant);
			System.out.println();
		}
		
		System.out.println("Total da compra: " + Utils.doubleToString(total));
		Marketplace.cart.clear();
		
		System.out.println("Obrigado");
		Utils.pause(5);
		Marketplace.menu();
		
	}
}
