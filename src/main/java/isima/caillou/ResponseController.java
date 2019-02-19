package isima.caillou;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {
    private static final String template = "Votre produit est %s!";

    @RequestMapping("/response")
    public Response greeting(@RequestParam(value="code", defaultValue="3029330003533") String name) {
        return new Response(String.format(template, name));
    }
}
