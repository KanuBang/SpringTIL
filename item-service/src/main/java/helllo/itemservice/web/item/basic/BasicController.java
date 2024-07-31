package helllo.itemservice.web.item.basic;


import helllo.itemservice.domain.item.Item;
import helllo.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> all = itemRepository.findAll();
        model.addAttribute("items", all);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /*
    @PostMapping("/add")
    public String addItemV1(@RequestParam("itemName") String itemName, @RequestParam("price") int price,
                            @RequestParam("quantity") Integer quantity, Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }
     */

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("testA",10000,10));
        itemRepository.save(new Item("testB",20000,20));
    }
}
