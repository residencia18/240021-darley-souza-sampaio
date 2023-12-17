package veiculo2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
    	
    	Garagem garagemComTomada = new Garagem(true);
    	Garagem garagemSemTomada = new Garagem(true);
    	
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Testar Veículo Genérico");
            System.out.println("2. Testar Carro");
            System.out.println("3. Testar Moto");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                escolha = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
                escolha = -1;
            }
            
            boolean tomada = false;

            switch (escolha) {
                case 1:
                    Veiculo veiculo = criarVeiculo(scanner);
                    System.out.print("A garagem que deseja colocar o veiculo possui tomada?(true ou false): ");
                    tomada = scanner.nextBoolean();
                    
                    testarVeiculo(veiculo);
                    if(tomada) 
                    	veiculo.estacionar(garagemComTomada);
                    else
                    	veiculo.estacionar(garagemSemTomada);
                    break;
                case 2:
                    Carro carro = criarCarro(scanner);
                    System.out.print("A garagem que deseja colocar o carro possui tomada?(true ou false): ");
                    tomada = scanner.nextBoolean();
                    
                    testarCarro(carro);
                    if(tomada) 
                    	carro.estacionar(garagemComTomada);
                    else
                    	carro.estacionar(garagemSemTomada);
                    break;
                case 3:
                    Moto moto = criarMoto(scanner);
                    System.out.print("A garagem que deseja colocar o moto possui tomada?(true ou false): ");
                    tomada = scanner.nextBoolean();
                    
                    testarMoto(moto);
                    if(tomada) 
                    	moto.estacionar(garagemComTomada);
                    else
                    	moto.estacionar(garagemSemTomada);
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (escolha != 0);

        scanner.close();
    }

    private static Veiculo criarVeiculo(Scanner scanner) {
        System.out.println("\nDigite os dados do Veículo Genérico:");
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("É elétrico? (true/false): ");
        boolean eletrico = scanner.nextBoolean();
        
        return new Veiculo(modelo, cor, ano, eletrico);
    }

    private static Carro criarCarro(Scanner scanner) {
        System.out.println("\nDigite os dados do Carro:");
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("É elétrico? (true/false): ");
        boolean eletrico = scanner.nextBoolean();
        
        return new Carro(modelo, cor, ano, eletrico);
    }

    private static Moto criarMoto(Scanner scanner) {
        System.out.println("\nDigite os dados da Moto:");
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("É elétrica? (true/false): ");
        boolean eletrico = scanner.nextBoolean();
        
        return new Moto(modelo, cor, ano, eletrico);
    }

    private static void testarVeiculo(Veiculo veiculo) {
        System.out.println("\nVeiculo: " + veiculo.getModelo() + " Cor: " + veiculo.getCor() + " Ano: " + veiculo.getAno());
        veiculo.ligar();
        veiculo.acelerar();
        veiculo.parar();
    }

    private static void testarCarro(Carro carro) {
        System.out.println("\nCarro: " + carro.getModelo() + " Cor: " + carro.getCor() + " Ano: " + carro.getAno());
        carro.ligar();
        carro.acelerar();
        carro.parar();
        carro.abrirPorta();
    }

    private static void testarMoto(Moto moto) {
        System.out.println("\nMoto: " + moto.getModelo() + " Cor: " + moto.getCor() + " Ano: " + moto.getAno());
        moto.ligar();
        moto.acelerar();
        moto.parar();
        moto.empinar();
    }
}
