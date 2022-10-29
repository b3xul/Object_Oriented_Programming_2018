package it.polito.po.test;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import diet.*;

public class TestR4_Menu {


  private Food food;
  private Recipe r;

  @Before
  public void setUp() throws Exception {
    food = new Food();
    food.defineRawMaterial("Zucchero", 400, 0, 100, 0);
    food.defineRawMaterial("Mais", 70, 2.7, 12, 1.3);
    food.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
    food.defineRawMaterial("Olio", 900, 0, 0, 100);
    food.defineRawMaterial("Nutella", 530, 6.8, 56, 31);
	food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);
	food.defineProduct("Cornetto Cioccolato", 230, 3, 27, 11);
	food.defineProduct("Barretta Bueno", 122, 2, 10.6, 8);
	food.defineProduct("Mozzarella Light", 206, 25, 2, 11.25);
    r = new Recipe("Pasta e Nutella", food);
    r.addIngredient("Pasta", 70);
    r.addIngredient("Nutella", 30);
  }

  @Test
  public void testMenuWithRecipe() {
    Menu menu = new Menu("M1", food);

    menu.addRecipe("Pasta e Nutella", 100);

    assertEquals("M1",menu.getName());
    assertEquals(350 * 0.7 + 530 * 0.3, menu.getCalories(), 0.001);
    assertEquals(12 * 0.7 + 6.8 * 0.3, menu.getProteins(), 0.001);
    assertEquals(72.2 * 0.7 + 56 * 0.3, menu.getCarbs(), 0.001);
    assertEquals(1.5 * 0.7 + 31 * 0.3, menu.getFat(), 0.001);
  }

  @Test
  public void testMenu() {
    Menu menu = new Menu("M1", food);

    menu.addRecipe("Pasta e Nutella", 50);
    menu.addProduct("Cracker");

    assertEquals(350 * 0.35 + 530 * 0.15 + 111, menu.getCalories(), 0.001);
    assertEquals(12 * 0.35 + 6.8 * 0.15 + 2.6, menu.getProteins(), 0.001);
    assertEquals(72.2 * 0.35 + 56 * 0.15 + 17.2, menu.getCarbs(), 0.001);
    assertEquals(1.5 * 0.35 + 31 * 0.15 + 3.5, menu.getFat(), 0.001);
  }

}
