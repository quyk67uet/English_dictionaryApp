package org.features.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class TranslateAPI {
    private static URL getURL(String langFrom, String langTo, String text) {
        URL url;
        try {
            String urlStr = "https://script.google.com/macros/s/AKfycbx-5kYLpbGX9o5cOtWyiJshWAZdy8zLVr7raw4UPCkr4t_1U9b4jiaO8ys_NuQbv2uz/exec" +
            "?q=" + URLEncoder.encode(text, "UTF-8") +
            "&target=" + langTo +
            "&source=" + langFrom;
            url = new URI(urlStr).toURL();
        } catch (Exception e) {
            System.out.println("URL initialization error.");
            e.printStackTrace();
            return null;
        }
        return url;
    }
    public static String translate(String langFrom, String langTo, String text) throws IOException {
        URL url = getURL(langFrom, langTo, text);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static void main(String[] args) {
        
        String text = "The 2022 PCI (provincial competitiveness index) report released by the Vietnam Confederation of Commerce and Industry (VCCI) showed that 35 percent of businesses said they would scale up their business in 2023-2024, just one percent higher than 2021.\r\n" + //
                "\r\n" + //
                "Meanwhile, the number of enterprises planning to scale down their business or shut down was 10.7 percent, a relatively high level since 2019. The growth rate and business efficiency of private enterprises are declining and lower than that in 2016-2020.\r\n" + //
                "\r\n" + //
                "Analysts pointed out that the worse business environment is a reason has affected private enterprises.  Businesses complain that they have to bear pressure because of inconsistent and unreasonable policies. Regulations change so regularly, while legal documents are controversial and overlapping, which makes it difficult for enterprises to plan their business.\r\n" + //
                "\r\n" + //
                "A regulation on controlling fire prevention and fighting alone, for example, has caused many enterprises to stop operation.\r\n" + //
                "\r\n" + //
                "The government in recent years has made great efforts to reform the business environment, remove required unnecessary business conditions and cut the number of conditional business fields. However, after the old business conditions were cut, new requirements on administrative procedures have appeared.\r\n" + //
                "\r\n" + //
                "Complicated administrative procedures have always been a burning issue which hinders activities in the national economy.\r\n" + //
                "\r\n" + //
                "A VCCI survey found that enterprises’ capability of predicting the changes of laws has been declining. The number of enterprises which can foresee changes in regulations has dropped from 14.29 percent in 2013 to below 5 percent now. The decline in policy predictability has been a consistent trend for nearly 10 years.\r\n" + //
                "\r\n" + //
                "This is a worrying problem for Vietnam as its businesses cannot predict business prospects, because policies always change suddenly. \r\n" + //
                "\r\n" + //
                "At some meetings between businesses and state management agencies, businesspeople say that what they need now is not financial support, but predictable, transparent, stable and consistent policies.\r\n" + //
                "\r\n" + //
                "The other problem that bars enterprises’ way is state officers’ attitudes. They avoid handling issues and pass the buck for fear that they may make mistakes which will end up with imprisonment.\r\n" + //
                "\r\n" + //
                "As a result, it takes a long time to solve a question. Businesspeople estimate that it takes two years to fulfill business procedures. The business environment is getting worse, while the spirit of officials at all levels to solve problems is deteriorating.\r\n" + //
                "\r\n" + //
                "The only way to settle the problems is institutional reform. The government needs to warn ministries against setting up more barriers for enterprises when releasing new legal documents.";
        try {
            System.out.println(translate("en", "vi", text));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
