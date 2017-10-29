package Haaga.SpringBoot.MealPlan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Haaga.SpringBoot.MealPlan.Domain.FoodList;
import Haaga.SpringBoot.MealPlan.Domain.FoodRepository;
import Haaga.SpringBoot.MealPlan.Domain.MarketList;
import Haaga.SpringBoot.MealPlan.Domain.MarketRepository;
import Haaga.SpringBoot.MealPlan.Domain.ProductType;
import Haaga.SpringBoot.MealPlan.Domain.ProductTypeRepository;






@SpringBootApplication
public class MealPlanApplication {

		public static void main(String[] args) {
			SpringApplication.run(MealPlanApplication.class, args);
		}
		
		@Bean
		public CommandLineRunner studentDemo(FoodRepository repository,MarketRepository mrepository,ProductTypeRepository trepository) {
			return (args) -> {
				
				mrepository.save(new MarketList("Smarket"));
				mrepository.save(new MarketList("Kmarket"));
				mrepository.save(new MarketList("Alepa"));
				
				trepository.save(new ProductType("Protein"));
				trepository.save(new ProductType("Veggie"));
				trepository.save(new ProductType("Dessert"));
				
				repository.save(new FoodList("Minced beef",3.5,"finnish beef low fat",2000,"Hakaniemi",mrepository.findBymarketName("Kmarket").get(0),trepository.findBytypeName("Protein").get(0)));
				repository.save(new FoodList("Salad",2.5,"organic with carrot and kale",600,"pasila",mrepository.findBymarketName("Smarket").get(0),trepository.findBytypeName("Veggie").get(0)));
				
				
				

			};
	}
}
