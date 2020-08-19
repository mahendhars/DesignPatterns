package com.factory.abstracts;

interface IHotDrink
{
  void consume();
}

class Tea implements IHotDrink
{
  @Override
  public void consume()
  {
    System.out.println("This tea is nice but I'd prefer it with milk.");
  }
}

class Coffee implements IHotDrink
{
  @Override
  public void consume()
  {
    System.out.println("This coffee is delicious");
  }
}

interface IHotDrinkFactory
{
  IHotDrink prepare(int amount);
}

class TeaFactory implements IHotDrinkFactory
{
  @Override
  public IHotDrink prepare(int amount)
  {
    System.out.println(
      "Put in tea bag, boil water, pour "
      + amount + "ml, add lemon, enjoy!"
    );
    return new Tea();
  }
}

class CoffeeFactory implements IHotDrinkFactory
{

  @Override
  public IHotDrink prepare(int amount)
  {
    System.out.println(
      "Grind some beans, boil water, pour "
      + amount + " ml, add cream and sugar, enjoy!"
    );
    return new Coffee();
  }
}

public class FactoryAbstractDemo {

	public static void main(String[] args) {
		IHotDrink tea = new TeaFactory().prepare(100);
		tea.consume();
		IHotDrink coffee = new CoffeeFactory().prepare(200);
		coffee.consume();
	}
}
