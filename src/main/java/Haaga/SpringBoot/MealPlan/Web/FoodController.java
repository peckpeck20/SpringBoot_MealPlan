package Haaga.SpringBoot.MealPlan.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Haaga.SpringBoot.MealPlan.Domain.FoodList;
import Haaga.SpringBoot.MealPlan.Domain.FoodRepository;
import Haaga.SpringBoot.MealPlan.Domain.MarketRepository;
import Haaga.SpringBoot.MealPlan.Domain.ProductTypeRepository;


@Controller
public class FoodController {
	@Autowired
	private FoodRepository repository; 
	@Autowired
	private MarketRepository mrepository;
	@Autowired
	private ProductTypeRepository trepository;
	
	
    @RequestMapping(value="/productlist")
    public String foodList(Model model) {	
        model.addAttribute("food", repository.findAll());
        return "productlist";
    }
  
    @RequestMapping(value="/Add")
    public String add(Model model){
    	model.addAttribute("food",new FoodList());
    	model.addAttribute("markets", mrepository.findAll());
    	model.addAttribute("types",trepository.findAll());
    	return "add";
    }
    
   @RequestMapping(value="/save",method=RequestMethod.POST)
   public String Save(FoodList food){
	   repository.save(food);
	   return "redirect:productlist";
   }
   @RequestMapping(value="/edit/{id}")
   public String editProduct(@PathVariable("id") Long foodId,Model model){
	   model.addAttribute("food", repository.findOne(foodId));
	   model.addAttribute("markets",mrepository.findAll());
	   model.addAttribute("types",trepository.findAll());
	   return "Edit";
   }
   @RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
   public String deleteProduct(@PathVariable("id") Long foodId,Model model){
	   repository.delete(foodId);
	   return "redirect:/productlist";
   }
}