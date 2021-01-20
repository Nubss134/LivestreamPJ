package ndn.livestream.apis;

import ndn.livestream.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SearchAPI {
    @Autowired
    private SearchService searchService;

    @GetMapping("/guest/search/{keyword}")
    public Object findByKeyWord(@PathVariable("keyword") String keyword) {
        return searchService.findByKeyWord(keyword);
    }

}
