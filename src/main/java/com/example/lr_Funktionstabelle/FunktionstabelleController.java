package com.example.lr_Funktionstabelle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class FunktionstabelleController {
    private static double round(double d) {
        return Double.parseDouble(String.format("%.2f", d).replace(',', '.'));
    }
    private static double func(double x) {
        //y=(x2+1)∙sin(3x)
        var y = (Math.pow(x, 2) + 1) * Math.sin(3 * x);
        return round(y);
    }

    @PostMapping
    public String calculate(@ModelAttribute("funktionstabelle") Funktionstabelle ftab, Model model) {
        System.out.println(ftab);
        try {
            if (ftab.getX0() > ftab.getXn() || ftab.gethX() <= 0) {
                throw new IllegalStateException("Входные данные заполнены неверно (x0 < xn and xh >= 0)");
            }

            List<Double> listValues = new ArrayList<>();
            var xList = Stream.iterate(ftab.getX0(), x -> x < ftab.getXn(), n -> round(n + ftab.gethX()))
                    .collect(Collectors.toList());

            xList.forEach(x -> listValues.add(func(x)));

            //log debug
            System.out.println("listValues");
            listValues.forEach(x -> System.out.print(x + ", "));
            System.out.println("\nxList");
            xList.forEach(x -> System.out.print(x + ", "));


            String result = "Результат " + ftab.getAction() + ": ";
            switch (ftab.getAction().toLowerCase(Locale.ROOT)) {
                case "min":
                    result += String.format("%.2f", Collections.min(listValues));
                    break;
                case "max":
                    result += String.format("%.2f", Collections.max(listValues));
                    break;
                case "avg":
                    result += String.format("%.2f", listValues.stream().mapToDouble(a -> a).average().getAsDouble());
                    break;
            }
            System.out.println(result);
            ftab.setResult(result);
            ftab.setValues(listValues);
            ftab.setxList(xList);
        } catch (Exception | Error e) {
            e.printStackTrace();
            model.addAttribute("errorMessage","Ошибка: " + e.getMessage());
        }
        return "index";
    }

    @GetMapping
    public String funktionstabelle(Model model) {
        model.addAttribute("funktionstabelle", new Funktionstabelle());
        return "index";
    }
}
