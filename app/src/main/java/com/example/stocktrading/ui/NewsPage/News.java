package com.example.stocktrading.ui.NewsPage;

import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class News {

    private String title;
    private String auther;
    private String summary;
    private String image;
    private String url;

    @SerializedName("items")
    @Expose
    private Items items;

    public News(String title, String auther, String summary, String image, String url) {
        this.title = title;
        this.auther = auther;
        this.summary = summary;
        this.image = image;
        this.url = url;

    }

    public String getUrl() {
        return url;
    }

    public Items getItems() {
        return items;
    }

    public String getTitle() {
        return title;
    }

    public String getAuther() {
        return auther;
    }

    public String getSummary() {
        return summary;
    }

    public String getImage() {
        return image;
    }

    public class Items {

        @SerializedName("result")
        @Expose
        private List<Result> result = null;

        public List<Result> getResult() {
            return result;
        }


    }

    public class MainImage {

        @SerializedName("resolutions")
        @Expose
        private List<Resolution> resolutions = null;

        public List<Resolution> getResolutions() {
            return resolutions;
        }


    }

    public class Resolution {

        @SerializedName("tag")
        @Expose
        private String tag;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("url")
        @Expose
        private String url;

        public String getTag() {
            return tag;
        }


        public Integer getWidth() {
            return width;
        }


        public Integer getHeight() {
            return height;
        }


        public String getUrl() {
            return url;
        }


    }


    public class Result {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("summary")
        @Expose
        private String summary;
        @SerializedName("publisher")
        @Expose
        private String publisher;
        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("main_image")
        @Expose
        private MainImage mainImage;

        public String getTitle() {
            return title;
        }


        public String getLink() {
            return link;
        }


        public String getSummary() {
            return summary;
        }


        public String getPublisher() {
            return publisher;
        }


        public String getAuthor() {
            return author;
        }


        public String getContent() {
            return content;
        }


        public MainImage getMainImage() {
            return mainImage;
        }


    }
}
