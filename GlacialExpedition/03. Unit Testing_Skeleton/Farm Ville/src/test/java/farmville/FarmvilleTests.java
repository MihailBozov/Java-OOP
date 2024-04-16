package farmville;

import org.junit.Assert;
import org.junit.Test;

public class FarmvilleTests {
    
    @Test
    public void createFarmTest() {
        Farm farm = new Farm("Sofi", 100);
        Assert.assertEquals(100, farm.getCapacity());
        Assert.assertEquals("Sofi", farm.getName());
    }
    
    @Test
    public void getCountTest() {
        Farm farm = new Farm("Sofi", 100);
        Assert.assertEquals(0, farm.getCount());
        
        Animal animal = new Animal("duck", 10);
        farm.add(animal);
        Assert.assertEquals(1, farm.getCount());
        
        Animal animal2 = new Animal("hen", 10);
        farm.add(animal2);
        Assert.assertEquals(2, farm.getCount());
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void addOverCapacity() {
        Farm farm = new Farm("Sofi", 0);
        Animal animal = new Animal("duck", 10);
        farm.add(animal);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addExistingAnimal() {
        Farm farm = new Farm("Sofi", 10);
        
        Animal animal = new Animal("duck", 10);
        farm.add(animal);
        
        Animal animal1 = new Animal("duck", 5);
        farm.add(animal1);
    }
    
    @Test
    public void removeAnimalTest() {
        Farm farm = new Farm("Sofi", 10);
        Animal animal = new Animal("duck", 10);
        farm.add(animal);
        
        Assert.assertEquals(1, farm.getCount());
        farm.remove("duck");
        
        Assert.assertEquals(0, farm.getCount());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void setCapacityNegativeNumber() {
        Farm farm = new Farm("Sofi", -1);
    }
    
    @Test(expected = NullPointerException.class)
    public void setEmptyName() {
        Farm farm = new Farm("", 10);
    }
}
