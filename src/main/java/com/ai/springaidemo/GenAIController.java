package com.ai.springaidemo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ai")
public class GenAIController {

    private final OllamaChatModel client;
    private final ChartService chartService;
/*    private final ImageService imageService;*/
    private final RecipeService recipeService;


/*    public GenAIController(OllamaChatModel client,ChartService chartService,
                           ImageService imageService,RecipeService recipeService) {
        this.client = client;
        this.chartService = chartService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }*/

    public GenAIController(OllamaChatModel client,ChartService chartService,
                           RecipeService recipeService) {
        this.client = client;
        this.chartService = chartService;

        this.recipeService = recipeService;
    }


    @GetMapping("/ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chartService.getResponse(prompt);
    }

    @GetMapping("/ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chartService.getResponse(prompt);
    }
 /*   @GetMapping("/generate-image")
    public void generateImages(HttpServletResponse  httpServletResponse, @RequestParam String prompt) throws IOException {
        String url= imageService.generateImage(prompt).getResult().getOutput().getUrl();
        httpServletResponse.sendRedirect(url);
    }*/

 /*   @GetMapping("/generate-image-option")
    public List<String> generateImagesOption( @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse= imageService.generateImage(prompt);
        List<String> imageUrls= imageResponse.getResults().stream().map((image)->
            image.getOutput().getUrl())
        .toList();
        return imageUrls;
    }*/
/*
    @GetMapping("/generate-image-option-default")
    public List<String> generateImagesOptionDefault(
            HttpServletResponse httpServletResponse,
            @RequestParam String prompt,
            @RequestParam(defaultValue = "hd") String quality,
            @RequestParam(defaultValue = "1") Integer n,
            @RequestParam(defaultValue = "1024") Integer width,
            @RequestParam(defaultValue = "1024")Integer height
                                              ) throws IOException {
        ImageResponse imageResponse= imageService.generateImageOptionDefault(prompt,quality,n,height,width);
        List<String> imageUrls= imageResponse.getResults().stream().map((image)->
                        image.getOutput().getUrl())
                .toList();
        return imageUrls;
    }*/

    @GetMapping("/generate-recipe")
    public String recipeCreator( @RequestParam String ingredients,
                                       @RequestParam(defaultValue = "any") String cuisine,
                                       @RequestParam(defaultValue = "") String dietaryRestrictions
                                       ) throws IOException {

        return recipeService.createRecipe(ingredients,cuisine,dietaryRestrictions);
    }
}
