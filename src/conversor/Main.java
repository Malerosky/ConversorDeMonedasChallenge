package conversor;

import conversor.service.CurrencyService;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyService currencyService = new CurrencyService();

        try {
            JsonObject rates = currencyService.getRates(); // Obtener tasas desde la API
            boolean continuar = true;

            while (continuar) {
                System.out.println("\n=== CONVERSOR DE MONEDAS ===");
                System.out.println("1. CLP a USD");
                System.out.println("2. ARS a USD");
                System.out.println("3. BRL a USD");
                System.out.println("4. CLP a ARS");
                System.out.println("5. ARS a BRL");
                System.out.println("6. BRL a CLP");
                System.out.println("7. Salir");
                System.out.print("Selecciona una opción: ");

                int opcion = scanner.nextInt();

                if (opcion == 7) {
                    continuar = false;
                    System.out.println("Saliendo del programa. ¡Adiós!");
                    break;
                }

                System.out.print("Ingresa la cantidad a convertir: ");
                double cantidad = scanner.nextDouble();

                double resultado = 0.0;

                switch (opcion) {
                    case 1 -> resultado = currencyService.convert("CLP", "USD", cantidad, rates);
                    case 2 -> resultado = currencyService.convert("ARS", "USD", cantidad, rates);
                    case 3 -> resultado = currencyService.convert("BRL", "USD", cantidad, rates);
                    case 4 -> resultado = currencyService.convert("CLP", "ARS", cantidad, rates);
                    case 5 -> resultado = currencyService.convert("ARS", "BRL", cantidad, rates);
                    case 6 -> resultado = currencyService.convert("BRL", "CLP", cantidad, rates);
                    default -> {
                        System.out.println("Opción inválida.");
                        continue;
                    }
                }

                System.out.printf("Resultado: %.2f%n", resultado);
            }

        } catch (Exception e) {
            System.out.println("Error al obtener datos de la API: " + e.getMessage());
        }
    }
}
