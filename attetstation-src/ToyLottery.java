import java.util.ArrayList;
import java.util.List;
import java.util.Random;
class Toy {
    private int toyId;
    private String name;
    private int quantity;
    private double weight;

    public Toy(int toyId, String name, int quantity, double weight) {
        this.toyId = toyId;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    public int getToyId() {
        return toyId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

public class ToyLottery {
    private List<Toy> toys;

    public ToyLottery() {
        toys = new ArrayList<>();
    }

    public void addToy(int toyId, String name, int quantity, double weight) {
        Toy toy = new Toy(toyId, name, quantity, weight);
        toys.add(toy);
    }

    public void updateWeight(int toyId, double newWeight) {
        for (Toy toy : toys) {
            if (toy.getToyId() == toyId) {
                toy.setWeight(newWeight);
                break;
            }
        }
    }

    public Toy playLottery() {
        double totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }

        Random random = new Random();
        double randomWeight = random.nextDouble() * totalWeight;

        double cumulativeWeight = 0;
        for (Toy toy : toys) {
            cumulativeWeight += toy.getWeight();
            if (randomWeight <= cumulativeWeight) {
                if (toy.getQuantity() > 0) {
                    toy.setQuantity(toy.getQuantity() - 1);
                    return toy;
                } else {
                    return null;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        ToyLottery toyLottery = new ToyLottery();

        // Добавляем игрушки
        toyLottery.addToy(1, "Кукла", 5, 30);
        toyLottery.addToy(2, "Мяч", 10, 20);
        toyLottery.addToy(3, "Конструктор", 8, 50);

        // Изменяем вес игрушки
        toyLottery.updateWeight(2, 10);

        // Розыгрыш игрушек
        Toy winner = toyLottery.playLottery();
        if (winner != null) {
            System.out.println("Поздравляем! Вы выиграли " + winner.getName() + "!");
        } else {
            System.out.println("К сожалению, призов нет.");
        }
    }
}