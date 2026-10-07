import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

void main() {
    final int LIMITE_CACHE = 10;

    List<Pessoa> banco = new ArrayList<>();
    List<Pessoa> cache = new ArrayList<>();

    // Dados mockados (12 pessoas, para dar para testar o limite do cache)
    banco.add(new Pessoa(1, "Gabriel", 16));
    banco.add(new Pessoa(2, "Leticia", 17));
    banco.add(new Pessoa(3, "Damazio", 18));
    banco.add(new Pessoa(4, "João", 19));
    banco.add(new Pessoa(5, "Sarah", 20));
    banco.add(new Pessoa(6, "Evellyn", 28));
    banco.add(new Pessoa(7, "Isis", 21));
    banco.add(new Pessoa(8, "Natielie", 22));
    banco.add(new Pessoa(9, "Maria Vitória", 31));
    banco.add(new Pessoa(10, "Maria Eduarda", 27));

    Scanner scanner = new Scanner(System.in);

    while (true) {
        IO.println("Digite o ID da pessoa (0 para sair): ");
        int id = scanner.nextInt();

        if (id == 0) {
            break;
        }

        // procura o primeiro do cache
        Pessoa encontrada = null;
        for (Pessoa p : cache) {
            if (p.getId() == id) {
                encontrada = p;
                break;
            }
        }

        if (encontrada != null) {
            IO.println("Pessoa encontrada no cache: " + encontrada);
            continue; // volta para pedir outro ID sem ir até o banco
        }

        // Não tava no cache e procura no banco
        for (Pessoa p : banco) {
            if (p.getId() == id) {
                encontrada = p;
                break;
            }
        }

        if (encontrada == null) {
            IO.println("Pessoa não encontrada.");
            continue;
        }

        //Remove a pessoa mais antiga após o limite da lista ser alcançado
        if (cache.size() >= LIMITE_CACHE) {
            Pessoa removida = cache.remove(0);
            IO.println("Cache cheio. Removida a mais antiga: " + removida);
        }

        cache.add(encontrada);
        IO.println("Pessoa buscada no banco e adicionada ao cache: " + encontrada);
    }

    scanner.close();
    IO.println("Programa encerrado.");
}
