package org.aut.e_gov.models;

public class News {
    private int newsId;
    private String title;
    private String shortDesc;
    private String content;
    private double rating;
    private int rates;
    private int image;

    public News(int newsId, String title, String shortDesc, String content, double rating, int rates, int image) {
        this.newsId = newsId;
        this.title = title;
        this.shortDesc = shortDesc;
        this.content = content;
        this.rating = rating;
        this.rates = rates;
        this.image = image;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRates() {
        return rates;
    }

    public void setRates(int rates) {
        this.rates = rates;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
